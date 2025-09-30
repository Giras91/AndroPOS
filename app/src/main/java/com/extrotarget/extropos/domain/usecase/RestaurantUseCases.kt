package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.*
import com.extrotarget.extropos.domain.repository.IMenuRepository
import com.extrotarget.extropos.domain.repository.IOrderRepository
import com.extrotarget.extropos.domain.repository.ITableRepository

class GetMenuUseCase(private val menuRepository: IMenuRepository) {
    suspend operator fun invoke(): Map<Category, List<MenuItem>> {
        val categories = menuRepository.getAllCategories()
        return categories.associateWith { category ->
            menuRepository.getMenuItemsByCategory(category.id)
        }
    }
}

class GetMenuItemsUseCase(private val menuRepository: IMenuRepository) {
    suspend operator fun invoke(): List<MenuItem> {
        return menuRepository.getAllMenuItems()
    }
}

class SearchMenuItemsUseCase(private val menuRepository: IMenuRepository) {
    suspend operator fun invoke(query: String): List<MenuItem> {
        return menuRepository.searchMenuItems(query)
    }
}

class CreateOrderUseCase(private val orderRepository: IOrderRepository) {
    suspend operator fun invoke(
        tableId: String? = null,
        orderType: OrderType = OrderType.DINE_IN
    ): String {
        val orderNumber = generateOrderNumber()
        val order = Order(
            id = "", // Will be set by repository
            tableId = tableId,
            orderNumber = orderNumber,
            orderType = orderType
        )
        return orderRepository.createOrder(order)
    }

    private fun generateOrderNumber(): String {
        val timestamp = System.currentTimeMillis()
        return "ORD-$timestamp"
    }
}

class AddItemToOrderUseCase(private val orderRepository: IOrderRepository) {
    suspend operator fun invoke(orderId: String, menuItem: MenuItem, quantity: Int = 1, notes: String? = null) {
        val orderItem = OrderItem(
            id = "", // Will be set by repository
            menuItemId = menuItem.id,
            menuItemName = menuItem.name,
            quantity = quantity,
            unitPrice = menuItem.price,
            totalPrice = menuItem.price * quantity,
            notes = notes
        )
        orderRepository.addItemToOrder(orderId, orderItem)
    }
}

class GetActiveOrdersUseCase(private val orderRepository: IOrderRepository) {
    suspend operator fun invoke(): List<Order> {
        return orderRepository.getActiveOrders()
    }
}

class UpdateOrderStatusUseCase(private val orderRepository: IOrderRepository) {
    suspend operator fun invoke(orderId: String, status: OrderStatus) {
        orderRepository.updateOrderStatus(orderId, status)
    }
}

class GetAvailableTablesUseCase(private val tableRepository: ITableRepository) {
    suspend operator fun invoke(): List<Table> {
        return tableRepository.getAvailableTables()
    }
}

class AssignTableToOrderUseCase(
    private val tableRepository: ITableRepository,
    private val orderRepository: IOrderRepository
) {
    suspend operator fun invoke(tableId: String, orderId: String) {
        tableRepository.assignOrderToTable(tableId, orderId)
        tableRepository.updateTableStatus(tableId, TableStatus.OCCUPIED)
    }
}