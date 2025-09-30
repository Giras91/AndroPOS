package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.model.TicketItem

class UpdateItemQuantityUseCase {
    suspend operator fun invoke(ticket: Ticket, itemId: String, newQuantity: Int): Ticket {
        // TODO: Implement with repository
        val updatedItems = ticket.items.map { item ->
            if (item.id == itemId) item.copy(quantity = newQuantity) else item
        }
        return ticket.copy(items = updatedItems)
    }
}