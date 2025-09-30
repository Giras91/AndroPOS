package com.extrotarget.extropos.domain.model

data class Ticket(
    val id: String,
    val items: List<TicketItem> = emptyList(),
    val status: TicketStatus = TicketStatus.OPEN,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    val totalAmountCents: Long
        get() = items.sumOf { it.totalPriceCents }
}

enum class TicketStatus {
    OPEN,
    SUSPENDED,
    COMPLETED,
    CANCELLED
}