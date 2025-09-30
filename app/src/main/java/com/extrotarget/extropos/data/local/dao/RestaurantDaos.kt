package com.extrotarget.extropos.data.local.dao

import androidx.room.*
import com.extrotarget.extropos.data.local.entity.*

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE isActive = 1 ORDER BY displayOrder ASC")
    suspend fun getAllActive(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getById(id: String): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg categories: CategoryEntity)

    @Query("UPDATE categories SET isActive = :isActive WHERE id = :id")
    suspend fun updateActiveStatus(id: String, isActive: Boolean)
}

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items WHERE isAvailable = 1")
    suspend fun getAllAvailable(): List<MenuItemEntity>

    @Query("SELECT * FROM menu_items WHERE categoryId = :categoryId AND isAvailable = 1")
    suspend fun getByCategory(categoryId: String): List<MenuItemEntity>

    @Query("SELECT * FROM menu_items WHERE id = :id")
    suspend fun getById(id: String): MenuItemEntity?

    @Query("SELECT * FROM menu_items WHERE name LIKE '%' || :query || '%' AND isAvailable = 1")
    suspend fun searchByName(query: String): List<MenuItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: MenuItemEntity)

    @Query("UPDATE menu_items SET isAvailable = :isAvailable WHERE id = :id")
    suspend fun updateAvailability(id: String, isAvailable: Boolean)
}

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders WHERE status IN ('PENDING', 'CONFIRMED', 'PREPARING', 'READY') ORDER BY createdAt DESC")
    suspend fun getActiveOrders(): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE status = :status ORDER BY createdAt DESC")
    suspend fun getByStatus(status: String): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE tableId = :tableId ORDER BY createdAt DESC")
    suspend fun getByTable(tableId: String): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getById(id: String): OrderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: OrderEntity): Long

    @Update
    suspend fun update(order: OrderEntity)

    @Query("UPDATE orders SET status = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateStatus(id: String, status: String, updatedAt: Long = System.currentTimeMillis())
}

@Dao
interface OrderItemDao {
    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    suspend fun getByOrderId(orderId: String): List<OrderItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg items: OrderItemEntity)

    @Update
    suspend fun update(item: OrderItemEntity)

    @Delete
    suspend fun delete(item: OrderItemEntity)

    @Query("DELETE FROM order_items WHERE orderId = :orderId AND id = :itemId")
    suspend fun deleteById(orderId: String, itemId: String)
}

@Dao
interface TableDao {
    @Query("SELECT * FROM tables")
    suspend fun getAll(): List<TableEntity>

    @Query("SELECT * FROM tables WHERE id = :id")
    suspend fun getById(id: String): TableEntity?

    @Query("SELECT * FROM tables WHERE status = 'AVAILABLE'")
    suspend fun getAvailable(): List<TableEntity>

    @Query("SELECT * FROM tables WHERE status = 'OCCUPIED'")
    suspend fun getOccupied(): List<TableEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg tables: TableEntity)

    @Query("UPDATE tables SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: String, status: String)

    @Query("UPDATE tables SET currentOrderId = :orderId WHERE id = :tableId")
    suspend fun assignOrder(tableId: String, orderId: String)
}