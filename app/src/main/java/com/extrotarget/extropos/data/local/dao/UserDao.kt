package com.extrotarget.extropos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.extrotarget.extropos.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username AND isActive = 1")
    suspend fun getUserByUsername(username: String): UserEntity?

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE isActive = 1 ORDER BY displayName")
    suspend fun getAllActiveUsers(): List<UserEntity>

    @Query("SELECT * FROM users WHERE isActive = 1 ORDER BY displayName")
    fun getAllActiveUsersFlow(): Flow<List<UserEntity>>

    @Query("SELECT COUNT(*) FROM users WHERE isActive = 1")
    suspend fun getActiveUserCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("UPDATE users SET isActive = 0, updatedAt = :timestamp WHERE id = :userId")
    suspend fun deactivateUser(userId: String, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE users SET passwordHash = :newHash, updatedAt = :timestamp WHERE id = :userId")
    suspend fun updatePassword(userId: String, newHash: String, timestamp: Long = System.currentTimeMillis())
}