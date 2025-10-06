package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

/** Posmac-specific adapter; Posmac printers are standard ESCPOS devices but this adapter
 * adds a short wake/preamble sequence used by some POSMAC models.
 */
class PosmacAdapter(
    private val host: String,
    private val port: Int = 9100
) : PrinterWrapper {
    private val wakeSequence = byteArrayOf(0x1B, 0x40) // ESC @
    private var socket: Socket? = null
    private var out: OutputStream? = null

    override val id: String get() = "posmac-$host:$port"

    override suspend fun connect(): Boolean {
        return try {
            val s = Socket()
            s.connect(InetSocketAddress(host, port), 3000)
            socket = s
            out = s.getOutputStream()
            // write wake
            out?.write(wakeSequence)
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
        return try { o.write(data); o.flush(); true } catch (t: Throwable) { false }
    }
}
