package com.extrotarget.extropos.printer.hardware.bluetooth

import com.extrotarget.extropos.printer.hardware.PrinterWrapper

class BluetoothPrinterWrapper(override val id: String) : PrinterWrapper {
    override suspend fun connect(): Boolean {
        // TODO: pair and connect to Bluetooth device
        return true
    }

    override suspend fun disconnect() {
        // TODO: disconnect
    }

    override suspend fun write(data: ByteArray): Boolean {
        // TODO: write via Bluetooth socket
        return true
    }
}
