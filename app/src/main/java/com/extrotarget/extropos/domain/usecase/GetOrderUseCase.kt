package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetOrderUseCase {
    operator fun invoke(orderId: String): Flow<Order?> = flowOf(null) // TODO: Implement with repository
}