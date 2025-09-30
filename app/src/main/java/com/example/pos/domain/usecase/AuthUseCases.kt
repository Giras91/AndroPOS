package com.example.pos.domain.usecase

import com.example.pos.data.repository.IAuthRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return authRepository.authenticate(username, password)
    }
}