package com.extrotarget.extropos.printer.domain.model

import kotlinx.serialization.Serializable
import java.util.UUID

// Printer Configuration Models
enum class ConnectionType {
    USB, BLUETOOTH, NETWORK
}

@Serializable
data class PrinterConfig(
    val id: String,
    val name: String,
    val connectionType: ConnectionType,
    val address: String, // IP address, Bluetooth MAC, or USB device path
    val port: Int? = null, // For network printers
    val selectedSdk: String,
    val isDefault: Boolean = false,
    val lastTestResult: Boolean? = null // null = not tested, true = success, false = failed
)

// Print Job Models
data class PrintJob(
    val id: String = UUID.randomUUID().toString(),
    val content: List<PrintItem>,
    val copies: Int = 1,
    val timestamp: Long = System.currentTimeMillis()
)

data class PrintItem(
    val type: String, // "text", "barcode", "qr", "image", "cut", "drawer"
    val data: String,
    val formatting: Map<String, Any> = emptyMap() // font size, alignment, etc.
)

data class PrintResult(
    val success: Boolean,
    val message: String? = null,
    val timestamp: Long = System.currentTimeMillis()
) {
    companion object {
        fun success(message: String? = null) = PrintResult(true, message)
        fun failure(message: String) = PrintResult(false, message)
    }
}

// Detected Printer Model
data class DetectedPrinter(
    val name: String,
    val connectionType: ConnectionType,
    val address: String, // MAC address, IP, or USB path
    val vendorId: Int = 0,
    val productId: Int = 0,
    val port: Int? = null,
    val compatibleSdks: List<PrinterSdk>
)
