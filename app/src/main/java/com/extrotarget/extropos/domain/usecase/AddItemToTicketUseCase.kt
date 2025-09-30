package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.CartItem
import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.model.TicketItem

class AddItemToTicketUseCase {
    suspend operator fun invoke(ticket: Ticket, cartItem: CartItem): Ticket {
        // TODO: Implement with repository
        val ticketItem = TicketItem(
            id = "item_${System.currentTimeMillis()}",
            productId = cartItem.productId,
            name = cartItem.name,
            quantity = cartItem.quantity,
            unitPriceCents = cartItem.unitPriceCents,
            notes = cartItem.notes
        )
        return ticket.copy(items = ticket.items + ticketItem)
    }
}