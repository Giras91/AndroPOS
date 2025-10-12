package com.extrotarget.extropos.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.extrotarget.extropos.data.model.PaymentConfiguration
import com.extrotarget.extropos.data.model.PaymentMethod
import com.extrotarget.extropos.data.model.TaxRate
import com.extrotarget.extropos.data.model.FinancialSettings
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing payment and financial settings persistence
 */
@Singleton
class PaymentConfigurationRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "payment_configuration", Context.MODE_PRIVATE
    )
    
    companion object {
        private const val KEY_PAYMENT_CONFIGURATION = "payment_configuration_json"
        private const val KEY_PAYMENT_METHODS = "payment_methods_json"
        private const val KEY_TAX_RATES = "tax_rates_json"
        private const val KEY_FINANCIAL_SETTINGS = "financial_settings_json"
        
        // Individual quick access keys
        private const val KEY_CURRENCY = "currency"
        private const val KEY_DEFAULT_TAX_RATE = "default_tax_rate"
        private const val KEY_ENABLE_TIPPING = "enable_tipping"
        private const val KEY_DEFAULT_TIP_PERCENTAGE = "default_tip_percentage"
        private const val KEY_ENABLE_DISCOUNTS = "enable_discounts"
        private const val KEY_MAX_DISCOUNT_PERCENTAGE = "max_discount_percentage"
    private const val KEY_ENABLE_TAX = "enable_tax"
    private const val KEY_MANUAL_TAX_PERCENTAGE = "manual_tax_percentage"
    }
    
    /**
     * Save complete payment configuration
     */
    fun savePaymentConfiguration(configuration: PaymentConfiguration) {
        prefs.edit().apply {
            // Save complete configuration as JSON
            putString(KEY_PAYMENT_CONFIGURATION, gson.toJson(configuration))
            
            // Save individual components
            putString(KEY_PAYMENT_METHODS, gson.toJson(configuration.paymentMethods))
            putString(KEY_TAX_RATES, gson.toJson(configuration.taxRates))
            putString(KEY_FINANCIAL_SETTINGS, gson.toJson(configuration.financialSettings))
            
            // Save frequently accessed values for quick retrieval
            putString(KEY_CURRENCY, configuration.financialSettings.currency)
            putBoolean(KEY_ENABLE_TIPPING, configuration.financialSettings.enableTipping)
            putFloat(KEY_DEFAULT_TIP_PERCENTAGE, configuration.financialSettings.defaultTipPercentage.toFloat())
            putBoolean(KEY_ENABLE_DISCOUNTS, configuration.financialSettings.enableDiscounts)
            putFloat(KEY_MAX_DISCOUNT_PERCENTAGE, configuration.financialSettings.maxDiscountPercentage.toFloat())
            putBoolean(KEY_ENABLE_TAX, configuration.financialSettings.enableTax)
            putFloat(KEY_MANUAL_TAX_PERCENTAGE, configuration.financialSettings.manualTaxPercentage.toFloat())
            
            // Save default tax rate ID
            configuration.getDefaultTaxRate()?.let { taxRate ->
                putString(KEY_DEFAULT_TAX_RATE, taxRate.id)
            }
            
            apply()
        }
    }
    
    /**
     * Load complete payment configuration
     */
    fun getPaymentConfiguration(): PaymentConfiguration {
        val configJson = prefs.getString(KEY_PAYMENT_CONFIGURATION, null)
        
        return if (configJson != null) {
            try {
                gson.fromJson(configJson, PaymentConfiguration::class.java)
            } catch (e: Exception) {
                // If JSON parsing fails, create from individual components
                createFromIndividualComponents()
            }
        } else {
            // Create from individual components or use defaults
            createFromIndividualComponents()
        }
    }
    
    private fun createFromIndividualComponents(): PaymentConfiguration {
        val paymentMethods = getPaymentMethods()
        val taxRates = getTaxRates()
        val financialSettings = getFinancialSettings()
        
        return PaymentConfiguration(
            paymentMethods = paymentMethods,
            taxRates = taxRates,
            financialSettings = financialSettings
        )
    }
    
    /**
     * Get payment methods only
     */
    fun getPaymentMethods(): List<PaymentMethod> {
        val methodsJson = prefs.getString(KEY_PAYMENT_METHODS, null)
        return if (methodsJson != null) {
            try {
                val typeToken = object : com.google.gson.reflect.TypeToken<List<PaymentMethod>>() {}
                gson.fromJson(methodsJson, typeToken.type)
            } catch (e: Exception) {
                PaymentMethod.getDefaultPaymentMethods()
            }
        } else {
            PaymentMethod.getDefaultPaymentMethods()
        }
    }
    
    /**
     * Get tax rates only
     */
    fun getTaxRates(): List<TaxRate> {
        val taxRatesJson = prefs.getString(KEY_TAX_RATES, null)
        return if (taxRatesJson != null) {
            try {
                val typeToken = object : com.google.gson.reflect.TypeToken<List<TaxRate>>() {}
                gson.fromJson(taxRatesJson, typeToken.type)
            } catch (e: Exception) {
                TaxRate.getDefaultTaxRates()
            }
        } else {
            TaxRate.getDefaultTaxRates()
        }
    }
    
    /**
     * Get financial settings only
     */
    fun getFinancialSettings(): FinancialSettings {
        val settingsJson = prefs.getString(KEY_FINANCIAL_SETTINGS, null)
        return if (settingsJson != null) {
            try {
                gson.fromJson(settingsJson, FinancialSettings::class.java)
            } catch (e: Exception) {
                createFinancialSettingsFromIndividualFields()
            }
        } else {
            createFinancialSettingsFromIndividualFields()
        }
    }
    
    private fun createFinancialSettingsFromIndividualFields(): FinancialSettings {
        return FinancialSettings(
            currency = prefs.getString(KEY_CURRENCY, "RM") ?: "RM",
            enableTipping = prefs.getBoolean(KEY_ENABLE_TIPPING, true),
            defaultTipPercentage = prefs.getFloat(KEY_DEFAULT_TIP_PERCENTAGE, 10.0f).toDouble(),
            enableDiscounts = prefs.getBoolean(KEY_ENABLE_DISCOUNTS, true),
            maxDiscountPercentage = prefs.getFloat(KEY_MAX_DISCOUNT_PERCENTAGE, 50.0f).toDouble()
            , enableTax = prefs.getBoolean(KEY_ENABLE_TAX, true)
            , manualTaxPercentage = prefs.getFloat(KEY_MANUAL_TAX_PERCENTAGE, -1.0f).toDouble()
        )
    }

    fun isTaxEnabled(): Boolean {
        return prefs.getBoolean(KEY_ENABLE_TAX, true)
    }

    fun getManualTaxPercentage(): Double {
        return prefs.getFloat(KEY_MANUAL_TAX_PERCENTAGE, -1.0f).toDouble()
    }
    
    /**
     * Update specific payment method
     */
    fun updatePaymentMethod(paymentMethod: PaymentMethod) {
        val configuration = getPaymentConfiguration()
        val updatedMethods = configuration.paymentMethods.map { method ->
            if (method.id == paymentMethod.id) paymentMethod else method
        }
        val updatedConfiguration = configuration.copy(paymentMethods = updatedMethods)
        savePaymentConfiguration(updatedConfiguration)
    }
    
    /**
     * Update specific tax rate
     */
    fun updateTaxRate(taxRate: TaxRate) {
        val configuration = getPaymentConfiguration()
        val updatedTaxRates = configuration.taxRates.map { rate ->
            if (rate.id == taxRate.id) taxRate else rate
        }
        val updatedConfiguration = configuration.copy(taxRates = updatedTaxRates)
        savePaymentConfiguration(updatedConfiguration)
    }
    
    /**
     * Quick access methods for POS operations
     */
    fun getCurrency(): String {
        return prefs.getString(KEY_CURRENCY, "RM") ?: "RM"
    }
    
    fun getDefaultTaxRateId(): String? {
        return prefs.getString(KEY_DEFAULT_TAX_RATE, null)
    }
    
    fun getDefaultTaxRate(): TaxRate? {
        val defaultId = getDefaultTaxRateId()
        return if (defaultId != null) {
            getTaxRates().find { it.id == defaultId }
        } else {
            getTaxRates().find { it.isDefault }
        }
    }
    
    fun isTippingEnabled(): Boolean {
        return prefs.getBoolean(KEY_ENABLE_TIPPING, true)
    }
    
    fun getDefaultTipPercentage(): Double {
        return prefs.getFloat(KEY_DEFAULT_TIP_PERCENTAGE, 10.0f).toDouble()
    }
    
    fun isDiscountEnabled(): Boolean {
        return prefs.getBoolean(KEY_ENABLE_DISCOUNTS, true)
    }
    
    fun getMaxDiscountPercentage(): Double {
        return prefs.getFloat(KEY_MAX_DISCOUNT_PERCENTAGE, 50.0f).toDouble()
    }
    
    /**
     * Reset to default configuration
     */
    fun resetToDefaults() {
        prefs.edit().clear().apply()
    }
    
    /**
     * Check if configuration has been customized
     */
    fun hasCustomConfiguration(): Boolean {
        return prefs.getString(KEY_PAYMENT_CONFIGURATION, null) != null
    }
}