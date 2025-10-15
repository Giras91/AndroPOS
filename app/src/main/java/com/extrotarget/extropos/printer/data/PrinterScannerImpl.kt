package com.extrotarget.extropos.printer.data

import android.content.Context
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.bluetooth.BluetoothAdapter
import com.extrotarget.extropos.ui.settings.printer.DiscoveredPrinter
import com.extrotarget.extropos.ui.settings.printer.ConnectionType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrinterScannerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : IPrinterScanner {

    override suspend fun scanBluetooth(): List<DiscoveredPrinter> = withContext(Dispatchers.IO) {
        val adapter = BluetoothAdapter.getDefaultAdapter() ?: return@withContext emptyList()
        if (!adapter.isEnabled) return@withContext emptyList()

        val paired = adapter.bondedDevices ?: emptySet()
        paired.map { device ->
            DiscoveredPrinter(
                name = device.name ?: "Bluetooth Printer",
                address = device.address ?: "",
                connectionType = ConnectionType.BLUETOOTH,
                manufacturer = device.name
            )
        }
    }

    override suspend fun scanUsb(): List<DiscoveredPrinter> = withContext(Dispatchers.IO) {
        val usbManager = context.getSystemService(Context.USB_SERVICE) as? UsbManager
            ?: return@withContext emptyList()

        val list = mutableListOf<DiscoveredPrinter>()
        val deviceList: HashMap<String, UsbDevice> = usbManager.deviceList
        for ((_, device) in deviceList) {
            val name = device.productName ?: "USB Printer"
            // Build a pseudo address using vendor/product ids
            val address = "usb:${device.vendorId}:${device.productId}"
            list.add(
                DiscoveredPrinter(
                    name = name,
                    address = address,
                    connectionType = ConnectionType.USB,
                    manufacturer = device.manufacturerName
                )
            )
        }

        // Also check common device paths on Linux/Android file system
        val commonPaths = listOf(
            "/dev/usb/lp0", "/dev/usb/lp1", "/dev/usb/lp2",
            "/dev/ttyUSB0", "/dev/ttyUSB1", "/dev/ttyUSB2"
        )
        for (path in commonPaths) {
            val f = java.io.File(path)
            if (f.exists()) {
                list.add(
                    DiscoveredPrinter(
                        name = "USB Device",
                        address = path,
                        connectionType = ConnectionType.USB,
                        manufacturer = null
                    )
                )
            }
        }

        list
    }

    override suspend fun scanNetwork(): List<DiscoveredPrinter> = withContext(Dispatchers.IO) {
        // Network discovery can be implemented with mDNS or targeted port scans.
        // For now return empty list; can be implemented later.
        emptyList()
    }
}

