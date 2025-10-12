package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.repository.IMenuRepository
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
    private val menuRepository: IMenuRepository
) {
    suspend operator fun invoke(categoryId: String) {
        menuRepository.deleteCategoryById(categoryId)
    }
}