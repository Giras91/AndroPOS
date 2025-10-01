package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.MenuItem
import com.extrotarget.extropos.domain.repository.IMenuRepository
import javax.inject.Inject

class GetMenuItemsUseCase @Inject constructor(
    private val menuRepository: IMenuRepository
) {
    suspend operator fun invoke(categoryId: String? = null): List<MenuItem> {
        return if (categoryId == null) menuRepository.getAllMenuItems()
        else menuRepository.getMenuItemsByCategory(categoryId)
    }
}