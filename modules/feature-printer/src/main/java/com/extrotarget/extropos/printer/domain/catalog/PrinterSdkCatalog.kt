package com.extrotarget.extropos.printer.domain.catalog

import com.extrotarget.extropos.printer.domain.model.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Comprehensive catalog of ESC/POS printer SDKs available from GitHub and vendors
 */
@Singleton
class PrinterSdkCatalog @Inject constructor() {

    /**
     * Complete catalog of available ESC/POS printer SDKs
     */
    fun getAllSdks(): List<PrinterSdk> = listOf(
        
        // === OPEN SOURCE GITHUB SDKs ===
        
        PrinterSdk(
            id = "dantsu-escpos",
            name = "DantSu ESC/POS",
            vendor = "DantSu",
            version = "3.3.0",
            description = "Android ESC/POS library for thermal printers",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf("Generic ESC/POS", "Epson TM series", "Star TSP series"),
            githubRepo = "https://github.com/DantSu/ESCPOS-ThermalPrinter-Android",
            licenseType = LicenseType.OPEN_SOURCE
        ),
        
        PrinterSdk(
            id = "escpos-coffee",
            name = "ESC/POS Coffee",
            vendor = "anastaciocintra",
            version = "4.1.0",
            description = "Java library for ESC/POS thermal printers",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf("Generic ESC/POS", "Epson", "Bixolon", "Citizen"),
            githubRepo = "https://github.com/anastaciocintra/escpos-coffee",
            licenseType = LicenseType.OPEN_SOURCE
        ),
        
        PrinterSdk(
            id = "thermal-printer",
            name = "Thermal Printer",
            vendor = "imkiran13",
            version = "1.0.6",
            description = "Android thermal printer library with ESC/POS commands",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH),
            supportedModels = listOf("Generic ESC/POS", "58mm", "80mm printers"),
            githubRepo = "https://github.com/imkiran13/thermal-printer",
            licenseType = LicenseType.OPEN_SOURCE
        ),
        
        PrinterSdk(
            id = "escpos4k",
            name = "ESC/POS for Kotlin",
            vendor = "escpos4k",
            version = "0.3.0",
            description = "Kotlin multiplatform ESC/POS library",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf("Generic ESC/POS", "Epson TM series"),
            githubRepo = "https://github.com/escpos4k/escpos4k",
            licenseType = LicenseType.OPEN_SOURCE
        ),
        
