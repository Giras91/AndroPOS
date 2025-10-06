package com.extrotarget.extropos.printer.hardware

interface PrinterWrapper {
    val id: String
    suspend fun connect(): Boolean
    suspend fun disconnect()
    suspend fun write(data: ByteArray): Boolean
}
