package com.example.pos.domain.usecase.ticket

import com.example.pos.data.repository.ITicketRepository
import com.example.pos.domain.model.Ticket
import com.example.pos.domain.model.TicketItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(): Ticket = ticketRepository.createTicket()
}

class GetCurrentTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    operator fun invoke(): Flow<Ticket?> = ticketRepository.getCurrentTicket()
}

class AddItemToTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(item: TicketItem) = ticketRepository.addItemToTicket(item)
}

class UpdateItemQuantityUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(item: TicketItem, newQuantity: Int) =
        ticketRepository.updateItemQuantity(item, newQuantity)
}

class RemoveItemFromTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke(item: TicketItem) = ticketRepository.removeItemFromTicket(item)
}

class ClearTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke() = ticketRepository.clearCurrentTicket()
}

class SuspendTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke() = ticketRepository.suspendTicket()
}

class CompleteTicketUseCase @Inject constructor(
    private val ticketRepository: ITicketRepository
) {
    suspend operator fun invoke() = ticketRepository.completeTicket()
}