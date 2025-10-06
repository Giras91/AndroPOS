package com.extrotarget.extropos.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.usecase.LoginUseCase
import com.extrotarget.extropos.domain.usecase.IsUserLoggedInUseCase
import com.extrotarget.extropos.domain.usecase.CheckEmailVerificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface EmailAuthState {
    object Idle: EmailAuthState
    object Loading: EmailAuthState
    object NeedsVerification: EmailAuthState
    object Authenticated: EmailAuthState
    data class Error(val message: String): EmailAuthState
}

@HiltViewModel
class EmailAuthViewModel @Inject constructor(
    private val login: LoginUseCase,
    private val isLoggedIn: IsUserLoggedInUseCase,
    private val checkEmailVerification: CheckEmailVerificationUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<EmailAuthState>(EmailAuthState.Idle)
    val state: StateFlow<EmailAuthState> = _state

    fun bootstrap() {
        viewModelScope.launch {
            _state.value = EmailAuthState.Loading
            val loggedIn = runCatching { isLoggedIn() }.getOrDefault(false)
            if (!loggedIn) {
                _state.value = EmailAuthState.Idle
                return@launch
            }
            val verified = runCatching { checkEmailVerification() }.getOrDefault(false)
            _state.value = if (verified) EmailAuthState.Authenticated else EmailAuthState.NeedsVerification
        }
    }

    fun performLogin(email: String, password: String) {
        viewModelScope.launch {
            _state.value = EmailAuthState.Loading
            val result = runCatching { login(email, password) }.getOrElse { e ->
                _state.value = EmailAuthState.Error(e.message ?: "Login failed")
                return@launch
            }
            if (!result.success) {
                _state.value = EmailAuthState.Error(result.errorMessage ?: "Login failed")
            } else if (result.requiresVerification) {
                _state.value = EmailAuthState.NeedsVerification
            } else {
                _state.value = EmailAuthState.Authenticated
            }
        }
    }
}
