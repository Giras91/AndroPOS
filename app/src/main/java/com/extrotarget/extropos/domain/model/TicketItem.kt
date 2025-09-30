package com.extrotarget.extropos.domain.model

data class TicketItem(
    val id: String,
    val productId: String,
    val name: String,
    val quantity: Int,
    val unitPriceCents: Long,
    val notes: String = ""
) {
    val totalPriceCents: Long
        get() = quantity * unitPriceCents
}