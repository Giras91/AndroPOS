package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.CategoryDao
import com.extrotarget.extropos.data.local.dao.MenuItemDao
import com.extrotarget.extropos.data.local.entity.CategoryEntity
import com.extrotarget.extropos.data.local.entity.MenuItemEntity
import com.extrotarget.extropos.domain.model.Category
import com.extrotarget.extropos.domain.model.MenuItem
import com.extrotarget.extropos.domain.repository.IMenuRepository
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MenuRepository @Inject constructor(
    private val categoryDao: CategoryDao,
    private val menuItemDao: MenuItemDao,
    private val moshi: Moshi
) : IMenuRepository {

    override suspend fun getAllCategories(): List<Category> {
        return categoryDao.getAllActive().map { it.toDomain() }
    }

    override suspend fun getCategoryById(id: String): Category? {
        return categoryDao.getById(id)?.toDomain()
    }

    override suspend fun getMenuItemsByCategory(categoryId: String): List<MenuItem> {
        return menuItemDao.getByCategory(categoryId).map { it.toDomain() }
    }

    override suspend fun getMenuItemById(id: String): MenuItem? {
        return menuItemDao.getById(id)?.toDomain()
    }

    override suspend fun getAllMenuItems(): List<MenuItem> {
        return menuItemDao.getAllAvailable().map { it.toDomain() }
    }

    override suspend fun searchMenuItems(query: String): List<MenuItem> {
        return menuItemDao.searchByName(query).map { it.toDomain() }
    }

    private fun CategoryEntity.toDomain(): Category {
        return Category(
            id = id,
            name = name,
            description = description,
            displayOrder = displayOrder,
            isActive = isActive
        )
    }

    private fun MenuItemEntity.toDomain(): MenuItem {
        val allergensList = allergens?.let {
            try {
                moshi.adapter(List::class.java).fromJson(it) as? List<String> ?: emptyList()
            } catch (e: Exception) {
                emptyList<String>()
            }
        } ?: emptyList()

        return MenuItem(
            id = id,
            name = name,
            description = description,
            price = priceCents,
            categoryId = categoryId,
            imageUrl = imageUrl,
            isAvailable = isAvailable,
            preparationTime = preparationTimeMinutes,
            allergens = allergensList
        )
    }
}