package com.extrotarget.extropos.data.local.dao

import com.extrotarget.extropos.data.local.entity.*

interface TicketDao {
    suspend fun getTicketById(id: Int): TicketEntity?
    suspend fun getTicketsByState(state: String): List<TicketEntity>
    suspend fun getTicketItems(ticketId: Int): List<TicketItemEntity>
    suspend fun getTicketTenders(ticketId: Int): List<TicketTenderEntity>
    suspend fun insertTicket(ticket: TicketEntity): Long
    suspend fun insertTicketItem(item: TicketItemEntity): Long
    suspend fun insertTicketTender(tender: TicketTenderEntity): Long
    suspend fun updateTicket(ticket: TicketEntity)
    suspend fun deleteTicket(id: Int)
    suspend fun getAllTenders(): List<TenderEntity>
    suspend fun getAllDepartments(): List<DepartmentEntity>
    suspend fun getAllTaxGroups(): List<TaxGroupEntity>
    suspend fun getCurrentTicket(): TicketEntity?
    suspend fun getCurrentTicketId(): Int?
    suspend fun updateTicketState(ticketId: Int, state: String, updatedAt: Long = System.currentTimeMillis())
    suspend fun clearTicket(ticketId: Int)
    suspend fun updateTicketItemQuantity(itemId: Int, quantity: Int, amount: Double)
    suspend fun deleteTicketItem(itemId: Int)
}