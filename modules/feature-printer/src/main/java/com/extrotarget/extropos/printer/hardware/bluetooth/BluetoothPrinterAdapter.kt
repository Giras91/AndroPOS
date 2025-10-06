package com.extrotarget.extropos.printer.hardware.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.OutputStream
import java.util.*

class BluetoothPrinterAdapter(
    private val deviceAddress: String,
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") // RFCOMM SPP
) : PrinterWrapper {
    override val id: String = "bluetooth:${deviceAddress}"

    private var socket: BluetoothSocket? = null
    private var out: OutputStream? = null

    override suspend fun connect(): Boolean = withContext(Dispatchers.IO) {
        val btAdapter = BluetoothAdapter.getDefaultAdapter() ?: return@withContext false
        val device: BluetoothDevice = btAdapter.getRemoteDevice(deviceAddress)
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid)
            btAdapter.cancelDiscovery()
            socket?.connect()
            out = socket?.outputStream
            return@withContext true
        } catch (e: IOException) {
            try { socket?.close() } catch (_: Exception) {}
            socket = null
            out = null
            return@withContext false
        }
    }

    override suspend fun disconnect() = withContext(Dispatchers.IO) {
        try { out?.flush() } catch (_: Exception) {}
        try { socket?.close() } catch (_: Exception) {}
        socket = null
        out = null
    }

    override suspend fun write(data: ByteArray): Boolean = withContext(Dispatchers.IO) {
        val o = out ?: return@withContext false
        return@withContext try {
            o.write(data)
            o.flush()
            true
        } catch (e: IOException) {
            false
        }
    }
}
