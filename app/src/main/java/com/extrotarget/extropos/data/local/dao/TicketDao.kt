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

// Adapter to bridge RoomTicketDao (generated Room DAO) to the application's TicketDao interface
class RoomTicketDaoAdapter(private val room: com.extrotarget.extropos.data.local.room.RoomTicketDao) : TicketDao {
    override suspend fun getTicketById(id: Int): com.extrotarget.extropos.data.local.entity.TicketEntity? = room.getTicketById(id)
    override suspend fun getTicketsByState(state: String): List<com.extrotarget.extropos.data.local.entity.TicketEntity> = room.getTicketsByState(state)
    override suspend fun getTicketItems(ticketId: Int): List<com.extrotarget.extropos.data.local.entity.TicketItemEntity> = room.getTicketItems(ticketId)
    override suspend fun getTicketTenders(ticketId: Int): List<com.extrotarget.extropos.data.local.entity.TicketTenderEntity> = room.getTicketTenders(ticketId)
    override suspend fun insertTicket(ticket: com.extrotarget.extropos.data.local.entity.TicketEntity): Long = room.insertTicket(ticket)
    override suspend fun insertTicketItem(item: com.extrotarget.extropos.data.local.entity.TicketItemEntity): Long = room.insertTicketItem(item)
    override suspend fun insertTicketTender(tender: com.extrotarget.extropos.data.local.entity.TicketTenderEntity): Long = room.insertTicketTender(tender)
    override suspend fun updateTicket(ticket: com.extrotarget.extropos.data.local.entity.TicketEntity) = room.updateTicket(ticket)
    override suspend fun deleteTicket(id: Int) = room.deleteTicket(id)
    override suspend fun getAllTenders(): List<com.extrotarget.extropos.data.local.entity.TenderEntity> = room.getAllTenders()
    override suspend fun getAllDepartments(): List<com.extrotarget.extropos.data.local.entity.DepartmentEntity> = room.getAllDepartments()
    override suspend fun getAllTaxGroups(): List<com.extrotarget.extropos.data.local.entity.TaxGroupEntity> = room.getAllTaxGroups()
    override suspend fun getCurrentTicket(): com.extrotarget.extropos.data.local.entity.TicketEntity? = room.getCurrentTicket()
    override suspend fun getCurrentTicketId(): Int? = room.getCurrentTicketId()
    override suspend fun updateTicketState(ticketId: Int, state: String, updatedAt: Long) = room.updateTicketState(ticketId, state, updatedAt)
    override suspend fun clearTicket(ticketId: Int) = room.clearTicket(ticketId)
    override suspend fun updateTicketItemQuantity(itemId: Int, quantity: Int, amount: Double) = room.updateTicketItemQuantity(itemId, quantity, (amount * 100).toLong())
    override suspend fun deleteTicketItem(itemId: Int) = room.deleteTicketItem(itemId)
}