package com.extrotarget.extropos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.extrotarget.extropos.data.local.entity.SyncStateEntity

@Dao
interface SyncStateDao {
    @Query("SELECT * FROM sync_states WHERE entity = :entityName")
    suspend fun getSyncState(entityName: String): SyncStateEntity?

    @Query("SELECT * FROM sync_states")
    suspend fun getAllSyncStates(): List<SyncStateEntity>

    @Query("SELECT lastSyncedAt FROM sync_states WHERE entity = :entityName")
    suspend fun getLastSyncTime(entityName: String): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSyncState(state: SyncStateEntity)

    @Query("UPDATE sync_states SET lastSyncedAt = :timestamp WHERE entity = :entityName")
    suspend fun updateLastSyncTime(entityName: String, timestamp: Long)

    @Query("DELETE FROM sync_states WHERE entity = :entityName")
    suspend fun deleteSyncState(entityName: String)
}