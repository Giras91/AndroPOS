package com.extrotarget.extropos.domain.usecase

class AuthenticateUserUseCase {
    suspend operator fun invoke(username: String, password: String): Boolean {
        // TODO: Implement with repository
        return username.isNotEmpty() && password.isNotEmpty()
    }
}