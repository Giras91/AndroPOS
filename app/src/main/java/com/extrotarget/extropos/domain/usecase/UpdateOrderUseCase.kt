package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Order

class UpdateOrderUseCase {
    suspend operator fun invoke(order: Order): Order {
        // TODO: Implement with repository
        return order.copy(updatedAt = System.currentTimeMillis())
    }
}