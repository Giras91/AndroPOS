package com.extrotarget.extropos.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    // Add repository when available
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    // Keep a master list so searches/filters can be applied from original dataset
    private var allProducts: List<Product> = emptyList()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadProducts()
    }

    // Mock categories store (id -> name). In a real app this comes from repository.
    private val categoriesMap: Map<String, String> = mapOf(
        "1" to "Mains",
        "2" to "Beverages"
    )

    fun getCategories(): List<Pair<String, String>> {
        // Return categories that are present in the dataset in stable order
        val ids = allProducts.map { it.categoryId }.distinct()
        return ids.map { Pair(it, categoriesMap[it] ?: "Category ${it}") }
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                // TODO: Load from repository
                // For now, create mock data
                allProducts = createMockProducts()
                _products.value = allProducts
                _isLoading.value = false
            } catch (e: Exception) {
                _error.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                _products.value = allProducts
                return@launch
            }
            
            val filteredProducts = allProducts.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
            }
            _products.value = filteredProducts
        }
    }

    fun filterByCategory(categoryId: String?) {
        viewModelScope.launch {
            if (categoryId.isNullOrBlank()) {
                _products.value = allProducts
                return@launch
            }
            _products.value = allProducts.filter { it.categoryId == categoryId }
        }
    }

    // Return a product by id from the loaded dataset (or null if missing)
    fun getProductById(id: String): Product? {
        return allProducts.find { it.id == id }
    }

    private fun createMockProducts(): List<Product> {
        return listOf(
            Product(
                id = "1",
                name = "Nasi Lemak",
                description = "Traditional Malaysian rice dish with coconut rice",
                priceCents = 1200, // RM 12.00
                categoryId = "1",
                isAvailable = true
            ),
            Product(
                id = "2", 
                name = "Teh Tarik",
                description = "Malaysian pulled tea with condensed milk",
                priceCents = 350, // RM 3.50
                categoryId = "2",
                isAvailable = true
            ),
            Product(
                id = "3",
                name = "Roti Canai",
                description = "Flaky layered flatbread served with curry",
                priceCents = 800, // RM 8.00
                categoryId = "1",
                isAvailable = false
            )
        )
    }
}