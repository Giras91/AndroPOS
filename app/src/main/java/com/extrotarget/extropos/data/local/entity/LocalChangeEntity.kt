package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Local change entity for audit logging and sync tracking.
 * Records all local modifications for potential sync to remote systems.
 */
@Entity(tableName = "local_changes")
data class LocalChangeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val entity: String,                    // "products", "orders", "inventory", etc.
    val entityId: String?,                 // ID of the modified entity
    val operation: String,                 // CREATE, UPDATE, DELETE
    val payload: String?,                  // JSON snapshot of the change
    val createdAt: Long = System.currentTimeMillis()
)