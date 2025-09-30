package com.example.pos.domain.model

data class Product(
    val id: String,
    val name: String,
    val priceCents: Long, // Price in cents to avoid floating point issues
    val sku: String? = null,
    val barcode: String? = null,
    val category: String? = null,
    val stockQuantity: Int = 0,
    val imageUrl: String? = null,
    val description: String? = null
) {
    fun getFormattedPrice(): String {
        // This would use CurrencyUtils to format the price
        val dollars = priceCents / 100.0
        return String.format("RM %.2f", dollars)
    }

    fun isInStock(): Boolean = stockQuantity > 0
}