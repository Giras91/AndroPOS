package com.extrotarget.extropos.ui.settings.hardware

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.data.model.ReceiptSettings
import com.extrotarget.extropos.data.repository.ReceiptSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HardwareSettingsViewModel @Inject constructor(
    private val receiptSettingsRepository: ReceiptSettingsRepository
) : ViewModel() {

    private val _receiptSettings = MutableStateFlow(ReceiptSettings())
    val receiptSettings: StateFlow<ReceiptSettings> = _receiptSettings

    private val _saveStatus = MutableStateFlow<SaveStatus>(SaveStatus.Idle)
    val saveStatus: StateFlow<SaveStatus> = _saveStatus

    sealed class SaveStatus {
        object Idle : SaveStatus()
        object Success : SaveStatus()
        data class Error(val message: String) : SaveStatus()
    }

    init {
        loadSettings()
    }

    fun loadSettings() {
        viewModelScope.launch {
            try {
                val settings = receiptSettingsRepository.getReceiptSettings()
                _receiptSettings.value = settings
            } catch (e: Exception) {
                _receiptSettings.value = ReceiptSettings() // Use defaults if loading fails
            }
        }
    }

    fun saveSettings(settings: ReceiptSettings) {
        viewModelScope.launch {
            try {
                receiptSettingsRepository.saveReceiptSettings(settings)
                _receiptSettings.value = settings
                _saveStatus.value = SaveStatus.Success
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(2000)
                _saveStatus.value = SaveStatus.Idle
                
            } catch (e: Exception) {
                _saveStatus.value = SaveStatus.Error(e.message ?: "Unknown error occurred")
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(3000)
                _saveStatus.value = SaveStatus.Idle
            }
        }
    }

    fun updatePaperSize(paperSize: ReceiptSettings.PaperSize) {
        val currentSettings = _receiptSettings.value
        val updatedSettings = currentSettings.copy(
            paperSize = paperSize,
            charactersPerLine = paperSize.charsPerLine
        )
        _receiptSettings.value = updatedSettings
    }

    fun updateStoreName(storeName: String) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(storeName = storeName)
    }

    fun updateStoreAddress(storeAddress: String) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(storeAddress = storeAddress)
    }

    fun updatePhoneNumber(phoneNumber: String) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(phoneNumber = phoneNumber)
    }

    fun updateFooterMessage(footerMessage: String) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(footerMessage = footerMessage)
    }

    fun toggleShowLogo(show: Boolean) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(showLogo = show)
    }

    fun toggleShowQrCode(show: Boolean) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(showQrCode = show)
    }

    fun toggleShowTaxBreakdown(show: Boolean) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(showTaxBreakdown = show)
    }

    fun toggleAutoCut(autoCut: Boolean) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(autoCut = autoCut)
    }

    fun toggleDuplicateReceipt(duplicate: Boolean) {
        val currentSettings = _receiptSettings.value
        _receiptSettings.value = currentSettings.copy(duplicateReceipt = duplicate)
    }

    fun resetToDefaults() {
        viewModelScope.launch {
            try {
                receiptSettingsRepository.resetToDefaults()
                _receiptSettings.value = ReceiptSettings()
                _saveStatus.value = SaveStatus.Success
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(2000)
                _saveStatus.value = SaveStatus.Idle
                
            } catch (e: Exception) {
                _saveStatus.value = SaveStatus.Error("Failed to reset settings: ${e.message}")
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(3000)
                _saveStatus.value = SaveStatus.Idle
            }
        }
    }
}