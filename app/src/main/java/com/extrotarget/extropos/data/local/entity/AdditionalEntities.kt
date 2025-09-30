package com.extrotarget.extropos.data.local.entity

data class DepartmentEntity(
    val id: Int,
    val name: String,
    val taxGroupId: Int? = null
)

data class TaxGroupEntity(
    val id: Int,
    val name: String,
    val rate: Double, // tax rate as percentage
    val inclusive: Boolean = false
)

data class TenderEntity(
    val id: Int,
    val name: String,
    val type: String, // "cash", "card", "voucher", etc.
    val openDrawer: Boolean = false,
    val printReceipt: Boolean = true
)

data class TicketEntity(
    val id: Int,
    val ticketType: Int,
    val state: Int,
    val total: Long, // in cents
    val createdAt: Long,
    val updatedAt: Long,
    val sessionId: Int
)

data class TicketItemEntity(
    val id: Int,
    val ticketId: Int,
    val itemId: Int,
    val sku: String?,
    val quantity: Int,
    val amount: Long, // unit price in cents
    val cost: Long, // cost in cents
    val itemDesc: String,
    val state: Int
)

data class TicketTenderEntity(
    val id: Int,
    val ticketId: Int,
    val tenderId: Int,
    val tenderType: String,
    val amount: Long, // in cents
    val status: Int
)