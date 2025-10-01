package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.TicketItem
import com.extrotarget.extropos.data.repository.ITicketRepository
import javax.inject.Inject

class UpdateItemQuantityUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(item: TicketItem, newQuantity: Int) =
        ticketRepository.updateItemQuantity(item, newQuantity)
}