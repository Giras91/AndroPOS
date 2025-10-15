package com.extrotarget.extropos.ui.settings.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.extrotarget.extropos.data.model.PaymentMethod
import com.extrotarget.extropos.data.model.TaxRate
import kotlinx.coroutines.flow.collect
// Use runtime check for debug flag to avoid compile-time BuildConfig resolution issues
import kotlinx.coroutines.launch
import com.extrotarget.extropos.databinding.FragmentPaymentSettingsBinding

class PaymentSettingsFragment : Fragment() {

    private var _binding: FragmentPaymentSettingsBinding? = null
    private val binding get() = _binding!!

    private val vm: PaymentSettingsViewModel by viewModels()

    private lateinit var paymentMethodsAdapter: PaymentMethodsAdapter
    private lateinit var taxRatesAdapter: TaxRatesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding.toolbar.title = "Payment & Financial Settings"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        // Setup adapters
        paymentMethodsAdapter = PaymentMethodsAdapter(
            onToggle = { method, enabled -> vm.togglePaymentMethod(method.id, enabled) },
            onEdit = { method -> showAddEditPaymentMethodDialog(method) },
            onDelete = { method ->
                androidx.appcompat.app.AlertDialog.Builder(requireContext())
                    .setTitle("Delete payment method")
                    .setMessage("Are you sure you want to delete '${method.displayName}'?")
                    .setPositiveButton("Delete") { _, _ ->
                        vm.removePaymentMethod(method.id)
                        // Immediately persist change
                        vm.saveConfiguration(vm.paymentConfiguration.value)
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        )

        taxRatesAdapter = TaxRatesAdapter(
            onToggle = { rate, enabled -> vm.toggleTaxRate(rate.id, enabled) },
            onSetDefault = { rate -> vm.setDefaultTaxRate(rate.id) }
        )

        binding.paymentMethodsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = paymentMethodsAdapter
        }

        binding.taxRatesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taxRatesAdapter
        }

        binding.saveSettingsButton.setOnClickListener {
            lifecycleScope.launch {
                // Read manual values from UI and update financial settings in ViewModel
                val current = vm.paymentConfiguration.value
                val fs = current.financialSettings.copy(
                    enableTax = binding.enableTaxCheckbox.isChecked,
                    manualTaxPercentage = binding.manualTaxEditText.text.toString().toDoubleOrNull() ?: -1.0,
                    enableServiceCharge = binding.enableServiceChargeCheckbox.isChecked,
                    serviceChargePercentage = binding.serviceChargeEditText.text.toString().toDoubleOrNull() ?: current.financialSettings.serviceChargePercentage
                )
                vm.updateFinancialSettings(fs)

                vm.saveConfiguration(vm.paymentConfiguration.value)
                // Simple feedback
                android.widget.Toast.makeText(requireContext(), "Settings saved", android.widget.Toast.LENGTH_SHORT).show()
            }
        }

        binding.addPaymentMethodButton.setOnClickListener {
            showAddEditPaymentMethodDialog(null)
        }

        binding.addTaxRateButton.setOnClickListener {
            showAddTaxRateDialog()
        }

        // Debug UI: only visible in debug builds
        val isDebugBuild = try {
            val bc = Class.forName("${requireContext().packageName}.BuildConfig")
            val f = bc.getField("DEBUG")
            f.getBoolean(null)
        } catch (e: Exception) {
            false
        }

