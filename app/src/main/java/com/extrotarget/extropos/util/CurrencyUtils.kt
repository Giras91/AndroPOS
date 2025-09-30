package com.extrotarget.extropos.util

import java.text.NumberFormat
import java.util.*

object CurrencyUtils {

    private val malaysianFormat: NumberFormat by lazy {
        NumberFormat.getCurrencyInstance(Locale("ms", "MY")).apply {
            currency = Currency.getInstance("MYR")
        }
    }

    private val malaysianFormatNoSymbol: NumberFormat by lazy {
        NumberFormat.getNumberInstance(Locale("ms", "MY")).apply {
            minimumFractionDigits = 2
            maximumFractionDigits = 2
        }
    }

    /**
     * Format cents to Malaysian Ringgit currency string
     * @param cents Amount in cents
     * @return Formatted currency string (e.g., "RM 12.50")
     */
    fun formatAmount(cents: Long): String {
        val amount = cents / 100.0
        return malaysianFormat.format(amount)
    }

    /**
     * Format cents to Malaysian Ringgit without currency symbol
     * @param cents Amount in cents
     * @return Formatted amount string (e.g., "12.50")
     */
    fun formatAmountNoSymbol(cents: Long): String {
        val amount = cents / 100.0
        return malaysianFormatNoSymbol.format(amount)
    }

    /**
     * Parse Malaysian Ringgit string to cents
     * @param amountString Amount string (e.g., "12.50" or "RM 12.50")
     * @return Amount in cents
     */
    fun parseAmount(amountString: String): Long {
        val cleanString = amountString.replace("RM", "").replace(" ", "").trim()
        return try {
            val amount = cleanString.toDouble()
            (amount * 100).toLong()
        } catch (e: NumberFormatException) {
            0L
        }
    }
}