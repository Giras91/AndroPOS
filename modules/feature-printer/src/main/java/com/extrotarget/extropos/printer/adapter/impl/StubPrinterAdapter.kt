package com.extrotarget.extropos.printer.adapter.impl

import android.content.Context
import com.extrotarget.extropos.printer.adapter.PrinterAdapter
import com.extrotarget.extropos.printer.adapter.PrinterFeature
import com.extrotarget.extropos.printer.domain.model.*

/**
 * Stub adapter for SDKs that are not yet implemented
 * Provides placeholder functionality for testing and development
 */
class StubPrinterAdapter(
    private val context: Context,
    private val sdkName: String
) : PrinterAdapter {

    override suspend fun connect(config: PrinterConfig): Boolean {
        println("StubAdapter[$sdkName]: Connect called with config: ${config.name}")
        return false // Stub implementation always returns false
    }

    override suspend fun disconnect() {
        println("StubAdapter[$sdkName]: Disconnect called")
    }

    override suspend fun print(printJob: PrintJob): PrintResult {
        println("StubAdapter[$sdkName]: Print called with job: ${printJob.id}")
        return PrintResult(
            success = false,
            message = "SDK '$sdkName' not yet implemented - this is a stub adapter"
        )
    }

    override suspend fun getStatus(): String {
        return "Disconnected (Stub SDK: $sdkName)"
    }

    override suspend fun getSupportedFeatures(): List<PrinterFeature> {
        return emptyList() // Stub implementation has no features
    }

    override suspend fun isConnected(): Boolean {
        return false // Stub implementation is never connected
    }

    override suspend fun testConnection(): Boolean {
        println("StubAdapter[$sdkName]: Test connection called")
        return false // Stub implementation always fails connection test
    }
}