package com.extrotarget.extropos.printer.data

import com.extrotarget.extropos.printer.domain.model.PrintJob

/**
 * High-level repository for printing operations. Implementations should
 * coordinate between available printer SDK wrappers and fallbacks.
 */
interface IPrinterRepository {
    suspend fun print(job: PrintJob): Result<Boolean>
    suspend fun availablePrinters(): List<String>
}
