package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Shift entity representing a staff shift session.
 */
@Entity(tableName = "shifts")
data class ShiftEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val username: String,
    val startedAt: Long,
    val endedAt: Long? = null,
    val notes: String? = null
)
