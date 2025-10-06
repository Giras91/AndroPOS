package com.extrotarget.extropos.printer.hardware.network

import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

/**
 * Simple TCP network printer wrapper that connects to port 9100 (raw socket)
 */
class NetworkPrinterWrapper(
	override val id: String,
	private val host: String,
	private val port: Int = 9100
) : PrinterWrapper {
	private var socket: Socket? = null

	override suspend fun connect(): Boolean {
		return try {
			val s = Socket()
			s.connect(InetSocketAddress(host, port), 3000)
			socket = s
			true
		} catch (e: Exception) {
			socket = null
			false
		}
	}

	override suspend fun disconnect() {
		try {
			socket?.close()
		} catch (_: Exception) {
		}
		socket = null
	}

	override suspend fun write(data: ByteArray): Boolean {
		val s = socket ?: return false
		return try {
			val out: OutputStream = s.getOutputStream()
			out.write(data)
			out.flush()
			true
		} catch (e: Exception) {
			false
		}
	}
}