package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.MenuItem

class GetMenuItemsUseCase {
    suspend operator fun invoke(categoryId: String? = null): List<MenuItem> {
        // TODO: Implement with repository
        return emptyList()
    }
}