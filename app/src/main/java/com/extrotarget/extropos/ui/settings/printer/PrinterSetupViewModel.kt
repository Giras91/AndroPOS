package com.extrotarget.extropos.ui.settings.printer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// TODO: Migrate to feature-printer module
// import com.extrotarget.extropos.printer.data.IPrinterLocalRepository
// import com.extrotarget.extropos.printer.data.IPrinterScanner
import com.extrotarget.extropos.printer.data.PrinterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrinterSetupViewModel @Inject constructor(
    // TODO: Migrate to feature-printer module dependencies
    // private val localRepo: IPrinterLocalRepository,
    // private val scanner: IPrinterScanner
) : ViewModel() {

    private val _printers = MutableStateFlow<List<Printer>>(emptyList())
    val printers: StateFlow<List<Printer>> = _printers

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning

    private val _scanResults = MutableStateFlow<List<DiscoveredPrinter>>(emptyList())
    val scanResults: StateFlow<List<DiscoveredPrinter>> = _scanResults

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        // TODO: Migrate to feature-printer module
        /*
        // Observe DB-backed printers and update UI state
        viewModelScope.launch {
            try {
                localRepo.observePrinters().collect { entities ->
                    _printers.value = entities.map { it.toModel() }
                }
            } catch (e: Exception) {
                _error.value = "Failed to observe printers: ${e.message}"
            }
        }
        */
        _error.value = "This screen is deprecated. Use Printer Management in main menu."
    }

    fun loadPrinters() {
        // TODO: Migrate to feature-printer module
        /*
        viewModelScope.launch {
            try {
                val list = localRepo.getAll()
                _printers.value = list.map { it.toModel() }
            } catch (e: Exception) {
                _error.value = "Failed to load printers: ${e.message}"
            }
        }
        */
    }

    fun scanForPrinters() {
        // TODO: Migrate to feature-printer module
        /*
        viewModelScope.launch {
            _isScanning.value = true
            _error.value = null

            try {
                val discovered = mutableListOf<DiscoveredPrinter>()
                discovered.addAll(scanner.scanBluetooth())
                kotlinx.coroutines.delay(1000)
                discovered.addAll(scanner.scanUsb())
                kotlinx.coroutines.delay(1000)
                discovered.addAll(scanner.scanNetwork())

                _scanResults.value = discovered
            } catch (e: Exception) {
                _error.value = "Failed to scan for printers: ${e.message}"
            } finally {
                _isScanning.value = false
            }
        }
        */
    }

    fun scanBluetoothPrinters() {
        // TODO: Migrate to feature-printer module
        /*
        viewModelScope.launch {
            _isScanning.value = true
            _error.value = null

            try {
                val bluetoothPrinters = scanner.scanBluetooth()
                _scanResults.value = bluetoothPrinters
            } catch (e: Exception) {
                _error.value = "Failed to scan Bluetooth printers: ${e.message}"
            } finally {
                _isScanning.value = false
            }
        }
        */
    }

    fun scanUsbPrinters() {
        // TODO: Migrate to feature-printer module
        /*
        viewModelScope.launch {
            _isScanning.value = true
            _error.value = null

            try {
                val usbPrinters = scanner.scanUsb()
                _scanResults.value = usbPrinters
            } catch (e: Exception) {
                _error.value = "Failed to scan USB printers: ${e.message}"
            } finally {
                _isScanning.value = false
            }
        }
        */
    }

    fun scanNetworkPrinters() {
        // TODO: Migrate to feature-printer module
        /*
        viewModelScope.launch {
            _isScanning.value = true
            _error.value = null

            try {
                val networkPrinters = scanner.scanNetwork()
                _scanResults.value = networkPrinters
            } catch (e: Exception) {
                _error.value = "Failed to scan network printers: ${e.message}"
            } finally {
                _isScanning.value = false
            }
        }
        */
    }


    fun addPrinter(name: String, type: PrinterType, connectionType: ConnectionType, address: String, port: Int? = null) {
        // TODO: Migrate to feature-printer module
        /*
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

            try {
                localRepo.upsert(newPrinter.toEntity())
                // Flow observation will update _printers
            } catch (e: Exception) {
                _error.value = "Failed to add printer: ${e.message}"
            }
        }
        */
    }

    fun deletePrinter(printer: Printer) {
        // TODO: Migrate to feature-printer module
        /*
        viewModelScope.launch {
            try {
                localRepo.deleteById(printer.id)
            } catch (e: Exception) {
                _error.value = "Failed to delete printer: ${e.message}"
            }
        }
        */
    }

    fun setDefaultPrinter(printer: Printer) {
        // TODO: Migrate to feature-printer module
        /*
        viewModelScope.launch {
            try {
                localRepo.setDefault(printer.id)
            } catch (e: Exception) {
                _error.value = "Failed to set default printer: ${e.message}"
            }
        }
        */
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

    // --- Converters between UI model and entity ---
    // TODO: Migrate to feature-printer module
    /*
    private fun Printer.toEntity(): PrinterEntity {
        return PrinterEntity(
            id = this.id,
            name = this.name,
            type = this.type.name,
            connectionType = this.connectionType.name,
            address = this.address,
            port = this.port,
            isDefault = this.isDefault,
            isConnected = this.isConnected
        )
    }

    private fun PrinterEntity.toModel(): Printer {
        val t = try { PrinterType.valueOf(this.type) } catch (e: Exception) { PrinterType.RECEIPT }
        val c = try { ConnectionType.valueOf(this.connectionType) } catch (e: Exception) { ConnectionType.USB }
        return Printer(
            id = this.id,
            name = this.name,
            type = t,
            connectionType = c,
            address = this.address,
            port = this.port,
            isDefault = this.isDefault,
            isConnected = this.isConnected
        )
    }
    */
}