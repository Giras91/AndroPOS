package com.extrotarget.extropos.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Product
import com.extrotarget.extropos.domain.model.Category
import com.extrotarget.extropos.domain.repository.IProductRepository
import com.extrotarget.extropos.domain.usecase.AddCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    private val addCategoryUseCase: AddCategoryUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    // Keep a master list so searches/filters can be applied from original dataset
    private var allProducts: MutableList<Product> = mutableListOf()

    // Categories as mutable state so UI can observe additions
    private val _categories = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val categories: StateFlow<List<Pair<String, String>>> = _categories.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        // Start empty; we'll populate from repository later when available
        _products.value = emptyList()
        _categories.value = emptyList()
    }

    fun loadProducts() {
        // Keep compatibility: publish current in-memory list
        _products.value = allProducts.toList()
    }

    fun searchProducts(query: String) {
        if (query.isBlank()) {
            _products.value = allProducts.toList()
            return
        }

        val filteredProducts = allProducts.filter {
            it.name.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
        }
        _products.value = filteredProducts
    }

    fun filterByCategory(categoryId: String?) {
        if (categoryId.isNullOrBlank()) {
            _products.value = allProducts.toList()
            return
        }
        _products.value = allProducts.filter { it.categoryId == categoryId }
    }

    // Return a product by id from the loaded dataset (or null if missing)
    fun getProductById(id: String): Product? {
        return allProducts.find { it.id == id }
    }

    // Management API for runtime adding categories and products (in-memory)
    fun addCategory(id: String, name: String) {
        // delegate to repository via menu repository? product repo doesn't manage categories
        val category = Category(id = id, name = name)
        // We still need to call menu repository for categories — fallback to no-op if not available
        viewModelScope.launch {
            // reflect immediately in UI
            val current = _categories.value.toMutableList()
            current.removeAll { it.first == id }
            current.add(Pair(id, name))
            _categories.value = current

            // persist the category via use-case
            try {
                addCategoryUseCase(category)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun addProduct(product: Product) {
        // delegate persistence to product repository
        viewModelScope.launch {
            if (allProducts.any { it.id == product.id }) return@launch
            productRepository.upsertProduct(product)
            allProducts.add(product)
            _products.value = allProducts.toList()

            // ensure category exists in UI list
            val catId = product.categoryId
            if (_categories.value.none { it.first == catId }) {
                val catList = _categories.value.toMutableList()
                catList.add(Pair(catId, "Category $catId"))
                _categories.value = catList
            }
        }
    }

    fun getCategories(): List<Pair<String, String>> = _categories.value
}