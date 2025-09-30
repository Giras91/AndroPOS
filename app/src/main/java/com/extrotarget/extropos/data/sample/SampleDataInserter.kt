package com.extrotarget.extropos.data.sample

import com.extrotarget.extropos.data.local.dao.CategoryDao
import com.extrotarget.extropos.data.local.dao.MenuItemDao
import com.extrotarget.extropos.data.local.dao.TableDao
import com.extrotarget.extropos.data.local.entity.CategoryEntity
import com.extrotarget.extropos.data.local.entity.MenuItemEntity
import com.extrotarget.extropos.data.local.entity.TableEntity
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleDataInserter @Inject constructor(
    private val categoryDao: CategoryDao,
    private val menuItemDao: MenuItemDao,
    private val tableDao: TableDao,
    private val moshi: Moshi
) {

    suspend fun insertSampleData() = withContext(Dispatchers.IO) {
        // Insert sample categories
        val categories = listOf(
            CategoryEntity(
                id = "cat-001",
                name = "Appetizers",
                description = "Start your meal with our delicious appetizers",
                displayOrder = 1,
                isActive = true
            ),
            CategoryEntity(
                id = "cat-002",
                name = "Main Courses",
                description = "Hearty main dishes for every taste",
                displayOrder = 2,
                isActive = true
            ),
            CategoryEntity(
                id = "cat-003",
                name = "Beverages",
                description = "Refreshing drinks and beverages",
                displayOrder = 3,
                isActive = true
            ),
            CategoryEntity(
                id = "cat-004",
                name = "Desserts",
                description = "Sweet endings to your meal",
                displayOrder = 4,
                isActive = true
            )
        )

        categories.forEach { categoryDao.insert(it) }

        // Insert sample menu items
        val menuItems = listOf(
            // Appetizers
            MenuItemEntity(
                id = "item-001",
                name = "Chicken Wings",
                description = "Crispy fried chicken wings with buffalo sauce",
                priceCents = 1250, // RM 12.50
                categoryId = "cat-001",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 15,
                allergens = moshi.adapter(List::class.java).toJson(listOf("chicken", "gluten"))
            ),
            MenuItemEntity(
                id = "item-002",
                name = "Spring Rolls",
                description = "Vegetable spring rolls with sweet chili sauce",
                priceCents = 850, // RM 8.50
                categoryId = "cat-001",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 10,
                allergens = moshi.adapter(List::class.java).toJson(listOf("gluten"))
            ),

            // Main Courses
            MenuItemEntity(
                id = "item-003",
                name = "Nasi Lemak",
                description = "Fragrant coconut rice with fried chicken, sambal, and condiments",
                priceCents = 1500, // RM 15.00
                categoryId = "cat-002",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 20,
                allergens = moshi.adapter(List::class.java).toJson(listOf("coconut", "chicken"))
            ),
            MenuItemEntity(
                id = "item-004",
                name = "Char Kway Teow",
                description = "Stir-fried flat rice noodles with prawns, eggs, and bean sprouts",
                priceCents = 1350, // RM 13.50
                categoryId = "cat-002",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 15,
                allergens = moshi.adapter(List::class.java).toJson(listOf("shellfish", "eggs", "gluten"))
            ),
            MenuItemEntity(
                id = "item-005",
                name = "Rendang Beef",
                description = "Slow-cooked beef in rich coconut curry",
                priceCents = 1850, // RM 18.50
                categoryId = "cat-002",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 25,
                allergens = moshi.adapter(List::class.java).toJson(listOf("coconut", "beef"))
            ),

            // Beverages
            MenuItemEntity(
                id = "item-006",
                name = "Teh Tarik",
                description = "Malaysian pulled tea with condensed milk",
                priceCents = 450, // RM 4.50
                categoryId = "cat-003",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 5,
                allergens = moshi.adapter(List::class.java).toJson(listOf("dairy"))
            ),
            MenuItemEntity(
                id = "item-007",
                name = "Fresh Orange Juice",
                description = "Freshly squeezed orange juice",
                priceCents = 650, // RM 6.50
                categoryId = "cat-003",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 3,
                allergens = null
            ),

            // Desserts
            MenuItemEntity(
                id = "item-008",
                name = "Cendol",
                description = "Sweet coconut dessert with green rice flour jelly and palm sugar",
                priceCents = 550, // RM 5.50
                categoryId = "cat-004",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 5,
                allergens = moshi.adapter(List::class.java).toJson(listOf("coconut", "gluten"))
            ),
            MenuItemEntity(
                id = "item-009",
                name = "Durian Pengat",
                description = "Creamy durian custard dessert",
                priceCents = 750, // RM 7.50
                categoryId = "cat-004",
                imageUrl = null,
                isAvailable = true,
                preparationTimeMinutes = 8,
                allergens = moshi.adapter(List::class.java).toJson(listOf("dairy"))
            )
        )

        menuItems.forEach { menuItemDao.insert(it) }

        // Insert sample tables
        val tables = listOf(
            TableEntity(
                id = "table-001",
                number = 1,
                name = "Table 1",
                status = "AVAILABLE",
                capacity = 4,
                location = "Window Side",
                createdAt = Date(),
                updatedAt = Date()
            ),
            TableEntity(
                id = "table-002",
                number = 2,
                name = "Table 2",
                status = "AVAILABLE",
                capacity = 6,
                location = "Center",
                createdAt = Date(),
                updatedAt = Date()
            ),
            TableEntity(
                id = "table-003",
                number = 3,
                name = "Table 3",
                status = "AVAILABLE",
                capacity = 2,
                location = "Corner",
                createdAt = Date(),
                updatedAt = Date()
            ),
            TableEntity(
                id = "table-004",
                number = 4,
                name = "Table 4",
                status = "AVAILABLE",
                capacity = 8,
                location = "Patio",
                createdAt = Date(),
                updatedAt = Date()
            ),
            TableEntity(
                id = "table-005",
                number = 5,
                name = "Bar Counter",
                status = "AVAILABLE",
                capacity = 1,
                location = "Bar",
                createdAt = Date(),
                updatedAt = Date()
            )
        )

        tables.forEach { tableDao.insert(it) }
    }
}