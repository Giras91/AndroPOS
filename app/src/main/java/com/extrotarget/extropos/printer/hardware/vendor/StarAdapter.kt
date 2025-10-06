package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper

class StarAdapter(override val id: String) : PrinterWrapper {
    override suspend fun connect(): Boolean { return true }
    override suspend fun disconnect() {}
    override suspend fun write(data: ByteArray): Boolean { return true }
}
