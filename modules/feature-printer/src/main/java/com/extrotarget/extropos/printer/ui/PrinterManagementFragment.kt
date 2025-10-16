package com.extrotarget.extropos.printer.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.printer.domain.model.DetectedPrinter
import com.extrotarget.extropos.printer.domain.service.PrinterConnectionStatus
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PrinterManagementFragment : Fragment() {

    private val vm: PrinterManagementViewModel by viewModels()

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            com.extrotarget.extropos.printer.R.layout.fragment_printer_management,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val scanButton: Button = view.findViewById(com.extrotarget.extropos.printer.R.id.scanButton)
            val connectDefaultButton: Button = view.findViewById(com.extrotarget.extropos.printer.R.id.connectDefaultButton)
            val testPrintButton: Button = view.findViewById(com.extrotarget.extropos.printer.R.id.testPrintButton)
            val addUsbPrinterButton: Button = view.findViewById(com.extrotarget.extropos.printer.R.id.addUsbPrinterButton)
            val addBluetoothPrinterButton: Button = view.findViewById(com.extrotarget.extropos.printer.R.id.addBluetoothPrinterButton)
            val addNetworkPrinterButton: Button = view.findViewById(com.extrotarget.extropos.printer.R.id.addNetworkPrinterButton)
            val recycler: RecyclerView = view.findViewById(com.extrotarget.extropos.printer.R.id.printerList)

            val adapter = PrinterListAdapter { detected ->
                // Connect on item click
                vm.connectToPrinter(detected)
            }

            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter

            scanButton.setOnClickListener { vm.scanForPrinters() }
            connectDefaultButton.setOnClickListener { vm.connectToDefaultPrinter() }
            testPrintButton.setOnClickListener { vm.printTestReceipt() }
            addUsbPrinterButton.setOnClickListener {
                // Show USB printer selection dialog
                showAddUsbPrinterDialog()
            }
            addBluetoothPrinterButton.setOnClickListener {
                // Show Bluetooth printer selection dialog
                showAddBluetoothPrinterDialog()
            }
            addNetworkPrinterButton.setOnClickListener {
                // Simple dialog to add network printer (name, ip, port) - minimal inputs
                showAddNetworkPrinterDialog()
            }

            // Observe flows
            lifecycleScope.launch {
                vm.printers.collectLatest { list ->
                    adapter.submitList(list)
                }
            }

            lifecycleScope.launch {
                vm.message.collectLatest { msg ->
                    msg?.let { showMessage(it) }
                }
            }

            lifecycleScope.launch {
                vm.status.collectLatest { status ->
                    when (status) {
                        is PrinterConnectionStatus.Connected -> {
                            showMessage("Connected: ${status.printerName} (${status.connectionType})")
                        }
                        PrinterConnectionStatus.Disconnected -> {
                            // no-op
                        }
                    }
                }
            }
    }

    private fun showAddUsbPrinterDialog() {
        val ctx = requireContext()
        val inflater = LayoutInflater.from(ctx)
        val dialogView = inflater.inflate(com.extrotarget.extropos.printer.R.layout.dialog_add_usb_printer, null)

        val printerList = dialogView.findViewById<android.widget.ListView>(com.extrotarget.extropos.printer.R.id.usbPrinterList)
        val noPrintersText = dialogView.findViewById<android.widget.TextView>(com.extrotarget.extropos.printer.R.id.noUsbPrintersText)
        val refreshButton = dialogView.findViewById<android.widget.Button>(com.extrotarget.extropos.printer.R.id.refreshUsbButton)

        var usbPrinters = listOf<com.extrotarget.extropos.printer.domain.model.DetectedPrinter>()

        fun updatePrinterList() {
            lifecycleScope.launch {
                try {
                    usbPrinters = vm.printerServicePublic.scanAllPrinters().filter { it.connectionType == com.extrotarget.extropos.printer.domain.model.ConnectionType.USB }

                    if (usbPrinters.isEmpty()) {
                        printerList.visibility = android.view.View.GONE
                        noPrintersText.visibility = android.view.View.VISIBLE
                    } else {
                        printerList.visibility = android.view.View.VISIBLE
                        noPrintersText.visibility = android.view.View.GONE

                        val adapter = android.widget.ArrayAdapter(
                            ctx,
                            android.R.layout.simple_list_item_single_choice,
                            usbPrinters.map { it.name }
                        )
                        printerList.adapter = adapter
                    }
                } catch (e: Exception) {
                    showMessage("Failed to scan USB printers: ${e.message}")
                }
            }
        }

        // Initial scan
        updatePrinterList()

        refreshButton.setOnClickListener {
            updatePrinterList()
        }

        MaterialAlertDialogBuilder(ctx)
            .setTitle("Add USB Printer")
            .setView(dialogView)
            .setPositiveButton("Add Selected") { _, _ ->
                val selectedPosition = printerList.checkedItemPosition
                if (selectedPosition >= 0 && selectedPosition < usbPrinters.size) {
                    val selectedPrinter = usbPrinters[selectedPosition]
                    val sdkId = selectedPrinter.compatibleSdks.firstOrNull()?.id ?: vm.sdks.value.firstOrNull()?.id ?: ""

                    if (sdkId.isNotBlank()) {
                        vm.addUsbPrinter(selectedPrinter, sdkId)
                    } else {
                        showMessage("No printer SDKs available")
                    }
                } else {
                    showMessage("Please select a USB printer")
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAddBluetoothPrinterDialog() {
        val ctx = requireContext()
        val inflater = LayoutInflater.from(ctx)
        val dialogView = inflater.inflate(com.extrotarget.extropos.printer.R.layout.dialog_add_bluetooth_printer, null)

        val printerList = dialogView.findViewById<android.widget.ListView>(com.extrotarget.extropos.printer.R.id.bluetoothPrinterList)
        val noPrintersText = dialogView.findViewById<android.widget.TextView>(com.extrotarget.extropos.printer.R.id.noBluetoothPrintersText)
        val refreshButton = dialogView.findViewById<android.widget.Button>(com.extrotarget.extropos.printer.R.id.refreshBluetoothButton)

        var bluetoothPrinters = listOf<com.extrotarget.extropos.printer.domain.model.DetectedPrinter>()

        fun updatePrinterList() {
            lifecycleScope.launch {
                try {
                    bluetoothPrinters = vm.printerServicePublic.scanAllPrinters().filter { it.connectionType == com.extrotarget.extropos.printer.domain.model.ConnectionType.BLUETOOTH }

                    if (bluetoothPrinters.isEmpty()) {
                        printerList.visibility = android.view.View.GONE
                        noPrintersText.visibility = android.view.View.VISIBLE
                    } else {
                        printerList.visibility = android.view.View.VISIBLE
                        noPrintersText.visibility = android.view.View.GONE

                        val adapter = android.widget.ArrayAdapter(
                            ctx,
                            android.R.layout.simple_list_item_single_choice,
                            bluetoothPrinters.map { it.name }
                        )
                        printerList.adapter = adapter
                    }
                } catch (e: Exception) {
                    showMessage("Failed to scan Bluetooth printers: ${e.message}")
                }
            }
        }

        // Initial scan
        updatePrinterList()

        refreshButton.setOnClickListener {
            updatePrinterList()
        }

        MaterialAlertDialogBuilder(ctx)
            .setTitle("Add Bluetooth Printer")
            .setView(dialogView)
            .setPositiveButton("Add Selected") { _, _ ->
                val selectedPosition = printerList.checkedItemPosition
                if (selectedPosition >= 0 && selectedPosition < bluetoothPrinters.size) {
                    val selectedPrinter = bluetoothPrinters[selectedPosition]
                    val sdkId = selectedPrinter.compatibleSdks.firstOrNull()?.id ?: vm.sdks.value.firstOrNull()?.id ?: ""

                    if (sdkId.isNotBlank()) {
                        vm.addBluetoothPrinter(selectedPrinter, sdkId)
                    } else {
                        showMessage("No printer SDKs available")
                    }
                } else {
                    showMessage("Please select a Bluetooth printer")
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAddNetworkPrinterDialog() {
        val ctx = requireContext()
        val inflater = LayoutInflater.from(ctx)
        val dialogView = inflater.inflate(com.extrotarget.extropos.printer.R.layout.dialog_add_network_printer, null)

        val nameField = dialogView.findViewById<android.widget.EditText>(com.extrotarget.extropos.printer.R.id.inputName)
        val ipField = dialogView.findViewById<android.widget.EditText>(com.extrotarget.extropos.printer.R.id.inputIp)
        val portField = dialogView.findViewById<android.widget.EditText>(com.extrotarget.extropos.printer.R.id.inputPort)
        val spinner = dialogView.findViewById<android.widget.Spinner>(com.extrotarget.extropos.printer.R.id.spinnerSdk)
        val sdkDesc = dialogView.findViewById<android.widget.TextView>(com.extrotarget.extropos.printer.R.id.textSdkDescription)
        val sdkHelp = dialogView.findViewById<android.widget.TextView>(com.extrotarget.extropos.printer.R.id.linkSdkHelp)

        // Populate SDK spinner from ViewModel
        lifecycleScope.launch {
            vm.sdks.collectLatest { sdkList ->
                val names = if (sdkList.isEmpty()) listOf("(no SDKs installed)") else sdkList.map { it.name }
                val adapter = android.widget.ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, names)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter

                // Update description for selected SDK or default text
                if (sdkList.isEmpty()) {
                    sdkDesc.text = "No SDKs detected. Install a compatible printer SDK (e.g., DantSu ESC/POS) or add a vendor AAR to modules/feature-printer/libs/."
                } else {
                    val sel = spinner.selectedItemPosition.coerceAtLeast(0)
                    val sdk = sdkList.getOrNull(sel)
                    sdkDesc.text = sdk?.description ?: "Select an SDK"
                }
            }
        }

        sdkHelp.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Installing Printer SDKs")
                .setMessage("To add SDKs place vendor AAR files in modules/feature-printer/libs/ and rebuild, or configure a Maven coordinate in modules/feature-printer/build.gradle.kts. For ESC/POS use the DantSu library which we wire by default.")
                .setPositiveButton("OK", null)
                .show()
        }

        MaterialAlertDialogBuilder(ctx)
            .setTitle("Add Network Printer")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameField.text.toString().takeIf { it.isNotBlank() } ?: "Network Printer"
                val ip = ipField.text.toString()
                val port = portField.text.toString().toIntOrNull() ?: 9100
                val sdkIndex = spinner.selectedItemPosition
                val sdkId = vm.sdks.value.getOrNull(sdkIndex)?.id ?: ""

                // Basic IP validation (IPv4) and port range check
                val ipRegex = Regex("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$")
                if (ip.isBlank()) {
                    showMessage("IP address is required")
                } else if (!ipRegex.matches(ip)) {
                    showMessage("Invalid IPv4 address")
                } else if (port !in 1..65535) {
                    showMessage("Port must be between 1 and 65535")
                } else if (sdkId.isBlank()) {
                    showMessage("Select a printer SDK first")
                } else {
                    vm.addNetworkPrinter(name, ip, port, sdkId)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showMessage(message: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Printer")
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