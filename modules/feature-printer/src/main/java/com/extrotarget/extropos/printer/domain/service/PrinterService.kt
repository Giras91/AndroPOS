package com.extrotarget.extropos.printer.domain.service

import com.extrotarget.extropos.printer.adapter.PrinterAdapter
import com.extrotarget.extropos.printer.adapter.PrinterAdapterFactory
import com.extrotarget.extropos.printer.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Main printer service that coordinates detection, configuration, and printing operations
 */
@Singleton
open class PrinterService @Inject constructor(
    private val printerDetectionService: PrinterDetectionService,
    private val printerConfigService: PrinterConfigService,
    private val adapterFactory: PrinterAdapterFactory
) {
    private var currentAdapter: PrinterAdapter? = null
    private var currentConfig: PrinterConfig? = null

    /**
     * Scan for available printers across all connection types
     */
    open suspend fun scanAllPrinters(): List<DetectedPrinter> {
        return withContext(Dispatchers.IO) {
            val usbPrinters = printerDetectionService.scanUsbPrinters().first()
            val bluetoothPrinters = printerDetectionService.scanBluetoothPrinters().first()
            usbPrinters + bluetoothPrinters
        }
    }

    /**
     * Connect to a specific printer configuration
     */
    suspend fun connectToPrinter(config: PrinterConfig): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                // Disconnect current if connected
                disconnect()

                // Create new adapter for the SDK
                val adapter = adapterFactory.createAdapter(config.selectedSdk)
                
                // Attempt connection
                val connected = adapter.connect(config)
                
                if (connected) {
                    currentAdapter = adapter
                    currentConfig = config
                    
                    // Update test result in config
                    printerConfigService.updateTestResult(config.id, true)
                }
                
                connected
            } catch (e: Exception) {
                // Update test result as failed
                printerConfigService.updateTestResult(config.id, false)
                false
            }
        }
    }

    /**
     * Connect to the default printer if configured
     */
    suspend fun connectToDefaultPrinter(): Boolean {
        val defaultConfig = printerConfigService.getDefaultPrinter()
        return if (defaultConfig != null) {
            connectToPrinter(defaultConfig)
        } else {
            false
        }
    }

    /**
     * Disconnect from current printer
     */
    suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            try {
                currentAdapter?.disconnect()
            } catch (e: Exception) {
                // Log error but don't throw
            } finally {
                currentAdapter = null
                currentConfig = null
            }
        }
    }

    /**
     * Test connection to a printer configuration
     */
    suspend fun testConnection(config: PrinterConfig): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val adapter = adapterFactory.createAdapter(config.selectedSdk)
                val connected = adapter.connect(config)
                
                if (connected) {
                    val testResult = adapter.testConnection()
                    adapter.disconnect()
                    
                    // Update stored config with test result
                    printerConfigService.updateTestResult(config.id, testResult)
                    testResult
                } else {
                    printerConfigService.updateTestResult(config.id, false)
                    false
                }
            } catch (e: Exception) {
                printerConfigService.updateTestResult(config.id, false)
                false
            }
        }
    }

    /**
     * Print a job using the currently connected printer
     */
    suspend fun print(job: PrintJob): PrintResult {
        return withContext(Dispatchers.IO) {
            val adapter = currentAdapter
            if (adapter == null || !adapter.isConnected()) {
                return@withContext PrintResult.failure("No printer connected")
            }

            try {
                adapter.print(job)
            } catch (e: Exception) {
                PrintResult.failure("Print failed: ${e.message}")
            }
        }
    }

    /**
     * Print a simple text receipt
     */
    suspend fun printReceipt(content: String): PrintResult {
        val printItems = listOf(
            PrintItem("text", content),
            PrintItem("cut", "")
        )
        val job = PrintJob(content = printItems)
        return print(job)
    }

    /**
     * Get current connection status
     */
    suspend fun getConnectionStatus(): PrinterConnectionStatus {
        val adapter = currentAdapter
        val config = currentConfig
        
        return when {
            adapter == null -> PrinterConnectionStatus.Disconnected
            !adapter.isConnected() -> PrinterConnectionStatus.Disconnected
            config == null -> PrinterConnectionStatus.Disconnected
            else -> PrinterConnectionStatus.Connected(
                printerName = config.name,
                sdkName = "DantSu ESC/POS", // Hardcoded for now since getSdkName doesn't exist in interface
                connectionType = config.connectionType,
                features = adapter.getSupportedFeatures()
            )
        }
    }

    /**
     * Get available printer configurations
     */
    suspend fun getSavedPrinters(): List<PrinterConfig> {
        return printerConfigService.getAllConfigs().first()
    }

    /**
     * Save a new printer configuration
     */
    suspend fun savePrinterConfig(
        detectedPrinter: DetectedPrinter,
        selectedSdk: PrinterSdk,
        customName: String? = null,
        setAsDefault: Boolean = false
    ): String {
        val config = PrinterConfig(
            id = java.util.UUID.randomUUID().toString(),
            name = customName?.takeIf { it.isNotBlank() } ?: detectedPrinter.name,
            connectionType = detectedPrinter.connectionType,
            address = detectedPrinter.address,
            port = detectedPrinter.port,
            selectedSdk = selectedSdk.id,
            isDefault = setAsDefault
        )
        
        printerConfigService.saveConfig(config)
        return config.id
    }

    /**
     * Delete a printer configuration
     */
    suspend fun deletePrinterConfig(configId: String) {
        // Disconnect if this is the current printer
        if (currentConfig?.id == configId) {
            disconnect()
        }
        printerConfigService.deleteConfig(configId)
    }

    /**
     * Set a printer as default
     */
    suspend fun setDefaultPrinter(configId: String) {
        printerConfigService.setDefaultPrinter(configId)
    }

    /**
     * Create a USB printer configuration
     */
    suspend fun addUsbPrinter(
        detectedPrinter: DetectedPrinter,
        sdkId: String,
        customName: String? = null
    ): String {
        val config = PrinterConfig(
            id = java.util.UUID.randomUUID().toString(),
            name = customName?.takeIf { it.isNotBlank() } ?: detectedPrinter.name,
            connectionType = ConnectionType.USB,
            address = detectedPrinter.address,
            port = null,
            selectedSdk = sdkId,
            isDefault = false
        )

        printerConfigService.saveConfig(config)
        return config.id
    }

    /**
     * Create a Bluetooth printer configuration
     */
    suspend fun addBluetoothPrinter(
        detectedPrinter: DetectedPrinter,
        sdkId: String,
        customName: String? = null
    ): String {
        val config = PrinterConfig(
            id = java.util.UUID.randomUUID().toString(),
            name = customName?.takeIf { it.isNotBlank() } ?: detectedPrinter.name,
            connectionType = ConnectionType.BLUETOOTH,
            address = detectedPrinter.address,
            port = null,
            selectedSdk = sdkId,
            isDefault = false
        )

        printerConfigService.saveConfig(config)
        return config.id
    }

    /**
     * Create a network printer configuration
     */
    suspend fun addNetworkPrinter(
        name: String,
        ip: String,
        port: Int,
        sdkId: String
    ): String {
        val config = PrinterConfig(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            connectionType = ConnectionType.NETWORK,
            address = ip,
            port = port,
            selectedSdk = sdkId,
            isDefault = false
        )

        printerConfigService.saveConfig(config)
        return config.id
    }

    /**
     * Get all available printer SDKs
     */
    open fun getAvailableSdks(): List<PrinterSdk> {
        return printerDetectionService.getAllAvailableSdks()
    }
}

/**
 * Represents the current printer connection status
 */
sealed class PrinterConnectionStatus {
    object Disconnected : PrinterConnectionStatus()
    
    data class Connected(
        val printerName: String,
        val sdkName: String,
        val connectionType: ConnectionType,
        val features: List<com.extrotarget.extropos.printer.adapter.PrinterFeature>
    ) : PrinterConnectionStatus()
}