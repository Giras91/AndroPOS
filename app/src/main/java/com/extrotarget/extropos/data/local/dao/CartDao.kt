package com.extrotarget.extropos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.extrotarget.extropos.data.local.entity.CartEntity
import com.extrotarget.extropos.data.local.entity.CartItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM carts WHERE id = :cartId")
    suspend fun getCart(cartId: String): CartEntity?

    @Query("SELECT * FROM carts WHERE userId = :userId ORDER BY updatedAt DESC LIMIT 1")
    suspend fun getLatestCartForUser(userId: String): CartEntity?

    @Query("SELECT * FROM cart_items WHERE cartId = :cartId ORDER BY createdAt")
    suspend fun getCartItems(cartId: String): List<CartItemEntity>

    @Query("SELECT * FROM cart_items WHERE cartId = :cartId ORDER BY createdAt")
    fun getCartItemsFlow(cartId: String): Flow<List<CartItemEntity>>

    @Query("SELECT COUNT(*) FROM cart_items WHERE cartId = :cartId")
    suspend fun getCartItemCount(cartId: String): Int

    @Query("SELECT SUM(quantity * priceCents) FROM cart_items WHERE cartId = :cartId")
    suspend fun getCartTotalCents(cartId: String): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCart(cart: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCartItem(item: CartItemEntity)

    @Query("UPDATE carts SET updatedAt = :timestamp WHERE id = :cartId")
    suspend fun updateCartTimestamp(cartId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE cart_items SET quantity = quantity + :adjustment WHERE id = :itemId")
    suspend fun adjustItemQuantity(itemId: Long, adjustment: Int)

    @Query("DELETE FROM cart_items WHERE id = :itemId")
    suspend fun removeCartItem(itemId: Long)

    @Query("DELETE FROM cart_items WHERE cartId = :cartId")
    suspend fun clearCart(cartId: String)

    @Query("DELETE FROM carts WHERE id = :cartId")
    suspend fun deleteCart(cartId: String)

    @Query("DELETE FROM carts WHERE updatedAt < :cutoffTime")
    suspend fun cleanupOldCarts(cutoffTime: Long)
}