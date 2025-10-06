package com.extrotarget.extropos.printer.data

import com.extrotarget.extropos.printer.domain.model.PrintJob

interface IPrinterRepository {
    suspend fun print(job: PrintJob): Result<Boolean>
    suspend fun availablePrinters(): List<String>
}
