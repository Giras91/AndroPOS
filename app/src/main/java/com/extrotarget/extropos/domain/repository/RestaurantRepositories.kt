package com.extrotarget.extropos.domain.repository

import com.extrotarget.extropos.domain.model.*

interface IMenuRepository {
    suspend fun getAllCategories(): List<Category>
    suspend fun getCategoryById(id: String): Category?
    suspend fun getMenuItemsByCategory(categoryId: String): List<MenuItem>
    suspend fun getMenuItemById(id: String): MenuItem?
    suspend fun getAllMenuItems(): List<MenuItem>
    suspend fun searchMenuItems(query: String): List<MenuItem>
}

interface IOrderRepository {
    suspend fun createOrder(order: Order): String // Returns order ID
    suspend fun getOrderById(id: String): Order?
    suspend fun getOrdersByStatus(status: OrderStatus): List<Order>
    suspend fun getOrdersByTable(tableId: String): List<Order>
    suspend fun updateOrder(order: Order)
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus)
    suspend fun addItemToOrder(orderId: String, item: OrderItem)
    suspend fun updateOrderItem(orderId: String, item: OrderItem)
    suspend fun removeOrderItem(orderId: String, itemId: String)
    suspend fun getActiveOrders(): List<Order>
}

interface ITableRepository {
    suspend fun getAllTables(): List<Table>
    suspend fun getTableById(id: String): Table?
    suspend fun updateTableStatus(tableId: String, status: TableStatus)
    suspend fun assignOrderToTable(tableId: String, orderId: String)
    suspend fun getAvailableTables(): List<Table>
    suspend fun getOccupiedTables(): List<Table>
}