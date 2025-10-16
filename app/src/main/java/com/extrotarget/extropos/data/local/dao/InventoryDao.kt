package com.extrotarget.extropos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.extrotarget.extropos.data.local.entity.InventoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Query("SELECT * FROM inventory WHERE productId = :productId")
    suspend fun getInventory(productId: String): InventoryEntity?

    @Query("SELECT * FROM inventory ORDER BY updatedAt DESC")
    suspend fun getAllInventory(): List<InventoryEntity>

    @Query("SELECT * FROM inventory ORDER BY updatedAt DESC")
    fun getAllInventoryFlow(): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory WHERE quantity <= :threshold ORDER BY quantity ASC")
    suspend fun getLowStockItems(threshold: Int = 5): List<InventoryEntity>

    @Query("SELECT SUM(quantity) FROM inventory")
    suspend fun getTotalStockQuantity(): Int?

    @Query("SELECT SUM(quantity * (SELECT priceCents FROM products WHERE id = productId)) FROM inventory")
    suspend fun getTotalStockValueCents(): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(inventory: InventoryEntity)

    @Update
    suspend fun update(inventory: InventoryEntity)

    @Query("UPDATE inventory SET quantity = quantity + :adjustment, updatedAt = :timestamp WHERE productId = :productId")
    suspend fun adjustQuantity(productId: String, adjustment: Int, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE inventory SET reserved = reserved + :adjustment, updatedAt = :timestamp WHERE productId = :productId")
    suspend fun adjustReserved(productId: String, adjustment: Int, timestamp: Long = System.currentTimeMillis())

    @Query("DELETE FROM inventory WHERE productId = :productId")
    suspend fun deleteInventory(productId: String)
}