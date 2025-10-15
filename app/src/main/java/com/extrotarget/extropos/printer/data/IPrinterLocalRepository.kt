package com.extrotarget.extropos.printer.data

import kotlinx.coroutines.flow.Flow

interface IPrinterLocalRepository {
    fun observePrinters(): Flow<List<PrinterEntity>>
    suspend fun getAll(): List<PrinterEntity>
    suspend fun upsert(printer: PrinterEntity)
    suspend fun deleteById(id: String)
    suspend fun setDefault(id: String)
}

