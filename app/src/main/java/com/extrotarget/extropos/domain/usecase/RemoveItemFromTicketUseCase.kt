package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Ticket

class RemoveItemFromTicketUseCase {
    suspend operator fun invoke(ticket: Ticket, itemId: String): Ticket {
        // TODO: Implement with repository
        val updatedItems = ticket.items.filter { it.id != itemId }
        return ticket.copy(items = updatedItems)
    }
}