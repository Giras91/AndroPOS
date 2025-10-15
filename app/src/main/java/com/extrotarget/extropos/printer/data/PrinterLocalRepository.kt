package com.extrotarget.extropos.printer.data

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class PrinterLocalRepository @Inject constructor(
    private val dao: PrinterDao
) : IPrinterLocalRepository {

    override fun observePrinters(): Flow<List<PrinterEntity>> = dao.getAllFlow()

    override suspend fun getAll(): List<PrinterEntity> = dao.getAll()

    override suspend fun upsert(printer: PrinterEntity) {
        dao.upsert(printer)
    }

    override suspend fun deleteById(id: String) {
        dao.deleteById(id)
    }

    override suspend fun setDefault(id: String) {
        dao.setDefault(id)
    }
}

