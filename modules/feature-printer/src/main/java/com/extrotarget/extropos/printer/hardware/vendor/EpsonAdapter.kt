package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

/** Epson adapter with common init and cut commands (generic ESC/POS). */
class EpsonAdapter(
    private val host: String,
    private val port: Int = 9100
) : PrinterWrapper {
    private var socket: Socket? = null
    private var out: OutputStream? = null

    // Full cut command for many Epson-like printers
    private val cutCommand = byteArrayOf(0x1D, 0x56, 0x00)

    override val id: String get() = "epson-$host:$port"

    override suspend fun connect(): Boolean {
        return try {
            val s = Socket(); s.connect(InetSocketAddress(host, port), 3000)
            socket = s; out = s.getOutputStream()
            // Initialize printer (ESC @)
            out?.write(byteArrayOf(0x1B, 0x40))
            out?.flush()
            true
        } catch (t: Throwable) {
            socket = null; out = null; false
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
            o.write(data)
            // Some apps want to automatically cut after printing; we won't force it
            o.flush()
            true
        } catch (t: Throwable) { false }
    }

    suspend fun cut(): Boolean {
        val o = out ?: return false
        return try { o.write(cutCommand); o.flush(); true } catch (t: Throwable) { false }
    }
}
