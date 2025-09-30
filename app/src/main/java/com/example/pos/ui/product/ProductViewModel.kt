package com.example.pos.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pos.domain.model.Product
import com.example.pos.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val getProducts: GetProductsUseCase) : ViewModel() {
    private val _state = MutableStateFlow<List<Product>>(emptyList())
    val state: StateFlow<List<Product>> = _state

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())

    fun load() {
        viewModelScope.launch {
            _state.value = getProducts()
            _allProducts.value = _state.value
        }
    }

    fun searchProducts(query: String) {
        if (query.isBlank()) {
            _state.value = _allProducts.value
        } else {
            _state.value = _allProducts.value.filter { product ->
                product.name.contains(query, ignoreCase = true) ||
                product.sku?.contains(query, ignoreCase = true) == true ||
                product.barcode?.contains(query, ignoreCase = true) == true
            }
        }
    }

    fun getProductById(id: String): Product? {
        return _allProducts.value.find { it.id == id }
    }
}