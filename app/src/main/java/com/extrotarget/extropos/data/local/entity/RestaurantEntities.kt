package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val displayOrder: Int = 0,
    val isActive: Boolean = true
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
    val notes: String?
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

@Entity(tableName = "tables")
data class TableEntity(
    @PrimaryKey val id: String,
    val number: String,
    val capacity: Int,
    val status: String, // TableStatus enum name
    val currentOrderId: String?
)