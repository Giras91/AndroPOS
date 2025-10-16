package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Key-value entity for storing application settings and configuration.
 * Simple string-based key-value store with JSON support.
 */
@Entity(tableName = "kv_store")
data class KeyValueEntity(
    @PrimaryKey val key: String,
    val value: String?,                    // Can be JSON string or plain text
    val updatedAt: Long = System.currentTimeMillis()
)