package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

/** Generic TCP adapter for ESCPOS-compatible printers */
class GenericAdapter(
    private val host: String,
    private val port: Int = 9100,
    private val timeoutMs: Int = 3000
) : PrinterWrapper {
    private var socket: Socket? = null
    private var out: OutputStream? = null

    override val id: String get() = "generic-$host:$port"

    override suspend fun connect(): Boolean {
        return try {
            val s = Socket()
            s.connect(InetSocketAddress(host, port), timeoutMs)
            socket = s
            out = s.getOutputStream()
            true
        } catch (t: Throwable) {
            socket = null
            out = null
            false
        }
    }

    override suspend fun disconnect() {
        try { out?.flush(); out?.close() } catch (_: Exception) {}
        try { socket?.close() } catch (_: Exception) {}
        out = null; socket = null
    }

    override suspend fun write(data: ByteArray): Boolean {
        val o = out ?: return false
        return try {
            o.write(data); o.flush(); true
        } catch (t: Throwable) { false }
    }
}
