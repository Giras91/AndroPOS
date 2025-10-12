package com.extrotarget.extropos.printer

import com.extrotarget.extropos.printer.data.PrinterRepository
import com.extrotarget.extropos.printer.domain.model.PrintJob
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class PrinterRepositoryTest {
    @Test
    fun testPrintSimulation() = runBlocking {
        val repo = PrinterRepository()
        val printItems = listOf(
            com.extrotarget.extropos.printer.domain.model.PrintItem("text", "Test content")
        )
        val res = repo.print(PrintJob(content = printItems))
        // Our current implementation simulates successful printing
        Assert.assertTrue("Print simulation should succeed", res.isSuccess)
        
        val printers = repo.availablePrinters()
        Assert.assertFalse("Should have simulated printers available", printers.isEmpty())
    }
}
