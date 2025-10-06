package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject

/**
 * Minimal TCP adapter for ESCPOS-compatible network printers (port 9100).
 * Does raw byte writes over a socket. This keeps the module dependency-free
 * and works for many common network printers.
 */
class DantSuEscPosAdapter @Inject constructor(
    private val host: String,
    private val port: Int = 9100,
    private val connectTimeoutMs: Int = 5000
) : PrinterWrapper {

    private var socket: Socket? = null
    private var out: OutputStream? = null

    override val id: String
        get() = "tcp-$host:$port"

    override suspend fun connect(): Boolean {
        return try {
            val s = Socket()
            s.connect(InetSocketAddress(host, port), connectTimeoutMs)
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
        try {
            out?.flush()
            out?.close()
        } catch (_: Exception) {
        }
        try {
            socket?.close()
        } catch (_: Exception) {
        } finally {
            out = null
            socket = null
        }
    }

    override suspend fun write(data: ByteArray): Boolean {
        try {
            val o = out ?: return false
            o.write(data)
            o.flush()
            return true
        } catch (t: Throwable) {
            return false
        }
    }
}
