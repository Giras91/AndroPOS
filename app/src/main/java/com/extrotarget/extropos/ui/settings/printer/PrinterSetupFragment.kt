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
            // Show add printer dialog
            showAddPrinterDialog()
        }

        binding.scanPrintersButton.setOnClickListener {
            viewModel.scanForPrinters()
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

    private fun showAddPrinterDialog() {
        val dialog = AddPrinterDialogFragment()
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