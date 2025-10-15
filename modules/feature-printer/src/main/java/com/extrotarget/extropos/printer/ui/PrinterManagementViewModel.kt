package com.extrotarget.extropos.printer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.printer.domain.model.DetectedPrinter
import com.extrotarget.extropos.printer.domain.model.PrinterConfig
import com.extrotarget.extropos.printer.domain.model.PrinterSdk
import com.extrotarget.extropos.printer.domain.service.PrinterConnectionStatus
import com.extrotarget.extropos.printer.domain.service.PrinterService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrinterManagementViewModel @Inject constructor(
    private val printerService: PrinterService
) : ViewModel() {

    private val _printers = MutableStateFlow<List<DetectedPrinter>>(emptyList())
    val printers: StateFlow<List<DetectedPrinter>> = _printers.asStateFlow()

    private val _status = MutableStateFlow<PrinterConnectionStatus>(PrinterConnectionStatus.Disconnected)
    val status: StateFlow<PrinterConnectionStatus> = _status.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    private val _sdks = MutableStateFlow<List<PrinterSdk>>(emptyList())
    val sdks: StateFlow<List<PrinterSdk>> = _sdks.asStateFlow()

    init {
        // Load available SDKs from service
        viewModelScope.launch {
            try {
                _sdks.value = printerService.getAvailableSdks()
            } catch (e: Exception) {
                _message.value = "Failed to load printer SDKs: ${e.message}"
            }
        }
    }

    fun scanForPrinters() {
        viewModelScope.launch {
            try {
                _message.value = "Scanning for printers..."
                val results = printerService.scanAllPrinters()
                _printers.value = results
                _message.value = if (results.isEmpty()) "No printers found" else "Found ${results.size} printer(s)"
            } catch (e: Exception) {
                _message.value = "Scan failed: ${e.message}"
            }
        }
    }

    fun connectToPrinter(detectedPrinter: DetectedPrinter) {
        viewModelScope.launch {
            try {
                // Prefer an SDK from the detection/catalog. If none available, inform the user.
                val availableSdks = printerService.getAvailableSdks()
                val sdk = availableSdks.firstOrNull()

                if (sdk == null) {
                    _message.value = "No printer SDKs available. Install or add a compatible SDK first."
                    return@launch
                }

                val configId = printerService.savePrinterConfig(detectedPrinter, sdk, setAsDefault = true)
                val config = printerService.getSavedPrinters().firstOrNull { it.id == configId }
                if (config != null) {
                    val connected = printerService.connectToPrinter(config)
                    _message.value = if (connected) "Connected to ${config.name}" else "Failed to connect to ${config.name}"
                    _status.value = printerService.getConnectionStatus()
                } else {
                    _message.value = "Could not create printer config"
                }
            } catch (e: Exception) {
                _message.value = "Connect failed: ${e.message}"
            }
        }
    }

    fun connectToDefaultPrinter() {
        viewModelScope.launch {
            try {
                val connected = printerService.connectToDefaultPrinter()
                _message.value = if (connected) "Connected to default printer" else "No default printer configured"
                _status.value = printerService.getConnectionStatus()
            } catch (e: Exception) {
                _message.value = "Connect default failed: ${e.message}"
            }
        }
    }

    fun printTestReceipt() {
        viewModelScope.launch {
            try {
                val testReceipt = com.extrotarget.extropos.printer.template.ReceiptTemplateBuilder.createTestReceipt()
                val result = printerService.print(testReceipt)
                _message.value = if (result.success) "Test print success" else "Test print failed: ${result.message}"
            } catch (e: Exception) {
                _message.value = "Print failed: ${e.message}"
            }
        }
    }

    fun addNetworkPrinter(name: String, ip: String, port: Int, sdkId: String) {
        viewModelScope.launch {
            try {
                printerService.addNetworkPrinter(name, ip, port, sdkId)
                scanForPrinters()
                _message.value = "Network printer added"
            } catch (e: Exception) {
                _message.value = "Add network failed: ${e.message}"
            }
        }
    }

    suspend fun refreshStatus() {
        _status.value = printerService.getConnectionStatus()
    }
}
