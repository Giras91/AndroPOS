package com.extrotarget.extropos.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.AuthResult
import com.extrotarget.extropos.domain.model.AuthState
import com.extrotarget.extropos.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signupUseCase: SignupUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val checkAppActivationUseCase: CheckAppActivationUseCase,
    private val resendVerificationEmailUseCase: ResendVerificationEmailUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.LOADING)
    val authState: StateFlow<AuthState> = _authState

    private val _loginResult = MutableStateFlow<AuthResult?>(null)
    val loginResult: StateFlow<AuthResult?> = _loginResult

    private val _signupResult = MutableStateFlow<AuthResult?>(null)
    val signupResult: StateFlow<AuthResult?> = _signupResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        checkAppActivation()
    }

    fun checkAppActivation() {
        viewModelScope.launch {
            _authState.value = AuthState.LOADING
            try {
                val state = checkAppActivationUseCase()
                _authState.value = state
            } catch (e: Exception) {
                _authState.value = AuthState.NOT_AUTHENTICATED
                _error.value = "Failed to check activation status: ${e.message}"
            }
        }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _error.value = "Please fill in all fields"
            return
        }

        if (!isValidEmail(email)) {
            _error.value = "Please enter a valid email address"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _loginResult.value = null

            try {
                val result = loginUseCase(email, password)
                _loginResult.value = result

                if (result.success) {
                    checkAppActivation() // Re-check activation status after login
                } else {
                    _error.value = result.errorMessage
                }
            } catch (e: Exception) {
                _error.value = "Login failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun signup(
        email: String,
        password: String,
        name: String,
        companyName: String,
        companyRegistrationNumber: String,
        address: String,
        phoneNumber: String
    ) {
        // Validation
        if (email.isBlank() || password.isBlank() || name.isBlank() ||
            companyName.isBlank() || companyRegistrationNumber.isBlank() ||
            address.isBlank() || phoneNumber.isBlank()) {
            _error.value = "Please fill in all fields"
            return
        }

        if (!isValidEmail(email)) {
            _error.value = "Please enter a valid email address"
            return
        }

        if (password.length < 8) {
            _error.value = "Password must be at least 8 characters long"
            return
        }

        if (!phoneNumber.matches(Regex("^(\\+?6?01)[0-46-9]-*[0-9]{7,8}\$"))) {
            _error.value = "Please enter a valid Malaysian phone number"
            return
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _signupResult.value = null

            try {
                val result = signupUseCase(
                    email, password, name, companyName,
                    companyRegistrationNumber, address, phoneNumber
                )
                _signupResult.value = result

                if (!result.success) {
                    _error.value = result.errorMessage
                }
            } catch (e: Exception) {
                _error.value = "Signup failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val success = logoutUseCase()
                if (success) {
                    _authState.value = AuthState.NOT_AUTHENTICATED
                } else {
                    _error.value = "Logout failed"
                }
            } catch (e: Exception) {
                _error.value = "Logout failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resendVerificationEmail() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val success = resendVerificationEmailUseCase()
                if (success) {
                    _error.value = "Verification email sent successfully"
                } else {
                    _error.value = "Failed to send verification email"
                }
            } catch (e: Exception) {
                _error.value = "Failed to send verification email: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun clearResults() {
        _loginResult.value = null
        _signupResult.value = null
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}