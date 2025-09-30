package com.extrotarget.extropos.domain.repository

import com.extrotarget.extropos.domain.model.*

interface IAuthRepository {
    suspend fun login(request: LoginRequest): AuthResult
    suspend fun signup(request: SignupRequest): AuthResult
    suspend fun logout(): Boolean
    suspend fun getCurrentUser(): User?
    suspend fun checkEmailVerification(): Boolean
    suspend fun resendVerificationEmail(): Boolean
    suspend fun isUserLoggedIn(): Boolean
}