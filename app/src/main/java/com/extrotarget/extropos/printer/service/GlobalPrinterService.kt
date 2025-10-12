package com.extrotarget.extropos.printer.service

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalPrinterService @Inject constructor() {
    
    private var connectedSocket: BluetoothSocket? = null
    private var connectedDevice: BluetoothDevice? = null
    private val connectionListeners = mutableSetOf<ConnectionListener>()
    
    companion object {
        private const val TAG = "GlobalPrinterService"
    }
    
    interface ConnectionListener {
        fun onConnectionStatusChanged(isConnected: Boolean, deviceName: String?)
    }
    
    fun addConnectionListener(listener: ConnectionListener) {
        connectionListeners.add(listener)
    }
    
    fun removeConnectionListener(listener: ConnectionListener) {
        connectionListeners.remove(listener)
    }
    
    private fun notifyConnectionListeners(isConnected: Boolean) {
        connectionListeners.forEach { listener ->
            try {
                listener.onConnectionStatusChanged(isConnected, connectedDevice?.name)
            } catch (e: Exception) {
                Log.e(TAG, "Error notifying listener: ${e.message}")
            }
        }
    }
    
    fun isConnected(): Boolean {
        return connectedSocket?.isConnected == true
    }
    
    fun getConnectedDeviceName(): String? {
        return connectedDevice?.name
    }
    
    suspend fun connectToPrinter(device: BluetoothDevice): ConnectionResult {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(TAG, "Connecting to printer: ${device.name}")
                
                // Disconnect existing connection
                disconnect()
                
                // Try multiple connection strategies
                val strategies = listOf(
                    ConnectionStrategy("Insecure SPP", "00001101-0000-1000-8000-00805F9B34FB", true),
                    ConnectionStrategy("Standard SPP", "00001101-0000-1000-8000-00805F9B34FB", false),
                    ConnectionStrategy("Reflection Method", "", true)
                )
                
                withTimeout(15000) {
                    for (strategy in strategies) {
                        Log.d(TAG, "Trying ${strategy.name}")
                        
                        val socket = createSocket(device, strategy)
                        if (socket != null) {
                            try {
                                socket.connect()
                                if (socket.isConnected) {
                                    connectedSocket = socket
                                    connectedDevice = device
                                    Log.d(TAG, "Successfully connected with ${strategy.name}")
                                    notifyConnectionListeners(true)
                                    return@withTimeout ConnectionResult(true, null)
                                }
                            } catch (e: Exception) {
                                Log.d(TAG, "${strategy.name} failed: ${e.message}")
                                try { socket.close() } catch (ignored: Exception) {}
                            }
                        }
                    }
                    ConnectionResult(false, "All connection strategies failed")
                }
            } catch (e: TimeoutCancellationException) {
                ConnectionResult(false, "Connection timeout")
            } catch (e: Exception) {
                Log.e(TAG, "Connection failed", e)
                ConnectionResult(false, e.message ?: "Connection failed")
            }
        }
    }
    
    private fun createSocket(device: BluetoothDevice, strategy: ConnectionStrategy): BluetoothSocket? {
        return try {
            when (strategy.name) {
                "Reflection Method" -> {
                    val method = device.javaClass.getMethod("createRfcommSocket", Int::class.java)
                    method.invoke(device, 1) as BluetoothSocket
                }
                else -> {
                    val uuid = java.util.UUID.fromString(strategy.uuid)
                    if (strategy.isInsecure) {
                        device.createInsecureRfcommSocketToServiceRecord(uuid)
                    } else {
                        device.createRfcommSocketToServiceRecord(uuid)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to create socket: ${e.message}")
            null
        }
    }
    
    suspend fun printReceipt(content: String): ConnectionResult {
        return withContext(Dispatchers.IO) {
            try {
                val socket = connectedSocket
                if (socket == null || !socket.isConnected) {
                    return@withContext ConnectionResult(false, "No active printer connection")
                }
                
                val outputStream = socket.outputStream
                
                // Convert content to ESC/POS commands
                val commands = mutableListOf<Byte>()
                
                // Initialize printer
                commands.addAll(byteArrayOf(0x1B, 0x40).toList()) // ESC @
                
                // Set character encoding (UTF-8 compatible)
                commands.addAll(byteArrayOf(0x1B, 0x74, 0x10).toList()) // ESC t 16
                
                // Print content
                commands.addAll(content.toByteArray(Charsets.UTF_8).toList())
                
                // Add line feeds and cut
                commands.addAll(byteArrayOf(0x0A, 0x0A, 0x0A).toList()) // 3 line feeds
                commands.addAll(byteArrayOf(0x1D, 0x56, 0x41, 0x10).toList()) // Partial cut
                
                outputStream.write(commands.toByteArray())
                outputStream.flush()
                
                Log.d(TAG, "Receipt printed successfully")
                ConnectionResult(true, null)
                
            } catch (e: Exception) {
                Log.e(TAG, "Print failed: ${e.message}", e)
                ConnectionResult(false, e.message ?: "Print failed")
            }
        }
    }
    
    suspend fun printTestReceipt(): ConnectionResult {
        val testContent = """
            ================================
                    ANDROPOS TEST
            ================================
            Date: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(java.util.Date())}
            
            This is a test receipt from your
            AndroPOS system.
            
            Printer: ${connectedDevice?.name ?: "Unknown"}
            Status: Connected & Working
            
            ================================
                    TEST SUCCESSFUL
            ================================
            
        """.trimIndent()
        
        return printReceipt(testContent)
    }

    suspend fun printCustomizedReceipt(receiptSettings: com.extrotarget.extropos.data.model.ReceiptSettings, receiptData: Map<String, Any>): Boolean {
        if (!isConnected()) {
            Log.w(TAG, "Printer not connected")
            return false
        }

        val receiptContent = generateCustomizedReceiptContent(receiptSettings, receiptData)
        val result = printReceipt(receiptContent)
        
        // Print duplicate if enabled
        if (result.isSuccess && receiptSettings.duplicateReceipt) {
            delay(1000) // Short delay between prints
            printReceipt(receiptContent)
        }
        
        return result.isSuccess
    }

    private fun generateCustomizedReceiptContent(receiptSettings: com.extrotarget.extropos.data.model.ReceiptSettings, receiptData: Map<String, Any>): String {
        val sb = StringBuilder()
        
        // ESC/POS initialization
        sb.append("\u001B@") // Initialize printer
        sb.append(receiptSettings.getFontWidthCommand()) // Set print area width
        
        // Header with store info
        sb.append(receiptSettings.getCenterAlignCommand())
        if (receiptSettings.showLogo) {
            sb.append("[STORE LOGO]\n")
        }
        
        sb.append("${receiptSettings.storeName}\n")
        sb.append("${receiptSettings.formatTextForWidth(receiptSettings.storeAddress)}\n")
        sb.append("Tel: ${receiptSettings.phoneNumber}\n\n")
        
        // Receipt details
        sb.append(receiptSettings.getLeftAlignCommand())
        sb.append("${receiptSettings.getSeparatorLine()}\n")
        sb.append("Receipt #: ${receiptData["receiptNumber"] ?: "N/A"}\n")
        
        val dateFormat = java.text.SimpleDateFormat(receiptSettings.dateFormat, java.util.Locale.getDefault())
        sb.append("Date: ${dateFormat.format(java.util.Date())}\n")
        sb.append("Cashier: ${receiptData["cashier"] ?: "N/A"}\n")
        sb.append("${receiptSettings.getSeparatorLine()}\n\n")
        
        // Items
        sb.append("ITEMS:\n")
        val items = receiptData["items"] as? List<Map<String, Any>> ?: emptyList()
        for (item in items) {
            val name = item["name"] as? String ?: "Unknown"
            val quantity = item["quantity"] as? Int ?: 1
            val price = item["price"] as? Int ?: 0
            val priceFormatted = String.format("%.2f", price / 100.0)
            
            val itemLine = "${name} x${quantity}".padEnd(receiptSettings.charactersPerLine - 8) + 
                          "${receiptSettings.currency}${priceFormatted}".padStart(8)
            sb.append("${itemLine}\n")
        }
        sb.append("${receiptSettings.getSeparatorLine()}\n")
        
        // Totals
        val subtotal = receiptData["subtotal"] as? Int ?: 0
        val tax = receiptData["tax"] as? Int ?: 0
        val total = receiptData["total"] as? Int ?: 0
        val cash = receiptData["cash"] as? Int ?: 0
        val change = receiptData["change"] as? Int ?: 0
        
        sb.append("Subtotal:".padEnd(receiptSettings.charactersPerLine - 8) + 
                  "${receiptSettings.currency}${String.format("%.2f", subtotal / 100.0)}".padStart(8) + "\n")
        
        if (receiptSettings.showTaxBreakdown && tax > 0) {
            sb.append("SST (6%):".padEnd(receiptSettings.charactersPerLine - 8) + 
                      "${receiptSettings.currency}${String.format("%.2f", tax / 100.0)}".padStart(8) + "\n")
        }
        
        sb.append("TOTAL:".padEnd(receiptSettings.charactersPerLine - 8) + 
                  "${receiptSettings.currency}${String.format("%.2f", total / 100.0)}".padStart(8) + "\n")
        sb.append("Cash:".padEnd(receiptSettings.charactersPerLine - 8) + 
                  "${receiptSettings.currency}${String.format("%.2f", cash / 100.0)}".padStart(8) + "\n")
        sb.append("Change:".padEnd(receiptSettings.charactersPerLine - 8) + 
                  "${receiptSettings.currency}${String.format("%.2f", change / 100.0)}".padStart(8) + "\n")
        sb.append("${receiptSettings.getSeparatorLine()}\n\n")
        
        // Footer
        sb.append(receiptSettings.getCenterAlignCommand())
        
        if (receiptSettings.showQrCode) {
            sb.append("[QR CODE]\n")
            sb.append("Scan for feedback\n\n")
        }
        
        sb.append("${receiptSettings.formatTextForWidth(receiptSettings.footerMessage)}\n\n")
        
        // Paper cut if enabled
        if (receiptSettings.autoCut) {
            sb.append("\u001D\u0056\u0042\u0000") // Full cut command
        } else {
            sb.append("\n\n\n") // Extra spacing for manual cut
        }
        
        return sb.toString()
    }
    
    fun disconnect() {
        try {
            connectedSocket?.close()
            connectedSocket = null
            val wasConnected = connectedDevice != null
            connectedDevice = null
            
            if (wasConnected) {
                notifyConnectionListeners(false)
            }
            Log.d(TAG, "Printer disconnected")
        } catch (e: Exception) {
            Log.e(TAG, "Error during disconnect: ${e.message}")
        }
    }
    
    data class ConnectionResult(
        val isSuccess: Boolean,
        val error: String?
    )
    
    private data class ConnectionStrategy(
        val name: String,
        val uuid: String,
        val isInsecure: Boolean
    )
}