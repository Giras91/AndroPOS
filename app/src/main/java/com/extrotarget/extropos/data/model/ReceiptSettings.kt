package com.extrotarget.extropos.data.model

/**
 * Data class to hold receipt customization settings
 */
data class ReceiptSettings(
    // Paper configuration
    val paperSize: PaperSize = PaperSize.MM_58,
    val charactersPerLine: Int = 32, // 58mm = 32 chars, 80mm = 48 chars
    
    // Store information
    val storeName: String = "AndroPOS Store",
    val storeAddress: String = "123 Main Street\nKuala Lumpur, Malaysia",
    val phoneNumber: String = "+60 3-1234 5678",
    val footerMessage: String = "Thank you for your business!\nPowered by AndroPOS",
    
    // Format options
    val showLogo: Boolean = true,
    val showQrCode: Boolean = true,
    val showTaxBreakdown: Boolean = true,
    val autoCut: Boolean = true,
    val duplicateReceipt: Boolean = false,
    
    // Additional formatting
    val currency: String = "RM",
    val dateFormat: String = "dd/MM/yyyy HH:mm:ss",
    val logoHeight: Int = 50, // pixels
    val fontSize: FontSize = FontSize.NORMAL
) {
    
    enum class PaperSize(val width: Int, val displayName: String, val charsPerLine: Int) {
        MM_58(58, "58mm (2.3 inch)", 32),
        MM_80(80, "80mm (3.1 inch)", 48)
    }
    
    enum class FontSize(val escCommand: String, val displayName: String) {
        SMALL("\u001B[0m", "Small"),
        NORMAL("\u001B[1m", "Normal"), 
        LARGE("\u001B[2m", "Large")
    }
    
    /**
     * Get ESC/POS command for setting font width based on paper size
     */
    fun getFontWidthCommand(): String {
        return when (paperSize) {
            PaperSize.MM_58 -> "\u001D\u0057\u0020" // Set print area width to 32 chars (58mm)
            PaperSize.MM_80 -> "\u001D\u0057\u0030" // Set print area width to 48 chars (80mm)
        }
    }
    
    /**
     * Get center alignment command for current paper size
     */
    fun getCenterAlignCommand(): String {
        return "\u001B\u0061\u0001" // ESC a 1 (center align)
    }
    
    /**
     * Get left alignment command
     */
    fun getLeftAlignCommand(): String {
        return "\u001B\u0061\u0000" // ESC a 0 (left align)
    }
    
    /**
     * Format text to fit within paper width, adding proper line breaks
     */
    fun formatTextForWidth(text: String): String {
        val maxChars = charactersPerLine
        val lines = mutableListOf<String>()
        
        text.split("\n").forEach { line ->
            if (line.length <= maxChars) {
                lines.add(line)
            } else {
                var remaining = line
                while (remaining.length > maxChars) {
                    val breakPoint = remaining.lastIndexOf(' ', maxChars)
                    if (breakPoint > 0) {
                        lines.add(remaining.substring(0, breakPoint))
                        remaining = remaining.substring(breakPoint + 1)
                    } else {
                        lines.add(remaining.substring(0, maxChars))
                        remaining = remaining.substring(maxChars)
                    }
                }
                if (remaining.isNotEmpty()) {
                    lines.add(remaining)
                }
            }
        }
        
        return lines.joinToString("\n")
    }
    
    /**
     * Center text within paper width
     */
    fun centerText(text: String): String {
        val maxChars = charactersPerLine
        return if (text.length >= maxChars) {
            text
        } else {
            val padding = (maxChars - text.length) / 2
            " ".repeat(padding) + text
        }
    }
    
    /**
     * Create a separator line for receipts
     */
    fun getSeparatorLine(): String {
        return "-".repeat(charactersPerLine)
    }
}