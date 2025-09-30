package com.extrotarget.extropos.ui.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.model.TableStatus
import com.extrotarget.extropos.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val getTables: GetTablesUseCase,
    private val getTable: GetTableUseCase,
    private val updateTableStatus: UpdateTableStatusUseCase,
    private val getAvailableTables: GetAvailableTablesUseCase,
    private val getOccupiedTables: GetOccupiedTablesUseCase
) : ViewModel() {

    private val _tables = MutableStateFlow<List<Table>>(emptyList())
    val tables: StateFlow<List<Table>> = _tables

    private val _availableTables = MutableStateFlow<List<Table>>(emptyList())
    val availableTables: StateFlow<List<Table>> = _availableTables

    private val _occupiedTables = MutableStateFlow<List<Table>>(emptyList())
    val occupiedTables: StateFlow<List<Table>> = _occupiedTables

    private val _selectedTable = MutableStateFlow<Table?>(null)
    val selectedTable: StateFlow<Table?> = _selectedTable

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadTables()
        loadAvailableTables()
        loadOccupiedTables()
    }

    fun loadTables() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val tablesList = getTables()
                _tables.value = tablesList
            } catch (e: Exception) {
                _error.value = "Failed to load tables: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadAvailableTables() {
        viewModelScope.launch {
            try {
                val available = getAvailableTables()
                _availableTables.value = available
            } catch (e: Exception) {
                _error.value = "Failed to load available tables: ${e.message}"
            }
        }
    }

    fun loadOccupiedTables() {
        viewModelScope.launch {
            try {
                val occupied = getOccupiedTables()
                _occupiedTables.value = occupied
            } catch (e: Exception) {
                _error.value = "Failed to load occupied tables: ${e.message}"
            }
        }
    }

    fun selectTable(table: Table) {
        _selectedTable.value = table
    }

    fun updateTableStatus(tableId: String, status: TableStatus) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                updateTableStatus(tableId, status)
                loadTables()
                loadAvailableTables()
                loadOccupiedTables()
            } catch (e: Exception) {
                _error.value = "Failed to update table status: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun occupyTable(tableId: String) {
        updateTableStatus(tableId, TableStatus.OCCUPIED)
    }

    fun freeTable(tableId: String) {
        updateTableStatus(tableId, TableStatus.AVAILABLE)
    }

    fun reserveTable(tableId: String) {
        updateTableStatus(tableId, TableStatus.RESERVED)
    }

    fun getTableById(tableId: String): Table? {
        return _tables.value.find { it.id == tableId }
    }

    fun getAvailableTableCount(): Int {
        return _availableTables.value.size
    }

    fun getOccupiedTableCount(): Int {
        return _occupiedTables.value.size
    }

    fun getTotalTableCount(): Int {
        return _tables.value.size
    }
}