package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetCategoriesUseCase {
    operator fun invoke(): Flow<List<Category>> = flowOf(emptyList()) // TODO: Implement with repository
}