package com.example.pos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.pos.data.local.entity.*

@Dao
interface TicketDao {
    @Query("SELECT * FROM tickets WHERE id = :id")
    suspend fun getTicketById(id: Int): TicketEntity?

    @Query("SELECT * FROM tickets WHERE state = :state")
    suspend fun getTicketsByState(state: String): List<TicketEntity>

    @Query("SELECT * FROM ticket_items WHERE ticket_id = :ticketId")
    suspend fun getTicketItems(ticketId: Int): List<TicketItemEntity>

    @Query("SELECT * FROM ticket_tenders WHERE ticket_id = :ticketId")
    suspend fun getTicketTenders(ticketId: Int): List<TicketTenderEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicket(ticket: TicketEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicketItem(item: TicketItemEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicketTender(tender: TicketTenderEntity): Long

    @Update
    suspend fun updateTicket(ticket: TicketEntity)

    @Query("DELETE FROM tickets WHERE id = :id")
    suspend fun deleteTicket(id: Int)

    @Query("SELECT * FROM tenders")
    suspend fun getAllTenders(): List<TenderEntity>

    @Query("SELECT * FROM departments")
    suspend fun getAllDepartments(): List<DepartmentEntity>

    @Query("SELECT * FROM tickets WHERE state IN ('SALE', 'RETURN') ORDER BY id DESC LIMIT 1")
    suspend fun getCurrentTicket(): TicketEntity?

    @Query("SELECT id FROM tickets WHERE state IN ('SALE', 'RETURN') ORDER BY id DESC LIMIT 1")
    suspend fun getCurrentTicketId(): Int?

    @Query("UPDATE tickets SET state = :state, updated_at = :updatedAt WHERE id = :ticketId")
    suspend fun updateTicketState(ticketId: Int, state: String, updatedAt: Long = System.currentTimeMillis())

    @Query("DELETE FROM ticket_items WHERE ticket_id = :ticketId")
    suspend fun clearTicket(ticketId: Int)

    @Query("UPDATE ticket_items SET quantity = :quantity, amount = :amount WHERE id = :itemId")
    suspend fun updateTicketItemQuantity(itemId: Int, quantity: Int, amount: Double)

    @Query("DELETE FROM ticket_items WHERE id = :itemId")
    suspend fun deleteTicketItem(itemId: Int)
}