package com.extrotarget.extropos.printer.domain.service

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog
import com.extrotarget.extropos.printer.domain.model.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Log

/**
 * Service for detecting printers across different connection types
 */
@Singleton
class PrinterDetectionService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sdkCatalog: PrinterSdkCatalog
) {

    /**
     * Scan for USB printers
     */
    fun scanUsbPrinters(): Flow<List<DetectedPrinter>> = flow {
        val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val detectedPrinters = mutableListOf<DetectedPrinter>()

        usbManager.deviceList.values.forEach { device ->
            val vendorId = String.format("%04X", device.vendorId)
            val productId = String.format("%04X", device.productId)
            
            Log.d("PrinterDetection", "USB Device: ${device.deviceName}, VID: $vendorId, PID: $productId")

            // Find compatible SDKs for this device
            val compatibleSdks = sdkCatalog.findSdksByUsbIds(vendorId, productId)
            
            if (compatibleSdks.isNotEmpty() || isPotentialPrinter(device)) {
                val detectedPrinter = DetectedPrinter(
                    name = getUsbDeviceName(device),
                    connectionType = ConnectionType.USB,
                    address = "usb_${device.deviceId}",
                    vendorId = device.vendorId,
                    productId = device.productId,
                    compatibleSdks = compatibleSdks.ifEmpty { 
                        listOf(sdkCatalog.getAllSdks().first { it.id == "generic-escpos" })
                    }
                )
                detectedPrinters.add(detectedPrinter)
            }
        }

        emit(detectedPrinters)
    }

    /**
     * Scan for Bluetooth printers
     */
    fun scanBluetoothPrinters(): Flow<List<DetectedPrinter>> = flow {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        val detectedPrinters = mutableListOf<DetectedPrinter>()

        if (bluetoothAdapter?.isEnabled == true) {
            // Get paired devices first
            bluetoothAdapter.bondedDevices?.forEach { device ->
                if (isPotentialBluetoothPrinter(device)) {
                    val compatibleSdks = findBluetoothCompatibleSdks(device)
                    
                    val detectedPrinter = DetectedPrinter(
                        name = device.name ?: "Unknown Bluetooth Printer",
                        connectionType = ConnectionType.BLUETOOTH,
                        address = device.address,
                        compatibleSdks = compatibleSdks
                    )
                    detectedPrinters.add(detectedPrinter)
                }
            }
        }

        emit(detectedPrinters)
    }

    /**
     * Create network printer configuration
     */
    fun createNetworkPrinter(
        name: String,
        ipAddress: String,
        port: Int = 9100,
        selectedSdkId: String? = null
    ): DetectedPrinter {
        val networkSdks = sdkCatalog.getSdksByConnectionType(ConnectionType.NETWORK)
        val selectedSdk = selectedSdkId?.let { id ->
            networkSdks.firstOrNull { it.id == id }
        } ?: networkSdks.firstOrNull { it.id == "generic-escpos" }

        return DetectedPrinter(
            name = name.ifBlank { "Network Printer $ipAddress" },
            connectionType = ConnectionType.NETWORK,
            address = ipAddress,
            port = port,
            compatibleSdks = networkSdks
        )
    }

    /**
     * Get all available SDKs for manual selection
     */
    fun getAllAvailableSdks(): List<PrinterSdk> = sdkCatalog.getAllSdks()

    /**
     * Get SDKs filtered by connection type
     */
    fun getSdksByConnectionType(type: ConnectionType): List<PrinterSdk> = 
        sdkCatalog.getSdksByConnectionType(type)

    private fun getUsbDeviceName(device: UsbDevice): String {
        val manufacturer = device.manufacturerName
        val product = device.productName
        
        return when {
            manufacturer != null && product != null -> "$manufacturer $product"
            product != null -> product
            manufacturer != null -> "$manufacturer Printer"
            else -> "USB Printer (${String.format("%04X:%04X", device.vendorId, device.productId)})"
        }
    }

    private fun isPotentialPrinter(device: UsbDevice): Boolean {
        // Check USB device class for printer class (7)
        if (device.deviceClass == 7) return true
        
        // Check interface classes
        for (i in 0 until device.interfaceCount) {
            val usbInterface = device.getInterface(i)
            if (usbInterface.interfaceClass == 7) return true // Printer class
        }
        
        // Check common printer vendor IDs
        val printerVendorIds = setOf(
            0x04B8, // Epson
            0x0519, // Star Micronics  
            0x1504, // Bixolon
            0x1CB0, // Citizen
            0x0CB8, // Gprinter
            0x0DD4, // Rongta
            0x067B, // Prolific (used by many Chinese manufacturers)
            0x1A86, // QinHeng Electronics (CH340 USB-to-serial, common in printers)
            0x0403, // FTDI (USB-to-serial, common in printers)
        )
        
        return printerVendorIds.contains(device.vendorId)
    }

    private fun isPotentialBluetoothPrinter(device: BluetoothDevice): Boolean {
        val deviceName = device.name?.lowercase() ?: ""
        
        // Check for common printer names/patterns
        val printerPatterns = listOf(
            "printer", "thermal", "receipt", "pos", "escpos",
            "epson", "star", "bixolon", "citizen", "xprinter",
            "rongta", "gprinter", "goojprt", "posmac", "sunmi",
            "rpp", "tsp", "tmp", "ct-", "spp-", "gp-"
        )
        
        return printerPatterns.any { pattern ->
            deviceName.contains(pattern)
        }
    }

    private fun findBluetoothCompatibleSdks(device: BluetoothDevice): List<PrinterSdk> {
        val deviceName = device.name?.lowercase() ?: ""
        val bluetoothSdks = sdkCatalog.getSdksByConnectionType(ConnectionType.BLUETOOTH)
        
        // Find SDKs by device name patterns
        val compatibleSdks = bluetoothSdks.filter { sdk ->
            when (sdk.vendor.lowercase()) {
                "epson" -> deviceName.contains("epson") || deviceName.contains("tm-")
                "star micronics" -> deviceName.contains("star") || deviceName.contains("tsp")
                "bixolon" -> deviceName.contains("bixolon") || deviceName.contains("spp-") || deviceName.contains("srp-")
                "citizen systems" -> deviceName.contains("citizen") || deviceName.contains("ct-")
                "xprinter" -> deviceName.contains("xprinter") || deviceName.contains("xp-")
                "rongta" -> deviceName.contains("rongta") || deviceName.contains("rpp")
                "gprinter" -> deviceName.contains("gprinter") || deviceName.contains("gp-")
                else -> false
            }
        }
        
        return compatibleSdks.ifEmpty {
            listOf(sdkCatalog.getAllSdks().first { it.id == "generic-escpos" })
        }
    }
}