package com.extrotarget.extropos.domain.usecase.ticket

import com.extrotarget.extropos.data.repository.ITicketRepository
import javax.inject.Inject

class ClearTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke() = ticketRepository.clearCurrentTicket()
}
