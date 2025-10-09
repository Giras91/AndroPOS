package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Category
import com.extrotarget.extropos.domain.repository.IMenuRepository
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
    private val menuRepository: IMenuRepository
) {
    suspend operator fun invoke(category: Category) {
        menuRepository.upsertCategory(category)
    }
}
