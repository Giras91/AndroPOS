package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tables")
data class TableEntity(
    @PrimaryKey val id: String,
    val number: String, // Table number/name (e.g., "T01", "VIP-1")
    val capacity: Int, // Maximum number of seats
    val status: String, // TableStatus enum name
    val currentOrderId: String? = null, // Current active order

    // Enhanced fields for restaurant POS
    val section: String? = null, // Section/area (e.g., "Main Dining", "Patio", "Bar")
    val tableType: String? = null, // TableType enum name (e.g., "Round", "Square", "Booth")
    val positionX: Float? = null, // X coordinate for floor plan
    val positionY: Float? = null, // Y coordinate for floor plan
    val width: Float? = null, // Table width for floor plan
    val height: Float? = null, // Table height for floor plan
    val rotation: Float? = null, // Rotation angle for floor plan
    val assignedServerId: String? = null, // Assigned server user ID
    val lastServedAt: Long? = null, // Timestamp of last service
    val estimatedOccupancyTime: Long? = null, // Expected occupancy duration in minutes
    val specialNotes: String? = null, // Special notes for staff
    val isReservable: Boolean = true, // Whether table can be reserved
    val minimumSpendCents: Long? = null, // Minimum spend requirement in cents
    val depositRequiredCents: Long? = null, // Deposit required for reservation in cents
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true, // Whether table is active/available

    // Additional accessibility and amenity fields
    val isSmokingAllowed: Boolean = false, // Whether smoking is allowed at this table
    val isAccessible: Boolean = true, // Whether table is wheelchair accessible
    val hasPowerOutlet: Boolean = false, // Whether table has power outlets
    val priority: Int = 0 // Priority level for table assignment (higher = preferred)
)