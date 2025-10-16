package com.extrotarget.extropos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.extrotarget.extropos.data.local.entity.LocalChangeEntity

@Dao
interface LocalChangeDao {
    @Query("SELECT * FROM local_changes WHERE entity = :entityType ORDER BY createdAt ASC")
    suspend fun getChangesByEntity(entityType: String): List<LocalChangeEntity>

    @Query("SELECT * FROM local_changes WHERE entity = :entityType AND entityId = :entityId ORDER BY createdAt DESC")
    suspend fun getChangesForEntity(entityType: String, entityId: String): List<LocalChangeEntity>

    @Query("SELECT * FROM local_changes ORDER BY createdAt DESC LIMIT :limit")
    suspend fun getRecentChanges(limit: Int = 100): List<LocalChangeEntity>

    @Query("SELECT COUNT(*) FROM local_changes WHERE entity = :entityType")
    suspend fun getChangeCountForEntity(entityType: String): Int

    @Query("SELECT COUNT(*) FROM local_changes")
    suspend fun getTotalChangeCount(): Int

    @Insert
    suspend fun logChange(change: LocalChangeEntity)

    @Query("DELETE FROM local_changes WHERE id = :changeId")
    suspend fun deleteChange(changeId: Long)

    @Query("DELETE FROM local_changes WHERE entity = :entityType AND entityId = :entityId")
    suspend fun deleteChangesForEntity(entityType: String, entityId: String)

    @Query("DELETE FROM local_changes WHERE createdAt < :cutoffTime")
    suspend fun cleanupOldChanges(cutoffTime: Long)
}