package com.extrotarget.extropos.printer.data

import com.extrotarget.extropos.printer.domain.model.PrintJob
import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import com.extrotarget.extropos.printer.hardware.network.NetworkPrinterWrapper
import com.extrotarget.extropos.printer.hardware.usb.UsbPrinterWrapper
import com.extrotarget.extropos.printer.hardware.bluetooth.BluetoothPrinterWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrinterRepository @Inject constructor(
    // runtime: we could inject lists of available wrappers via DI
) : IPrinterRepository {

    private fun preferredWrappers(): List<PrinterWrapper> {
        // prefer vendor/network then usb/bluetooth
        return listOf(
            NetworkPrinterWrapper("network-1", "192.168.0.100"),
            UsbPrinterWrapper("usb-1"),
            BluetoothPrinterWrapper("bt-1")
        )
    }

    override suspend fun print(job: PrintJob): Result<Boolean> {
        val data = com.extrotarget.extropos.printer.hardware.EscPos.formatReceipt(job.title, job.body)
        for (w in preferredWrappers()) {
            try {
                if (w.connect()) {
                    val ok = w.write(data)
                    w.disconnect()
                    if (ok) return Result.success(true)
                }
            } catch (e: Exception) {
                // try next
            }
        }
        return Result.failure(Exception("no-printer-available"))
    }

    override suspend fun availablePrinters(): List<String> {
        return preferredWrappers().map { it.id }
    }
}
