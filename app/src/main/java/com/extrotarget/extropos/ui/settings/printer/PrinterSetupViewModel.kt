package com.extrotarget.extropos.ui.settings.printer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PrinterSetupViewModel : ViewModel() {

    private val _printers = MutableStateFlow<List<Printer>>(emptyList())
    val printers: StateFlow<List<Printer>> = _printers

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning

    private val _scanResults = MutableStateFlow<List<DiscoveredPrinter>>(emptyList())
    val scanResults: StateFlow<List<DiscoveredPrinter>> = _scanResults

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadPrinters() {
        viewModelScope.launch {
            // TODO: Load printers from repository
            // For now, load sample data
            val samplePrinters = listOf(
                Printer(
                    id = "1",
                    name = "Receipt Printer 1",
                    type = PrinterType.RECEIPT,
                    connectionType = ConnectionType.USB,
                    address = "/dev/usb/lp0",
                    isDefault = true,
                    isConnected = true
                ),
                Printer(
                    id = "2",
                    name = "Kitchen Printer",
                    type = PrinterType.KITCHEN,
                    connectionType = ConnectionType.NETWORK,
                    address = "192.168.1.100",
                    port = 9100,
                    isConnected = false
                )
            )
            _printers.value = samplePrinters
        }
    }

    fun scanForPrinters() {
        viewModelScope.launch {
            _isScanning.value = true
            _error.value = null

            try {
                // TODO: Implement actual printer discovery
                // For now, simulate discovery
                kotlinx.coroutines.delay(2000)

                val discoveredPrinters = listOf(
                    DiscoveredPrinter(
                        name = "Epson TM-T88V",
                        address = "192.168.1.101",
                        connectionType = ConnectionType.NETWORK,
                        manufacturer = "Epson"
                    ),
                    DiscoveredPrinter(
                        name = "Star Micronics TSP100",
                        address = "192.168.1.102",
                        connectionType = ConnectionType.NETWORK,
                        manufacturer = "Star Micronics"
                    )
                )

                _scanResults.value = discoveredPrinters
            } catch (e: Exception) {
                _error.value = "Failed to scan for printers: ${e.message}"
            } finally {
                _isScanning.value = false
            }
        }
    }

    fun addPrinter(name: String, type: PrinterType, connectionType: ConnectionType, address: String, port: Int? = null) {
        viewModelScope.launch {
            val newPrinter = Printer(
                id = System.currentTimeMillis().toString(),
                name = name,
                type = type,
                connectionType = connectionType,
                address = address,
                port = port,
                isConnected = false
            )

            val currentPrinters = _printers.value.toMutableList()
            currentPrinters.add(newPrinter)
            _printers.value = currentPrinters
        }
    }

    fun deletePrinter(printer: Printer) {
        viewModelScope.launch {
            val currentPrinters = _printers.value.toMutableList()
            currentPrinters.remove(printer)
            _printers.value = currentPrinters
        }
    }

    fun setDefaultPrinter(printer: Printer) {
        viewModelScope.launch {
            val currentPrinters = _printers.value.map {
                it.copy(isDefault = it.id == printer.id)
            }
            _printers.value = currentPrinters
        }
    }

    fun testPrint(printer: Printer) {
        viewModelScope.launch {
            // TODO: Implement test print functionality
            _error.value = "Test print sent to ${printer.name}"
        }
    }

    fun clearError() {
        _error.value = null
    }
}