package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Order
import com.extrotarget.extropos.domain.repository.IOrderRepository
import javax.inject.Inject

class GetOrderUseCase @Inject constructor(
    private val orderRepository: IOrderRepository
) {
    suspend operator fun invoke(orderId: String): Order? = orderRepository.getOrderById(orderId)
}