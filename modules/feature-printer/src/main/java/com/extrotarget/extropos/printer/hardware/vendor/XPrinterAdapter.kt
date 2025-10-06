package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

/** XPrinter adapter — compatible with many TCP XPrinter models. */
class XPrinterAdapter(
    private val host: String,
    private val port: Int = 9100
) : PrinterWrapper {
    private var socket: Socket? = null
    private var out: OutputStream? = null

    override val id: String get() = "xprinter-$host:$port"

    override suspend fun connect(): Boolean {
        return try {
            val s = Socket(); s.connect(InetSocketAddress(host, port), 3000)
            socket = s; out = s.getOutputStream()
            // gentle wake: send LF
            out?.write(byteArrayOf(0x0A))
            out?.flush()
            true
        } catch (t: Throwable) { socket = null; out = null; false }
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
