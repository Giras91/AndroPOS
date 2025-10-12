package com.extrotarget.extropos.data.local.dao

import androidx.room.*
import com.extrotarget.extropos.data.local.entity.ShiftEntity

@Dao
interface ShiftDao {
    @Query("SELECT * FROM shifts WHERE id = :id")
    suspend fun getById(id: String): ShiftEntity?

    @Query("SELECT * FROM shifts WHERE userId = :userId ORDER BY startedAt DESC LIMIT 1")
    suspend fun getLatestForUser(userId: String): ShiftEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shift: ShiftEntity)

    @Update
    suspend fun update(shift: ShiftEntity)

    @Query("SELECT * FROM shifts WHERE userId = :userId ORDER BY startedAt DESC")
    suspend fun getAllForUser(userId: String): List<ShiftEntity>

    @Query("SELECT * FROM shifts WHERE startedAt BETWEEN :startTime AND :endTime ORDER BY startedAt ASC")
    suspend fun getShiftsByStartRange(startTime: Long, endTime: Long): List<ShiftEntity>
}