        if (isDebugBuild) {
            binding.debugCard.visibility = View.VISIBLE
            binding.runSmokeTestButton.setOnClickListener {
                // Launch debug activity which will run the smoke test and export DB
                // Use dynamic class name to avoid compile-time dependency on a debug-only activity
                val intent = android.content.Intent().setClassName(requireContext(), "com.extrotarget.extropos.DebugSmokeActivity")
                try {
                    startActivity(intent)
                } catch (_: Exception) {
                    // Activity not found or other issue in non-debug builds; ignore gracefully
                }
            }

            // Secret gesture: long-press toolbar title to toggle debug card
            binding.toolbar.setOnLongClickListener {
                binding.debugCard.visibility = if (binding.debugCard.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                true
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            vm.paymentConfiguration.collect { config ->
                // Update adapters
                paymentMethodsAdapter.submitList(config.paymentMethods)
                taxRatesAdapter.submitList(config.taxRates)

                // Update basic financial settings UI (match IDs in layout)
                binding.currencyEditText.setText(config.financialSettings.currency)
                binding.enableTippingCheckbox.isChecked = config.financialSettings.enableTipping
                binding.defaultTipEditText.setText(config.financialSettings.defaultTipPercentage.toString())
                binding.enableServiceChargeCheckbox.isChecked = config.financialSettings.enableServiceCharge
                binding.serviceChargeEditText.setText(config.financialSettings.serviceChargePercentage.toString())
                binding.enableDiscountsCheckbox.isChecked = config.financialSettings.enableDiscounts
                binding.maxDiscountEditText.setText(config.financialSettings.maxDiscountPercentage.toString())
                binding.enableRefundsCheckbox.isChecked = config.financialSettings.enableRefunds
                binding.refundTimeLimitEditText.setText(config.financialSettings.refundTimeLimit.toString())
                binding.enablePartialRefundsCheckbox.isChecked = config.financialSettings.enablePartialRefunds
                binding.enableStoreCreditCheckbox.isChecked = config.financialSettings.enableStoreCredit
            }
        }

        lifecycleScope.launch {
            vm.saveStatus.collect { status ->
                when (status) {
                    is PaymentSettingsViewModel.SaveStatus.Saving -> android.widget.Toast.makeText(requireContext(), "Saving...", android.widget.Toast.LENGTH_SHORT).show()
                    is PaymentSettingsViewModel.SaveStatus.Success -> android.widget.Toast.makeText(requireContext(), "Settings saved", android.widget.Toast.LENGTH_SHORT).show()
                    is PaymentSettingsViewModel.SaveStatus.Error -> android.widget.Toast.makeText(requireContext(), "Error: ${status.message}", android.widget.Toast.LENGTH_LONG).show()
                    else -> { /* no-op */ }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showAddEditPaymentMethodDialog(edit: com.extrotarget.extropos.data.model.PaymentMethod?) {
        val dialogView = layoutInflater.inflate(com.extrotarget.extropos.R.layout.dialog_add_payment_method, null)
        val nameInput = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(com.extrotarget.extropos.R.id.pmName)
        val displayInput = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(com.extrotarget.extropos.R.id.pmDisplayName)
        val enabledBox = dialogView.findViewById<android.widget.CheckBox>(com.extrotarget.extropos.R.id.pmEnabled)
        val feeInput = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(com.extrotarget.extropos.R.id.pmFee)

        if (edit != null) {
            nameInput.setText(edit.name)
            displayInput.setText(edit.displayName)
            enabledBox.isChecked = edit.isEnabled
            feeInput.setText(edit.processingFeePercentage.toString())
        }

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(if (edit == null) "Add Payment Method" else "Edit Payment Method")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val id = edit?.id ?: java.util.UUID.randomUUID().toString()
                val method = com.extrotarget.extropos.data.model.PaymentMethod(
                    id = id,
                    name = nameInput.text.toString(),
                    displayName = displayInput.text.toString(),
                    isEnabled = enabledBox.isChecked,
                    processingFeePercentage = feeInput.text.toString().toDoubleOrNull() ?: 0.0
                )
                if (edit == null) vm.addPaymentMethod(method) else vm.updatePaymentMethod(method)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showAddTaxRateDialog() {
        val dialogView = layoutInflater.inflate(com.extrotarget.extropos.R.layout.dialog_add_tax_rate, null)
        val nameInput = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(com.extrotarget.extropos.R.id.trName)
        val percentInput = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(com.extrotarget.extropos.R.id.trPercent)

        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Add Tax Rate")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val tr = com.extrotarget.extropos.data.model.TaxRate(
                    id = java.util.UUID.randomUUID().toString(),
                    name = nameInput.text.toString(),
                    displayName = nameInput.text.toString(),
                    rate = percentInput.text.toString().toDoubleOrNull() ?: 0.0
                )
                vm.addTaxRate(tr)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}