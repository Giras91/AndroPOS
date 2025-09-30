package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Ticket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetCurrentTicketUseCase {
    operator fun invoke(): Flow<Ticket?> = flowOf(null) // TODO: Implement with repository
}