package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.data.repository.ITicketRepository
import javax.inject.Inject

class CreateTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(): Ticket = ticketRepository.createTicket()
}