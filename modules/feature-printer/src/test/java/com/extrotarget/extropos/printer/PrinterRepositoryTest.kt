package com.extrotarget.extropos.printer

import com.extrotarget.extropos.printer.data.PrinterRepository
import com.extrotarget.extropos.printer.domain.model.PrintJob
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class PrinterRepositoryTest {
    @Test
    fun testPrintReturnsFailureWhenNoPrinter() = runBlocking {
        val repo = PrinterRepository()
        val res = repo.print(PrintJob("t", "b"))
        Assert.assertTrue(res.isFailure)
    }
}
