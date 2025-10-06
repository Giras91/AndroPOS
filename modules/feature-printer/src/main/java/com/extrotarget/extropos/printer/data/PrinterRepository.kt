package com.extrotarget.extropos.printer.data

import com.extrotarget.extropos.printer.domain.model.PrintJob
import com.extrotarget.extropos.printer.hardware.EscPos
import com.extrotarget.extropos.printer.hardware.network.NetworkPrinterWrapper
import com.extrotarget.extropos.printer.hardware.PrinterWrapper
import com.extrotarget.extropos.printer.hardware.vendor.XPrinterSdkAdapter
import com.extrotarget.extropos.printer.hardware.vendor.PosmacSdkAdapter
import com.extrotarget.extropos.printer.hardware.vendor.GenericAdapter
import javax.inject.Inject

class PrinterRepository @Inject constructor() : IPrinterRepository {

    private fun preferredWrappers(): List<PrinterWrapper> {
        // Order: SDK-backed adapters first (if SDK AARs are present they'll be used),
        // then vendor-specific TCP adapters, and finally Generic/TCP fallback.
        return listOf<PrinterWrapper>(
            // Try XPrinter SDK adapter (reflection will fall back if SDK missing)
            XPrinterSdkAdapter("192.168.0.100"),
            // Try Posmac SDK adapter
            PosmacSdkAdapter("192.168.0.100"),
            // Legacy network wrapper and generic fallback
            NetworkPrinterWrapper("network-1", "192.168.0.100"),
            GenericAdapter("192.168.0.100")
        )
    }

    override suspend fun print(job: PrintJob): Result<Boolean> {
        val data = EscPos.formatReceipt(job.title, job.body)
        for (w in preferredWrappers()) {
            try {
                if (w.connect()) {
                    val ok = w.write(data)
                    w.disconnect()
                    if (ok) return Result.success(true)
                }
            } catch (e: Exception) {
                // continue
            }
        }
        return Result.failure(Exception("no-printer-available"))
    }

    override suspend fun availablePrinters(): List<String> {
        return preferredWrappers().map { it.id }
    }
}
