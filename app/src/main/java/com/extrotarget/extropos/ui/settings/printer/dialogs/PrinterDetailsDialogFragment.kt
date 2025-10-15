package com.extrotarget.extropos.ui.settings.printer.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.extrotarget.extropos.ui.settings.printer.ConnectionType
import com.extrotarget.extropos.ui.settings.printer.Printer
import com.extrotarget.extropos.ui.settings.printer.PrinterType

class PrinterDetailsDialogFragment : DialogFragment() {

    private var printer: Printer? = null

    companion object {
        private const val ARG_PRINTER_ID = "printer_id"
        private const val ARG_PRINTER_NAME = "printer_name"
        private const val ARG_PRINTER_TYPE = "printer_type"
        private const val ARG_CONNECTION_TYPE = "connection_type"
        private const val ARG_ADDRESS = "address"
        private const val ARG_PORT = "port"
        private const val ARG_IS_DEFAULT = "is_default"
        private const val ARG_IS_CONNECTED = "is_connected"
        
        fun newInstance(printer: Printer): PrinterDetailsDialogFragment {
            return PrinterDetailsDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PRINTER_ID, printer.id)
                    putString(ARG_PRINTER_NAME, printer.name)
                    putString(ARG_PRINTER_TYPE, printer.type.name)
                    putString(ARG_CONNECTION_TYPE, printer.connectionType.name)
                    putString(ARG_ADDRESS, printer.address)
                    printer.port?.let { putInt(ARG_PORT, it) }
                    putBoolean(ARG_IS_DEFAULT, printer.isDefault)
                    putBoolean(ARG_IS_CONNECTED, printer.isConnected)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            printer = Printer(
                id = args.getString(ARG_PRINTER_ID) ?: "",
                name = args.getString(ARG_PRINTER_NAME) ?: "",
                type = PrinterType.valueOf(args.getString(ARG_PRINTER_TYPE) ?: "RECEIPT"),
                connectionType = ConnectionType.valueOf(args.getString(ARG_CONNECTION_TYPE) ?: "USB"),
                address = args.getString(ARG_ADDRESS) ?: "",
                port = if (args.containsKey(ARG_PORT)) args.getInt(ARG_PORT) else null,
                isDefault = args.getBoolean(ARG_IS_DEFAULT),
                isConnected = args.getBoolean(ARG_IS_CONNECTED)
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val rootView = createPrinterDetailsView()

        return AlertDialog.Builder(requireContext())
            .setTitle("🖨️ ${printer?.name ?: "Printer Details"}")
            .setView(rootView)
            .setPositiveButton("Close", null)
            .setNeutralButton("Edit Settings") { _, _ -> showAdvancedSettings() }
            .create()
    }

    private fun createPrinterDetailsView(): View {
        val context = requireContext()
        val scrollView = androidx.core.widget.NestedScrollView(context)
        val mainLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 16, 24, 16)
        }

        printer?.let { printer ->
            // Status & Connection Info Card
            mainLayout.addView(createStatusCard(printer))
            
            // Configuration Details Card
            mainLayout.addView(createConfigCard(printer))
            
            // Actions Card
            mainLayout.addView(createActionsCard(printer))
            
            // Advanced Features Card
            mainLayout.addView(createAdvancedFeaturesCard(printer))
        }

