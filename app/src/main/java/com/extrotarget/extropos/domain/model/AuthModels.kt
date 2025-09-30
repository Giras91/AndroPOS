package com.extrotarget.extropos.domain.model

data class User(
    val id: String = "",
    val email: String,
    val name: String,
    val companyName: String,
    val companyRegistrationNumber: String,
    val address: String,
    val phoneNumber: String,
    val emailVerified: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

data class AuthResult(
    val success: Boolean,
    val user: User? = null,
    val errorMessage: String? = null,
    val requiresVerification: Boolean = false
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class SignupRequest(
    val email: String,
    val password: String,
    val name: String,
    val companyName: String,
    val companyRegistrationNumber: String,
    val address: String,
    val phoneNumber: String
)

enum class AuthState {
    NOT_AUTHENTICATED,
    AUTHENTICATED_NOT_VERIFIED,
    AUTHENTICATED_VERIFIED,
    LOADING
}