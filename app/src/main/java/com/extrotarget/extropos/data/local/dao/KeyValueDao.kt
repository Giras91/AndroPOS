package com.extrotarget.extropos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.extrotarget.extropos.data.local.entity.KeyValueEntity

@Dao
interface KeyValueDao {
    @Query("SELECT value FROM kv_store WHERE key = :key")
    suspend fun getValue(key: String): String?

    @Query("SELECT * FROM kv_store WHERE key = :key")
    suspend fun getKeyValue(key: String): KeyValueEntity?

    @Query("SELECT * FROM kv_store ORDER BY updatedAt DESC")
    suspend fun getAllKeyValues(): List<KeyValueEntity>

    @Query("SELECT key FROM kv_store WHERE value LIKE '%' || :searchTerm || '%'")
    suspend fun searchKeys(searchTerm: String): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setValue(kvEntity: KeyValueEntity)

    @Query("INSERT OR REPLACE INTO kv_store (key, value, updatedAt) VALUES (:key, :value, :timestamp)")
    suspend fun setValue(key: String, value: String?, timestamp: Long = System.currentTimeMillis())

    @Query("DELETE FROM kv_store WHERE key = :key")
    suspend fun deleteKey(key: String)

    @Query("DELETE FROM kv_store WHERE key LIKE :pattern")
    suspend fun deleteKeysByPattern(pattern: String)

    @Query("SELECT COUNT(*) FROM kv_store")
    suspend fun getKeyCount(): Int
}