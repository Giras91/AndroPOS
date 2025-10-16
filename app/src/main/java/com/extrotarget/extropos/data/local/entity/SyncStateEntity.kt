package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Sync state entity for tracking synchronization metadata.
 * Records the last sync timestamp for each entity type.
 */
@Entity(tableName = "sync_states")
data class SyncStateEntity(
    @PrimaryKey val entity: String,        // "products", "orders", "inventory", etc.
    val lastSyncedAt: Long                 // Epoch millis timestamp
)