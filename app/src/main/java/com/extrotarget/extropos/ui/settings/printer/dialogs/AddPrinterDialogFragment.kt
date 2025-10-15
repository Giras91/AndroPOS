package com.extrotarget.extropos.ui.settings.printer.dialogs

import android.app.Dialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.extrotarget.extropos.databinding.DialogAddPrinterBinding
import com.extrotarget.extropos.ui.settings.printer.ConnectionType
import com.extrotarget.extropos.ui.settings.printer.PrinterSetupViewModel
import com.extrotarget.extropos.ui.settings.printer.PrinterType
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AddPrinterDialogFragment : DialogFragment() {

    private var _binding: DialogAddPrinterBinding? = null
    private val binding get() = _binding!!
    
    // Use the parent fragment as the owner so we share the same PrinterSetupViewModel instance
    private val viewModel: PrinterSetupViewModel by viewModels({ requireParentFragment() })
    private var preselectedConnectionType: ConnectionType? = null

    companion object {
        private const val ARG_CONNECTION_TYPE = "connection_type"
        
        fun newInstance(connectionType: ConnectionType? = null): AddPrinterDialogFragment {
            return AddPrinterDialogFragment().apply {
                arguments = Bundle().apply {
                    connectionType?.let { putString(ARG_CONNECTION_TYPE, it.name) }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(ARG_CONNECTION_TYPE)?.let {
            preselectedConnectionType = ConnectionType.valueOf(it)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        try {
            _binding = DialogAddPrinterBinding.inflate(LayoutInflater.from(requireContext()))
            
            setupSpinners()
            setupConnectionTypeListener()
            
            // Pre-select connection type if provided
            preselectedConnectionType?.let { connectionType ->
                selectConnectionType(connectionType)
                // Also populate available devices for the selected type
                populateAvailableDevices(connectionType)
            }
            
            val title = if (preselectedConnectionType != null) {
                "Add ${preselectedConnectionType?.name} Printer"
            } else {
                "Add Printer"
            }
            
            return AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setView(binding.root)
                .setPositiveButton("Add") { _, _ -> addPrinter() }
                .setNegativeButton("Cancel", null)
                .setNeutralButton("Scan Devices") { _, _ -> scanForDevices() }
                .create()
        } catch (e: Exception) {
            // Fallback if there's an issue with the complex dialog
            return AlertDialog.Builder(requireContext())
                .setTitle("Add Printer - Debug Mode")
                .setMessage("Error loading enhanced dialog: ${e.message}\n\nPreselected type: $preselectedConnectionType")
                .setPositiveButton("OK", null)
                .create()
        }
    }

    private fun setupSpinners() {
        // Printer Type Spinner
        val printerTypes = PrinterType.values().map { it.name }
        val printerTypeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, printerTypes)
        binding.printerTypeSpinner.setAdapter(printerTypeAdapter)
        binding.printerTypeSpinner.setText(printerTypes[0], false)

        // Connection Type Spinner
        val connectionTypes = ConnectionType.values().map { it.name }
        val connectionTypeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, connectionTypes)
        binding.connectionTypeSpinner.setAdapter(connectionTypeAdapter)
        binding.connectionTypeSpinner.setText(connectionTypes[0], false)
    }

    private fun setupConnectionTypeListener() {
        binding.connectionTypeSpinner.setOnItemClickListener { _, _, position, _ ->
            val connectionType = ConnectionType.values()[position]
            updateUIForConnectionType(connectionType)
            populateAvailableDevices(connectionType)
        }
    }
    
    private fun selectConnectionType(connectionType: ConnectionType) {
        binding.connectionTypeSpinner.setText(connectionType.name, false)
        updateUIForConnectionType(connectionType)
    }

    private fun updateUIForConnectionType(connectionType: ConnectionType) {
        // Hide all connection-specific layouts first
        binding.networkSettingsLayout.visibility = View.GONE
        binding.usbSettingsLayout.visibility = View.GONE
        binding.bluetoothSettingsLayout.visibility = View.GONE
        binding.portInputLayout.visibility = View.GONE
        
        when (connectionType) {
            ConnectionType.NETWORK -> {
                binding.networkSettingsLayout.visibility = View.VISIBLE
                binding.portInputLayout.visibility = View.VISIBLE
                binding.addressEditText.hint = "IP Address (e.g., 192.168.1.100)"
            }
            ConnectionType.USB -> {
                binding.usbSettingsLayout.visibility = View.VISIBLE
                binding.addressEditText.hint = "USB Device Path (e.g., /dev/usb/lp0)"
            }
            ConnectionType.BLUETOOTH -> {
                binding.bluetoothSettingsLayout.visibility = View.VISIBLE
                binding.addressEditText.hint = "Bluetooth MAC Address (e.g., AA:BB:CC:DD:EE:FF)"
            }
        }
    }
    
    private fun populateAvailableDevices(connectionType: ConnectionType) {
        when (connectionType) {
            ConnectionType.USB -> populateUSBDevices()
            ConnectionType.BLUETOOTH -> populateBluetoothDevices()
            ConnectionType.NETWORK -> showNetworkTips()
        }
    }
    
    private fun populateUSBDevices() {
        // Scan for USB printer devices
        val usbDevices = mutableListOf<String>()
        
        // Common USB printer paths
        val commonPaths = listOf(
            "/dev/usb/lp0", "/dev/usb/lp1", "/dev/usb/lp2",
            "/dev/ttyUSB0", "/dev/ttyUSB1", "/dev/ttyUSB2"
        )
        
        commonPaths.forEach { path ->
            if (File(path).exists()) {
                usbDevices.add(path)
            }
        }
        
        if (usbDevices.isNotEmpty()) {
            // Create dropdown adapter for found USB devices
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, usbDevices)
            binding.addressEditText.setOnClickListener {
                val dialog = AlertDialog.Builder(requireContext())
                    .setTitle("Select USB Device")
                    .setItems(usbDevices.toTypedArray()) { _, which ->
                        binding.addressEditText.setText(usbDevices[which])
                    }
                    .setNegativeButton("Manual Entry", null)
                    .create()
                dialog.show()
            }
        }
    }
    
    private fun populateBluetoothDevices() {
        try {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if (bluetoothAdapter?.isEnabled == true) {
                val pairedDevices = bluetoothAdapter.bondedDevices
                val printerDevices = pairedDevices?.filter { device ->
                    // Filter devices that might be printers
                    device.name?.contains("printer", true) == true ||
                    device.name?.contains("pos", true) == true ||
                    device.bluetoothClass?.deviceClass == 1664 // Printer device class
                } ?: emptyList()
                
                if (printerDevices.isNotEmpty()) {
                    binding.addressEditText.setOnClickListener {
                        val deviceNames = printerDevices.map { "${it.name} (${it.address})" }.toTypedArray()
                        val dialog = AlertDialog.Builder(requireContext())
                            .setTitle("Select Paired Printer")
                            .setItems(deviceNames) { _, which ->
                                binding.addressEditText.setText(printerDevices[which].address)
                            }
                            .setNegativeButton("Manual Entry", null)
                            .create()
                        dialog.show()
                    }
                }
            }
        } catch (e: SecurityException) {
            // Handle permission issues
            binding.addressEditText.hint = "Enter MAC Address manually (Bluetooth permission needed)"
        }
    }
    
    private fun showNetworkTips() {
        // For network printers, we can't really scan without additional network discovery
        // But we can provide helpful suggestions
        binding.addressEditText.hint = "Enter IP Address (try 192.168.1.x range)"
    }
    
    private fun scanForDevices() {
        val connectionType = ConnectionType.valueOf(binding.connectionTypeSpinner.text.toString())
        
        AlertDialog.Builder(requireContext())
            .setTitle("Scanning for ${connectionType.name} Printers...")
            .setMessage("This will search for available printers on your ${connectionType.name.lowercase()} connection.")
            .setPositiveButton("Start Scan") { _, _ ->
                // Trigger the actual scan via ViewModel
                when (connectionType) {
                    ConnectionType.USB -> viewModel.scanUsbPrinters()
                    ConnectionType.BLUETOOTH -> viewModel.scanBluetoothPrinters()  
                    ConnectionType.NETWORK -> viewModel.scanNetworkPrinters()
                }
                dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun addPrinter() {
        val name = binding.nameEditText.text.toString().trim()
        val address = binding.addressEditText.text.toString().trim()
        val portText = binding.portEditText.text.toString().trim()
        
        // Validation
        if (name.isBlank()) {
            binding.nameEditText.error = "Printer name is required"
            return
        }
        
        if (address.isBlank()) {
            binding.addressEditText.error = "Address is required"
            return
        }

        val printerType = PrinterType.valueOf(binding.printerTypeSpinner.text.toString())
        val connectionType = ConnectionType.valueOf(binding.connectionTypeSpinner.text.toString())
        
        val port = if (connectionType == ConnectionType.NETWORK && portText.isNotBlank()) {
            portText.toIntOrNull() ?: 9100
        } else null

        // Add printer via ViewModel
        viewModel.addPrinter(name, printerType, connectionType, address, port)
        
        // Show success message
        AlertDialog.Builder(requireContext())
            .setTitle("✅ Printer Added")
            .setMessage("$name has been successfully added to your printer list.")
            .setPositiveButton("OK", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
