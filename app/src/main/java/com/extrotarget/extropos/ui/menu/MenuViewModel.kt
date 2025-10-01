package com.extrotarget.extropos.ui.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Category
import com.extrotarget.extropos.domain.model.MenuItem
import com.extrotarget.extropos.domain.usecase.GetCategoriesUseCase
import com.extrotarget.extropos.domain.usecase.GetMenuItemsUseCase
import com.extrotarget.extropos.domain.usecase.SearchMenuItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase,
    private val getMenuItemsUseCase: GetMenuItemsUseCase,
    private val searchMenuItemsUseCase: SearchMenuItemsUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> = _selectedCategory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadCategories()
        loadAllMenuItems()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val categoriesList = getCategories()
                _categories.value = categoriesList
                if (categoriesList.isNotEmpty() && _selectedCategory.value == null) {
                    _selectedCategory.value = categoriesList.first()
                }
            } catch (e: Exception) {
                _error.value = "Failed to load categories: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAllMenuItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val items: List<MenuItem> = getMenuItemsUseCase.invoke()
                _menuItems.value = items
            } catch (e: Exception) {
                _error.value = "Failed to load menu items: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMenuItemsByCategory(categoryId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val items: List<MenuItem> = getMenuItemsUseCase.invoke(categoryId)
                _menuItems.value = items
            } catch (e: Exception) {
                _error.value = "Failed to load menu items: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectCategory(category: Category) {
        _selectedCategory.value = category
        loadMenuItemsByCategory(category.id)
    }

    fun searchMenuItems(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val items = searchMenuItemsUseCase.invoke(query)
                _menuItems.value = items
            } catch (e: Exception) {
                _error.value = "Failed to search menu items: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearSearch() {
        loadAllMenuItems()
    }
}