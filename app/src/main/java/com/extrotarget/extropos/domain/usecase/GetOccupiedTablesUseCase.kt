package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.model.TableStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetOccupiedTablesUseCase {
    operator fun invoke(): Flow<List<Table>> = flowOf(emptyList()) // TODO: Implement with repository
}