package com.extrotarget.extropos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * Product entity representing items available for sale.
 * Uses cents for monetary values to avoid floating-point precision issues.
 */
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: String,
    val name: String,
    val priceCents: Long,      // Price in cents (e.g., 1250 = RM 12.50)
    val sku: String?,          // Stock Keeping Unit
    val stockQuantity: Int,
    val categoryId: String? = null,
    val description: String? = null,
    val imageUrl: String? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

/**
 * Sale entity representing a completed transaction.
 * Links to SaleItemEntity for line items.
 */
@Entity(tableName = "sales")
data class SaleEntity(
    @PrimaryKey val id: String,
    val receiptNo: String,           // Human-readable receipt number
    val totalAmountCents: Long,      // Total in cents
    val subtotalCents: Long,         // Subtotal before tax/discount
    val taxCents: Long = 0L,         // Tax amount in cents
    val discountCents: Long = 0L,    // Discount amount in cents
    val createdAt: Long,             // Transaction timestamp
    val completedAt: Long? = null,   // When transaction was finalized
    val customerId: String? = null,  // Optional customer reference
    val userId: String,              // Staff who processed the sale
    val paymentMethod: String,       // CASH, CARD, EWALLET, etc.
    val paymentStatus: String = "PAID", // PAID, PENDING, REFUNDED
    val notes: String? = null,
    val isTraining: Boolean = false  // True if created in training mode
)

/**
 * Sale item entity representing individual line items in a sale.
 * This is the detailed breakdown of what was sold.
 */
@Entity(
    tableName = "sale_items",
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["id"],
            childColumns = ["saleId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("saleId"), Index("productId")]
)
data class SaleItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val saleId: String,              // Reference to parent sale
    val productId: String,           // Reference to product
    val productName: String,         // Snapshot of product name at sale time
    val quantity: Int,
    val unitPriceCents: Long,        // Price per unit at sale time
    val totalPriceCents: Long,       // quantity * unitPriceCents
    val discountCents: Long = 0L,    // Item-level discount
    val notes: String? = null        // Special instructions, modifiers
)

/**
 * Customer entity for customer relationship management.
 */
@Entity(tableName = "customers")
data class CustomerEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String? = null,
    val phone: String? = null,
    val address: String? = null,
    val loyaltyPoints: Int = 0,
    val totalPurchasesCents: Long = 0L,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val lastVisitAt: Long? = null,
    val notes: String? = null
)

/**
 * Inventory transaction entity for stock tracking.
 * Records all stock movements (sales, returns, adjustments, restocks).
 */
@Entity(
    tableName = "inventory_transactions",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index("productId")]
)
data class InventoryTransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val productId: String,
    val type: String,                // SALE, RETURN, ADJUSTMENT, RESTOCK
    val quantityChange: Int,         // Positive for increase, negative for decrease
    val quantityAfter: Int,          // Stock level after transaction
    val referenceSaleId: String? = null,  // Link to sale if applicable
    val userId: String,              // Staff who made the change
    val reason: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

/**
 * Payment entity for tracking payment details.
 * Supports split payments (multiple payment methods for one sale).
 */
@Entity(
    tableName = "payments",
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["id"],
            childColumns = ["saleId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("saleId")]
)
data class PaymentEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val saleId: String,
    val method: String,              // CASH, CARD, EWALLET, BANK_TRANSFER
    val amountCents: Long,
    val receivedCents: Long? = null, // For cash: amount tendered
    val changeCents: Long? = null,   // For cash: change given
    val referenceNo: String? = null, // Card transaction ref, e-wallet ID
    val status: String = "SUCCESS",  // SUCCESS, FAILED, PENDING
    val createdAt: Long = System.currentTimeMillis()
)

