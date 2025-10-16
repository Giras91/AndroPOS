package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val displayOrder: Int = 0,
    val isActive: Boolean = true,
    // New fields to match SQL schema
    val parentId: String? = null,        // For nested categories
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "menu_items")
data class MenuItemEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val priceCents: Long,
    val categoryId: String,
    val imageUrl: String?,
    val isAvailable: Boolean = true,
    val preparationTimeMinutes: Int?,
    val allergens: String? // JSON string of allergens list
)

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val tableId: String?,
    val orderNumber: String,
    val status: String, // OrderStatus enum name
    val orderType: String, // OrderType enum name
    val subtotalCents: Long = 0,
    val taxCents: Long = 0,
    val discountCents: Long = 0,
    val totalCents: Long = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val notes: String?,
    // New fields to match SQL schema
    val currency: String = "MYR",
    val externalId: String? = null,      // Remote/external reference
    val metadata: String? = null         // JSON blob
)

@Entity(tableName = "order_items")
data class OrderItemEntity(
    @PrimaryKey val id: String,
    val orderId: String,
    val menuItemId: String,
    val menuItemName: String,
    val quantity: Int,
    val unitPriceCents: Long,
    val totalPriceCents: Long,
    val notes: String?,
    val status: String // OrderItemStatus enum name
)

@Entity(tableName = "table_sections")
data class TableSectionEntity(
    @PrimaryKey val id: String,
    val name: String, // Section name (e.g., "Main Dining", "Patio", "Bar")
    val description: String? = null,
    val color: String? = null, // Color code for UI representation
    val displayOrder: Int = 0,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "table_layouts")
data class TableLayoutEntity(
    @PrimaryKey val id: String,
    val name: String, // Layout name (e.g., "Weekend Setup", "Private Event")
    val description: String? = null,
    val isDefault: Boolean = false,
    val layoutData: String? = null, // JSON string containing layout configuration
    val createdBy: String? = null, // User who created the layout
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "table_reservations")
data class ReservationEntity(
    @PrimaryKey val id: String,
    val tableId: String,
    val tableNumber: String, // For display purposes
    val customerName: String,
    val customerPhone: String?,
    val customerEmail: String?,
    val partySize: Int,
    val reservationDateTime: Long,
    val durationMinutes: Int = 120, // Default 2 hours
    val status: String, // CONFIRMED, PENDING, CANCELLED, COMPLETED, NO_SHOW, WAITLIST, SEATED
    val specialRequests: String?,
    val depositRequired: Boolean = false,
    val depositAmountCents: Long = 0,
    val depositPaid: Boolean = false,
    val notes: String?,
    val createdBy: String? = null,
    val assignedServerId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)