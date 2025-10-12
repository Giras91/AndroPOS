package com.extrotarget.extropos.printer.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.printer.domain.service.PrinterService
import com.extrotarget.extropos.printer.template.ReceiptTemplateBuilder
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PrinterManagementFragment : Fragment() {

    @Inject
    lateinit var printerService: PrinterService

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createPrinterManagementUI()
    }

    private fun createPrinterManagementUI(): LinearLayout {
        val rootLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // Title
        val titleText = TextView(requireContext()).apply {
            text = "🖨️ Printer Management"
            textSize = 24f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 24)
        }
        rootLayout.addView(titleText)

        // Quick Actions Card
        val quickActionsCard = createQuickActionsCard()
        rootLayout.addView(quickActionsCard)

        // SDK Info Card
        val sdkInfoCard = createSdkInfoCard()
        rootLayout.addView(sdkInfoCard)

        // Test Printing Card
        val testCard = createTestPrintingCard()
        rootLayout.addView(testCard)

        return rootLayout
    }

    private fun createQuickActionsCard(): MaterialCardView {
        val card = MaterialCardView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            radius = 12f
            cardElevation = 4f
        }

        val content = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val title = TextView(requireContext()).apply {
            text = "🔍 Printer Detection"
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 16)
        }
        content.addView(title)

        // Scan Buttons
        val scanButton = MaterialButton(requireContext()).apply {
            text = "Scan for Printers"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 8) }
            setOnClickListener { scanForPrinters() }
        }
        content.addView(scanButton)

        val connectButton = MaterialButton(requireContext()).apply {
            text = "Connect to Default Printer"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener { connectToDefaultPrinter() }
        }
        content.addView(connectButton)

        card.addView(content)
        return card
    }

    private fun createSdkInfoCard(): MaterialCardView {
        val card = MaterialCardView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            radius = 12f
            cardElevation = 4f
        }

        val content = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val title = TextView(requireContext()).apply {
            text = "📚 Active SDK: DantSu ESC/POS"
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 8)
        }
        content.addView(title)

        val description = TextView(requireContext()).apply {
            text = "✅ Supports: Text, Barcodes, QR Codes, Images, Paper Cutting\n" +
                   "🔌 Connections: USB, Bluetooth, Network (TCP/IP)\n" +
                   "🏭 Compatible: Most ESC/POS thermal printers"
            textSize = 14f
            setLineSpacing(4f, 1f)
        }
        content.addView(description)

        card.addView(content)
        return card
    }

    private fun createTestPrintingCard(): MaterialCardView {
        val card = MaterialCardView(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            radius = 12f
            cardElevation = 4f
        }

        val content = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val title = TextView(requireContext()).apply {
            text = "🧪 Test Printing"
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 16)
        }
        content.addView(title)

        // Test buttons
        val testReceiptButton = MaterialButton(requireContext()).apply {
            text = "Print Test Receipt"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 8) }
            setOnClickListener { printTestReceipt() }
        }
        content.addView(testReceiptButton)

        val testConnectionButton = MaterialButton(requireContext()).apply {
            text = "Test Connection"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener { testConnection() }
        }
        content.addView(testConnectionButton)

        card.addView(content)
        return card
    }

    // Action methods

    private fun scanForPrinters() {
        lifecycleScope.launch {
            try {
                showMessage("Scanning for printers...")
                val printers = printerService.scanAllPrinters()
                
                if (printers.isEmpty()) {
                    showMessage("No printers found. Try connecting a printer or check network settings.")
                } else {
                    showMessage("Found ${printers.size} printer(s)! Check logs for details.")
                    // Log printer details for debugging
                    printers.forEach { printer ->
                        println("Found printer: ${printer.name} (${printer.connectionType})")
                    }
                }
            } catch (e: Exception) {
                showMessage("Scan failed: ${e.message}")
            }
        }
    }

    private fun connectToDefaultPrinter() {
        lifecycleScope.launch {
            try {
                showMessage("Connecting to default printer...")
                val connected = printerService.connectToDefaultPrinter()
                
                if (connected) {
                    val status = printerService.getConnectionStatus()
                    if (status is com.extrotarget.extropos.printer.domain.service.PrinterConnectionStatus.Connected) {
                        showMessage("✅ Connected to ${status.printerName} via ${status.connectionType}")
                    } else {
                        showMessage("✅ Connected to default printer")
                    }
                } else {
                    showMessage("❌ Failed to connect. Please configure a default printer first.")
                }
            } catch (e: Exception) {
                showMessage("Connection failed: ${e.message}")
            }
        }
    }

    private fun printTestReceipt() {
        lifecycleScope.launch {
            try {
                showMessage("Preparing test receipt...")
                
                // Create a test receipt using our template builder
                val testReceipt = ReceiptTemplateBuilder.createTestReceipt()
                
                // Print the receipt
                val result = printerService.print(testReceipt)
                
                if (result.success) {
                    showMessage("✅ Test receipt printed successfully!")
                } else {
                    showMessage("❌ Print failed: ${result.message}")
                }
            } catch (e: Exception) {
                showMessage("Print error: ${e.message}")
            }
        }
    }

    private fun testConnection() {
        lifecycleScope.launch {
            try {
                val status = printerService.getConnectionStatus()
                
                when (status) {
                    is com.extrotarget.extropos.printer.domain.service.PrinterConnectionStatus.Connected -> {
                        showMessage("✅ Printer connected: ${status.printerName}\n" +
                                  "SDK: ${status.sdkName}\n" +
                                  "Connection: ${status.connectionType}\n" +
                                  "Features: ${status.features.size} available")
                    }
                    is com.extrotarget.extropos.printer.domain.service.PrinterConnectionStatus.Disconnected -> {
                        showMessage("❌ No printer connected. Please connect to a printer first.")
                    }
                }
            } catch (e: Exception) {
                showMessage("Status check failed: ${e.message}")
            }
        }
    }

    private fun showMessage(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Printer Status")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun checkBluetoothPermission(): Boolean {
        val hasPermission = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.BLUETOOTH
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN),
                PERMISSION_REQUEST_CODE
            )
        }
        return hasPermission
    }
}