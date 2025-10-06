package com.extrotarget.extropos.printer.hardware.network

import com.extrotarget.extropos.printer.hardware.PrinterWrapper

class NetworkPrinterWrapper(override val id: String, private val host: String, private val port: Int = 9100) : PrinterWrapper {
    override suspend fun connect(): Boolean {
        // TODO: open TCP socket
        return true
    }

    override suspend fun disconnect() {
        // TODO: close socket
    }

    override suspend fun write(data: ByteArray): Boolean {
        // TODO: send bytes over socket
        return true
    }
}
