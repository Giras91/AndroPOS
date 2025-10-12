package com.extrotarget.extropos.domain.usecase.ticket

import com.extrotarget.extropos.data.repository.ITicketRepository
import com.extrotarget.extropos.domain.model.TicketTender
import javax.inject.Inject

class AddTicketTenderUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(tender: TicketTender) = ticketRepository.addTicketTender(tender)
}
