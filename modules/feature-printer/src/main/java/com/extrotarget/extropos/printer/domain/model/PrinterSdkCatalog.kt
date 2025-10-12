package com.extrotarget.extropos.printer.domain.model

/**
 * Represents a printer SDK/driver in the catalog
 */
data class PrinterSdk(
    val id: String,
    val name: String,
    val vendor: String,
    val version: String,
    val description: String,
    val connectionTypes: List<ConnectionType>,
    val supportedModels: List<String> = emptyList(),
    val vendorIds: List<String> = emptyList(), // USB Vendor IDs
    val productIds: List<String> = emptyList(), // USB Product IDs  
    val bluetoothServiceUuids: List<String> = emptyList(),
    val isInstalled: Boolean = false,
    val downloadUrl: String? = null,
    val githubRepo: String? = null,
    val documentation: String? = null,
    val licenseType: LicenseType = LicenseType.UNKNOWN
)

enum class LicenseType {
    OPEN_SOURCE, COMMERCIAL, FREE, UNKNOWN
}