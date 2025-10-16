package com.extrotarget.extropos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.model.TableSection
import com.extrotarget.extropos.domain.model.TableStatus
import com.extrotarget.extropos.domain.repository.ITableRepository
import com.extrotarget.extropos.domain.repository.ITableSectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableSelectionViewModel @Inject constructor(
    private val tableRepository: ITableRepository,
    private val tableSectionRepository: ITableSectionRepository
) : ViewModel() {

    private val _tables = MutableStateFlow<List<Table>>(emptyList())
    val tables: StateFlow<List<Table>> = _tables

    private val _sections = MutableStateFlow<List<TableSection>>(emptyList())
    val sections: StateFlow<List<TableSection>> = _sections

    private val _selectedSection = MutableStateFlow<TableSection?>(null)
    val selectedSection: StateFlow<TableSection?> = _selectedSection

    private val _selectedTable = MutableStateFlow<Table?>(null)
    val selectedTable: StateFlow<Table?> = _selectedTable

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Filtered tables based on selected section
    val filteredTables = combine(_tables, _selectedSection) { tables, section ->
        if (section == null) {
            tables.filter { it.isActive }
        } else {
            tables.filter { it.isActive && it.section == section.name }
        }
    }

    // Tables grouped by section for display
    val tablesBySection = combine(_tables, _sections) { tables, sections ->
        sections.filter { it.isActive }.associateWith { section ->
            tables.filter { it.isActive && it.section == section.name }
        }
    }

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val tablesList = tableRepository.getAllTables()
                val sectionsList = tableSectionRepository.getActiveSections()

                _tables.value = tablesList
                _sections.value = sectionsList.sortedBy { it.displayOrder }
            } catch (e: Exception) {
                _error.value = "Failed to load table data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectSection(section: TableSection?) {
        _selectedSection.value = section
    }

    fun selectTable(table: Table) {
        _selectedTable.value = table
    }

    fun clearSelection() {
        _selectedTable.value = null
    }

    fun getAvailableTables(): List<Table> {
        return _tables.value.filter { it.status == TableStatus.AVAILABLE && it.isActive }
    }

    fun getTablesInSection(sectionName: String): List<Table> {
        return _tables.value.filter { it.isActive && it.section == sectionName }
    }

    fun getTableById(tableId: String): Table? {
        return _tables.value.find { it.id == tableId }
    }

    fun getSectionByName(sectionName: String): TableSection? {
        return _sections.value.find { it.name == sectionName }
    }

    fun refreshData() {
        loadData()
    }
}