package com.extrotarget.extropos.data.local.room

import androidx.room.*
import com.extrotarget.extropos.data.local.entity.*

@Dao
interface RoomTicketDao {
    @Query("SELECT * FROM tickets WHERE id = :id LIMIT 1")
    suspend fun getTicketById(id: Int): TicketEntity?

    @Query("SELECT * FROM tickets WHERE state = :state")
    suspend fun getTicketsByState(state: String): List<TicketEntity>

    @Query("SELECT * FROM ticket_items WHERE ticketId = :ticketId")
    suspend fun getTicketItems(ticketId: Int): List<TicketItemEntity>

    @Query("SELECT * FROM ticket_tenders WHERE ticketId = :ticketId")
    suspend fun getTicketTenders(ticketId: Int): List<TicketTenderEntity>

    @Insert
    suspend fun insertTicket(ticket: TicketEntity): Long

    @Insert
    suspend fun insertTicketItem(item: TicketItemEntity): Long

    @Insert
    suspend fun insertTicketTender(tender: TicketTenderEntity): Long

    @Update
    suspend fun updateTicket(ticket: TicketEntity)

    @Query("DELETE FROM tickets WHERE id = :id")
    suspend fun deleteTicket(id: Int)

    @Query("SELECT * FROM tenders")
    suspend fun getAllTenders(): List<TenderEntity>

    @Query("SELECT * FROM departments")
    suspend fun getAllDepartments(): List<DepartmentEntity>

    @Query("SELECT * FROM tax_groups")
    suspend fun getAllTaxGroups(): List<TaxGroupEntity>

    @Query("SELECT * FROM tickets WHERE state IN (1,2) ORDER BY id DESC LIMIT 1")
    suspend fun getCurrentTicket(): TicketEntity?

    @Query("SELECT id FROM tickets WHERE state IN (1,2) ORDER BY id DESC LIMIT 1")
    suspend fun getCurrentTicketId(): Int?

    @Query("UPDATE tickets SET state = :state, updatedAt = :updatedAt WHERE id = :ticketId")
    suspend fun updateTicketState(ticketId: Int, state: String, updatedAt: Long)

    @Query("DELETE FROM ticket_items WHERE ticketId = :ticketId")
    suspend fun clearTicket(ticketId: Int)

    @Query("UPDATE ticket_items SET quantity = :quantity, amount = :amountCents WHERE id = :itemId")
    suspend fun updateTicketItemQuantity(itemId: Int, quantity: Int, amountCents: Long)

    @Query("DELETE FROM ticket_items WHERE id = :itemId")
    suspend fun deleteTicketItem(itemId: Int)
}
