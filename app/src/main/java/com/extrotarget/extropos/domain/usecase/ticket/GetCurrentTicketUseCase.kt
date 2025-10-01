package com.extrotarget.extropos.domain.usecase.ticket

import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.data.repository.ITicketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    operator fun invoke(): Flow<Ticket?> = ticketRepository.getCurrentTicket()
}
