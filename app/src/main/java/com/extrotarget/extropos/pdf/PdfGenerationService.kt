package com.extrotarget.extropos.pdf

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Environment
import com.extrotarget.extropos.data.local.entity.ProductEntity
import com.extrotarget.extropos.data.local.entity.SaleEntity
import com.extrotarget.extropos.domain.model.CartItem
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Service for generating PDF documents (receipts, reports, invoices).
 * Follows AndroPOS conventions: uses cents for amounts, MYR currency.
 */
@Singleton
class PdfGenerationService @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    private val dateOnlyFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    /**
     * Generate a receipt PDF for a sale transaction.
     * 
     * @param sale The sale entity with transaction details
     * @param items List of cart items in the sale
     * @param storeName Name of the store (e.g. "ExtroPOS")
     * @param storeAddress Store address line
     * @return File path to the generated PDF, or null if failed
     */
    fun generateReceipt(
        sale: SaleEntity,
        items: List<CartItem>,
        storeName: String = "ExtroPOS",
        storeAddress: String = "123 Main Street, Kuala Lumpur"
    ): String? {
        return try {
            val pdfDocument = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            val paint = android.graphics.Paint()

            var yPos = 50f

            // Header
            paint.textSize = 24f
            paint.isFakeBoldText = true
            canvas.drawText(storeName, 50f, yPos, paint)
            yPos += 30f

            paint.textSize = 12f
            paint.isFakeBoldText = false
            canvas.drawText(storeAddress, 50f, yPos, paint)
            yPos += 20f

            canvas.drawText("Receipt No: ${sale.receiptNo}", 50f, yPos, paint)
            yPos += 20f
            canvas.drawText("Date: ${dateFormat.format(Date(sale.createdAt))}", 50f, yPos, paint)
            yPos += 30f

            // Divider
            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Items header
            paint.isFakeBoldText = true
            canvas.drawText("Item", 50f, yPos, paint)
            canvas.drawText("Qty", 300f, yPos, paint)
            canvas.drawText("Price", 380f, yPos, paint)
            canvas.drawText("Total", 480f, yPos, paint)
            yPos += 20f
            paint.isFakeBoldText = false

            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Items
            for (item in items) {
                canvas.drawText(item.name, 50f, yPos, paint)
                canvas.drawText(item.quantity.toString(), 300f, yPos, paint)
                canvas.drawText(formatCentsToRM(item.unitPriceCents), 380f, yPos, paint)
                val totalCents = item.totalPriceCents
                canvas.drawText(formatCentsToRM(totalCents), 480f, yPos, paint)
                yPos += 20f
            }

            yPos += 10f
            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Total
            paint.textSize = 16f
            paint.isFakeBoldText = true
            canvas.drawText("TOTAL:", 380f, yPos, paint)
            canvas.drawText(formatCentsToRM(sale.totalAmountCents), 480f, yPos, paint)
            yPos += 30f

            paint.textSize = 12f
            paint.isFakeBoldText = false
            canvas.drawText("Payment: ${sale.paymentMethod}", 50f, yPos, paint)
            yPos += 30f

            // Footer
            canvas.drawText("Thank you for your business!", 50f, yPos, paint)
            yPos += 15f
            canvas.drawText("Powered by ExtroPOS", 50f, yPos, paint)

            pdfDocument.finishPage(page)

            // Save to file
            val fileName = "Receipt_${sale.receiptNo}_${System.currentTimeMillis()}.pdf"
            val file = getOutputFile(fileName)
            FileOutputStream(file).use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
            pdfDocument.close()

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Generate a daily sales report PDF.
     * 
     * @param sales List of sales for the day
     * @param date Date for the report
     * @return File path to the generated PDF, or null if failed
     */
    fun generateDailySalesReport(
        sales: List<SaleEntity>,
        date: Date = Date()
    ): String? {
        return try {
            val pdfDocument = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            val paint = android.graphics.Paint()

            var yPos = 50f

            // Header
            paint.textSize = 20f
            paint.isFakeBoldText = true
            canvas.drawText("Daily Sales Report", 50f, yPos, paint)
            yPos += 30f

            paint.textSize = 14f
            paint.isFakeBoldText = false
            canvas.drawText("Date: ${dateOnlyFormat.format(date)}", 50f, yPos, paint)
            yPos += 30f

            // Summary
            val totalSales = sales.size
            val totalAmountCents = sales.sumOf { it.totalAmountCents }
            val cashSales = sales.count { it.paymentMethod == "CASH" }
            val cardSales = sales.count { it.paymentMethod == "CARD" }

            paint.textSize = 12f
            canvas.drawText("Total Transactions: $totalSales", 50f, yPos, paint)
            yPos += 20f
            canvas.drawText("Total Amount: ${formatCentsToRM(totalAmountCents)}", 50f, yPos, paint)
            yPos += 20f
            canvas.drawText("Cash Payments: $cashSales", 50f, yPos, paint)
            yPos += 20f
            canvas.drawText("Card Payments: $cardSales", 50f, yPos, paint)
            yPos += 30f

            // Divider
            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Sales list header
            paint.isFakeBoldText = true
            canvas.drawText("Receipt No", 50f, yPos, paint)
            canvas.drawText("Time", 200f, yPos, paint)
            canvas.drawText("Payment", 350f, yPos, paint)
            canvas.drawText("Amount", 480f, yPos, paint)
            yPos += 20f
            paint.isFakeBoldText = false

            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Sales list
            for (sale in sales) {
                if (yPos > 750f) break // Prevent overflow (multi-page handling would be added in production)
                
                canvas.drawText(sale.receiptNo, 50f, yPos, paint)
                canvas.drawText(SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(sale.createdAt)), 200f, yPos, paint)
                canvas.drawText(sale.paymentMethod, 350f, yPos, paint)
                canvas.drawText(formatCentsToRM(sale.totalAmountCents), 480f, yPos, paint)
                yPos += 20f
            }

            pdfDocument.finishPage(page)

            // Save to file
            val fileName = "DailySalesReport_${dateOnlyFormat.format(date)}_${System.currentTimeMillis()}.pdf"
            val file = getOutputFile(fileName)
            FileOutputStream(file).use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
            pdfDocument.close()

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Generate an invoice PDF (for B2B transactions).
     * 
     * @param sale The sale entity
     * @param items List of cart items
     * @param customerName Customer/business name
     * @param customerAddress Customer address
     * @return File path to the generated PDF, or null if failed
     */
    fun generateInvoice(
        sale: SaleEntity,
        items: List<CartItem>,
        customerName: String,
        customerAddress: String
    ): String? {
        return try {
            val pdfDocument = PdfDocument()
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
            val page = pdfDocument.startPage(pageInfo)
            val canvas = page.canvas
            val paint = android.graphics.Paint()

            var yPos = 50f

            // Header
            paint.textSize = 28f
            paint.isFakeBoldText = true
            canvas.drawText("INVOICE", 50f, yPos, paint)
            yPos += 40f

            // Invoice details
            paint.textSize = 12f
            paint.isFakeBoldText = false
            canvas.drawText("Invoice No: ${sale.receiptNo}", 50f, yPos, paint)
            canvas.drawText("Date: ${dateFormat.format(Date(sale.createdAt))}", 350f, yPos, paint)
            yPos += 30f

            // Bill to
            paint.isFakeBoldText = true
            canvas.drawText("BILL TO:", 50f, yPos, paint)
            yPos += 20f
            paint.isFakeBoldText = false
            canvas.drawText(customerName, 50f, yPos, paint)
            yPos += 15f
            canvas.drawText(customerAddress, 50f, yPos, paint)
            yPos += 30f

            // Divider
            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Items header
            paint.isFakeBoldText = true
            canvas.drawText("Description", 50f, yPos, paint)
            canvas.drawText("Qty", 300f, yPos, paint)
            canvas.drawText("Unit Price", 380f, yPos, paint)
            canvas.drawText("Amount", 480f, yPos, paint)
            yPos += 20f
            paint.isFakeBoldText = false

            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Items
            for (item in items) {
                canvas.drawText(item.name, 50f, yPos, paint)
                canvas.drawText(item.quantity.toString(), 300f, yPos, paint)
                canvas.drawText(formatCentsToRM(item.unitPriceCents), 380f, yPos, paint)
                val totalCents = item.totalPriceCents
                canvas.drawText(formatCentsToRM(totalCents), 480f, yPos, paint)
                yPos += 20f
            }

            yPos += 10f
            canvas.drawLine(50f, yPos, 545f, yPos, paint)
            yPos += 20f

            // Total
            paint.textSize = 16f
            paint.isFakeBoldText = true
            canvas.drawText("TOTAL AMOUNT:", 320f, yPos, paint)
            canvas.drawText(formatCentsToRM(sale.totalAmountCents), 480f, yPos, paint)
            yPos += 40f

            // Payment terms
            paint.textSize = 12f
            paint.isFakeBoldText = false
            canvas.drawText("Payment Method: ${sale.paymentMethod}", 50f, yPos, paint)
            yPos += 20f
            canvas.drawText("Payment Status: PAID", 50f, yPos, paint)
            yPos += 40f

            // Footer
            canvas.drawText("Thank you for your business!", 50f, yPos, paint)

            pdfDocument.finishPage(page)

            // Save to file
            val fileName = "Invoice_${sale.receiptNo}_${System.currentTimeMillis()}.pdf"
            val file = getOutputFile(fileName)
            FileOutputStream(file).use { outputStream ->
                pdfDocument.writeTo(outputStream)
            }
            pdfDocument.close()

            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Format cents (Long) to RM currency string.
     * Example: 12345 cents → "RM 123.45"
     */
    private fun formatCentsToRM(cents: Long): String {
        val ringgit = cents / 100
        val sen = cents % 100
        return "RM %,d.%02d".format(ringgit, sen)
    }

    /**
     * Get output file in the app's documents directory.
     */
    private fun getOutputFile(fileName: String): File {
        val documentsDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            "ExtroPOS"
        )
        if (!documentsDir.exists()) {
            documentsDir.mkdirs()
        }
        return File(documentsDir, fileName)
    }

    /**
     * Get all generated PDF files.
     */
    fun getAllPdfFiles(): List<File> {
        val documentsDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            "ExtroPOS"
        )
        return if (documentsDir.exists()) {
            documentsDir.listFiles()?.filter { it.extension == "pdf" } ?: emptyList()
        } else {
            emptyList()
        }
    }
}
