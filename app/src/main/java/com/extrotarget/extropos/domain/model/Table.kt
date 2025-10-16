package com.extrotarget.extropos.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class Table(
    val id: String,
    val number: Int,
    val capacity: Int,
    val status: TableStatus = TableStatus.AVAILABLE,
    val currentOrderId: String? = null,

    // Enhanced fields for proper table management
    val section: String? = null,
    val tableType: String? = null,
    val positionX: Float? = null,
    val positionY: Float? = null,
    val width: Float? = null,
    val height: Float? = null,
    val rotation: Float = 0f,
    val assignedServerId: String? = null,
    val lastServedAt: Long? = null,
    val estimatedOccupancyTime: Long? = null,
    val specialNotes: String? = null,
    val isReservable: Boolean = true,
    val minimumSpendCents: Long? = null,
    val depositRequiredCents: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true,

    // Additional amenities and configuration
    val isSmokingAllowed: Boolean = false,
    val isAccessible: Boolean = false,
    val hasPowerOutlet: Boolean = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        TableStatus.valueOf(parcel.readString() ?: TableStatus.AVAILABLE.name),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readFloat(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readLong(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(number)
        parcel.writeInt(capacity)
        parcel.writeString(status.name)
        parcel.writeString(currentOrderId)
        parcel.writeString(section)
        parcel.writeString(tableType)
        parcel.writeValue(positionX)
        parcel.writeValue(positionY)
        parcel.writeValue(width)
        parcel.writeValue(height)
        parcel.writeFloat(rotation)
        parcel.writeString(assignedServerId)
        parcel.writeValue(lastServedAt)
        parcel.writeValue(estimatedOccupancyTime)
        parcel.writeString(specialNotes)
        parcel.writeByte(if (isReservable) 1 else 0)
        parcel.writeValue(minimumSpendCents)
        parcel.writeValue(depositRequiredCents)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeByte(if (isSmokingAllowed) 1 else 0)
        parcel.writeByte(if (isAccessible) 1 else 0)
        parcel.writeByte(if (hasPowerOutlet) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Table> {
        override fun createFromParcel(parcel: Parcel): Table {
            return Table(parcel)
        }

        override fun newArray(size: Int): Array<Table?> {
            return arrayOfNulls(size)
        }
    }
}

enum class TableStatus {
    AVAILABLE,
    OCCUPIED,
    RESERVED,
    OUT_OF_ORDER
}

enum class ReservationStatus {
    PENDING,
    CONFIRMED,
    SEATED,
    COMPLETED,
    CANCELLED,
    NO_SHOW,
    WAITLIST
}

@Parcelize
data class Reservation(
    val id: String,
    val tableId: String,
    val tableNumber: String, // For display purposes
    val customerName: String,
    val customerPhone: String?,
    val customerEmail: String?,
    val partySize: Int,
    val reservationDateTime: Long,
    val durationMinutes: Int = 120,
    val specialRequests: String?,
    val status: ReservationStatus = ReservationStatus.PENDING,
    val depositRequired: Boolean = false,
    val depositAmountCents: Long = 0,
    val depositPaid: Boolean = false,
    val notes: String?,
    val createdBy: String?,
    val assignedServerId: String?,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable