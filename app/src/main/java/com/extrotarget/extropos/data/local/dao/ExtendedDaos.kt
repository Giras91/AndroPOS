package com.extrotarget.extropos.data.local.dao

import androidx.room.*
import com.extrotarget.extropos.data.local.entity.*
import kotlinx.coroutines.flow.Flow

/**
 * DAO for SaleItemEntity - handles line items in sales transactions.
 */
@Dao
interface SaleItemDao {
    @Query("SELECT * FROM sale_items WHERE saleId = :saleId")
    suspend fun getItemsBySaleId(saleId: String): List<SaleItemEntity>

    @Query("SELECT * FROM sale_items WHERE saleId = :saleId")
    fun observeItemsBySaleId(saleId: String): Flow<List<SaleItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: SaleItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SaleItemEntity>)

    @Delete
    suspend fun delete(item: SaleItemEntity)

    @Query("DELETE FROM sale_items WHERE saleId = :saleId")
    suspend fun deleteAllForSale(saleId: String)

    @Query("DELETE FROM sale_items")
    suspend fun clearAll()
}

/**
 * DAO for CustomerEntity - customer management.
 */
@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers WHERE isActive = 1 ORDER BY name ASC")
    suspend fun getAllActive(): List<CustomerEntity>

    @Query("SELECT * FROM customers WHERE isActive = 1 ORDER BY name ASC")
    fun observeAllActive(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE id = :id")
    suspend fun getById(id: String): CustomerEntity?

    @Query("SELECT * FROM customers WHERE phone = :phone LIMIT 1")
    suspend fun getByPhone(phone: String): CustomerEntity?

    @Query("SELECT * FROM customers WHERE email = :email LIMIT 1")
    suspend fun getByEmail(email: String): CustomerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: CustomerEntity)

    @Update
    suspend fun update(customer: CustomerEntity)

    @Query("UPDATE customers SET isActive = 0 WHERE id = :id")
    suspend fun deactivate(id: String)

    @Query("DELETE FROM customers")
    suspend fun clearAll()

    @Query("SELECT * FROM customers WHERE name LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%'")
    suspend fun search(query: String): List<CustomerEntity>
}

/**
 * DAO for InventoryTransactionEntity - stock tracking.
 */
@Dao
interface InventoryTransactionDao {
    @Query("SELECT * FROM inventory_transactions WHERE productId = :productId ORDER BY createdAt DESC")
    suspend fun getByProductId(productId: String): List<InventoryTransactionEntity>

    @Query("SELECT * FROM inventory_transactions WHERE productId = :productId ORDER BY createdAt DESC")
    fun observeByProductId(productId: String): Flow<List<InventoryTransactionEntity>>

    @Query("SELECT * FROM inventory_transactions WHERE type = :type ORDER BY createdAt DESC LIMIT :limit")
    suspend fun getByType(type: String, limit: Int = 100): List<InventoryTransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: InventoryTransactionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transactions: List<InventoryTransactionEntity>)

    @Query("DELETE FROM inventory_transactions")
    suspend fun clearAll()

    @Query("SELECT * FROM inventory_transactions WHERE createdAt BETWEEN :startTime AND :endTime ORDER BY createdAt DESC")
    suspend fun getByDateRange(startTime: Long, endTime: Long): List<InventoryTransactionEntity>
}

/**
 * DAO for PaymentEntity - payment tracking with split payment support.
 */
@Dao
interface PaymentDao {
    @Query("SELECT * FROM payments WHERE saleId = :saleId")
    suspend fun getBySaleId(saleId: String): List<PaymentEntity>

    @Query("SELECT * FROM payments WHERE saleId = :saleId")
    fun observeBySaleId(saleId: String): Flow<List<PaymentEntity>>

    @Query("SELECT * FROM payments WHERE method = :method AND createdAt BETWEEN :startTime AND :endTime")
    suspend fun getByMethodAndDateRange(method: String, startTime: Long, endTime: Long): List<PaymentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(payment: PaymentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(payments: List<PaymentEntity>)

    @Update
    suspend fun update(payment: PaymentEntity)

    @Query("DELETE FROM payments WHERE saleId = :saleId")
    suspend fun deleteAllForSale(saleId: String)

    @Query("DELETE FROM payments")
    suspend fun clearAll()
}

/**
 * Extended DAO for SaleEntity with comprehensive querying.
 */
@Dao
interface SaleDao {
    @Query("SELECT * FROM sales ORDER BY createdAt DESC LIMIT :limit")
    suspend fun getRecent(limit: Int = 50): List<SaleEntity>

    @Query("SELECT * FROM sales WHERE id = :id")
    suspend fun getById(id: String): SaleEntity?

    @Query("SELECT * FROM sales WHERE receiptNo = :receiptNo LIMIT 1")
    suspend fun getByReceiptNo(receiptNo: String): SaleEntity?

    @Query("SELECT * FROM sales WHERE createdAt BETWEEN :startTime AND :endTime ORDER BY createdAt DESC")
    suspend fun getByDateRange(startTime: Long, endTime: Long): List<SaleEntity>

    @Query("SELECT * FROM sales WHERE customerId = :customerId ORDER BY createdAt DESC")
    suspend fun getByCustomerId(customerId: String): List<SaleEntity>

    @Query("SELECT * FROM sales WHERE userId = :userId ORDER BY createdAt DESC LIMIT :limit")
    suspend fun getByUserId(userId: String, limit: Int = 100): List<SaleEntity>

    @Query("SELECT * FROM sales WHERE paymentMethod = :method AND createdAt BETWEEN :startTime AND :endTime")
    suspend fun getByPaymentMethod(method: String, startTime: Long, endTime: Long): List<SaleEntity>

    @Query("SELECT * FROM sales WHERE isTraining = :isTraining ORDER BY createdAt DESC")
    fun observeByTrainingMode(isTraining: Boolean): Flow<List<SaleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sale: SaleEntity)

    @Update
    suspend fun update(sale: SaleEntity)

    @Query("DELETE FROM sales WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM sales WHERE isTraining = 1")
    suspend fun deleteAllTraining()

    @Query("DELETE FROM sales")
    suspend fun clearAll()

    // Analytics queries
    @Query("SELECT SUM(totalAmountCents) FROM sales WHERE createdAt BETWEEN :startTime AND :endTime AND paymentStatus = 'PAID'")
    suspend fun getTotalSales(startTime: Long, endTime: Long): Long?

    @Query("SELECT COUNT(*) FROM sales WHERE createdAt BETWEEN :startTime AND :endTime")
    suspend fun getTransactionCount(startTime: Long, endTime: Long): Int
}
