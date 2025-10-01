package com.extrotarget.extropos.domain.usecase.ticket

import com.extrotarget.extropos.domain.model.TicketItem
import com.extrotarget.extropos.data.repository.ITicketRepository
import javax.inject.Inject

class AddItemToTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(item: TicketItem) = ticketRepository.addItemToTicket(item)
}
