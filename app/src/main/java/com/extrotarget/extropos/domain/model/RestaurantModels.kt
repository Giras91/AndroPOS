package com.extrotarget.extropos.domain.model

data class MenuItem(
    val id: String,
    val name: String,
    val description: String? = null,
    val price: Long, // Price in cents to avoid floating point issues
    val categoryId: String,
    val imageUrl: String? = null,
    val isAvailable: Boolean = true,
    val preparationTime: Int? = null, // in minutes
    val allergens: List<String> = emptyList()
)

data class Category(
    val id: String,
    val name: String,
    val description: String? = null,
    val displayOrder: Int = 0,
    val isActive: Boolean = true
)

data class Order(
    val id: String,
    val tableId: String? = null,
    val orderNumber: String,
    val status: OrderStatus = OrderStatus.PENDING,
    val orderType: OrderType = OrderType.DINE_IN,
    val items: List<OrderItem> = emptyList(),
    val subtotal: Long = 0, // in cents
    val tax: Long = 0, // in cents
    val discount: Long = 0, // in cents
    val total: Long = 0, // in cents
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val notes: String? = null
)

data class OrderItem(
    val id: String,
    val menuItemId: String,
    val menuItemName: String,
    val quantity: Int,
    val unitPrice: Long, // in cents
    val totalPrice: Long, // in cents
    val notes: String? = null,
    val status: OrderItemStatus = OrderItemStatus.PENDING
)

data class Table(
    val id: String,
    val number: String,
    val capacity: Int,
    val status: TableStatus = TableStatus.AVAILABLE,
    val currentOrderId: String? = null
)

enum class OrderStatus {
    PENDING,    // Order placed, waiting for confirmation
    CONFIRMED,  // Order confirmed by staff
    PREPARING,  // Being prepared in kitchen
    READY,      // Ready for pickup/serving
    SERVED,     // Served to customer
    COMPLETED,  // Payment completed
    CANCELLED   // Order cancelled
}

enum class OrderType {
    DINE_IN,    // Customer eating in restaurant
    TAKEOUT,    // Customer taking food home
    DELIVERY    // Food delivered to customer
}

enum class OrderItemStatus {
    PENDING,    // Not yet started
    PREPARING,  // Being prepared
    READY,      // Ready to serve
    SERVED      // Served to customer
}

enum class TableStatus {
    AVAILABLE,  // Table is free
    OCCUPIED,   // Table has customers
    RESERVED,   // Table is reserved
    CLEANING    // Table being cleaned
}