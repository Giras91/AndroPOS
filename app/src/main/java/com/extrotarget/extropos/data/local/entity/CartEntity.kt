package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

/**
 * Cart entity representing a shopping cart session.
 * Used for persisting cart state across app sessions.
 */
@Entity(tableName = "carts")
data class CartEntity(
    @PrimaryKey val id: String,
    val userId: String?,                    // Staff member who owns this cart
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)