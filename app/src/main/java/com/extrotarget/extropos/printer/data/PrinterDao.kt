package com.extrotarget.extropos.printer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PrinterDao {

    @Query("SELECT * FROM printers ORDER BY isDefault DESC, name ASC")
    fun getAllFlow(): Flow<List<PrinterEntity>>

    @Query("SELECT * FROM printers ORDER BY isDefault DESC, name ASC")
    suspend fun getAll(): List<PrinterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(printer: PrinterEntity)

    @Update
    suspend fun update(printer: PrinterEntity)

    @Query("DELETE FROM printers WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("UPDATE printers SET isDefault = CASE WHEN id = :id THEN 1 ELSE 0 END")
    suspend fun setDefault(id: String)
}

