package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Ticket

class CreateTicketUseCase {
    suspend operator fun invoke(): Ticket {
        // TODO: Implement with repository
        return Ticket(id = "ticket_${System.currentTimeMillis()}")
    }
}