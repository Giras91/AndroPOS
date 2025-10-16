package com.extrotarget.extropos.ui.settings.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.TableSection
import com.extrotarget.extropos.domain.repository.ITableSectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableSectionsViewModel @Inject constructor(
    private val repo: ITableSectionRepository
) : ViewModel() {

    private val _sections = MutableStateFlow<List<TableSection>>(emptyList())
    val sections: StateFlow<List<TableSection>> = _sections

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
                _sections.value = repo.getAllSections().sortedBy { it.displayOrder }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun save(section: TableSection) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.upsertSection(section)
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
                repo.deleteSection(id)
                load()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
