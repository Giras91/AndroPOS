package com.extrotarget.extropos.printer.adapter.impl

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.hardware.usb.UsbManager
import com.dantsu.escposprinter.EscPosPrinter
import com.dantsu.escposprinter.connection.bluetooth.BluetoothConnection
import com.dantsu.escposprinter.connection.tcp.TcpConnection
import com.dantsu.escposprinter.connection.usb.UsbConnection
import com.dantsu.escposprinter.connection.DeviceConnection
import com.dantsu.escposprinter.textparser.PrinterTextParserImg
import com.extrotarget.extropos.printer.adapter.PrinterAdapter
import com.extrotarget.extropos.printer.adapter.PrinterFeature
import com.extrotarget.extropos.printer.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetSocketAddress
import java.net.Socket

/**
 * DantSu ESC/POS printer adapter implementation
 * Uses the popular DantSu/ESCPOS-ThermalPrinter-Android library
 */
class DantSuEscPosAdapter(
    private val context: Context
) : PrinterAdapter {

    private var connection: DeviceConnection? = null
    private var printer: EscPosPrinter? = null
    private var isConnected = false
    private var currentConfig: PrinterConfig? = null

    override suspend fun connect(config: PrinterConfig): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                currentConfig = config
                
                // Create appropriate connection based on type
                connection = when (config.connectionType) {
                    ConnectionType.BLUETOOTH -> createBluetoothConnection(config.address)
                    ConnectionType.USB -> createUsbConnection(config.address)
                    ConnectionType.NETWORK -> createNetworkConnection(config.address, config.port ?: 9100)
                }
                
                connection?.let { conn ->
                    // Create printer instance with connection
                    printer = EscPosPrinter(conn, 203, 48f, 32)
                    isConnected = true
                    true
                } ?: false
                
            } catch (e: Exception) {
                disconnect()
                false
            }
        }
    }

    override suspend fun disconnect() {
        withContext(Dispatchers.IO) {
            try {
                connection?.disconnect()
            } catch (e: Exception) {
                // Ignore disconnection errors
            } finally {
                connection = null
                printer = null
                isConnected = false
                currentConfig = null
            }
        }
    }

    override suspend fun print(job: PrintJob): PrintResult {
        return withContext(Dispatchers.IO) {
            val currentPrinter = printer
            if (!isConnected || currentPrinter == null) {
                return@withContext PrintResult.failure("Printer not connected")
            }

            try {
                // Build ESC/POS text content
                val textContent = buildEscPosText(job)
                
                // Print using DantSu library
                currentPrinter.printFormattedTextAndCut(textContent)
                
                PrintResult.success("Print completed successfully")
                
            } catch (e: Exception) {
                PrintResult.failure("Print failed: ${e.message}")
            }
        }
    }

    override suspend fun testConnection(): Boolean {
        return withContext(Dispatchers.IO) {
            if (!isConnected || printer == null) return@withContext false
            
            try {
                // Send a simple test print
                printer?.printFormattedText("[L]<b>CONNECTION TEST</b>[/L]\n")
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    override suspend fun isConnected(): Boolean = isConnected

    override suspend fun getStatus(): String {
        return when {
            connection == null -> "Not connected"
            isConnected -> "Connected and ready"
            else -> "Connection failed"
        }
    }

    override suspend fun getSupportedFeatures(): List<PrinterFeature> {
        return listOf(
            PrinterFeature.TEXT_PRINTING,
            PrinterFeature.BARCODE_1D, 
            PrinterFeature.BARCODE_2D_QR,
            PrinterFeature.IMAGE_PRINTING,
            PrinterFeature.PAPER_CUTTING,
            PrinterFeature.BLUETOOTH_SUPPORT,
            PrinterFeature.USB_SUPPORT,
            PrinterFeature.NETWORK_SUPPORT
        )
    }

    // Private helper methods
    
    private fun createBluetoothConnection(macAddress: String): DeviceConnection? {
        return try {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled) {
                return null
            }
            
            val device = bluetoothAdapter.getRemoteDevice(macAddress)
            BluetoothConnection(device)
        } catch (e: Exception) {
            null
        }
    }

    private fun createUsbConnection(devicePath: String): DeviceConnection? {
        return try {
            // For USB, we need to find the USB device by path or other identifier
            // This is a simplified implementation - in practice you'd enumerate USB devices
            val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
            UsbConnection(usbManager, null) // Will be enhanced when we have actual USB device
        } catch (e: Exception) {
            null
        }
    }

    private fun createNetworkConnection(ipAddress: String, port: Int): DeviceConnection? {
        return try {
            // Test if we can reach the printer first
            val socket = Socket()
            socket.connect(InetSocketAddress(ipAddress, port), 5000) // 5 second timeout
            socket.close()
            
            // Create TCP connection using DantSu library
            TcpConnection(ipAddress, port)
        } catch (e: Exception) {
            null
        }
    }

    private fun buildEscPosText(job: PrintJob): String {
        val escPosBuilder = StringBuilder()
        
        job.content.forEach { item ->
            when (item.type) {
                "text" -> {
                    val alignment = item.formatting["alignment"] as? String ?: "left"
                    val bold = item.formatting["bold"] as? Boolean ?: false
                    val size = item.formatting["size"] as? String ?: "normal"
                    
                    // Apply formatting
                    when (alignment.lowercase()) {
                        "center" -> escPosBuilder.append("[C]")
                        "right" -> escPosBuilder.append("[R]")
                        else -> escPosBuilder.append("[L]")
                    }
                    
                    if (bold) escPosBuilder.append("<b>")
                    
                    when (size.lowercase()) {
                        "large" -> escPosBuilder.append("<font size='big'>")
                        "small" -> escPosBuilder.append("<font size='small'>")
                    }
                    
                    escPosBuilder.append(item.data)
                    
                    if (size.lowercase() != "normal") escPosBuilder.append("</font>")
                    if (bold) escPosBuilder.append("</b>")
                    
                    escPosBuilder.append("\n")
                }
                
                "barcode" -> {
                    escPosBuilder.append("[C]<barcode type='128' height='10'>${item.data}</barcode>\n")
                }
                
                "qr" -> {
                    escPosBuilder.append("[C]<qrcode size='20'>${item.data}</qrcode>\n")
                }
                
                "line" -> {
                    escPosBuilder.append("[L]").append("-".repeat(32)).append("\n")
                }
                
                "cut" -> {
                    // Cut command will be handled by printFormattedTextAndCut()
                }
                
                else -> {
                    // Unknown type, print as text
                    escPosBuilder.append("[L]${item.data}\n")
                }
            }
        }
        
        return escPosBuilder.toString()
    }
}