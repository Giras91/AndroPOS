package com.extrotarget.extropos.printer.hardware.usb

import com.extrotarget.extropos.printer.hardware.PrinterWrapper

class UsbPrinterWrapper(override val id: String) : PrinterWrapper {
    override suspend fun connect(): Boolean {
        // TODO: implement USB device discovery and claim interface
        return true
    }

    override suspend fun disconnect() {
        // TODO: release resources
    }

    override suspend fun write(data: ByteArray): Boolean {
        // TODO: write to USB endpoint
        return true
    }
}
