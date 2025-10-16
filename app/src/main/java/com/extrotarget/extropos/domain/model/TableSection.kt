package com.extrotarget.extropos.domain.model

import android.os.Parcel
import android.os.Parcelable

// Domain model for a dining section/area
// Keep it Parcelable for dialog arguments

data class TableSection(
    val id: String,
    val name: String,
    val description: String? = null,
    val color: String? = null,
    val displayOrder: Int = 0,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(color)
        parcel.writeInt(displayOrder)
        parcel.writeByte(if (isActive) 1 else 0)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<TableSection> {
        override fun createFromParcel(parcel: Parcel): TableSection = TableSection(parcel)
        override fun newArray(size: Int): Array<TableSection?> = arrayOfNulls(size)
    }
}
