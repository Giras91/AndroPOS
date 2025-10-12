package com.extrotarget.extropos.printer.adapter

import com.extrotarget.extropos.printer.adapter.impl.DantSuEscPosAdapter
import com.extrotarget.extropos.printer.adapter.impl.StubPrinterAdapter
import com.extrotarget.extropos.printer.domain.model.PrinterConfig
import com.extrotarget.extropos.printer.domain.model.PrintJob
import com.extrotarget.extropos.printer.domain.model.PrintResult

enum class PrinterFeature {
    TEXT_PRINTING,
    BARCODE_1D,
    BARCODE_2D_QR,
    IMAGE_PRINTING,
    PAPER_CUTTING,
    RECEIPT_MODE,
    KITCHEN_MODE,
    FONT_STYLES,
    TEXT_ALIGNMENT,
    BLUETOOTH_SUPPORT,
    USB_SUPPORT,
    NETWORK_SUPPORT,
    CASH_DRAWER
}

interface PrinterAdapter {
    suspend fun connect(config: PrinterConfig): Boolean
    suspend fun disconnect()
    suspend fun print(printJob: PrintJob): PrintResult
    suspend fun getStatus(): String
    suspend fun getSupportedFeatures(): List<PrinterFeature>
    suspend fun isConnected(): Boolean
    suspend fun testConnection(): Boolean
}

class PrinterAdapterFactory(
    private val context: android.content.Context
) {
    
    fun createAdapter(sdkId: String): PrinterAdapter {
        return when (sdkId) {
            "dantsu-escpos" -> DantSuEscPosAdapter(context)
            else -> StubPrinterAdapter(context, "Unknown SDK: $sdkId")
        }
    }
}
