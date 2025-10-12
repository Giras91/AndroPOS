package com.extrotarget.extropos.ui.settings.hardware

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.extrotarget.extropos.R
import com.extrotarget.extropos.data.model.ReceiptSettings
import com.extrotarget.extropos.databinding.FragmentHardwareSettingsBinding
import com.extrotarget.extropos.printer.service.GlobalPrinterService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HardwareSettingsFragment : Fragment() {

    private var _binding: FragmentHardwareSettingsBinding? = null
    private val binding get() = _binding!!
    
    @Inject
    lateinit var printerService: GlobalPrinterService
    
    private val viewModel: HardwareSettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHardwareSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupReceiptCustomization()
        loadCurrentSettings()
        observeViewModel()
    }

    private fun setupUI() {
        binding.toolbar.title = "Hardware & Device Settings"
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupReceiptCustomization() {
        // Paper size radio group listener
        binding.paperSizeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val newPaperSize = when (checkedId) {
                R.id.paper58mmRadio -> ReceiptSettings.PaperSize.MM_58
                R.id.paper80mmRadio -> ReceiptSettings.PaperSize.MM_80
                else -> ReceiptSettings.PaperSize.MM_58
            }
            viewModel.updatePaperSize(newPaperSize)
        }

        // Preview receipt button
        binding.previewReceiptButton.setOnClickListener {
            showReceiptPreview()
        }

        // Print sample receipt button
        binding.printSampleButton.setOnClickListener {
            printSampleReceipt()
        }

        // Save settings button
        binding.saveSettingsButton.setOnClickListener {
            saveCurrentSettings()
        }
    }

    private fun loadCurrentSettings() {
        viewModel.loadSettings()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.receiptSettings.collect { settings ->
                updateUIFromSettings(settings)
            }
        }
        
        lifecycleScope.launch {
            viewModel.saveStatus.collect { status ->
                when (status) {
                    is HardwareSettingsViewModel.SaveStatus.Success -> {
                        Toast.makeText(requireContext(), "Settings saved successfully", Toast.LENGTH_SHORT).show()
                    }
                    is HardwareSettingsViewModel.SaveStatus.Error -> {
                        Toast.makeText(requireContext(), "Failed to save settings: ${status.message}", Toast.LENGTH_LONG).show()
                    }
                    HardwareSettingsViewModel.SaveStatus.Idle -> { /* No action needed */ }
                }
            }
        }
    }

    private fun updateUIFromSettings(settings: ReceiptSettings) {
        // Update paper size radio buttons
        when (settings.paperSize) {
            ReceiptSettings.PaperSize.MM_58 -> binding.paper58mmRadio.isChecked = true
            ReceiptSettings.PaperSize.MM_80 -> binding.paper80mmRadio.isChecked = true
        }

        // Update text fields
        binding.storeNameEditText.setText(settings.storeName)
        binding.storeAddressEditText.setText(settings.storeAddress)
        binding.phoneEditText.setText(settings.phoneNumber)
        binding.footerEditText.setText(settings.footerMessage)

        // Update checkboxes
        binding.showLogoCheckbox.isChecked = settings.showLogo
        binding.showQrCodeCheckbox.isChecked = settings.showQrCode
        binding.showTaxBreakdownCheckbox.isChecked = settings.showTaxBreakdown
        binding.autoCutCheckbox.isChecked = settings.autoCut
        binding.duplicateReceiptCheckbox.isChecked = settings.duplicateReceipt
    }

    private fun getCurrentSettingsFromUI(): ReceiptSettings {
        val selectedPaperSize = when (binding.paperSizeRadioGroup.checkedRadioButtonId) {
            R.id.paper58mmRadio -> ReceiptSettings.PaperSize.MM_58
            R.id.paper80mmRadio -> ReceiptSettings.PaperSize.MM_80
            else -> ReceiptSettings.PaperSize.MM_58
        }

        return ReceiptSettings(
            paperSize = selectedPaperSize,
            charactersPerLine = selectedPaperSize.charsPerLine,
            storeName = binding.storeNameEditText.text.toString().trim(),
            storeAddress = binding.storeAddressEditText.text.toString().trim(),
            phoneNumber = binding.phoneEditText.text.toString().trim(),
            footerMessage = binding.footerEditText.text.toString().trim(),
            showLogo = binding.showLogoCheckbox.isChecked,
            showQrCode = binding.showQrCodeCheckbox.isChecked,
            showTaxBreakdown = binding.showTaxBreakdownCheckbox.isChecked,
            autoCut = binding.autoCutCheckbox.isChecked,
            duplicateReceipt = binding.duplicateReceiptCheckbox.isChecked
        )
    }

    private fun saveCurrentSettings() {
        val settings = getCurrentSettingsFromUI()
        viewModel.saveSettings(settings)
    }

    private fun showReceiptPreview() {
        val settings = getCurrentSettingsFromUI()
        val previewText = generateSampleReceiptText(settings)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Receipt Preview (${settings.paperSize.displayName})")
            .setMessage(previewText)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setNeutralButton("Print Sample") { _, _ -> printSampleReceipt() }
            .show()
    }

    private fun printSampleReceipt() {
        if (!printerService.isConnected()) {
            Toast.makeText(requireContext(), "Printer not connected. Please connect printer first.", Toast.LENGTH_LONG).show()
            return
        }

        val settings = getCurrentSettingsFromUI()
        lifecycleScope.launch {
            val success = printerService.printCustomizedReceipt(settings, generateSampleReceiptData())
            
            if (success) {
                Toast.makeText(requireContext(), "Sample receipt sent to printer", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Failed to print sample receipt", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun generateSampleReceiptText(settings: ReceiptSettings): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val currentDateTime = dateFormat.format(Date())
        
        val sb = StringBuilder()
        
        // Header with store info
        if (settings.showLogo) {
            sb.append(settings.centerText("[STORE LOGO]")).append("\n")
        }
        sb.append(settings.centerText(settings.formatTextForWidth(settings.storeName))).append("\n")
        sb.append(settings.centerText(settings.formatTextForWidth(settings.storeAddress))).append("\n")
        sb.append(settings.centerText("Tel: ${settings.phoneNumber}")).append("\n\n")
        
        // Receipt details
        sb.append(settings.getSeparatorLine()).append("\n")
        sb.append("Receipt #: SAMPLE001\n")
        sb.append("Date: $currentDateTime\n")
        sb.append("Cashier: Demo User\n")
        sb.append(settings.getSeparatorLine()).append("\n\n")
        
        // Sample items
        sb.append("ITEMS:\n")
        sb.append("Nasi Lemak x2        ${settings.currency}12.00\n")
        sb.append("Teh Tarik x1         ${settings.currency}3.50\n")
        sb.append("Roti Canai x3        ${settings.currency}4.50\n")
        sb.append(settings.getSeparatorLine()).append("\n")
        
        // Totals
        sb.append("Subtotal:            ${settings.currency}20.00\n")
        if (settings.showTaxBreakdown) {
            sb.append("SST (6%):            ${settings.currency}1.20\n")
        }
        sb.append("TOTAL:               ${settings.currency}21.20\n")
        sb.append("Cash:                ${settings.currency}25.00\n")
        sb.append("Change:              ${settings.currency}3.80\n")
        sb.append(settings.getSeparatorLine()).append("\n\n")
        
        // Footer
        if (settings.showQrCode) {
            sb.append(settings.centerText("[QR CODE]")).append("\n")
            sb.append(settings.centerText("Scan for feedback")).append("\n\n")
        }
        
        sb.append(settings.centerText(settings.formatTextForWidth(settings.footerMessage))).append("\n\n")
        sb.append(settings.centerText("Paper size: ${settings.paperSize.displayName}")).append("\n")
        sb.append(settings.centerText("Characters per line: ${settings.charactersPerLine}"))
        
        return sb.toString()
    }

    private fun generateSampleReceiptData(): Map<String, Any> {
        return mapOf(
            "receiptNumber" to "SAMPLE001",
            "cashier" to "Demo User",
            "items" to listOf(
                mapOf("name" to "Nasi Lemak", "quantity" to 2, "price" to 1200),
                mapOf("name" to "Teh Tarik", "quantity" to 1, "price" to 350),
                mapOf("name" to "Roti Canai", "quantity" to 3, "price" to 450)
            ),
            "subtotal" to 2000,
            "tax" to 120,
            "total" to 2120,
            "cash" to 2500,
            "change" to 380
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}