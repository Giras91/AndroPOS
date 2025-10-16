package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Cart item entity representing individual items in a shopping cart.
 */
@Entity(
    tableName = "cart_items",
    foreignKeys = [
        ForeignKey(
            entity = CartEntity::class,
            parentColumns = ["id"],
            childColumns = ["cartId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("cartId"), Index("productId")]
)
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val cartId: String,
    val productId: String?,              // References ProductEntity.id
    val name: String,                    // Product name at time of adding
    val sku: String?,                    // Product SKU
    val quantity: Int = 1,
    val priceCents: Long,                // Unit price in cents
    val createdAt: Long = System.currentTimeMillis()
)