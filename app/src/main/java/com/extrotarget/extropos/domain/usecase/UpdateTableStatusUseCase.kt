package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.model.TableStatus

class UpdateTableStatusUseCase {
    suspend operator fun invoke(table: Table, newStatus: TableStatus): Table {
        // TODO: Implement with repository
        return table.copy(status = newStatus)
    }
}