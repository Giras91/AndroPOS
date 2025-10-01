package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.repository.ITableRepository
import javax.inject.Inject

class GetOccupiedTablesUseCase @Inject constructor(
    private val tableRepository: ITableRepository
) {
    suspend operator fun invoke(): List<Table> = tableRepository.getOccupiedTables()
}