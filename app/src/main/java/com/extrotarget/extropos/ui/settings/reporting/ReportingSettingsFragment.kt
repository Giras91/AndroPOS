package com.extrotarget.extropos.ui.settings.reporting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.extrotarget.extropos.databinding.FragmentReportingSettingsBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extrotarget.extropos.reporting.ProductSummaryAdapter
import com.extrotarget.extropos.reporting.ReportType
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.pdf.PdfGenerationService
import com.extrotarget.extropos.reporting.ReportingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReportingSettingsFragment : Fragment() {

    private var _binding: FragmentReportingSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportingSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupActions()
    }

    private val reportingViewModel: ReportingViewModel by viewModels()
    private var productAdapter: ProductSummaryAdapter? = null

    private fun setupActions() {
        // Populate report type spinner
        val types = ReportType.values().toList()
        val adapterSpinner = android.widget.ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, types)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerReportType.adapter = adapterSpinner

        // Generate today's daily sales report (Generate Report button)
        binding.btnGenerateReport.setOnClickListener {
            lifecycleScope.launch {
                val start = startTimestamp
                val end = endTimestamp
                if (start == null || end == null) {
                    android.widget.Toast.makeText(requireContext(), "Please select start and end dates", android.widget.Toast.LENGTH_SHORT).show()
                    return@launch
                }
                val selected = binding.spinnerReportType.selectedItem as ReportType
                when (selected) {
                    ReportType.DAILY, ReportType.MONTHLY -> {
                        val path = reportingViewModel.generateReportForRange(start, end)
                        if (path != null) android.widget.Toast.makeText(requireContext(), "Report: $path", android.widget.Toast.LENGTH_LONG).show()
                        else android.widget.Toast.makeText(requireContext(), "Failed to generate report", android.widget.Toast.LENGTH_SHORT).show()
                    }
                    ReportType.SALES_BY_PRODUCT -> {
                        val map = reportingViewModel.getSalesByProductForRange(start, end)
                        // Show in the recycler view
                        if (productAdapter == null) {
                            val rv = binding.rvResults
                            rv.layoutManager = LinearLayoutManager(requireContext())
                            productAdapter = ProductSummaryAdapter(emptyList())
                            rv.adapter = productAdapter
                        }
                        val list = map.values.toList()
                        productAdapter?.update(list)
                    }
                    ReportType.SALES_OVER_TIME -> {
                        val map = reportingViewModel.getSalesOverTimeDailyForRange(start, end)
                        // Convert to simple text display in results title for now
                        binding.tvResultsTitle.text = "Sales over time (${map.size} days)"
                    }
                    ReportType.TRANSACTIONS, ReportType.TRANSACTION_SUMMARY -> {
                        val sales = reportingViewModel.getSalesForRange(start, end)
                        binding.tvResultsTitle.text = "Transactions: ${sales.size}"
                    }
                    ReportType.SHIFT -> {
                        // Build shift reports for the day range
                        val reports = reportingViewModel.getShiftReportsForRange(start, end)
                        val rv = binding.rvResults
                        rv.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = com.extrotarget.extropos.reporting.ShiftReportAdapter(emptyList())
                        rv.adapter = adapter
                        adapter.update(reports)
                        binding.tvResultsTitle.text = "Shift reports (${reports.size})"
                    }
                }
            }
        }

        binding.btnExportCsv.setOnClickListener {
            lifecycleScope.launch {
                val start = startTimestamp
                val end = endTimestamp
                if (start == null || end == null) {
                    android.widget.Toast.makeText(requireContext(), "Please select start and end dates", android.widget.Toast.LENGTH_SHORT).show()
                    return@launch
                }
                val selected = binding.spinnerReportType.selectedItem as ReportType
                when (selected) {
                    ReportType.SALES_BY_PRODUCT -> {
                        val map = reportingViewModel.getSalesByProductForRange(start, end)
                        // Create CSV string and save to cache
                        val csv = StringBuilder()
                        csv.append("product_id,product_name,quantity,revenue_cents\n")
                        for ((_, v) in map) {
                            csv.append("${v.productId},\"${v.productName}\",${v.quantity},${v.revenueCents}\n")
                        }
                        val file = java.io.File(requireContext().cacheDir, "sales_by_product_${start}_to_${end}.csv")
                        file.writeText(csv.toString())
                        android.widget.Toast.makeText(requireContext(), "CSV exported: ${file.absolutePath}", android.widget.Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        android.widget.Toast.makeText(requireContext(), "CSV export for this report type is not implemented yet", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    binding.btnSalesByProduct.setOnClickListener {
            // Initialize RecyclerView and adapter lazily
            if (productAdapter == null) {
                val rv = binding.rvResults
                rv.layoutManager = LinearLayoutManager(requireContext())
                productAdapter = ProductSummaryAdapter(emptyList())
                rv.adapter = productAdapter
            }

            lifecycleScope.launch {
                val map = reportingViewModel.getSalesByProductToday()
                val list = map.values.toList()
                productAdapter?.update(list)
                // Simple feedback: count of products
                android.widget.Toast.makeText(requireContext(), "Products sold: ${list.size}", android.widget.Toast.LENGTH_LONG).show()
            }
        }

        // Start / End date pickers
        binding.btnStartDate.setOnClickListener {
            showDatePicker(startTimestamp) { ts ->
                startTimestamp = ts
                val fmt = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
                binding.btnStartDate.text = fmt.format(java.util.Date(ts))
            }
        }

        binding.btnEndDate.setOnClickListener {
            showDatePicker(endTimestamp) { ts ->
                // Set to end of day for the selected date
                val c = java.util.Calendar.getInstance()
                c.timeInMillis = ts
                c.set(java.util.Calendar.HOUR_OF_DAY, 23)
                c.set(java.util.Calendar.MINUTE, 59)
                c.set(java.util.Calendar.SECOND, 59)
                c.set(java.util.Calendar.MILLISECOND, 999)
                endTimestamp = c.timeInMillis
                val fmt = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
                binding.btnEndDate.text = fmt.format(java.util.Date(endTimestamp!!))
            }
        }

        // Shift controls
        binding.btnStartShift.setOnClickListener {
            lifecycleScope.launch {
                val shift = reportingViewModel.startShift(null)
                if (shift != null) {
                    android.widget.Toast.makeText(requireContext(), "Shift started for ${shift.username}", android.widget.Toast.LENGTH_LONG).show()
                } else {
                    android.widget.Toast.makeText(requireContext(), "Failed to start shift (no user?)", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnEndShift.setOnClickListener {
            lifecycleScope.launch {
                val shift = reportingViewModel.endShiftForCurrentUser()
                if (shift != null) {
                    android.widget.Toast.makeText(requireContext(), "Shift ended for ${shift.username}", android.widget.Toast.LENGTH_LONG).show()
                } else {
                    android.widget.Toast.makeText(requireContext(), "No open shift to end", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupUI() {
        binding.toolbar.title = "Reporting & Analytics"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        // TODO: Implement sales reporting and data export configuration
    }

    // Stored start/end timestamps (milliseconds)
    private var startTimestamp: Long? = null
    private var endTimestamp: Long? = null

    private fun showDatePicker(initial: Long?, onChosen: (Long) -> Unit) {
        val cal = java.util.Calendar.getInstance()
        if (initial != null) cal.timeInMillis = initial
        val y = cal.get(java.util.Calendar.YEAR)
        val m = cal.get(java.util.Calendar.MONTH)
        val d = cal.get(java.util.Calendar.DAY_OF_MONTH)

        val dp = android.app.DatePickerDialog(requireContext(), { _, yy, mm, dd ->
            val c = java.util.Calendar.getInstance()
            c.set(java.util.Calendar.YEAR, yy)
            c.set(java.util.Calendar.MONTH, mm)
            c.set(java.util.Calendar.DAY_OF_MONTH, dd)
            c.set(java.util.Calendar.HOUR_OF_DAY, 0)
            c.set(java.util.Calendar.MINUTE, 0)
            c.set(java.util.Calendar.SECOND, 0)
            c.set(java.util.Calendar.MILLISECOND, 0)
            onChosen(c.timeInMillis)
        }, y, m, d)
        dp.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}