package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Order

class RemoveOrderItemUseCase {
    suspend operator fun invoke(order: Order, itemId: String): Order {
        // TODO: Implement with repository
        val updatedItems = order.items.filter { it.id != itemId }
        return order.copy(items = updatedItems, updatedAt = System.currentTimeMillis())
    }
}