package com.extrotarget.extropos.printer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PrinterDao {
    @Query("SELECT * FROM printers ORDER BY name")
    fun getAllFlow(): Flow<List<PrinterEntity>>

    @Query("SELECT * FROM printers WHERE id = :id")
    suspend fun getById(id: String): PrinterEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(printer: PrinterEntity)

    @Query("DELETE FROM printers WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("UPDATE printers SET isDefault = CASE WHEN id = :id THEN 1 ELSE 0 END")
    suspend fun setDefault(id: String)
}
