package com.extrotarget.extropos.ui.settings.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.TableLayout
import com.extrotarget.extropos.domain.repository.ITableLayoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableLayoutsViewModel @Inject constructor(
    private val repo: ITableLayoutRepository
) : ViewModel() {

    private val _layouts = MutableStateFlow<List<TableLayout>>(emptyList())
    val layouts: StateFlow<List<TableLayout>> = _layouts

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init { load() }

    fun load() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _layouts.value = repo.getAll()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun save(layout: TableLayout) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.upsert(layout)
                load()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.delete(id)
                load()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setDefault(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.setDefault(id)
                load()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
