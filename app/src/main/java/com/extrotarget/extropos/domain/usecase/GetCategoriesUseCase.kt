package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.Category
import com.extrotarget.extropos.domain.repository.IMenuRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val menuRepository: IMenuRepository
) {
    suspend operator fun invoke(): List<Category> = menuRepository.getAllCategories()
}