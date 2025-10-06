package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper

/**
 * Adapter that attempts to use Posmac official SDK via reflection. If not found,
 * falls back to GenericAdapter. This keeps the build independent of the vendor
 * AAR while allowing integration later by adding the AAR to module dependencies.
 */
class PosmacSdkAdapter(
    private val host: String,
    private val port: Int = 9100
) : PrinterWrapper {
    private var delegate: PrinterWrapper? = null

    override val id: String = "posmac-sdk-$host:$port"

    private fun ensureDelegate(): Boolean {
        if (delegate != null) return true
        try {
            val clazz = Class.forName("com.posmac.sdk.Printer")
            // If present, reflectively construct and wire methods (simplified)
            val ctor = try { clazz.getConstructor(String::class.java, Int::class.javaPrimitiveType) } catch (_: NoSuchMethodException) { null }
            val instance = if (ctor != null) ctor.newInstance(host, port) else clazz.getConstructor().newInstance()
            val write = try { clazz.getMethod("write", ByteArray::class.java) } catch (_: NoSuchMethodException) { null }
            val connect = try { clazz.getMethod("connect") } catch (_: NoSuchMethodException) { null }
            val disconnect = try { clazz.getMethod("disconnect") } catch (_: NoSuchMethodException) { null }

            if (write != null) {
                delegate = object : PrinterWrapper {
                    override val id: String = this@PosmacSdkAdapter.id
                    override suspend fun connect(): Boolean {
                        return try { connect?.invoke(instance); true } catch (_: Throwable) { false }
                    }
                    override suspend fun disconnect() { try { disconnect?.invoke(instance) } catch (_: Throwable) {} }
                    override suspend fun write(data: ByteArray): Boolean {
                        return try { write?.invoke(instance, data); true } catch (_: Throwable) { false }
                    }
                }
                return true
            }
        } catch (_: ClassNotFoundException) {
            // SDK not present
        }

        // Fallback
        delegate = GenericAdapter(host, port)
        return true
    }

    override suspend fun connect(): Boolean { ensureDelegate(); return delegate?.connect() ?: false }
    override suspend fun disconnect() { delegate?.disconnect() }
    override suspend fun write(data: ByteArray): Boolean { ensureDelegate(); return delegate?.write(data) ?: false }
}
