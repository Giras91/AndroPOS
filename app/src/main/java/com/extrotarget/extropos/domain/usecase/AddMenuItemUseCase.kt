package com.extrotarget.extropos.domain.usecase

import com.extrotarget.extropos.domain.model.MenuItem
import com.extrotarget.extropos.domain.repository.IMenuRepository
import javax.inject.Inject

class AddMenuItemUseCase @Inject constructor(
    private val menuRepository: IMenuRepository
) {
    suspend operator fun invoke(item: MenuItem) {
        menuRepository.upsertMenuItem(item)
    }
}
