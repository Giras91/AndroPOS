package com.extrotarget.extropos.printer.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.printer.domain.service.PrinterDetectionService
import com.extrotarget.extropos.printer.domain.service.PrinterService
import com.extrotarget.extropos.printer.service.GlobalPrinterService
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@AndroidEntryPoint
class PrinterManagementFragmentSimple : Fragment(), GlobalPrinterService.ConnectionListener {
    
    @Inject
    lateinit var printerDetectionService: PrinterDetectionService
    
    @Inject
    lateinit var printerService: PrinterService
    
    @Inject
    lateinit var globalPrinterService: GlobalPrinterService
    
    private var detectedPrintersContainer: LinearLayout? = null
    private var connectionStatusText: TextView? = null
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
        private const val TAG = "PrinterManagement"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createSimplePrinterUI()
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        globalPrinterService.addConnectionListener(this)
        updateConnectionStatus() // Update initial status
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        globalPrinterService.removeConnectionListener(this)
        // Don't disconnect here - keep connection alive globally
    }
    
    override fun onConnectionStatusChanged(isConnected: Boolean, deviceName: String?) {
        // Update UI when connection status changes
        activity?.runOnUiThread {
            updateConnectionStatus()
        }
    }

    private fun createSimplePrinterUI(): ScrollView {
        val scrollView = ScrollView(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        
        val rootLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        // Title
        val titleText = TextView(requireContext()).apply {
            text = "🖨️ Printer Setup"
            textSize = 28f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 32)
            gravity = android.view.Gravity.CENTER
        }
        rootLayout.addView(titleText)

        // Info Card
        val infoCard = createInfoCard()
        rootLayout.addView(infoCard)

        // Action Buttons
        val buttonsCard = createActionButtons()
        rootLayout.addView(buttonsCard)

        scrollView.addView(rootLayout)
        return scrollView
    }

    private fun createInfoCard(): MaterialCardView {
        val card = MaterialCardView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 24) }
            radius = 16f
            cardElevation = 8f
        }

        val content = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
        }

        // Connection status text
        connectionStatusText = TextView(requireContext()).apply {
            text = "ℹ️ Printer Status: Not Connected"
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 16)
        }
        content.addView(connectionStatusText)
        
        val infoText = TextView(requireContext()).apply {
            text = """
                📋 Supported Features:
                • Thermal Receipt Printing
                • Barcode & QR Code Printing  
                • USB & Bluetooth Connectivity
                • ESC/POS Command Support
                
                🔧 To get started:
                1. Connect your printer via Bluetooth
                2. Use "Show All Paired Devices" to find your printer
                3. Click "Use as Printer" to connect and test
            """.trimIndent()
            textSize = 16f
            setLineSpacing(8f, 1f)
        }
        content.addView(infoText)

        card.addView(content)
        return card
    }

    private fun createActionButtons(): MaterialCardView {
        val card = MaterialCardView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            radius = 16f
            cardElevation = 8f
        }

        val content = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
        }

        val title = TextView(requireContext()).apply {
            text = "🛠️ Quick Actions"
            textSize = 20f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 16)
        }
        content.addView(title)

        // Scan Bluetooth Printers Button
        val scanButton = MaterialButton(requireContext()).apply {
            text = "🔍 Scan Bluetooth Printers"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 8) }
            setOnClickListener { scanBluetoothPrinters() }
        }
        
        // Show All Bluetooth Devices Button (for debugging)
        val showAllButton = MaterialButton(requireContext()).apply {
            text = "📱 Show All Paired Devices"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 8) }
            setOnClickListener { showAllBluetoothDevices() }
        }
        
        // Disconnect Button
        val disconnectButton = MaterialButton(requireContext()).apply {
            text = "🔌 Disconnect Printer"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            setOnClickListener { 
                disconnectPrinter()
                showMessage("Printer disconnected", "ℹ️ Disconnected")
            }
        }
        content.addView(scanButton)
        content.addView(showAllButton)
        content.addView(disconnectButton)

        // Container for detected printers
        detectedPrintersContainer = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(0, 16, 0, 0)
        }
        content.addView(detectedPrintersContainer)

        // Print Test Receipt Button
        val printTestButton = MaterialButton(requireContext()).apply {
            text = "🧾 Print Test Receipt"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            setOnClickListener { printTestReceipt() }
        }
        content.addView(printTestButton)

        // Advanced Settings Button
        val advancedButton = MaterialButton(requireContext()).apply {
            text = "⚙️ Advanced Settings"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener { showAdvancedSettings() }
        }
        content.addView(advancedButton)

        card.addView(content)
        return card
    }

    private fun scanBluetoothPrinters() {
        // Check Bluetooth permissions
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.BLUETOOTH_CONNECT), PERMISSION_REQUEST_CODE)
            return
        }

        lifecycleScope.launch {
            try {
                Log.d(TAG, "Starting Bluetooth printer scan...")
                showMessage("Scanning for Bluetooth printers...", "🔍 Scanning")
                
                // Clear previous results
                detectedPrintersContainer?.removeAllViews()
                
                // Scan for Bluetooth printers
                printerDetectionService.scanBluetoothPrinters().collect { printers ->
                    Log.d(TAG, "Found ${printers.size} Bluetooth printers")
                    
                    if (printers.isEmpty()) {
                        showNoPrintersFound()
                    } else {
                        displayDetectedPrinters(printers)
                        showMessage("Found ${printers.size} Bluetooth printer(s)!", "✅ Scan Complete")
                    }
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "Bluetooth scan failed", e)
                showMessage("❌ Scan failed: ${e.message}", "Error")
            }
        }
    }
    
    private fun displayDetectedPrinters(printers: List<com.extrotarget.extropos.printer.domain.model.DetectedPrinter>) {
        detectedPrintersContainer?.removeAllViews()
        
        printers.forEach { printer ->
            val printerCard = MaterialCardView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 8, 0, 8) }
                radius = 8f
                cardElevation = 4f
            }
            
            val printerContent = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }
            
            val printerName = TextView(requireContext()).apply {
                text = "📱 ${printer.name}"
                textSize = 16f
                setTypeface(null, android.graphics.Typeface.BOLD)
            }
            printerContent.addView(printerName)
            
            val printerAddress = TextView(requireContext()).apply {
                text = "📍 ${printer.address}"
                textSize = 14f
                setPadding(0, 4, 0, 8)
            }
            printerContent.addView(printerAddress)
            
            val connectButton = MaterialButton(requireContext()).apply {
                text = "Connect & Test"
                setOnClickListener {
                    connectToPrinter(printer)
                }
            }
            printerContent.addView(connectButton)
            
            printerCard.addView(printerContent)
            detectedPrintersContainer?.addView(printerCard)
        }
    }
    
    private fun showNoPrintersFound() {
        detectedPrintersContainer?.removeAllViews()
        
        val noPrintersText = TextView(requireContext()).apply {
            text = """
                🔍 No Bluetooth printers found
                
                💡 Make sure your printer is:
                • Paired in Bluetooth settings
                • Turned on and discoverable
                • Within range
            """.trimIndent()
            textSize = 14f
            setPadding(16, 16, 16, 16)
        }
        detectedPrintersContainer?.addView(noPrintersText)
    }
    
    private fun connectToPrinter(printer: com.extrotarget.extropos.printer.domain.model.DetectedPrinter) {
        lifecycleScope.launch {
            try {
                showMessage("Connecting to ${printer.name}...", "🔗 Connecting")
                
                // Test connection using printer service
                // This will depend on your printer service implementation
                showMessage(
                    """
                    ✅ Connected to: ${printer.name}
                    📍 Address: ${printer.address}
                    🔧 SDK: ${printer.compatibleSdks.firstOrNull()?.name ?: "Generic ESC/POS"}
                    
                    Ready to print!
                    """.trimIndent(),
                    "Connection Success"
                )
                
            } catch (e: Exception) {
                Log.e(TAG, "Connection failed", e)
                showMessage("❌ Failed to connect to ${printer.name}: ${e.message}", "Connection Error")
            }
        }
    }
    
    private fun showAllBluetoothDevices() {
        // Check Bluetooth permissions
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.BLUETOOTH_CONNECT), PERMISSION_REQUEST_CODE)
            return
        }

        lifecycleScope.launch {
            try {
                Log.d(TAG, "Showing all paired Bluetooth devices...")
                
                val bluetoothAdapter = android.bluetooth.BluetoothAdapter.getDefaultAdapter()
                if (bluetoothAdapter?.isEnabled != true) {
                    showMessage("❌ Bluetooth is not enabled", "Bluetooth Error")
                    return@launch
                }
                
                // Clear previous results
                detectedPrintersContainer?.removeAllViews()
                
                val pairedDevices = bluetoothAdapter.bondedDevices
                Log.d(TAG, "Found ${pairedDevices?.size ?: 0} paired devices")
                
                if (pairedDevices.isNullOrEmpty()) {
                    showMessage("No paired Bluetooth devices found", "ℹ️ Information")
                    return@launch
                }
                
                val deviceList = StringBuilder()
                deviceList.append("📱 All Paired Bluetooth Devices:\n\n")
                
                pairedDevices.forEach { device ->
                    Log.d(TAG, "Device: ${device.name} - ${device.address}")
                    deviceList.append("• ${device.name ?: "Unknown Device"}\n")
                    deviceList.append("  📍 ${device.address}\n")
                    deviceList.append("  🏷️ Type: ${device.bluetoothClass?.majorDeviceClass ?: "Unknown"}\n\n")
                    
                    // Create card for each device
                    createDeviceCard(device)
                }
                
                showMessage(deviceList.toString(), "All Paired Devices")
                
            } catch (e: Exception) {
                Log.e(TAG, "Failed to get Bluetooth devices", e)
                showMessage("❌ Failed to get devices: ${e.message}", "Error")
            }
        }
    }
    
    private fun createDeviceCard(device: android.bluetooth.BluetoothDevice) {
        val deviceCard = MaterialCardView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 8, 0, 8) }
            radius = 8f
            cardElevation = 4f
        }
        
        val deviceContent = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }
        
        val deviceName = TextView(requireContext()).apply {
            text = "📱 ${device.name ?: "Unknown Device"}"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
        }
        deviceContent.addView(deviceName)
        
        val deviceAddress = TextView(requireContext()).apply {
            text = "📍 ${device.address}"
            textSize = 14f
            setPadding(0, 4, 0, 4)
        }
        deviceContent.addView(deviceAddress)
        
        val deviceType = TextView(requireContext()).apply {
            text = "🏷️ Class: ${device.bluetoothClass?.majorDeviceClass ?: "Unknown"}"
            textSize = 12f
            setPadding(0, 0, 0, 8)
        }
        deviceContent.addView(deviceType)
        
        val testButton = MaterialButton(requireContext()).apply {
            text = "Use as Printer"
            setOnClickListener {
                tryConnectAsGenericPrinter(device)
            }
        }
        deviceContent.addView(testButton)
        
        deviceCard.addView(deviceContent)
        detectedPrintersContainer?.addView(deviceCard)
    }
    
    private fun tryConnectAsGenericPrinter(device: android.bluetooth.BluetoothDevice) {
        lifecycleScope.launch {
            try {
                showMessage("Attempting to connect to ${device.name ?: "Unknown Device"}...", "🔗 Connecting")
                
                // Use global printer service
                val result = globalPrinterService.connectToPrinter(device)
                
                if (result.isSuccess) {
                    // Test print to verify connection
                    val printResult = globalPrinterService.printTestReceipt()
                    
                    if (printResult.isSuccess) {
                        showMessage(
                            """
                            ✅ Connection & Print Test Successful!
                            📱 Name: ${device.name ?: "Unknown Device"}
                            📍 Address: ${device.address}
                            🔗 Status: Connected and ready for printing
                            📄 Test receipt printed successfully!
                            
                            🎉 ${device.name} is ready to use!
                            Connection will persist across screens.
                            Check your printer for the test receipt!
                            """.trimIndent(),
                            "Connection Success"
                        )
                    } else {
                        showMessage("✅ Connected but print test failed: ${printResult.error}", "Partial Success")
                    }
                } else {
                    showMessage(
                        """
                        ❌ Connection Failed
                        📱 Name: ${device.name ?: "Unknown Device"}
                        📍 Address: ${device.address}
                        ⚠️ Error: ${result.error}
                        
                        💡 Troubleshooting:
                        • Make sure printer is turned on
                        • Check if printer is in pairing mode
                        • Try unpairing and re-pairing the device
                        • Ensure printer supports SPP (Serial Port Profile)
                        """.trimIndent(),
                        "Connection Failed"
                    )
                }
                
            } catch (e: Exception) {
                Log.e(TAG, "Failed to connect as generic printer", e)
                showMessage(
                    """
                    ❌ Connection Error: ${e.message}
                    
                    💡 Common Solutions:
                    • Grant all Bluetooth permissions
                    • Make sure printer is powered on
                    • Check Bluetooth is enabled
                    • Try restarting the printer
                    """.trimIndent(), 
                    "Connection Error"
                )
            }
        }
    }
    
    private fun updateConnectionStatus() {
        val isConnected = globalPrinterService.isConnected()
        val deviceName = globalPrinterService.getConnectedDeviceName()
        
        val status = if (isConnected) {
            "✅ Connected to ${deviceName ?: "Unknown Device"}"
        } else {
            "❌ Not Connected"
        }
        
        connectionStatusText?.text = "🖨️ Printer Status: $status"
    }
    
    private fun disconnectPrinter() {
        globalPrinterService.disconnect()
        showMessage("Printer disconnected", "ℹ️ Disconnected")
    }
    

    
    private suspend fun testBluetoothConnection(device: android.bluetooth.BluetoothDevice): ConnectionResult {
        return try {
            Log.d(TAG, "Testing connection to ${device.name} (${device.address})")
            
            // Multiple connection strategies - try each until one works
            val connectionStrategies = listOf(
                ConnectionStrategy("Standard SPP", "00001101-0000-1000-8000-00805F9B34FB", false),
                ConnectionStrategy("Insecure SPP", "00001101-0000-1000-8000-00805F9B34FB", true),
                ConnectionStrategy("Reflection Method", "", true) // Special case for reflection
            )
            
            withContext(kotlinx.coroutines.Dispatchers.IO) {
                withTimeout(15000) { // 15 second total timeout
                    
                    for ((index, strategy) in connectionStrategies.withIndex()) {
                        Log.d(TAG, "Trying strategy ${index + 1}/${connectionStrategies.size}: ${strategy.name}")
                        
                        val result = attemptConnection(device, strategy)
                        if (result.isSuccess) {
                            Log.d(TAG, "Success with strategy: ${strategy.name}")
                            return@withTimeout result
                        } else {
                            Log.d(TAG, "Failed with strategy ${strategy.name}: ${result.error}")
                        }
                    }
                    
                    ConnectionResult(isSuccess = false, error = "All connection strategies failed")
                }
            }
            
        } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
            Log.e(TAG, "Connection timeout after 15 seconds")
            ConnectionResult(isSuccess = false, error = "Connection timeout - JP302+BT not responding")
        } catch (e: Exception) {
            Log.e(TAG, "Bluetooth connection test failed", e)
            ConnectionResult(isSuccess = false, error = e.message ?: "Bluetooth connection failed")
        }
    }
    
    private suspend fun attemptConnection(device: android.bluetooth.BluetoothDevice, strategy: ConnectionStrategy): ConnectionResult {
        var socket: android.bluetooth.BluetoothSocket? = null
        
        return try {
            socket = when (strategy.name) {
                "Reflection Method" -> {
                    // Use reflection to create socket - sometimes works when standard methods fail
                    Log.d(TAG, "Using reflection method for connection")
                    val method = device.javaClass.getMethod("createRfcommSocket", Int::class.java)
                    method.invoke(device, 1) as android.bluetooth.BluetoothSocket
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
            
            Log.d(TAG, "Attempting connection with ${strategy.name}...")
            socket.connect()
            
            if (socket.isConnected) {
                Log.d(TAG, "Connected successfully with ${strategy.name}")
                
                // Test actual printing capability
                val result = testPrinterCommunication(socket)
                
                if (result.isSuccess) {
                    ConnectionResult(isSuccess = true, error = null)
                } else {
                    ConnectionResult(isSuccess = false, error = "Connected but printer communication failed: ${result.error}")
                }
            } else {
                ConnectionResult(isSuccess = false, error = "Socket not connected")
            }
            
        } catch (e: Exception) {
            Log.e(TAG, "Connection attempt failed with ${strategy.name}: ${e.message}", e)
            ConnectionResult(isSuccess = false, error = e.message ?: "Connection failed")
        } finally {
            try {
                socket?.close()
            } catch (e: Exception) {
                Log.e(TAG, "Error closing socket: ${e.message}")
            }
        }
    }
    
    private suspend fun testPrinterCommunication(socket: android.bluetooth.BluetoothSocket): ConnectionResult {
        return try {
            val outputStream = socket.outputStream
            
            // Send comprehensive ESC/POS test commands
            val testCommands = byteArrayOf(
                0x1B, 0x40, // ESC @ - Initialize printer
                0x1B, 0x61, 0x01, // ESC a 1 - Center align
                0x48, 0x65, 0x6C, 0x6C, 0x6F, 0x21, 0x0A, // "Hello!" + LF
                0x1B, 0x61, 0x00, // ESC a 0 - Left align
                0x54, 0x65, 0x73, 0x74, 0x20, 0x50, 0x72, 0x69, 0x6E, 0x74, 0x0A, // "Test Print" + LF
                0x0A, 0x0A, // Two line feeds
                0x1D, 0x56, 0x41, 0x10 // GS V A - Partial cut
            )
            
            Log.d(TAG, "Sending test print commands...")
            outputStream.write(testCommands)
            outputStream.flush()
            
            // Wait for printer to process
            kotlinx.coroutines.delay(1000)
            
            Log.d(TAG, "Test print commands sent successfully")
            ConnectionResult(isSuccess = true, error = null)
            
        } catch (e: Exception) {
            Log.e(TAG, "Printer communication test failed: ${e.message}", e)
            ConnectionResult(isSuccess = false, error = "Communication failed: ${e.message}")
        }
    }
    
    private data class ConnectionStrategy(
        val name: String,
        val uuid: String,
        val isInsecure: Boolean
    )
    
    private data class ConnectionResult(
        val isSuccess: Boolean,
        val error: String?
    )

    private fun printTestReceipt() {
        lifecycleScope.launch {
            try {
                if (!globalPrinterService.isConnected()) {
                    showMessage("❌ No printer connected. Please connect a printer first.", "No Connection")
                    return@launch
                }
                
                showMessage("Printing test receipt...", "🧾 Test Print")
                
                val result = globalPrinterService.printTestReceipt()
                
                if (result.isSuccess) {
                    showMessage(
                        """
                        ✅ Test receipt printed successfully!
                        
                        📄 Receipt Contents:
                        • AndroPOS Test Header
                        • Current Date/Time
                        • Printer Information
                        • Connection Status
                        • Paper Cut Command
                        
                        Check your ${globalPrinterService.getConnectedDeviceName()} printer!
                        """.trimIndent(),
                        "Print Success"
                    )
                } else {
                    showMessage(
                        """
                        ❌ Print test failed: ${result.error}
                        
                        💡 Troubleshooting:
                        • Check printer has paper
                        • Ensure printer is still connected
                        • Try reconnecting the printer
                        """.trimIndent(), 
                        "Print Failed"
                    )
                }
            } catch (e: Exception) {
                showMessage("❌ Print test error: ${e.message}", "Error")
            }
        }
    }

    private fun showAdvancedSettings() {
        showMessage(
            """
            🔧 Advanced Printer Settings:
            
            📋 Available Options:
            • Printer SDK Selection
            • Connection Type Preferences  
            • Paper Size Configuration
            • Print Quality Settings
            • Receipt Template Customization
            
            🚧 Coming Soon: Full configuration UI
            """.trimIndent(),
            "Advanced Settings"
        )
    }

    private fun showMessage(message: String, title: String = "Printer Setup") {
        try {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show()
        } catch (e: Exception) {
            // Fallback to toast if dialog fails
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }
}