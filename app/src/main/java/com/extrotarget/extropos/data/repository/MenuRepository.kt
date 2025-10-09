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

    override suspend fun upsertCategory(category: com.extrotarget.extropos.domain.model.Category) {
        val entity = CategoryEntity(
            id = category.id,
            name = category.name,
            description = category.description.ifBlank { null },
            displayOrder = 0,
            isActive = true
        )
        categoryDao.upsert(entity)
    }

    override suspend fun upsertMenuItem(item: com.extrotarget.extropos.domain.model.MenuItem) {
        val allergensJson = try {
            moshi.adapter(List::class.java).toJson(item.allergens)
        } catch (e: Exception) {
            null
        }

        val entity = MenuItemEntity(
            id = item.id,
            name = item.name,
            description = item.description.ifBlank { null },
            priceCents = item.priceCents,
            categoryId = item.categoryId,
            imageUrl = item.imageUrl.ifBlank { null },
            isAvailable = item.isAvailable,
            preparationTimeMinutes = item.preparationTimeMinutes.takeIf { it > 0 },
            allergens = allergensJson
        )
        menuItemDao.upsert(entity)
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
            description = description ?: "",
            imageUrl = ""
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
            categoryId = categoryId,
            name = name,
            description = description ?: "",
            priceCents = priceCents,
            imageUrl = imageUrl ?: "",
            preparationTimeMinutes = preparationTimeMinutes ?: 0,
            allergens = allergensList,
            isAvailable = isAvailable
        )
    }
}