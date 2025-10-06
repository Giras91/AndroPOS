package com.extrotarget.extropos.printer.hardware.usb

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbEndpoint
import android.hardware.usb.UsbConstants
import android.hardware.usb.UsbInterface
import android.hardware.usb.UsbManager
import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class USBPrinterAdapter(
    private val context: Context,
    private val vendorId: Int,
    private val productId: Int
) : PrinterWrapper {
    override val id: String = "usb:${vendorId}:${productId}"

    private var connection: UsbDeviceConnection? = null
    private var outEndpoint: UsbEndpoint? = null
    private var device: UsbDevice? = null

    companion object {
        const val ACTION_USB_PERMISSION = "com.extrotarget.extropos.USB_PERMISSION"
    }

    private fun findDevice(): UsbDevice? {
        val manager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val list = manager.deviceList
        for (d in list.values) {
            if (d.vendorId == vendorId && d.productId == productId) return d
        }
        return null
    }

    override suspend fun connect(): Boolean = withContext(Dispatchers.IO) {
        val manager = context.getSystemService(Context.USB_SERVICE) as UsbManager
        val dev = findDevice() ?: return@withContext false
        device = dev
        if (!manager.hasPermission(dev)) {
            // Calling component should request permission using UsbPermissionHelper
            return@withContext false
        }
        val intf: UsbInterface = dev.getInterface(0)
        val conn = manager.openDevice(dev) ?: return@withContext false
        if (!conn.claimInterface(intf, true)) {
            try { conn.close() } catch (_: Exception) {}
            return@withContext false
        }

        // Find bulk out endpoint
        val ep = (0 until intf.endpointCount).mapNotNull { idx ->
            val e = intf.getEndpoint(idx)
            if (e.type == UsbConstants.USB_ENDPOINT_XFER_BULK && e.direction == UsbConstants.USB_DIR_OUT) e else null
        }.firstOrNull()

        if (ep == null) {
            conn.releaseInterface(intf)
            try { conn.close() } catch (_: Exception) {}
            return@withContext false
        }

        connection = conn
        outEndpoint = ep
        return@withContext true
    }

    override suspend fun disconnect() = withContext(Dispatchers.IO) {
        try {
            connection?.close()
        } catch (_: Exception) {}
        connection = null
        outEndpoint = null
        device = null
    }

    override suspend fun write(data: ByteArray): Boolean = withContext(Dispatchers.IO) {
        val conn = connection ?: return@withContext false
        val ep = outEndpoint ?: return@withContext false
        return@withContext try {
            val sent = conn.bulkTransfer(ep, data, data.size, 3000)
            sent >= 0
        } catch (e: Exception) {
            false
        }
    }
}