        PrinterSdk(
            id = "escpos-android",
            name = "ESC/POS Android",
            vendor = "Dev4mobile",
            version = "2.0.1",
            description = "Simple ESC/POS thermal printer library for Android",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH),
            supportedModels = listOf("Generic ESC/POS", "58mm", "80mm thermal printers"),
            githubRepo = "https://github.com/Dev4mobile/escpos-android",
            licenseType = LicenseType.OPEN_SOURCE
        ),
        
        // === VENDOR SPECIFIC SDKs ===
        
        PrinterSdk(
            id = "epson-epos2",
            name = "Epson ePOS2",
            vendor = "Epson",
            version = "2.27.0",
            description = "Official Epson ePOS SDK for TM series printers",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf(
                "TM-T88V", "TM-T88VI", "TM-T82III", "TM-T70II", "TM-T20III", 
                "TM-P60II", "TM-P80", "TM-H6000V", "TM-U220"
            ),
            vendorIds = listOf("04B8"), // Epson USB VID
            productIds = listOf("0202", "0204", "0205", "0206"),
            downloadUrl = "https://download.epson-biz.com/modules/pos/index.php?page=single_soft&cid=6597",
            licenseType = LicenseType.COMMERCIAL
        ),
        
        PrinterSdk(
            id = "star-io10",
            name = "Star IO10",
            vendor = "Star Micronics",
            version = "1.7.0",
            description = "Official Star Micronics printer SDK",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf(
                "TSP650II", "TSP700II", "TSP800II", "mC-Print2", "mC-Print3", "mPOP",
                "FVP10", "TSP143III", "TSP143IIIBI", "TSP143IIIU"
            ),
            vendorIds = listOf("0519"), // Star Micronics USB VID
            downloadUrl = "https://www.star-m.jp/products/s_print/sdk/android_ios_sdk.html",
            githubRepo = "https://github.com/star-micronics/StarIO10-StarXpandSDK-Android",
            licenseType = LicenseType.COMMERCIAL
        ),
        
        PrinterSdk(
            id = "bixolon-mobile",
            name = "Bixolon Mobile SDK",
            vendor = "Bixolon",
            version = "1.18.4",
            description = "Official Bixolon mobile printer SDK",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH),
            supportedModels = listOf(
                "SPP-R200III", "SPP-R310", "SPP-R410", "SPP-L3000", 
                "SRP-275III", "SRP-280", "SRP-350III", "SRP-380"
            ),
            vendorIds = listOf("1504"), // Bixolon USB VID
            downloadUrl = "https://www.bixolon.com/html/en/support/download_view.xhtml?seq=1737",
            licenseType = LicenseType.COMMERCIAL
        ),
        
        PrinterSdk(
            id = "citizen-sdk",
            name = "Citizen Printer SDK",
            vendor = "Citizen Systems",
            version = "1.4.2.0",
            description = "Official Citizen thermal printer SDK",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf(
                "CT-S310II", "CT-S651", "CT-S801", "CT-E351", "CT-E601", "CT-D150"
            ),
            vendorIds = listOf("1CB0"), // Citizen USB VID
            downloadUrl = "https://www.citizen-systems.co.jp/english/support/download/pos-printer/",
            licenseType = LicenseType.COMMERCIAL
        ),
        
        PrinterSdk(
            id = "xprinter-sdk",
            name = "XPrinter SDK",
            vendor = "XPrinter",
            version = "2.1.0",
            description = "Official XPrinter thermal printer SDK",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf(
                "XP-58IIH", "XP-80C", "XP-N160I", "XP-Q200I", "XP-Q800", "XP-T200M"
            ),
            vendorIds = listOf("0519", "04B8", "067B"), // Various VIDs used by XPrinter
            githubRepo = "https://github.com/xprinter/xprinter-sdk",
            licenseType = LicenseType.FREE
        ),
        
        PrinterSdk(
            id = "posmac-sdk",
            name = "PosMac SDK",
            vendor = "PosMac",
            version = "1.0.8",
            description = "PosMac printer SDK for Android",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH),
            supportedModels = listOf("PosMac P58", "PosMac P80", "PosMac T58", "PosMac T80"),
            licenseType = LicenseType.COMMERCIAL
        ),
        
        PrinterSdk(
            id = "sunmi-sdk",
            name = "Sunmi Printer SDK",
            vendor = "Sunmi",
            version = "1.0.22",
            description = "Official Sunmi device printer SDK",
            connectionTypes = listOf(ConnectionType.USB), // Built-in printers
            supportedModels = listOf("Sunmi T1", "Sunmi T2", "Sunmi V1s", "Sunmi V2", "Sunmi T2 mini"),
            downloadUrl = "https://docs.sunmi.com/general-function-modules/printing/",
            licenseType = LicenseType.COMMERCIAL
        ),
        
        // === CHINESE MANUFACTURER SDKs ===
        
        PrinterSdk(
            id = "gprinter-sdk",
            name = "GPrinter SDK",
            vendor = "Gprinter",
            version = "3.3.5",
            description = "Gprinter thermal printer SDK",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf("GP-L80250I", "GP-L80180I", "GP-U80300I", "GP-58MBIII"),
            vendorIds = listOf("0CB8"), // Gprinter USB VID
            licenseType = LicenseType.FREE
        ),
        
        PrinterSdk(
            id = "rongta-sdk",
            name = "Rongta Printer SDK",
            vendor = "Rongta",
            version = "2.6.9",
            description = "Rongta thermal printer SDK",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH),
            supportedModels = listOf("RPP02N", "RPP210", "RPP320", "RP58", "RP80"),
            vendorIds = listOf("0DD4"), // Rongta USB VID  
            licenseType = LicenseType.FREE
        ),
        
        PrinterSdk(
            id = "goojprt-sdk",
            name = "Goojprt SDK",
            vendor = "Goojprt",
            version = "1.2.0",
            description = "Goojprt mobile printer SDK",
            connectionTypes = listOf(ConnectionType.BLUETOOTH),
            supportedModels = listOf("QR203", "PT-210", "MTP-II", "MTP-III"),
            licenseType = LicenseType.FREE
        ),
        
        // === MULTI-VENDOR SUPPORT ===
        
        PrinterSdk(
            id = "generic-escpos",
            name = "Generic ESC/POS",
            vendor = "Generic",
            version = "1.0.0",
            description = "Generic ESC/POS command implementation for compatible printers",
            connectionTypes = listOf(ConnectionType.USB, ConnectionType.BLUETOOTH, ConnectionType.NETWORK),
            supportedModels = listOf("Any ESC/POS compatible printer"),
            licenseType = LicenseType.OPEN_SOURCE
        )
    )

    /**
     * Get SDKs by connection type
     */
    fun getSdksByConnectionType(type: ConnectionType): List<PrinterSdk> =
        getAllSdks().filter { it.connectionTypes.contains(type) }

    /**
     * Get SDKs by vendor
     */
    fun getSdksByVendor(vendor: String): List<PrinterSdk> =
        getAllSdks().filter { it.vendor.equals(vendor, ignoreCase = true) }

    /**
     * Find compatible SDKs by USB Vendor/Product ID
     */
    fun findSdksByUsbIds(vendorId: String, productId: String): List<PrinterSdk> =
        getAllSdks().filter { sdk ->
            sdk.vendorIds.contains(vendorId.uppercase()) || 
            sdk.productIds.contains(productId.uppercase())
        }

    /**
     * Find SDKs by printer model name
     */
    fun findSdksByModel(modelName: String): List<PrinterSdk> =
        getAllSdks().filter { sdk ->
            sdk.supportedModels.any { model ->
                model.contains(modelName, ignoreCase = true) ||
                modelName.contains(model, ignoreCase = true)
            }
        }

    /**
     * Get open source SDKs only
     */
    fun getOpenSourceSdks(): List<PrinterSdk> =
        getAllSdks().filter { it.licenseType == LicenseType.OPEN_SOURCE }

    /**
     * Get vendor official SDKs only
     */
    fun getOfficialSdks(): List<PrinterSdk> =
        getAllSdks().filter { it.licenseType == LicenseType.COMMERCIAL }
}