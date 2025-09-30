package com.extrotarget.extropos.ui.settings.printer.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.extrotarget.extropos.databinding.DialogAddPrinterBinding
import com.extrotarget.extropos.ui.settings.printer.ConnectionType
import com.extrotarget.extropos.ui.settings.printer.PrinterSetupViewModel
import com.extrotarget.extropos.ui.settings.printer.PrinterType
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddPrinterDialogFragment : DialogFragment() {

    private var _binding: DialogAddPrinterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PrinterSetupViewModel by viewModels({ requireParentFragment() })

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddPrinterBinding.inflate(layoutInflater)

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add Printer")
            .setView(binding.root)
            .setPositiveButton("Add") { _, _ -> addPrinter() }
            .setNegativeButton("Cancel", null)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCanceledOnTouchOutside(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinners()
        setupConnectionTypeListener()
    }

    private fun setupSpinners() {
        // Setup printer type spinner
        val printerTypes = arrayOf("Receipt Printer", "Kitchen Printer")
        binding.printerTypeSpinner.setAdapter(
            android.widget.ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                printerTypes
            )
        )

        // Setup connection type spinner
        val connectionTypes = arrayOf("USB", "Bluetooth", "Network")
        binding.connectionTypeSpinner.setAdapter(
            android.widget.ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                connectionTypes
            )
        )
    }

    private fun setupConnectionTypeListener() {
        binding.connectionTypeSpinner.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> { // USB
                    binding.addressInputLayout.hint = "USB Device Path (e.g., /dev/usb/lp0)"
                    binding.portInputLayout.visibility = View.GONE
                }
                1 -> { // Bluetooth
                    binding.addressInputLayout.hint = "Bluetooth MAC Address (e.g., AA:BB:CC:DD:EE:FF)"
                    binding.portInputLayout.visibility = View.GONE
                }
                2 -> { // Network
                    binding.addressInputLayout.hint = "IP Address (e.g., 192.168.1.100)"
                    binding.portInputLayout.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun addPrinter() {
        val name = binding.nameEditText.text.toString().trim()
        val printerTypeIndex = binding.printerTypeSpinner.selectedItemPosition
        val connectionTypeIndex = binding.connectionTypeSpinner.selectedItemPosition
        val address = binding.addressEditText.text.toString().trim()
        val portText = binding.portEditText.text.toString().trim()

        if (name.isEmpty() || address.isEmpty()) {
            // Show error
            return
        }

        val printerType = when (printerTypeIndex) {
            0 -> PrinterType.RECEIPT
            1 -> PrinterType.KITCHEN
            else -> PrinterType.RECEIPT
        }

        val connectionType = when (connectionTypeIndex) {
            0 -> ConnectionType.USB
            1 -> ConnectionType.BLUETOOTH
            2 -> ConnectionType.NETWORK
            else -> ConnectionType.USB
        }

        val port = if (portText.isNotEmpty() && connectionType == ConnectionType.NETWORK) {
            portText.toIntOrNull() ?: 9100
        } else {
            null
        }

        viewModel.addPrinter(name, printerType, connectionType, address, port)
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}