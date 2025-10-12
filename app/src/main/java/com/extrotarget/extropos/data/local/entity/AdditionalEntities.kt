package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class DepartmentEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val taxGroupId: Int? = null
)

@Entity(tableName = "tax_groups")
data class TaxGroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val rate: Double, // tax rate as percentage
    val inclusive: Boolean = false
)

@Entity(tableName = "tenders")
data class TenderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String, // "cash", "card", "voucher", etc.
    val openDrawer: Boolean = false,
    val printReceipt: Boolean = true
)

@Entity(tableName = "tickets")
data class TicketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ticketType: Int,
    val state: Int,
    val total: Long, // in cents
    val createdAt: Long,
    val updatedAt: Long,
    val sessionId: Int
)

@Entity(tableName = "ticket_items")
data class TicketItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ticketId: Int,
    val itemId: Int,
    val sku: String?,
    val quantity: Int,
    val amount: Long, // unit price in cents
    val cost: Long, // cost in cents
    val itemDesc: String,
    val state: Int
)

@Entity(tableName = "ticket_tenders")
data class TicketTenderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val ticketId: Int,
    val tenderId: String,
    val tenderType: String,
    val amount: Long, // in cents
    val status: Int
)