package com.extrotarget.extropos.ui.settings.employee

enum class EmployeeRole {
    ADMIN,
    MANAGER,
    CASHIER,
    COOK,
    SERVER
}

data class Employee(
    val id: String,
    val name: String,
    val email: String,
    val phone: String? = null,
    val role: EmployeeRole,
    val isActive: Boolean = true,
    val hireDate: Long = System.currentTimeMillis(),
    val pin: String? = null // For login PIN
)