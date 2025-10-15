package com.extrotarget.extropos.printer.data

import com.extrotarget.extropos.ui.settings.printer.DiscoveredPrinter

interface IPrinterScanner {
    suspend fun scanBluetooth(): List<DiscoveredPrinter>
    suspend fun scanUsb(): List<DiscoveredPrinter>
    suspend fun scanNetwork(): List<DiscoveredPrinter>
}

