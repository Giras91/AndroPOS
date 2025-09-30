package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Table
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetTableUseCase {
    operator fun invoke(tableId: String): Flow<Table?> = flowOf(null) // TODO: Implement with repository
}