package com.extrotarget.extropos.ui.settings.employee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EmployeeManagementViewModel : ViewModel() {

    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees: StateFlow<List<Employee>> = _employees

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadEmployees() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // TODO: Load employees from repository
                // For now, load sample data
                val sampleEmployees = listOf(
                    Employee(
                        id = "1",
                        name = "John Doe",
                        email = "john@example.com",
                        phone = "+60123456789",
                        role = EmployeeRole.ADMIN,
                        isActive = true
                    ),
                    Employee(
                        id = "2",
                        name = "Jane Smith",
                        email = "jane@example.com",
                        phone = "+60123456790",
                        role = EmployeeRole.CASHIER,
                        isActive = true
                    ),
                    Employee(
                        id = "3",
                        name = "Mike Johnson",
                        email = "mike@example.com",
                        role = EmployeeRole.COOK,
                        isActive = true
                    )
                )
                _employees.value = sampleEmployees
            } catch (e: Exception) {
                _error.value = "Failed to load employees: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addEmployee(name: String, email: String, phone: String?, role: EmployeeRole, pin: String?) {
        viewModelScope.launch {
            try {
                val newEmployee = Employee(
                    id = System.currentTimeMillis().toString(),
                    name = name,
                    email = email,
                    phone = phone,
                    role = role,
                    pin = pin
                )

                val currentEmployees = _employees.value.toMutableList()
                currentEmployees.add(newEmployee)
                _employees.value = currentEmployees
            } catch (e: Exception) {
                _error.value = "Failed to add employee: ${e.message}"
            }
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                val currentEmployees = _employees.value.map {
                    if (it.id == employee.id) employee else it
                }
                _employees.value = currentEmployees
            } catch (e: Exception) {
                _error.value = "Failed to update employee: ${e.message}"
            }
        }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                val currentEmployees = _employees.value.toMutableList()
                currentEmployees.remove(employee)
                _employees.value = currentEmployees
            } catch (e: Exception) {
                _error.value = "Failed to delete employee: ${e.message}"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}