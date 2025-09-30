package com.example.pos.data.repository

import javax.inject.Singleton

interface IAuthRepository {
    suspend fun authenticate(username: String, password: String): Result<Unit>
    suspend fun logout(): Result<Unit>
    fun isLoggedIn(): Boolean
    fun getCurrentUser(): String?
}

@Singleton
class AuthRepository : IAuthRepository {

    private var currentUser: String? = null

    override suspend fun authenticate(username: String, password: String): Result<Unit> {
        // TODO: Implement actual authentication logic
        // For now, accept any non-empty credentials
        return if (username.isNotBlank() && password.isNotBlank()) {
            currentUser = username
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        currentUser = null
        return Result.success(Unit)
    }

    override fun isLoggedIn(): Boolean = currentUser != null

    override fun getCurrentUser(): String? = currentUser
}