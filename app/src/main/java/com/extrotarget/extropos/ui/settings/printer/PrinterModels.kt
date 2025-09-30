package com.extrotarget.extropos.ui.settings.printer

enum class PrinterType {
    RECEIPT,
    KITCHEN
}

enum class ConnectionType {
    USB,
    BLUETOOTH,
    NETWORK
}

data class Printer(
    val id: String,
    val name: String,
    val type: PrinterType,
    val connectionType: ConnectionType,
    val address: String, // IP address, MAC address, or USB path
    val port: Int? = null, // For network printers
    val isDefault: Boolean = false,
    val isConnected: Boolean = false
)

data class DiscoveredPrinter(
    val name: String,
    val address: String,
    val connectionType: ConnectionType,
    val manufacturer: String? = null
)