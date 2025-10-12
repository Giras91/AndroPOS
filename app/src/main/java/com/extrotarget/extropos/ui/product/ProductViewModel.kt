package com.extrotarget.extropos.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Product
import com.extrotarget.extropos.domain.model.Category
import com.extrotarget.extropos.domain.repository.IProductRepository
import com.extrotarget.extropos.domain.usecase.AddCategoryUseCase
import com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: IProductRepository,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
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
        
        // Load products from database on initialization
        loadProductsFromDatabase()
    }

    fun loadProducts() {
        // Keep compatibility: publish current in-memory list
        _products.value = allProducts.toList()
    }

    private fun loadProductsFromDatabase() {
        viewModelScope.launch {
            try {
                // Load products from database
                val productsFromDb = productRepository.getAllProducts()
                allProducts.clear()
                allProducts.addAll(productsFromDb)
                _products.value = allProducts.toList()
                
                // Load categories from database (proper way)
                val categoriesFromDb = getCategoriesUseCase()
                val categoryPairs = categoriesFromDb.map { Pair(it.id, it.name) }.toMutableList()
                
                // Ensure there's always an "Uncategorized" option
                if (categoryPairs.none { it.first == "0" }) {
                    categoryPairs.add(0, Pair("0", "Uncategorized"))
                }
                
                _categories.value = categoryPairs
                
                android.util.Log.d("ProductViewModel", "Loaded ${productsFromDb.size} products and ${categoriesFromDb.size} categories from database")
            } catch (e: Exception) {
                android.util.Log.e("ProductViewModel", "Failed to load data from database", e)
                _error.value = e.message
            }
        }
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
        android.util.Log.d("ProductViewModel", "addCategory called: id=$id, name=$name")
        // delegate to repository via menu repository? product repo doesn't manage categories
        val category = Category(id = id, name = name)
        // We still need to call menu repository for categories — fallback to no-op if not available
        viewModelScope.launch {
            // reflect immediately in UI
            val current = _categories.value.toMutableList()
            current.removeAll { it.first == id }
            current.add(Pair(id, name))
            _categories.value = current
            android.util.Log.d("ProductViewModel", "Updated ProductViewModel categories: ${_categories.value}")

            // persist the category via use-case
            try {
                android.util.Log.d("ProductViewModel", "Calling addCategoryUseCase")
                addCategoryUseCase(category)
                android.util.Log.d("ProductViewModel", "addCategoryUseCase completed successfully")
            } catch (e: Exception) {
                android.util.Log.e("ProductViewModel", "addCategoryUseCase failed", e)
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
    
    fun updateProduct(product: Product) {
        viewModelScope.launch {
            try {
                productRepository.upsertProduct(product)
                
                // Update in-memory list
                val index = allProducts.indexOfFirst { it.id == product.id }
                if (index >= 0) {
                    allProducts[index] = product
                    _products.value = allProducts.toList()
                }
                
                android.util.Log.d("ProductViewModel", "Product updated: ${product.name}")
            } catch (e: Exception) {
                android.util.Log.e("ProductViewModel", "Failed to update product", e)
                _error.value = e.message
            }
        }
    }
    
    fun deleteProduct(productId: String) {
        viewModelScope.launch {
            try {
                // Remove from in-memory list first
                val removedProduct = allProducts.find { it.id == productId }
                allProducts.removeAll { it.id == productId }
                _products.value = allProducts.toList()
                
                // TODO: Add deleteProduct to repository interface when available
                // For now, we'll just remove from memory
                
                android.util.Log.d("ProductViewModel", "Product deleted: $productId")
            } catch (e: Exception) {
                android.util.Log.e("ProductViewModel", "Failed to delete product", e)
                _error.value = e.message
            }
        }
    }

    fun getCategories(): List<Pair<String, String>> = _categories.value
}