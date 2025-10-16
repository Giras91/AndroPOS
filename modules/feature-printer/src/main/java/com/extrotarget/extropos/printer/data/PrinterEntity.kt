package com.extrotarget.extropos.printer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "printers")
data class PrinterEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "connectionType")
    val connectionType: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "port")
    val port: Int? = null,

    @ColumnInfo(name = "isDefault")
    val isDefault: Boolean = false,

    @ColumnInfo(name = "isConnected")
    val isConnected: Boolean = false
)
