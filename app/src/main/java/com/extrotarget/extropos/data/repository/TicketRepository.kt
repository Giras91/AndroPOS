package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.TicketDao
import com.extrotarget.extropos.data.local.entity.*
import com.extrotarget.extropos.domain.model.Ticket
import com.extrotarget.extropos.domain.model.TicketItem
import com.extrotarget.extropos.domain.model.TicketStatus
import com.extrotarget.extropos.domain.model.TicketTender
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
            ticketType = 1, // SALE/default
            state = 1, // OPEN/default
            total = 0L,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            sessionId = 1 // TODO: Get from session manager
        )
        val id = ticketDao.insertTicket(entity)
        return getTicketById(id.toInt()) ?: throw IllegalStateException("Failed to create ticket")
    }

    override fun getCurrentTicket(): Flow<Ticket?> {
        return flow {
            val entity = ticketDao.getCurrentTicket()
            emit(entity?.let { mapToDomain(it) })
        }
    }

    override suspend fun getTicketById(id: Int): Ticket? {
        val entity = ticketDao.getTicketById(id) ?: return null
        return mapToDomain(entity)
    }

    override suspend fun addItemToTicket(item: TicketItem) {
        val ticketId = getCurrentTicketId()
        val entity = TicketItemEntity(
            id = 0,
            ticketId = ticketId,
            itemId = item.productId.toIntOrNull() ?: 0,
            sku = "", // TODO: Add SKU to TicketItem
            quantity = item.quantity,
            amount = item.unitPriceCents,
            cost = 0L, // TODO: Add cost
            itemDesc = item.name,
            state = 1
        )
        ticketDao.insertTicketItem(entity)
    }

    override suspend fun updateItemQuantity(item: TicketItem, newQuantity: Int) {
        val newAmount = newQuantity * (item.unitPriceCents / 100.0)
        ticketDao.updateTicketItemQuantity(item.id.toIntOrNull() ?: 0, newQuantity, newAmount)
    }

    override suspend fun removeItemFromTicket(item: TicketItem) {
        ticketDao.deleteTicketItem(item.id.toIntOrNull() ?: 0)
    }

    override suspend fun clearCurrentTicket() {
        val currentId = getCurrentTicketId()
        ticketDao.clearTicket(currentId)
    }

    override suspend fun suspendTicket() {
        val currentId = getCurrentTicketId()
        ticketDao.updateTicketState(currentId, statusToInt(TicketStatus.SUSPENDED).toString())
    }

    override suspend fun completeTicket() {
        val currentId = getCurrentTicketId()
        ticketDao.updateTicketState(currentId, statusToInt(TicketStatus.COMPLETED).toString())
    }

    override suspend fun getSuspendedTickets(): List<Ticket> {
        val entities = ticketDao.getTicketsByState(statusToInt(TicketStatus.SUSPENDED).toString())
        return entities.mapNotNull { getTicketById(it.id) }
    }

    override suspend fun getAllTenders(): List<TenderEntity> = ticketDao.getAllTenders()

    override suspend fun getAllDepartments(): List<DepartmentEntity> = ticketDao.getAllDepartments()

    override suspend fun getAllTaxGroups(): List<TaxGroupEntity> = ticketDao.getAllTaxGroups()

    private suspend fun getCurrentTicketId(): Int {
        val current = ticketDao.getCurrentTicketId()
        if (current != null) return current
        val newTicket = createTicket()
        return newTicket.id.toIntOrNull()
            ?: throw IllegalStateException("Created ticket id is not numeric: ${'$'}{newTicket.id}")
    }

    private suspend fun mapToDomain(entity: TicketEntity): Ticket {
        val items = ticketDao.getTicketItems(entity.id).map { itemEntity ->
            TicketItem(
                id = itemEntity.id.toString(),
                productId = itemEntity.itemId.toString(),
                name = itemEntity.itemDesc,
                quantity = itemEntity.quantity,
                unitPriceCents = itemEntity.amount,
                notes = ""
            )
        }

        val tenders = ticketDao.getTicketTenders(entity.id).map { tenderEntity ->
            TicketTender(
                id = tenderEntity.id.toString(),
                ticketId = tenderEntity.ticketId.toString(),
                tenderType = tenderEntity.tenderType,
                amountCents = tenderEntity.amount,
                reference = ""
            )
        }

        return Ticket(
            id = entity.id.toString(),
            status = intToStatus(entity.state),
            items = items,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }

    private fun intToStatus(state: Int): TicketStatus = when (state) {
        1 -> TicketStatus.OPEN
        2 -> TicketStatus.SUSPENDED
        3 -> TicketStatus.COMPLETED
        4 -> TicketStatus.CANCELLED
        else -> TicketStatus.OPEN
    }

    private fun statusToInt(status: TicketStatus): Int = when (status) {
        TicketStatus.OPEN -> 1
        TicketStatus.SUSPENDED -> 2
        TicketStatus.COMPLETED -> 3
        TicketStatus.CANCELLED -> 4
    }
}