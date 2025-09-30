package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.model.TicketStatus

class ClearTicketUseCase {
    suspend operator fun invoke(ticket: Ticket): Ticket {
        // TODO: Implement with repository
        return ticket.copy(items = emptyList())
    }
}