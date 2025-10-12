package com.extrotarget.extropos.data.model

/**
 * Payment method configuration for the POS system
 */
data class PaymentMethod(
    val id: String,
    val name: String,
    val displayName: String,
    val isEnabled: Boolean = true,
    val requiresAmount: Boolean = true,
    val allowsPartialPayment: Boolean = false,
    val processingFeePercentage: Double = 0.0,
    val processingFeeFixed: Long = 0, // in cents
    val minimumAmount: Long = 0, // in cents
    val maximumAmount: Long = Long.MAX_VALUE, // in cents
    val icon: String = "",
    val category: PaymentCategory = PaymentCategory.STANDARD,
    val configuration: Map<String, String> = emptyMap()
) {
    enum class PaymentCategory {
        CASH,
        CARD,
        DIGITAL_WALLET,
        BANK_TRANSFER,
        GIFT_CARD,
        LOYALTY,
        STANDARD
    }

    companion object {
        fun getDefaultPaymentMethods(): List<PaymentMethod> {
            return listOf(
                PaymentMethod(
                    id = "cash",
                    name = "cash",
                    displayName = "Cash",
                    category = PaymentCategory.CASH,
                    icon = "💵",
                    allowsPartialPayment = true
                ),
                PaymentMethod(
                    id = "card",
                    name = "card",
                    displayName = "Credit/Debit Card",
                    category = PaymentCategory.CARD,
                    icon = "💳",
                    processingFeePercentage = 2.5
                ),
                PaymentMethod(
                    id = "ewallet_tng",
                    name = "ewallet_tng",
                    displayName = "Touch 'n Go eWallet",
                    category = PaymentCategory.DIGITAL_WALLET,
                    icon = "📱",
                    processingFeePercentage = 1.5
                ),
                PaymentMethod(
                    id = "ewallet_grab",
                    name = "ewallet_grab",
                    displayName = "GrabPay",
                    category = PaymentCategory.DIGITAL_WALLET,
                    icon = "📱",
                    processingFeePercentage = 1.8
                ),
                PaymentMethod(
                    id = "bank_transfer",
                    name = "bank_transfer",
                    displayName = "Bank Transfer",
                    category = PaymentCategory.BANK_TRANSFER,
                    icon = "🏦",
                    processingFeeFixed = 100, // RM 1.00
                    minimumAmount = 1000 // RM 10.00
                ),
                PaymentMethod(
                    id = "gift_card",
                    name = "gift_card",
                    displayName = "Gift Card",
                    category = PaymentCategory.GIFT_CARD,
                    icon = "🎁",
                    allowsPartialPayment = true
                )
            )
        }
    }
}

/**
 * Tax configuration for different types of items/services
 */
data class TaxRate(
    val id: String,
    val name: String,
    val displayName: String,
    val rate: Double, // percentage (e.g., 6.0 for 6%)
    val isDefault: Boolean = false,
    val isEnabled: Boolean = true,
    val description: String = "",
    val applicableCategories: List<String> = emptyList()
) {
    companion object {
        fun getDefaultTaxRates(): List<TaxRate> {
            return listOf(
                TaxRate(
                    id = "sst_standard",
                    name = "sst_standard",
                    displayName = "SST (Standard Rate)",
                    rate = 6.0,
                    isDefault = true,
                    description = "Standard Sales and Service Tax rate in Malaysia"
                ),
                TaxRate(
                    id = "sst_zero",
                    name = "sst_zero",
                    displayName = "Zero-rated",
                    rate = 0.0,
                    description = "Zero-rated items (essential goods)"
                ),
                TaxRate(
                    id = "sst_exempt",
                    name = "sst_exempt",
                    displayName = "Tax Exempt",
                    rate = 0.0,
                    description = "Tax-exempt items"
                ),
                TaxRate(
                    id = "service_charge",
                    name = "service_charge",
                    displayName = "Service Charge",
                    rate = 10.0,
                    description = "Service charge for dine-in restaurants"
                )
            )
        }
    }
}

/**
 * Financial settings configuration
 */
data class FinancialSettings(
    val currency: String = "RM",
    val currencySymbol: String = "RM",
    val decimalPlaces: Int = 2,
    val roundingMethod: RoundingMethod = RoundingMethod.NEAREST,
    val enableTipping: Boolean = true,
    val defaultTipPercentage: Double = 10.0,
    val enableServiceCharge: Boolean = false,
    val enableTax: Boolean = true,
    val manualTaxPercentage: Double = -1.0,
    val serviceChargePercentage: Double = 10.0,
    val enableDiscounts: Boolean = true,
    val maxDiscountPercentage: Double = 50.0,
    val requireManagerApprovalForDiscount: Boolean = true,
    val enableRefunds: Boolean = true,
    val refundTimeLimit: Int = 30, // days
    val enablePartialRefunds: Boolean = true,
    val enableStoreCredit: Boolean = true,
    val enableLayaway: Boolean = false,
    val layawayFeePercentage: Double = 5.0
) {
    enum class RoundingMethod {
        NEAREST,
        UP,
        DOWN
    }
}

/**
 * Complete payment and financial configuration
 */
data class PaymentConfiguration(
    val paymentMethods: List<PaymentMethod> = PaymentMethod.getDefaultPaymentMethods(),
    val taxRates: List<TaxRate> = TaxRate.getDefaultTaxRates(),
    val financialSettings: FinancialSettings = FinancialSettings()
) {
    fun getEnabledPaymentMethods(): List<PaymentMethod> {
        return paymentMethods.filter { it.isEnabled }
    }
    
    fun getDefaultTaxRate(): TaxRate? {
        return taxRates.find { it.isDefault && it.isEnabled }
    }
    
    fun getPaymentMethodById(id: String): PaymentMethod? {
        return paymentMethods.find { it.id == id }
    }
    
    fun getTaxRateById(id: String): TaxRate? {
        return taxRates.find { it.id == id }
    }
}