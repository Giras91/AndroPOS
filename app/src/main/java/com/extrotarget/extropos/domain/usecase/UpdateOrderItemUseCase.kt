package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Order
import com.extrotarget.extropos.domain.model.OrderItem

class UpdateOrderItemUseCase {
    suspend operator fun invoke(order: Order, itemId: String, updatedItem: OrderItem): Order {
        // TODO: Implement with repository
        val updatedItems = order.items.map { item ->
            if (item.id == itemId) updatedItem else item
        }
        return order.copy(items = updatedItems, updatedAt = System.currentTimeMillis())
    }
}