package com.extrotarget.extropos.domain.model

data class CartItem(
    val productId: String,
    val name: String,
    val quantity: Int = 1,
    val unitPriceCents: Long,
    val notes: String = ""
) {
    val totalPriceCents: Long
        get() = quantity * unitPriceCents
}