package com.extrotarget.extropos.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.extrotarget.extropos.data.model.ReceiptSettings
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for managing receipt settings persistence
 */
@Singleton
class ReceiptSettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "receipt_settings", Context.MODE_PRIVATE
    )
    
    companion object {
        private const val KEY_RECEIPT_SETTINGS = "receipt_settings_json"
        private const val KEY_PAPER_SIZE = "paper_size"
        private const val KEY_STORE_NAME = "store_name"
        private const val KEY_STORE_ADDRESS = "store_address"
        private const val KEY_PHONE_NUMBER = "phone_number"
        private const val KEY_FOOTER_MESSAGE = "footer_message"
        private const val KEY_SHOW_LOGO = "show_logo"
        private const val KEY_SHOW_QR_CODE = "show_qr_code"
        private const val KEY_SHOW_TAX_BREAKDOWN = "show_tax_breakdown"
        private const val KEY_AUTO_CUT = "auto_cut"
        private const val KEY_DUPLICATE_RECEIPT = "duplicate_receipt"
    }
    
    /**
     * Save receipt settings to SharedPreferences
     */
    fun saveReceiptSettings(settings: ReceiptSettings) {
        prefs.edit().apply {
            // Save as JSON for complex object
            putString(KEY_RECEIPT_SETTINGS, gson.toJson(settings))
            
            // Also save individual fields for easy access
            putString(KEY_PAPER_SIZE, settings.paperSize.name)
            putString(KEY_STORE_NAME, settings.storeName)
            putString(KEY_STORE_ADDRESS, settings.storeAddress)
            putString(KEY_PHONE_NUMBER, settings.phoneNumber)
            putString(KEY_FOOTER_MESSAGE, settings.footerMessage)
            putBoolean(KEY_SHOW_LOGO, settings.showLogo)
            putBoolean(KEY_SHOW_QR_CODE, settings.showQrCode)
            putBoolean(KEY_SHOW_TAX_BREAKDOWN, settings.showTaxBreakdown)
            putBoolean(KEY_AUTO_CUT, settings.autoCut)
            putBoolean(KEY_DUPLICATE_RECEIPT, settings.duplicateReceipt)
            apply()
        }
    }
    
    /**
     * Load receipt settings from SharedPreferences
     */
    fun getReceiptSettings(): ReceiptSettings {
        val jsonSettings = prefs.getString(KEY_RECEIPT_SETTINGS, null)
        
        return if (jsonSettings != null) {
            try {
                gson.fromJson(jsonSettings, ReceiptSettings::class.java)
            } catch (e: Exception) {
                // If JSON parsing fails, create from individual fields
                createFromIndividualFields()
            }
        } else {
            // Create from individual fields or use defaults
            createFromIndividualFields()
        }
    }
    
    private fun createFromIndividualFields(): ReceiptSettings {
        val paperSizeString = prefs.getString(KEY_PAPER_SIZE, ReceiptSettings.PaperSize.MM_58.name)
        val paperSize = try {
            ReceiptSettings.PaperSize.valueOf(paperSizeString ?: ReceiptSettings.PaperSize.MM_58.name)
        } catch (e: Exception) {
            ReceiptSettings.PaperSize.MM_58
        }
        
        return ReceiptSettings(
            paperSize = paperSize,
            charactersPerLine = paperSize.charsPerLine,
            storeName = prefs.getString(KEY_STORE_NAME, "AndroPOS Store") ?: "AndroPOS Store",
            storeAddress = prefs.getString(KEY_STORE_ADDRESS, "123 Main Street\nKuala Lumpur, Malaysia") 
                ?: "123 Main Street\nKuala Lumpur, Malaysia",
            phoneNumber = prefs.getString(KEY_PHONE_NUMBER, "+60 3-1234 5678") ?: "+60 3-1234 5678",
            footerMessage = prefs.getString(KEY_FOOTER_MESSAGE, "Thank you for your business!\nPowered by AndroPOS") 
                ?: "Thank you for your business!\nPowered by AndroPOS",
            showLogo = prefs.getBoolean(KEY_SHOW_LOGO, true),
            showQrCode = prefs.getBoolean(KEY_SHOW_QR_CODE, true),
            showTaxBreakdown = prefs.getBoolean(KEY_SHOW_TAX_BREAKDOWN, true),
            autoCut = prefs.getBoolean(KEY_AUTO_CUT, true),
            duplicateReceipt = prefs.getBoolean(KEY_DUPLICATE_RECEIPT, false)
        )
    }
    
    /**
     * Get paper size only (for quick access)
     */
    fun getPaperSize(): ReceiptSettings.PaperSize {
        val paperSizeString = prefs.getString(KEY_PAPER_SIZE, ReceiptSettings.PaperSize.MM_58.name)
        return try {
            ReceiptSettings.PaperSize.valueOf(paperSizeString ?: ReceiptSettings.PaperSize.MM_58.name)
        } catch (e: Exception) {
            ReceiptSettings.PaperSize.MM_58
        }
    }
    
    /**
     * Update only paper size
     */
    fun updatePaperSize(paperSize: ReceiptSettings.PaperSize) {
        prefs.edit().apply {
            putString(KEY_PAPER_SIZE, paperSize.name)
            apply()
        }
    }
    
    /**
     * Reset to default settings
     */
    fun resetToDefaults() {
        prefs.edit().clear().apply()
    }
    
    /**
     * Check if settings have been customized
     */
    fun hasCustomSettings(): Boolean {
        return prefs.getString(KEY_RECEIPT_SETTINGS, null) != null ||
               prefs.contains(KEY_STORE_NAME)
    }
}