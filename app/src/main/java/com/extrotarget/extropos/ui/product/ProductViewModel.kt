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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                // TODO: Load from repository
                // For now, create mock data
                _products.value = createMockProducts()
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
                loadProducts()
                return@launch
            }
            
            val filteredProducts = _products.value.filter {
                it.name.contains(query, ignoreCase = true) ||
                it.description.contains(query, ignoreCase = true)
            }
            _products.value = filteredProducts
        }
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