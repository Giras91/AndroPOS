package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.*
import com.extrotarget.extropos.domain.repository.IAuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): AuthResult {
        val request = LoginRequest(email, password)
        return authRepository.login(request)
    }
}

class SignupUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String,
        companyName: String,
        companyRegistrationNumber: String,
        address: String,
        phoneNumber: String
    ): AuthResult {
        val request = SignupRequest(
            email = email,
            password = password,
            name = name,
            companyName = companyName,
            companyRegistrationNumber = companyRegistrationNumber,
            address = address,
            phoneNumber = phoneNumber
        )
        return authRepository.signup(request)
    }
}

class LogoutUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.logout()
    }
}

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): User? {
        return authRepository.getCurrentUser()
    }
}

class CheckEmailVerificationUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.checkEmailVerification()
    }
}

class ResendVerificationEmailUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.resendVerificationEmail()
    }
}

class IsUserLoggedInUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn()
    }
}

class CheckAppActivationUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(): AuthState {
        val isLoggedIn = authRepository.isUserLoggedIn()
        if (!isLoggedIn) {
            return AuthState.NOT_AUTHENTICATED
        }

        val isVerified = authRepository.checkEmailVerification()
        return if (isVerified) {
            AuthState.AUTHENTICATED_VERIFIED
        } else {
            AuthState.AUTHENTICATED_NOT_VERIFIED
        }
    }
}