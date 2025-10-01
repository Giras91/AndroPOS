package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Order
import com.extrotarget.extropos.domain.repository.IOrderRepository
import javax.inject.Inject

class RemoveOrderItemUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {
    suspend operator fun invoke(order: Order, itemId: String): Order {
        val updatedItems = order.items.filter { it.id != itemId }
        return order.copy(items = updatedItems, updatedAt = System.currentTimeMillis())
    }

    suspend operator fun invoke(orderId: String, itemId: String) {
        orderRepository.removeOrderItem(orderId, itemId)
    }
}