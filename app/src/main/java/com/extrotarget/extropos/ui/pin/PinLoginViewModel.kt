package com.extrotarget.extropos.ui.pin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.data.local.auth.LocalAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface PinLoginEvent {
    object NavigateTechnicianSetup : PinLoginEvent
    object NavigateMain : PinLoginEvent
    data class ShowMessage(val message: String) : PinLoginEvent
}

@HiltViewModel
class PinLoginViewModel @Inject constructor(
    private val localAuth: LocalAuthManager
) : ViewModel() {

    private val _events = Channel<PinLoginEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun submitPin(raw: String) {
        viewModelScope.launch {
            if (raw.length < 4) {
                _events.send(PinLoginEvent.ShowMessage("PIN too short"))
                return@launch
            }
            // Technician bootstrap path
            val technicianAllowed = localAuth.canUseTechnicianPin(raw)
            if (technicianAllowed) {
                localAuth.consumeTechnicianPin()
                _events.send(PinLoginEvent.NavigateTechnicianSetup)
                return@launch
            }
            // Normal user PIN path
            val ok = localAuth.validateUserPin(raw)
            if (ok) {
                _events.send(PinLoginEvent.NavigateMain)
            } else {
                _events.send(PinLoginEvent.ShowMessage("Invalid PIN"))
            }
        }
    }
}
