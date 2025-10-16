package com.extrotarget.extropos.ui.settings.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.repository.ITableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableConfigurationViewModel @Inject constructor(
    private val tableRepository: ITableRepository
) : ViewModel() {

    private val _tables = MutableStateFlow<List<Table>>(emptyList())
    val tables: StateFlow<List<Table>> = _tables

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadTables()
    }

    fun loadTables() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val tablesList = tableRepository.getAllTables()
                _tables.value = tablesList.sortedBy { it.number }
            } catch (e: Exception) {
                _error.value = "Failed to load tables: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addTable(table: Table) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                tableRepository.upsertTable(table)
                loadTables() // Refresh the list
            } catch (e: Exception) {
                _error.value = "Failed to add table: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateTable(table: Table) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                tableRepository.upsertTable(table)
                loadTables() // Refresh the list
            } catch (e: Exception) {
                _error.value = "Failed to update table: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteTable(tableId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                tableRepository.deleteTable(tableId)
                loadTables() // Refresh the list
            } catch (e: Exception) {
                _error.value = "Failed to delete table: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}