        scrollView.addView(mainLayout)
        return scrollView
    }

    private fun createStatusCard(printer: Printer): MaterialCardView {
        val context = requireContext()
        val card = MaterialCardView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            cardElevation = 4f
            radius = 8f
        }

        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // Card title
        content.addView(TextView(context).apply {
            text = "📊 Status & Connection"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 12)
        })

        // Status
        val statusText = if (printer.isConnected) "✅ Connected" else "❌ Disconnected"
        val statusColor = if (printer.isConnected) android.graphics.Color.GREEN else android.graphics.Color.RED
        
        content.addView(TextView(context).apply {
            text = "Status: $statusText"
            textSize = 14f
            setTextColor(statusColor)
            setPadding(0, 0, 0, 8)
        })

        // Default status
        if (printer.isDefault) {
            content.addView(TextView(context).apply {
                text = "⭐ Default Printer"
                textSize = 14f
                setTextColor(android.graphics.Color.BLUE)
                setPadding(0, 0, 0, 8)
            })
        }

        // Connection info
        val connectionIcon = when (printer.connectionType) {
            ConnectionType.USB -> "🔌"
            ConnectionType.BLUETOOTH -> "📶"
            ConnectionType.NETWORK -> "🌐"
        }
        
        content.addView(TextView(context).apply {
            text = "Connection: $connectionIcon ${printer.connectionType.name}"
            textSize = 14f
            setPadding(0, 0, 0, 8)
        })

        content.addView(TextView(context).apply {
            text = "Address: ${printer.address}"
            textSize = 14f
            setPadding(0, 0, 0, 8)
        })

        printer.port?.let { port ->
            content.addView(TextView(context).apply {
                text = "Port: $port"
                textSize = 14f
                setPadding(0, 0, 0, 8)
            })
        }

        card.addView(content)
        return card
    }

    private fun createConfigCard(printer: Printer): MaterialCardView {
        val context = requireContext()
        val card = MaterialCardView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            cardElevation = 4f
            radius = 8f
        }

        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // Card title
        content.addView(TextView(context).apply {
            text = "⚙️ Configuration"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 12)
        })

        // Printer type
        val typeIcon = when (printer.type) {
            PrinterType.RECEIPT -> "🧾"
            PrinterType.KITCHEN -> "🍽️"
        }
        
        content.addView(TextView(context).apply {
            text = "Type: $typeIcon ${printer.type.name}"
            textSize = 14f
            setPadding(0, 0, 0, 8)
        })

        // Additional configuration info based on connection type
        when (printer.connectionType) {
            ConnectionType.NETWORK -> {
                content.addView(TextView(context).apply {
                    text = "• Network printer using TCP/IP"
                    textSize = 12f
                    setPadding(0, 4, 0, 4)
                })
                content.addView(TextView(context).apply {
                    text = "• Default protocol: Raw/ESC-POS"
                    textSize = 12f
                    setPadding(0, 4, 0, 4)
                })
            }
            ConnectionType.USB -> {
                content.addView(TextView(context).apply {
                    text = "• Direct USB connection"
                    textSize = 12f
                    setPadding(0, 4, 0, 4)
                })
                content.addView(TextView(context).apply {
                    text = "• No network configuration needed"
                    textSize = 12f
                    setPadding(0, 4, 0, 4)
                })
            }
            ConnectionType.BLUETOOTH -> {
                content.addView(TextView(context).apply {
                    text = "• Wireless Bluetooth connection"
                    textSize = 12f
                    setPadding(0, 4, 0, 4)
                })
                content.addView(TextView(context).apply {
                    text = "• Range: ~10 meters"
                    textSize = 12f
                    setPadding(0, 4, 0, 4)
                })
            }
        }

        card.addView(content)
        return card
    }

    private fun createActionsCard(printer: Printer): MaterialCardView {
        val context = requireContext()
        val card = MaterialCardView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            cardElevation = 4f
            radius = 8f
        }

        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // Card title
        content.addView(TextView(context).apply {
            text = "🎯 Actions"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 12)
        })

        // Action buttons
        val buttonLayout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
        }

        // Test Print button
        buttonLayout.addView(MaterialButton(context).apply {
            text = "🧾 Test Print"
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 8) }
            setOnClickListener { testPrint(printer) }
        })

        // Connect/Disconnect button
        val connectionButtonText = if (printer.isConnected) "🔌 Disconnect" else "🔗 Connect"
        buttonLayout.addView(MaterialButton(context).apply {
            text = connectionButtonText
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 8) }
            setOnClickListener { toggleConnection(printer) }
        })

        // Set as Default button (if not already default)
        if (!printer.isDefault) {
            buttonLayout.addView(MaterialButton(context).apply {
                text = "⭐ Set as Default"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { setMargins(0, 0, 0, 8) }
                setOnClickListener { setAsDefault(printer) }
            })
        }

        content.addView(buttonLayout)
        card.addView(content)
        return card
    }

    private fun createAdvancedFeaturesCard(printer: Printer): MaterialCardView {
        val context = requireContext()
        val card = MaterialCardView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(0, 0, 0, 16) }
            cardElevation = 4f
            radius = 8f
        }

        val content = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        // Card title
        content.addView(TextView(context).apply {
            text = "🚀 Features & Capabilities"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 12)
        })

        // Feature list
        val features = listOf(
            "✅ Text Printing",
            "✅ QR Code Generation",
            "✅ Barcode Printing",
            "✅ Logo/Image Support",
            "✅ Auto Paper Cut",
            "✅ Cash Drawer Control",
            "✅ Receipt Templates"
        )

        features.forEach { feature ->
            content.addView(TextView(context).apply {
                text = feature
                textSize = 12f
                setPadding(0, 2, 0, 2)
            })
        }

        card.addView(content)
        return card
    }

    private fun testPrint(printer: Printer) {
        AlertDialog.Builder(requireContext())
            .setTitle("🧾 Test Print")
            .setMessage("""
                Sending test receipt to: ${printer.name}
                
                ==================
                    TEST RECEIPT
                ==================
                Date: ${java.text.SimpleDateFormat("yyyy-MM-dd HH:mm", java.util.Locale.getDefault()).format(java.util.Date())}
                Printer: ${printer.name}
                Connection: ${printer.connectionType.name}
                Address: ${printer.address}
                
                TEST ITEM 1         RM 10.00
                TEST ITEM 2         RM 15.50
                -------------------------
                TOTAL:              RM 25.50
                
                Thank you for testing!
                ==================
                
                ✅ Test print completed successfully!
            """.trimIndent())
            .setPositiveButton("OK", null)
            .show()
    }

    private fun toggleConnection(printer: Printer) {
        val action = if (printer.isConnected) "Disconnect from" else "Connect to"
        AlertDialog.Builder(requireContext())
            .setTitle("Connection")
            .setMessage("$action ${printer.name}?")
            .setPositiveButton("Yes") { _, _ ->
                // TODO: Implement actual connection logic
                val newStatus = if (printer.isConnected) "Disconnected from" else "Connected to"
                AlertDialog.Builder(requireContext())
                    .setTitle("Success")
                    .setMessage("✅ $newStatus ${printer.name}")
                    .setPositiveButton("OK", null)
                    .show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun setAsDefault(printer: Printer) {
        AlertDialog.Builder(requireContext())
            .setTitle("Set Default Printer")
            .setMessage("Set ${printer.name} as the default printer?")
            .setPositiveButton("Yes") { _, _ ->
                // TODO: Implement set default logic
                AlertDialog.Builder(requireContext())
                    .setTitle("Success")
                    .setMessage("⭐ ${printer.name} is now the default printer")
                    .setPositiveButton("OK", null)
                    .show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAdvancedSettings() {
        val advancedDialog = AdvancedPrinterSettingsDialogFragment.newInstance()
        advancedDialog.show(parentFragmentManager, "AdvancedSettings")
    }
}