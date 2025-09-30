package com.example.pos.data.repository

import com.example.pos.data.local.dao.TicketDao
import com.example.pos.data.local.entity.*
import com.example.pos.domain.model.Ticket
import com.example.pos.domain.model.TicketItem
import com.example.pos.domain.model.TicketState
import com.example.pos.domain.model.TicketTender
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import javax.inject.Inject
import javax.inject.Singleton

interface ITicketRepository {
    suspend fun createTicket(): Ticket
    fun getCurrentTicket(): Flow<Ticket?>
    suspend fun getTicketById(id: Int): Ticket?
    suspend fun addItemToTicket(item: TicketItem)
    suspend fun updateItemQuantity(item: TicketItem, newQuantity: Int)
    suspend fun removeItemFromTicket(item: TicketItem)
    suspend fun clearCurrentTicket()
    suspend fun suspendTicket()
    suspend fun completeTicket()
    suspend fun getSuspendedTickets(): List<Ticket>
    suspend fun getAllTenders(): List<TenderEntity>
    suspend fun getAllDepartments(): List<DepartmentEntity>
    suspend fun getAllTaxGroups(): List<TaxGroupEntity>
}

@Singleton
class TicketRepository @Inject constructor(
    private val ticketDao: TicketDao
) : ITicketRepository {

    override suspend fun createTicket(): Ticket {
        val entity = TicketEntity(
            id = 0,
            ticketType = "SALE",
            state = TicketState.SALE.name,
            total = 0.0,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            sessionId = 1 // TODO: Get from session manager
        )
        val id = ticketDao.insertTicket(entity)
        return getTicketById(id.toInt()) ?: throw IllegalStateException("Failed to create ticket")
    }

    override fun getCurrentTicket(): Flow<Ticket?> {
        return ticketDao.getCurrentTicket().map { entity ->
            entity?.let { mapToDomain(it) }
        }
    }

    override suspend fun getTicketById(id: Int): Ticket? {
        val entity = ticketDao.getTicketById(id) ?: return null
        return mapToDomain(entity)
    }

    override suspend fun addItemToTicket(item: TicketItem) {
        val ticketId = getCurrentTicketId()
        val entity = TicketItemEntity(
            ticketId = ticketId,
            itemId = item.productId.toString(),
            sku = "", // TODO: Add SKU to TicketItem
            quantity = item.quantity,
            amount = item.totalCents / 100.0,
            cost = 0.0, // TODO: Add cost
            itemDesc = item.productName,
            state = "ACTIVE"
        )
        ticketDao.insertTicketItem(entity)
    }

    override suspend fun updateItemQuantity(item: TicketItem, newQuantity: Int) {
        val newAmount = newQuantity * (item.unitPriceCents / 100.0)
        ticketDao.updateTicketItemQuantity(item.id, newQuantity, newAmount)
    }

    override suspend fun removeItemFromTicket(item: TicketItem) {
        ticketDao.deleteTicketItem(item.id)
    }

    override suspend fun clearCurrentTicket() {
        val currentId = getCurrentTicketId()
        ticketDao.clearTicket(currentId)
    }

    override suspend fun suspendTicket() {
        val currentId = getCurrentTicketId()
        ticketDao.updateTicketState(currentId, TicketState.SUSPENDED.name)
    }

    override suspend fun completeTicket() {
        val currentId = getCurrentTicketId()
        ticketDao.updateTicketState(currentId, TicketState.COMPLETED.name)
    }

    override suspend fun getSuspendedTickets(): List<Ticket> {
        val entities = ticketDao.getTicketsByState(TicketState.SUSPENDED.name)
        return entities.mapNotNull { getTicketById(it.id) }
    }

    override suspend fun getAllTenders(): List<TenderEntity> = ticketDao.getAllTenders()

    override suspend fun getAllDepartments(): List<DepartmentEntity> = ticketDao.getAllDepartments()

    override suspend fun getAllTaxGroups(): List<TaxGroupEntity> = ticketDao.getAllTaxGroups()

    private suspend fun getCurrentTicketId(): Int {
        return ticketDao.getCurrentTicketId() ?: createTicket().id
    }

    private suspend fun mapToDomain(entity: TicketEntity): Ticket {
        val items = ticketDao.getTicketItems(entity.id).map { itemEntity ->
            TicketItem(
                id = itemEntity.id,
                productId = itemEntity.itemId,
                productName = itemEntity.itemDesc,
                quantity = itemEntity.quantity,
                unitPriceCents = (itemEntity.amount / itemEntity.quantity * 100).toInt(),
                totalCents = (itemEntity.amount * 100).toInt()
            )
        }

        val tenders = ticketDao.getTicketTenders(entity.id).map { tenderEntity ->
            TicketTender(
                id = tenderEntity.id,
                ticketId = tenderEntity.ticketId,
                tenderType = tenderEntity.tenderType,
                amountCents = (tenderEntity.amount * 100).toInt(),
                reference = "" // TODO: Add reference field
            )
        }

        return Ticket(
            id = entity.id,
            state = TicketState.valueOf(entity.state),
            items = items,
            tenders = tenders,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }
}