package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper

class EpsonAdapter(override val id: String) : PrinterWrapper {
    override suspend fun connect(): Boolean {
        // TODO: use Epson SDK (if present) to connect
        return true
    }

    override suspend fun disconnect() {}
    override suspend fun write(data: ByteArray): Boolean { return true }
}
