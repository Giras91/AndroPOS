package com.example.pos.domain.usecase.ticket

import com.example.pos.data.repository.ITicketRepository
import com.example.pos.domain.model.TicketItem
import javax.inject.Inject

class UpdateItemQuantityUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(item: TicketItem, newQuantity: Int) {
        ticketRepository.updateItemQuantity(item, newQuantity)
    }
}