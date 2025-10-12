package com.extrotarget.extropos.printer.template

import com.extrotarget.extropos.printer.domain.model.PrintItem
import com.extrotarget.extropos.printer.domain.model.PrintJob
import java.text.SimpleDateFormat
import java.util.*

/**
 * Receipt template builder for POS transactions
 */
object ReceiptTemplateBuilder {

    /**
     * Create a standard POS receipt
     */
    fun createPosReceipt(
        storeName: String,
        storeAddress: String? = null,
        transactionId: String,
        items: List<ReceiptItem>,
        subtotal: Double,
        tax: Double = 0.0,
        total: Double,
        paymentMethod: String = "Cash",
        amountPaid: Double = total,
        change: Double = 0.0,
        cashierName: String? = null
    ): PrintJob {
        val printItems = mutableListOf<PrintItem>()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val currentDate = dateFormat.format(Date())

        // Header
        printItems.add(PrintItem(
            "text", 
            storeName,
            mapOf("alignment" to "center", "bold" to true, "size" to "large")
        ))
        
        if (!storeAddress.isNullOrBlank()) {
            printItems.add(PrintItem(
                "text",
                storeAddress,
                mapOf("alignment" to "center", "size" to "small")
            ))
        }
        
        printItems.add(PrintItem("line", ""))
        
        // Transaction info
        printItems.add(PrintItem("text", "Date: $currentDate"))
        printItems.add(PrintItem("text", "Transaction: $transactionId"))
        if (!cashierName.isNullOrBlank()) {
            printItems.add(PrintItem("text", "Cashier: $cashierName"))
        }
        
        printItems.add(PrintItem("line", ""))

        // Items
        items.forEach { item ->
            // Item name and quantity
            printItems.add(PrintItem(
                "text",
                "${item.name} x${item.quantity}",
                mapOf("bold" to true)
            ))
            
            // Price (right aligned)
            printItems.add(PrintItem(
                "text",
                "RM %.2f".format(item.totalPrice),
                mapOf("alignment" to "right")
            ))
        }

        printItems.add(PrintItem("line", ""))

        // Totals
        printItems.add(PrintItem(
            "text",
            "Subtotal:${" ".repeat(15)}RM %.2f".format(subtotal),
            mapOf("alignment" to "left")
        ))
        
        if (tax > 0) {
            printItems.add(PrintItem(
                "text",
                "Tax:${" ".repeat(20)}RM %.2f".format(tax)
            ))
        }
        
        printItems.add(PrintItem(
            "text",
            "TOTAL:${" ".repeat(17)}RM %.2f".format(total),
            mapOf("bold" to true, "size" to "large")
        ))

        printItems.add(PrintItem("line", ""))

        // Payment info
        printItems.add(PrintItem("text", "Payment: $paymentMethod"))
        printItems.add(PrintItem("text", "Amount Paid:${" ".repeat(11)}RM %.2f".format(amountPaid)))
        
        if (change > 0) {
            printItems.add(PrintItem(
                "text",
                "Change:${" ".repeat(17)}RM %.2f".format(change),
                mapOf("bold" to true)
            ))
        }

        printItems.add(PrintItem("line", ""))

        // Footer
        printItems.add(PrintItem(
            "text",
            "Thank you for your purchase!",
            mapOf("alignment" to "center", "bold" to true)
        ))
        
        printItems.add(PrintItem(
            "text",
            "Visit us again soon!",
            mapOf("alignment" to "center")
        ))

        // QR Code for digital receipt (optional)
        printItems.add(PrintItem("qr", "TXN:$transactionId|TOTAL:$total|DATE:$currentDate"))

        // Cut paper
        printItems.add(PrintItem("cut", ""))

        return PrintJob(content = printItems, copies = 1)
    }

    /**
     * Create a simple test receipt
     */
    fun createTestReceipt(): PrintJob {
        val items = listOf(
            ReceiptItem("Test Product 1", 2, 10.50, 21.00),
            ReceiptItem("Test Product 2", 1, 15.75, 15.75)
        )
        
        return createPosReceipt(
            storeName = "AndroPOS Demo Store",
            storeAddress = "123 Demo Street, Test City",
            transactionId = "TEST-${System.currentTimeMillis()}",
            items = items,
            subtotal = 36.75,
            tax = 0.0,
            total = 36.75,
            paymentMethod = "Cash",
            amountPaid = 40.00,
            change = 3.25,
            cashierName = "Demo User"
        )
    }

    /**
     * Create a kitchen order ticket
     */
    fun createKitchenTicket(
        orderNumber: String,
        tableNumber: String? = null,
        items: List<ReceiptItem>,
        specialInstructions: String? = null
    ): PrintJob {
        val printItems = mutableListOf<PrintItem>()
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTime = timeFormat.format(Date())

        // Header
        printItems.add(PrintItem(
            "text",
            "KITCHEN ORDER",
            mapOf("alignment" to "center", "bold" to true, "size" to "large")
        ))
        
        printItems.add(PrintItem("line", ""))
        
        printItems.add(PrintItem(
            "text",
            "Order: $orderNumber",
            mapOf("bold" to true, "size" to "large")
        ))
        
        if (!tableNumber.isNullOrBlank()) {
            printItems.add(PrintItem(
                "text",
                "Table: $tableNumber",
                mapOf("bold" to true, "size" to "large")
            ))
        }
        
        printItems.add(PrintItem("text", "Time: $currentTime"))
        printItems.add(PrintItem("line", ""))

        // Items
        items.forEach { item ->
            printItems.add(PrintItem(
                "text",
                "${item.quantity}x ${item.name}",
                mapOf("bold" to true, "size" to "large")
            ))
            
            if (item.notes.isNotBlank()) {
                printItems.add(PrintItem(
                    "text",
                    "   Notes: ${item.notes}",
                    mapOf("size" to "small")
                ))
            }
        }

        if (!specialInstructions.isNullOrBlank()) {
            printItems.add(PrintItem("line", ""))
            printItems.add(PrintItem(
                "text",
                "SPECIAL INSTRUCTIONS:",
                mapOf("bold" to true)
            ))
            printItems.add(PrintItem("text", specialInstructions))
        }

        printItems.add(PrintItem("cut", ""))

        return PrintJob(content = printItems, copies = 1)
    }
}

/**
 * Data class for receipt items
 */
data class ReceiptItem(
    val name: String,
    val quantity: Int,
    val unitPrice: Double,
    val totalPrice: Double,
    val notes: String = ""
)