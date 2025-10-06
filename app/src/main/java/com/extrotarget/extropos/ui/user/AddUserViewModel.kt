package com.extrotarget.extropos.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.data.local.auth.LocalAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface AddUserEvent {
    object NavigatePinLogin : AddUserEvent
    data class ShowMessage(val message: String) : AddUserEvent
}

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val localAuth: LocalAuthManager
) : ViewModel() {

    private val _events = Channel<AddUserEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun setPin(pin: String, confirm: String) {
        viewModelScope.launch {
            if (pin != confirm) {
                _events.send(AddUserEvent.ShowMessage("PIN mismatch"))
                return@launch
            }
            if (pin.length < 4) {
                _events.send(AddUserEvent.ShowMessage("PIN too short"))
                return@launch
            }
            localAuth.saveUserPin(pin)
            _events.send(AddUserEvent.NavigatePinLogin)
        }
    }
}
