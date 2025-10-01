package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.repository.ITableRepository
import javax.inject.Inject

class GetTableUseCase @Inject constructor(
    private val tableRepository: ITableRepository
) {
    suspend operator fun invoke(tableId: String): Table? = tableRepository.getTableById(tableId)
}