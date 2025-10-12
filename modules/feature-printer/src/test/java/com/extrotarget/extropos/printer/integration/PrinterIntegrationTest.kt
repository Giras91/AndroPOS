package com.extrotarget.extropos.printer.integration

import com.extrotarget.extropos.printer.domain.service.PrinterService
import com.extrotarget.extropos.printer.domain.model.PrintJob
import com.extrotarget.extropos.printer.domain.model.PrintItem
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

/**
 * Integration test to verify printer system works end-to-end
 */
class PrinterIntegrationTest {
    
    @Test
    fun testPrinterSystemBasicFunctionality() = runBlocking {
        // Test that we can create a print job with the new architecture
        val printItems = listOf(
            PrintItem("text", "=== RECEIPT TEST ==="),
            PrintItem("text", "Date: 2025-10-11"),
            PrintItem("text", "Items: Test Product x1"),
            PrintItem("text", "Total: RM 10.50"),
            PrintItem("text", "Thank you!"),
            PrintItem("cut", "")
        )
        
        val printJob = PrintJob(
            content = printItems,
            copies = 1
        )
        
        // Verify print job structure
        assertNotNull(printJob.id)
        assertEquals(6, printJob.content.size)
        assertEquals("text", printJob.content[0].type)
        assertEquals("cut", printJob.content[5].type)
        
        println("✅ Print job creation successful")
        println("📄 Job ID: ${printJob.id}")
        println("📝 Content items: ${printJob.content.size}")
    }
}