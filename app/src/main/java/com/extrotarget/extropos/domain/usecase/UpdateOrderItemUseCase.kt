package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Order
import com.extrotarget.extropos.domain.model.OrderItem
import com.extrotarget.extropos.domain.repository.IOrderRepository
import javax.inject.Inject

class UpdateOrderItemUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {
    // Existing API operating on full Order (kept for compatibility)
    suspend operator fun invoke(order: Order, itemId: String, updatedItem: OrderItem): Order {
        val updatedItems = order.items.map { item ->
            if (item.id == itemId) updatedItem else item
        }
        return order.copy(items = updatedItems, updatedAt = System.currentTimeMillis())
    }

    // New helper that operates by orderId and delegates to repository
    suspend operator fun invoke(orderId: String, updatedItem: OrderItem) {
        orderRepository.updateOrderItem(orderId, updatedItem)
    }
}