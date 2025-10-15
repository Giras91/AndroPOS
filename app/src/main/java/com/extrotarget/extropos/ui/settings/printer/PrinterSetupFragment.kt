package com.extrotarget.extropos.ui.settings.printer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.extrotarget.extropos.databinding.FragmentPrinterSetupBinding
import com.extrotarget.extropos.ui.settings.printer.adapters.PrinterAdapter
import com.extrotarget.extropos.ui.settings.printer.dialogs.AddPrinterDialogFragment
import com.extrotarget.extropos.ui.settings.printer.dialogs.AdvancedPrinterSettingsDialogFragment
import com.extrotarget.extropos.ui.settings.printer.dialogs.PrinterDetailsDialogFragment
import com.extrotarget.extropos.ui.settings.printer.dialogs.ScanResultsDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PrinterSetupFragment : Fragment() {

    private var _binding: FragmentPrinterSetupBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PrinterSetupViewModel by viewModels()
    private lateinit var printerAdapter: PrinterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrinterSetupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupRecyclerView()
        observeViewModel()
        loadPrinters()
    }

    private fun setupUI() {
        binding.toolbar.title = "Printer Setup"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.addPrinterFab.setOnClickListener {
            showPrinterTypeDialog()
        }

        binding.addPrinterButton.setOnClickListener {
            showPrinterTypeDialog()
        }

        binding.scanPrintersButton.setOnClickListener {
            showScanOptionsDialog()
        }
    }

    private fun setupRecyclerView() {
        printerAdapter = PrinterAdapter(
            onPrinterClick = { printer ->
                // Handle printer selection
                showPrinterDetailsDialog(printer)
            },
            onTestPrintClick = { printer ->
                viewModel.testPrint(printer)
            },
            onDeleteClick = { printer ->
                viewModel.deletePrinter(printer)
            },
            onDetailsClick = { printer ->
                showPrinterDetailsDialog(printer)
            }
        )

        binding.printersRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = printerAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.printers.collect { printers ->
                        printerAdapter.submitList(printers)
                        binding.emptyStateTextView.visibility = if (printers.isEmpty()) View.VISIBLE else View.GONE
                    }
                }

                launch {
                    viewModel.isScanning.collect { isScanning ->
                        binding.scanningProgressBar.visibility = if (isScanning) View.VISIBLE else View.GONE
                        binding.scanPrintersButton.isEnabled = !isScanning
                        binding.scanPrintersButton.text = if (isScanning) "Scanning..." else "Scan for Printers"
                    }
                }

                launch {
                    viewModel.scanResults.collect { results ->
                        if (results.isNotEmpty()) {
                            showScanResultsDialog(results)
                        }
                    }
                }
            }
        }
    }

    private fun loadPrinters() {
        viewModel.loadPrinters()
    }

    private fun showPrinterTypeDialog() {
        val items = arrayOf(
            "🖨️ Add Bluetooth Printer",
            "🌐 Add Network Printer", 
            "🔌 Add USB Printer",
            "🔍 Auto-Discover Printers",
            "⚙️ Advanced Printer Settings"
        )
        
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("📱 Enhanced Printer Setup")
            .setMessage("Select printer connection type or access advanced settings:")
            .setItems(items) { _, which ->
                when (which) {
                    0 -> {
                        // Show a confirmation first to debug
                        androidx.appcompat.app.AlertDialog.Builder(requireContext())
                            .setTitle("Debug: Opening Bluetooth Dialog")
                            .setMessage("About to open Bluetooth printer setup dialog")
                            .setPositiveButton("Continue") { _, _ ->
                                showAddPrinterDialog(ConnectionType.BLUETOOTH)
                            }
                            .show()
                    }
                    1 -> {
                        androidx.appcompat.app.AlertDialog.Builder(requireContext())
                            .setTitle("Debug: Opening Network Dialog")
                            .setMessage("About to open Network printer setup dialog")
                            .setPositiveButton("Continue") { _, _ ->
                                showAddPrinterDialog(ConnectionType.NETWORK)
                            }
                            .show()
                    }
                    2 -> {
                        androidx.appcompat.app.AlertDialog.Builder(requireContext())
                            .setTitle("Debug: Opening USB Dialog")
                            .setMessage("About to open USB printer setup dialog")
                            .setPositiveButton("Continue") { _, _ ->
                                showAddPrinterDialog(ConnectionType.USB)
                            }
                            .show()
                    }
                    3 -> showScanOptionsDialog()
                    4 -> showAdvancedSettings()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun showAdvancedSettings() {
        val advancedDialog = AdvancedPrinterSettingsDialogFragment.newInstance()
        advancedDialog.show(childFragmentManager, "AdvancedSettings")
    }

    private fun showScanOptionsDialog() {
        val items = arrayOf(
            "📶 Scan Bluetooth Printers",
            "🔌 Detect USB Printers",
            "🌐 Search Network Printers",
            "🔍 Auto-Detect All Types"
        )
        
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Scan for Printers")
            .setItems(items) { _, which ->
                when (which) {
                    0 -> viewModel.scanBluetoothPrinters()
                    1 -> viewModel.scanUsbPrinters() 
                    2 -> viewModel.scanNetworkPrinters()
                    3 -> viewModel.scanForPrinters()
                }
            }
            .show()
    }

    private fun showAddPrinterDialog(connectionType: ConnectionType? = null) {
        val dialog = AddPrinterDialogFragment.newInstance(connectionType)
        dialog.show(childFragmentManager, "AddPrinterDialog")
    }

    private fun showPrinterDetailsDialog(printer: Printer) {
        val dialog = PrinterDetailsDialogFragment.newInstance(printer)
        dialog.show(childFragmentManager, "PrinterDetailsDialog")
    }

    private fun showScanResultsDialog(results: List<DiscoveredPrinter>) {
        val dialog = ScanResultsDialogFragment.newInstance(results)
        dialog.show(childFragmentManager, "ScanResultsDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}