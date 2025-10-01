package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.CartItem
import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.model.TicketItem
import com.extrotarget.extropos.data.repository.ITicketRepository
import javax.inject.Inject

@Deprecated("Use com.extrotarget.extropos.domain.usecase.ticket.AddItemToTicketUseCase")
class AddItemToTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    // Legacy wrapper: map CartItem -> TicketItem, delegate to repository, return the ticket unchanged
    suspend operator fun invoke(ticket: Ticket, cartItem: CartItem): Ticket {
        val ticketItem = TicketItem(
            id = "",
            productId = cartItem.productId,
            name = cartItem.name,
            quantity = cartItem.quantity,
            unitPriceCents = cartItem.unitPriceCents,
            notes = ""
        )
        ticketRepository.addItemToTicket(ticketItem)
        return ticket
    }
}