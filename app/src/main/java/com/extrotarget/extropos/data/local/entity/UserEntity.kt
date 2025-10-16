package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

/**
 * User entity representing staff/employees in the POS system.
 * Stores authentication and authorization information.
 */
@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class UserEntity(
    @PrimaryKey val id: String,
    val username: String,                    // Unique username for login
    val displayName: String? = null,         // Human-readable name
    val passwordHash: String,                // Salted hash (NOT plaintext)
    val role: String,                        // ADMIN, CASHIER, MANAGER, etc.
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)