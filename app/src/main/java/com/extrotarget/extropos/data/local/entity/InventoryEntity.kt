package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

/**
 * Inventory entity representing current stock levels per product.
 * This is separate from InventoryTransactionEntity which logs all stock movements.
 */
@Entity(
    tableName = "inventory",
    foreignKeys = [ForeignKey(
        entity = ProductEntity::class,
        parentColumns = ["id"],
        childColumns = ["productId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class InventoryEntity(
    @PrimaryKey val productId: String,    // References ProductEntity.id
    val quantity: Int = 0,                // Current on-hand quantity
    val reserved: Int = 0,                // Quantity allocated/reserved
    val updatedAt: Long = System.currentTimeMillis()
)