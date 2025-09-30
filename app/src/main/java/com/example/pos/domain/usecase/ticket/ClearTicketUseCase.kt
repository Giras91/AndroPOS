package com.example.pos.domain.usecase.ticket

import com.example.pos.data.repository.ITicketRepository
import javax.inject.Inject

class ClearTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke() {
        ticketRepository.clearCurrentTicket()
    }
}