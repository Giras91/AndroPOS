package com.extrotarget.extropos.pdf.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.extrotarget.extropos.R
import com.extrotarget.extropos.data.local.entity.SaleEntity
import com.extrotarget.extropos.domain.model.CartItem
import com.extrotarget.extropos.pdf.PdfGenerationService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Test activity to demonstrate PDF generation features.
 * This shows how to use the PdfGenerationService to generate receipts, reports, and invoices.
 */
@AndroidEntryPoint
class PdfTestActivity : AppCompatActivity() {

    @Inject
    lateinit var pdfService: PdfGenerationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_test)

        // Test 1: Generate a sample receipt
        findViewById<android.widget.Button>(R.id.btn_test_receipt).setOnClickListener {
            testGenerateReceipt()
        }

        // Test 2: Generate a sample daily sales report
        findViewById<android.widget.Button>(R.id.btn_test_report).setOnClickListener {
            testGenerateDailySalesReport()
        }

        // Test 3: Generate a sample invoice
        findViewById<android.widget.Button>(R.id.btn_test_invoice).setOnClickListener {
            testGenerateInvoice()
        }

        // Test 4: List all generated PDFs
        findViewById<android.widget.Button>(R.id.btn_list_pdfs).setOnClickListener {
            listAllPdfs()
        }
    }

    private fun testGenerateReceipt() {
        // Create sample sale
        val sale = SaleEntity(
            id = "SALE001",
            receiptNo = "RCP001",
            totalAmountCents = 15000L, // RM 150.00
            subtotalCents = 14500L,
            taxCents = 500L,
            discountCents = 0L,
            createdAt = System.currentTimeMillis(),
            completedAt = System.currentTimeMillis(),
            customerId = null,
            userId = "USER001",
            paymentMethod = "CASH",
            paymentStatus = "PAID",
            notes = "Walk-in customer",
            isTraining = false
        )

        // Create sample cart items
        val items = listOf(
            CartItem(
                productId = "P001",
                name = "Nasi Lemak",
                quantity = 2,
                unitPriceCents = 1200L, // RM 12.00 each
                notes = ""
            ),
            CartItem(
                productId = "P002",
                name = "Teh Tarik",
                quantity = 3,
                unitPriceCents = 300L, // RM 3.00 each
                notes = "Less sugar"
            ),
            CartItem(
                productId = "P003",
                name = "Roti Canai",
                quantity = 5,
                unitPriceCents = 200L, // RM 2.00 each
                notes = ""
            ),
            CartItem(
                productId = "P004",
                name = "Mee Goreng",
                quantity = 1,
                unitPriceCents = 1100L, // RM 11.00
                notes = "Extra spicy"
            )
        )

        val filePath = pdfService.generateReceipt(sale, items)
        if (filePath != null) {
            Toast.makeText(this, "Receipt generated: $filePath", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Failed to generate receipt", Toast.LENGTH_SHORT).show()
        }
    }

    private fun testGenerateDailySalesReport() {
        // Create sample sales for the day
        val sales = listOf(
            SaleEntity("SALE001", "RCP001", 15000L, 14500L, 500L, 0L, System.currentTimeMillis() - 3600000, System.currentTimeMillis() - 3600000, null, "USER001", "CASH", "PAID"),
            SaleEntity("SALE002", "RCP002", 8500L, 8200L, 300L, 0L, System.currentTimeMillis() - 7200000, System.currentTimeMillis() - 7200000, null, "USER001", "CASH", "PAID"),
            SaleEntity("SALE003", "RCP003", 22000L, 21200L, 800L, 0L, System.currentTimeMillis() - 10800000, System.currentTimeMillis() - 10800000, "CUST001", "USER002", "CARD", "PAID"),
            SaleEntity("SALE004", "RCP004", 5000L, 4800L, 200L, 0L, System.currentTimeMillis() - 14400000, System.currentTimeMillis() - 14400000, null, "USER001", "CASH", "PAID"),
            SaleEntity("SALE005", "RCP005", 31000L, 29800L, 1200L, 0L, System.currentTimeMillis() - 18000000, System.currentTimeMillis() - 18000000, "CUST002", "USER002", "EWALLET", "PAID")
        )

        val filePath = pdfService.generateDailySalesReport(sales)
        if (filePath != null) {
            Toast.makeText(this, "Sales report generated: $filePath", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Failed to generate sales report", Toast.LENGTH_SHORT).show()
        }
    }

    private fun testGenerateInvoice() {
        // Create sample sale
        val sale = SaleEntity(
            id = "SALE006",
            receiptNo = "INV001",
            totalAmountCents = 450000L, // RM 4,500.00
            subtotalCents = 435000L,
            taxCents = 15000L,
            discountCents = 0L,
            createdAt = System.currentTimeMillis(),
            completedAt = System.currentTimeMillis(),
            customerId = "CUST123",
            userId = "USER001",
            paymentMethod = "BANK_TRANSFER",
            paymentStatus = "PAID",
            notes = "B2B order",
            isTraining = false
        )

        // Create sample cart items
        val items = listOf(
            CartItem("P010", "Catering Package A", 10, 25000L, "For 10 pax each"),
            CartItem("P011", "Beverage Package", 5, 5000L, "Assorted drinks"),
            CartItem("P012", "Dessert Platter", 15, 8000L, "Mixed desserts"),
            CartItem("P013", "Setup & Service Fee", 1, 75000L, "Full service")
        )

        val filePath = pdfService.generateInvoice(
            sale = sale,
            items = items,
            customerName = "ABC Sdn Bhd",
            customerAddress = "45, Jalan Sultan Ismail, 50250 Kuala Lumpur"
        )

        if (filePath != null) {
            Toast.makeText(this, "Invoice generated: $filePath", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Failed to generate invoice", Toast.LENGTH_SHORT).show()
        }
    }

    private fun listAllPdfs() {
        val pdfs = pdfService.getAllPdfFiles()
        val message = if (pdfs.isEmpty()) {
            "No PDFs generated yet"
        } else {
            "Found ${pdfs.size} PDF(s):\n${pdfs.joinToString("\n") { it.name }}"
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
