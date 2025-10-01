package com.extrotarget.extropos.data.local

import com.extrotarget.extropos.data.local.dao.ProductDao
import com.extrotarget.extropos.data.local.dao.TicketDao
import com.extrotarget.extropos.data.local.entity.*

class InMemoryProductDao : ProductDao {
    private val products = mutableListOf<ProductEntity>()

    override suspend fun getAll(): List<ProductEntity> = products.toList()

    override suspend fun upsert(vararg product: ProductEntity) {
        product.forEach { p ->
            val existingIndex = products.indexOfFirst { it.id == p.id }
            if (existingIndex >= 0) {
                products[existingIndex] = p
            } else {
                products.add(p)
            }
        }
    }

    override suspend fun getById(id: String): ProductEntity? = products.find { it.id == id }
}

class InMemoryTicketDao : TicketDao {
    private val tickets = mutableListOf<TicketEntity>()
    private val ticketItems = mutableListOf<TicketItemEntity>()
    private val ticketTenders = mutableListOf<TicketTenderEntity>()
    private val tenders = mutableListOf<TenderEntity>()
    private val departments = mutableListOf<DepartmentEntity>()

    init {
        // Initialize with some default data
        tenders.addAll(listOf(
            TenderEntity(1, "Cash", "cash", true, true),
            TenderEntity(2, "Card", "card", false, true),
            TenderEntity(3, "QR Pay", "qr", false, true)
        ))

        departments.addAll(listOf(
            DepartmentEntity(1, "Food & Beverage", 1),
            DepartmentEntity(2, "Retail", 2)
        ))
    }

    override suspend fun getTicketById(id: Int): TicketEntity? = tickets.find { it.id == id }

    override suspend fun getTicketsByState(state: String): List<TicketEntity> = tickets.filter { it.state.toString() == state }

    override suspend fun getTicketItems(ticketId: Int): List<TicketItemEntity> = ticketItems.filter { it.ticketId == ticketId }

    override suspend fun getTicketTenders(ticketId: Int): List<TicketTenderEntity> = ticketTenders.filter { it.ticketId == ticketId }

    override suspend fun insertTicket(ticket: TicketEntity): Long {
        val newId = (tickets.maxOfOrNull { it.id } ?: 0) + 1
        val newTicket = ticket.copy(id = newId)
        tickets.add(newTicket)
        return newId.toLong()
    }

    override suspend fun insertTicketItem(item: TicketItemEntity): Long {
        val newId = (ticketItems.maxOfOrNull { it.id } ?: 0) + 1
        val newItem = item.copy(id = newId)
        ticketItems.add(newItem)
        return newId.toLong()
    }

    override suspend fun insertTicketTender(tender: TicketTenderEntity): Long {
        val newId = (ticketTenders.maxOfOrNull { it.id } ?: 0) + 1
        val newTender = tender.copy(id = newId)
        ticketTenders.add(newTender)
        return newId.toLong()
    }

    override suspend fun updateTicket(ticket: TicketEntity) {
        val index = tickets.indexOfFirst { it.id == ticket.id }
        if (index >= 0) {
            tickets[index] = ticket
        }
    }

    override suspend fun deleteTicket(id: Int) {
        tickets.removeIf { it.id == id }
        ticketItems.removeIf { it.ticketId == id }
        ticketTenders.removeIf { it.ticketId == id }
    }

    override suspend fun getAllTenders(): List<TenderEntity> = tenders.toList()

    override suspend fun getAllDepartments(): List<DepartmentEntity> = departments.toList()

    override suspend fun getAllTaxGroups(): List<TaxGroupEntity> = emptyList()

    override suspend fun getCurrentTicket(): TicketEntity? = tickets.filter { it.state in listOf(1, 2) }.maxByOrNull { it.id }

    override suspend fun getCurrentTicketId(): Int? = getCurrentTicket()?.id

    override suspend fun updateTicketState(ticketId: Int, state: String, updatedAt: Long) {
        val index = tickets.indexOfFirst { it.id == ticketId }
        if (index >= 0) {
            val current = tickets[index]
            tickets[index] = current.copy(state = state.toIntOrNull() ?: current.state, updatedAt = updatedAt)
        }
    }

    override suspend fun clearTicket(ticketId: Int) {
        ticketItems.removeIf { it.ticketId == ticketId }
    }

    override suspend fun updateTicketItemQuantity(itemId: Int, quantity: Int, amount: Double) {
        val index = ticketItems.indexOfFirst { it.id == itemId }
        if (index >= 0) {
            val current = ticketItems[index]
            ticketItems[index] = current.copy(quantity = quantity, amount = (amount * 100).toLong())
        }
    }

    override suspend fun deleteTicketItem(itemId: Int) {
        ticketItems.removeIf { it.id == itemId }
    }
}