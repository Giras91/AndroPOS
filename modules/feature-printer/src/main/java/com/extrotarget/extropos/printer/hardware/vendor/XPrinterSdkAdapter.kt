package com.extrotarget.extropos.printer.hardware.vendor

import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import java.lang.reflect.Method

/**
 * Adapter that tries to use the official XPrinter SDK if present. If the SDK classes
 * are not available at runtime, it falls back to the GenericAdapter (raw TCP).
 *
 * The adapter uses reflection so the SDK AAR can be added later without compile-time
 * dependency. To enable the SDK capabilities, add the XPrinter AAR or Maven artifact
 * to module dependencies (see README).
 */
class XPrinterSdkAdapter(
    private val host: String,
    private val port: Int = 9100
) : PrinterWrapper {

    private var delegate: PrinterWrapper? = null
    private var sdkInstance: Any? = null
    private var connectMethod: Method? = null
    private var writeMethod: Method? = null
    private var disconnectMethod: Method? = null

    override val id: String = "xprinter-sdk-$host:$port"

    private fun tryInitSdk(): Boolean {
        if (delegate != null) return true
        // Candidate XPrinter SDK class names (may vary across versions)
        val candidates = listOf(
            "com.xprinter.sdk.XPrinter",
            "com.xprinter.sdk.EscPosPrinter",
            "com.xprinter.sdk.PrinterService",
            "com.xprinter.sdk.XpPrinter"
        )

        for (cn in candidates) {
            try {
                val clazz = Class.forName(cn)
                // Try common constructors: (String host, int port) or (String host)
                val ctor = try {
                    clazz.getConstructor(String::class.java, Int::class.javaPrimitiveType)
                } catch (e: NoSuchMethodException) {
                    try {
                        clazz.getConstructor(String::class.java)
                    } catch (e2: NoSuchMethodException) {
                        null
                    }
                }

                sdkInstance = if (ctor != null) {
                    if (ctor.parameterCount == 2) ctor.newInstance(host, port) else ctor.newInstance(host)
                } else {
                    // try no-arg
                    try {
                        clazz.getConstructor().newInstance()
                    } catch (e: Exception) { null }
                }

                if (sdkInstance != null) {
                    // discover methods
                    connectMethod = findMethod(clazz, arrayOf("connect", "open", "start"))
                    writeMethod = findMethod(clazz, arrayOf("write", "send", "print", "printText"), arrayOf(ByteArray::class.java, String::class.java))
                    disconnectMethod = findMethod(clazz, arrayOf("disconnect", "close", "stop"))

                    if (writeMethod != null) {
                        // create a reflection-backed delegate
                        delegate = object : PrinterWrapper {
                            override val id: String = this@XPrinterSdkAdapter.id
                            override suspend fun connect(): Boolean {
                                return try {
                                    connectMethod?.invoke(sdkInstance)
                                    true
                                } catch (t: Throwable) { false }
                            }

                            override suspend fun disconnect() {
                                try { disconnectMethod?.invoke(sdkInstance) } catch (_: Throwable) {}
                            }

                            override suspend fun write(data: ByteArray): Boolean {
                                return try {
                                    // prefer byte[] method
                                    try {
                                        writeMethod?.invoke(sdkInstance, data)
                                        true
                                    } catch (e: IllegalArgumentException) {
                                        // maybe the SDK expects String
                                        val s = String(data, Charsets.UTF_8)
                                        writeMethod?.invoke(sdkInstance, s)
                                        true
                                    }
                                } catch (t: Throwable) { false }
                            }
                        }
                        return true
                    }
                }
            } catch (_: ClassNotFoundException) {
                // try next candidate
            }
        }

        // No SDK found — use GenericAdapter fallback
        delegate = GenericAdapter(host, port)
        return true
    }

    private fun findMethod(clazz: Class<*>, names: Array<String>, paramTypesCandidates: Array<Class<*>>? = null): Method? {
        for (n in names) {
            if (paramTypesCandidates != null) {
                for (pt in paramTypesCandidates) {
                    try { return clazz.getMethod(n, pt) } catch (_: NoSuchMethodException) {}
                }
            } else {
                try { return clazz.getMethod(n) } catch (_: NoSuchMethodException) {}
            }
        }
        return null
    }

    override suspend fun connect(): Boolean {
        tryInitSdk()
        return delegate?.connect() ?: false
    }

    override suspend fun disconnect() {
        delegate?.disconnect()
    }

    override suspend fun write(data: ByteArray): Boolean {
        return delegate?.write(data) ?: false
    }
}
