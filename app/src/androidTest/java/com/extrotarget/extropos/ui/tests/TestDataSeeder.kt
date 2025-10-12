package com.extrotarget.extropos.ui.tests

import com.extrotarget.extropos.data.local.dao.CategoryDao
import com.extrotarget.extropos.data.local.dao.ProductDao
import com.extrotarget.extropos.data.local.dao.MenuItemDao
import com.extrotarget.extropos.data.local.dao.OrderDao
import com.extrotarget.extropos.data.local.entity.CategoryEntity
import com.extrotarget.extropos.data.local.entity.ProductEntity
import com.extrotarget.extropos.data.local.entity.MenuItemEntity
import com.extrotarget.extropos.data.local.entity.OrderEntity
import kotlinx.coroutines.runBlocking

class TestDataSeeder(
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao,
    private val menuItemDao: MenuItemDao,
    private val orderDao: OrderDao
) {
    fun seedCategory(id: String, name: String, description: String? = null, displayOrder: Int = 0, isActive: Boolean = true) {
        val category = CategoryEntity(id, name, description, displayOrder, isActive)
        runBlocking { categoryDao.upsert(category) }
    }

    fun seedProduct(
        id: String,
        name: String,
        priceCents: Long,
        sku: String? = null,
        stockQuantity: Int = 0,
        categoryId: String? = null,
        description: String? = null,
        imageUrl: String? = null,
        isActive: Boolean = true
    ) {
        val product = ProductEntity(id, name, priceCents, sku, stockQuantity, categoryId, description, imageUrl, isActive)
        runBlocking { productDao.upsert(product) }
    }

    fun seedMenuItem(
        id: String,
        name: String,
        description: String? = null,
        priceCents: Long,
        categoryId: String,
        imageUrl: String? = null,
        isAvailable: Boolean = true,
        preparationTimeMinutes: Int? = null,
        allergens: String? = null
    ) {
        val item = MenuItemEntity(id, name, description, priceCents, categoryId, imageUrl, isAvailable, preparationTimeMinutes, allergens)
        runBlocking { menuItemDao.upsert(item) }
    }

    fun seedOrder(
        id: String,
        tableId: String?,
        orderNumber: String,
        status: String,
        orderType: String,
        subtotalCents: Long = 0,
        taxCents: Long = 0,
        discountCents: Long = 0,
        totalCents: Long = 0,
        notes: String? = null
    ) {
        val order = OrderEntity(id, tableId, orderNumber, status, orderType, subtotalCents, taxCents, discountCents, totalCents, System.currentTimeMillis(), System.currentTimeMillis(), notes)
        runBlocking { orderDao.insert(order) }
    }
}