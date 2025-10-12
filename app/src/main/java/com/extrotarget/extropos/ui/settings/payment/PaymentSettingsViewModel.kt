package com.extrotarget.extropos.ui.settings.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.data.model.PaymentConfiguration
import com.extrotarget.extropos.data.model.PaymentMethod
import com.extrotarget.extropos.data.model.TaxRate
import com.extrotarget.extropos.data.model.FinancialSettings
import com.extrotarget.extropos.data.repository.PaymentConfigurationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentSettingsViewModel @Inject constructor(
    private val paymentConfigurationRepository: PaymentConfigurationRepository
) : ViewModel() {

    private val _paymentConfiguration = MutableStateFlow(PaymentConfiguration())
    val paymentConfiguration: StateFlow<PaymentConfiguration> = _paymentConfiguration

    private val _saveStatus = MutableStateFlow<SaveStatus>(SaveStatus.Idle)
    val saveStatus: StateFlow<SaveStatus> = _saveStatus

    sealed class SaveStatus {
        object Idle : SaveStatus()
        object Saving : SaveStatus()
        object Success : SaveStatus()
        data class Error(val message: String) : SaveStatus()
    }

    init {
        loadConfiguration()
    }

    fun loadConfiguration() {
        viewModelScope.launch {
            try {
                val configuration = paymentConfigurationRepository.getPaymentConfiguration()
                _paymentConfiguration.value = configuration
            } catch (e: Exception) {
                _paymentConfiguration.value = PaymentConfiguration() // Use defaults if loading fails
            }
        }
    }

    fun saveConfiguration(configuration: PaymentConfiguration) {
        viewModelScope.launch {
            try {
                _saveStatus.value = SaveStatus.Saving
                paymentConfigurationRepository.savePaymentConfiguration(configuration)
                _paymentConfiguration.value = configuration
                _saveStatus.value = SaveStatus.Success
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(2000)
                _saveStatus.value = SaveStatus.Idle
                
            } catch (e: Exception) {
                _saveStatus.value = SaveStatus.Error(e.message ?: "Unknown error occurred")
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(3000)
                _saveStatus.value = SaveStatus.Idle
            }
        }
    }

    fun updatePaymentMethod(paymentMethod: PaymentMethod) {
        val currentConfig = _paymentConfiguration.value
        val updatedMethods = currentConfig.paymentMethods.map { method ->
            if (method.id == paymentMethod.id) paymentMethod else method
        }
        val updatedConfig = currentConfig.copy(paymentMethods = updatedMethods)
        _paymentConfiguration.value = updatedConfig
    }

    fun togglePaymentMethod(paymentMethodId: String, isEnabled: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedMethods = currentConfig.paymentMethods.map { method ->
            if (method.id == paymentMethodId) {
                method.copy(isEnabled = isEnabled)
            } else method
        }
        val updatedConfig = currentConfig.copy(paymentMethods = updatedMethods)
        _paymentConfiguration.value = updatedConfig
    }

    fun addPaymentMethod(paymentMethod: PaymentMethod) {
        val currentConfig = _paymentConfiguration.value
        val updatedMethods = currentConfig.paymentMethods + paymentMethod
        val updatedConfig = currentConfig.copy(paymentMethods = updatedMethods)
        _paymentConfiguration.value = updatedConfig
    }

    fun removePaymentMethod(paymentMethodId: String) {
        val currentConfig = _paymentConfiguration.value
        val updatedMethods = currentConfig.paymentMethods.filter { it.id != paymentMethodId }
        val updatedConfig = currentConfig.copy(paymentMethods = updatedMethods)
        _paymentConfiguration.value = updatedConfig
    }

    fun updateTaxRate(taxRate: TaxRate) {
        val currentConfig = _paymentConfiguration.value
        val updatedTaxRates = currentConfig.taxRates.map { rate ->
            if (rate.id == taxRate.id) taxRate else rate
        }
        val updatedConfig = currentConfig.copy(taxRates = updatedTaxRates)
        _paymentConfiguration.value = updatedConfig
    }

    fun toggleTaxRate(taxRateId: String, isEnabled: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedTaxRates = currentConfig.taxRates.map { rate ->
            if (rate.id == taxRateId) {
                rate.copy(isEnabled = isEnabled)
            } else rate
        }
        val updatedConfig = currentConfig.copy(taxRates = updatedTaxRates)
        _paymentConfiguration.value = updatedConfig
    }

    fun setDefaultTaxRate(taxRateId: String) {
        val currentConfig = _paymentConfiguration.value
        val updatedTaxRates = currentConfig.taxRates.map { rate ->
            rate.copy(isDefault = rate.id == taxRateId)
        }
        val updatedConfig = currentConfig.copy(taxRates = updatedTaxRates)
        _paymentConfiguration.value = updatedConfig
    }

    fun addTaxRate(taxRate: TaxRate) {
        val currentConfig = _paymentConfiguration.value
        val updatedTaxRates = currentConfig.taxRates + taxRate
        val updatedConfig = currentConfig.copy(taxRates = updatedTaxRates)
        _paymentConfiguration.value = updatedConfig
    }

    fun removeTaxRate(taxRateId: String) {
        val currentConfig = _paymentConfiguration.value
        val updatedTaxRates = currentConfig.taxRates.filter { it.id != taxRateId }
        val updatedConfig = currentConfig.copy(taxRates = updatedTaxRates)
        _paymentConfiguration.value = updatedConfig
    }

    fun updateFinancialSettings(financialSettings: FinancialSettings) {
        val currentConfig = _paymentConfiguration.value
        val updatedConfig = currentConfig.copy(financialSettings = financialSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun updateCurrency(currency: String) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(currency = currency, currencySymbol = currency)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun toggleTipping(enableTipping: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(enableTipping = enableTipping)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun updateDefaultTipPercentage(tipPercentage: Double) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(defaultTipPercentage = tipPercentage)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun toggleServiceCharge(enableServiceCharge: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(enableServiceCharge = enableServiceCharge)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun updateServiceChargePercentage(serviceChargePercentage: Double) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(serviceChargePercentage = serviceChargePercentage)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun toggleDiscounts(enableDiscounts: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(enableDiscounts = enableDiscounts)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun updateMaxDiscountPercentage(maxDiscountPercentage: Double) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(maxDiscountPercentage = maxDiscountPercentage)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun toggleManagerApprovalForDiscount(requireApproval: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(requireManagerApprovalForDiscount = requireApproval)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun toggleRefunds(enableRefunds: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(enableRefunds = enableRefunds)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun updateRefundTimeLimit(refundTimeLimit: Int) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(refundTimeLimit = refundTimeLimit)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun togglePartialRefunds(enablePartialRefunds: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(enablePartialRefunds = enablePartialRefunds)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun toggleStoreCredit(enableStoreCredit: Boolean) {
        val currentConfig = _paymentConfiguration.value
        val updatedSettings = currentConfig.financialSettings.copy(enableStoreCredit = enableStoreCredit)
        val updatedConfig = currentConfig.copy(financialSettings = updatedSettings)
        _paymentConfiguration.value = updatedConfig
    }

    fun resetToDefaults() {
        viewModelScope.launch {
            try {
                paymentConfigurationRepository.resetToDefaults()
                _paymentConfiguration.value = PaymentConfiguration()
                _saveStatus.value = SaveStatus.Success
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(2000)
                _saveStatus.value = SaveStatus.Idle
                
            } catch (e: Exception) {
                _saveStatus.value = SaveStatus.Error("Failed to reset settings: ${e.message}")
                
                // Reset status after a short delay
                kotlinx.coroutines.delay(3000)
                _saveStatus.value = SaveStatus.Idle
            }
        }
    }
}