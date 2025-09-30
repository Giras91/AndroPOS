package com.extrotarget.extropos.domain.model

data class OrderItem(
    val id: String,
    val menuItemId: String,
    val name: String,
    val quantity: Int,
    val unitPriceCents: Long,
    val notes: String = ""
) {
    val totalPriceCents: Long
        get() = quantity * unitPriceCents
}