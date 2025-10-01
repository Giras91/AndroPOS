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

// Lightweight in-memory implementations for DAOs expected by repositories
class InMemoryCategoryDao : com.extrotarget.extropos.data.local.dao.CategoryDao {
    private val categories = mutableListOf<com.extrotarget.extropos.data.local.entity.CategoryEntity>()

    override suspend fun getAllActive(): List<com.extrotarget.extropos.data.local.entity.CategoryEntity> =
        categories.filter { it.isActive }.sortedBy { it.displayOrder }

    override suspend fun getById(id: String): com.extrotarget.extropos.data.local.entity.CategoryEntity? =
        categories.find { it.id == id }

    override suspend fun upsert(vararg categories: com.extrotarget.extropos.data.local.entity.CategoryEntity) {
        categories.forEach { c ->
            val idx = this.categories.indexOfFirst { it.id == c.id }
            if (idx >= 0) this.categories[idx] = c else this.categories.add(c)
        }
    }

    override suspend fun updateActiveStatus(id: String, isActive: Boolean) {
        val idx = categories.indexOfFirst { it.id == id }
        if (idx >= 0) categories[idx] = categories[idx].copy(isActive = isActive)
    }
}

class InMemoryMenuItemDao : com.extrotarget.extropos.data.local.dao.MenuItemDao {
    private val items = mutableListOf<com.extrotarget.extropos.data.local.entity.MenuItemEntity>()

    override suspend fun getAllAvailable(): List<com.extrotarget.extropos.data.local.entity.MenuItemEntity> =
        items.filter { it.isAvailable }

    override suspend fun getByCategory(categoryId: String): List<com.extrotarget.extropos.data.local.entity.MenuItemEntity> =
        items.filter { it.categoryId == categoryId && it.isAvailable }

    override suspend fun getById(id: String): com.extrotarget.extropos.data.local.entity.MenuItemEntity? =
        items.find { it.id == id }

    override suspend fun searchByName(query: String): List<com.extrotarget.extropos.data.local.entity.MenuItemEntity> =
        items.filter { it.name.contains(query, ignoreCase = true) && it.isAvailable }

    override suspend fun upsert(vararg items: com.extrotarget.extropos.data.local.entity.MenuItemEntity) {
        items.forEach { p ->
            val existing = this.items.indexOfFirst { it.id == p.id }
            if (existing >= 0) this.items[existing] = p else this.items.add(p)
        }
    }

    override suspend fun updateAvailability(id: String, isAvailable: Boolean) {
        val idx = items.indexOfFirst { it.id == id }
        if (idx >= 0) items[idx] = items[idx].copy(isAvailable = isAvailable)
    }
}

class InMemoryOrderDao : com.extrotarget.extropos.data.local.dao.OrderDao {
    private val orders = mutableListOf<com.extrotarget.extropos.data.local.entity.OrderEntity>()
    private var nextId = 1L

    override suspend fun getActiveOrders(): List<com.extrotarget.extropos.data.local.entity.OrderEntity> =
        orders.filter { it.status in listOf("PENDING", "CONFIRMED", "PREPARING", "READY") }.sortedByDescending { it.createdAt }

    override suspend fun getByStatus(status: String): List<com.extrotarget.extropos.data.local.entity.OrderEntity> =
        orders.filter { it.status == status }.sortedByDescending { it.createdAt }

    override suspend fun getByTable(tableId: String): List<com.extrotarget.extropos.data.local.entity.OrderEntity> =
        orders.filter { it.tableId == tableId }.sortedByDescending { it.createdAt }

    override suspend fun getById(id: String): com.extrotarget.extropos.data.local.entity.OrderEntity? =
        orders.find { it.id == id }

    override suspend fun insert(order: com.extrotarget.extropos.data.local.entity.OrderEntity): Long {
        val assigned = if (order.id.isBlank()) {
            val newId = nextId++
            orders.add(order.copy(id = newId.toString()))
            newId
        } else {
            orders.add(order)
            order.id.toLongOrNull() ?: nextId++
        }
        return assigned
    }

    override suspend fun update(order: com.extrotarget.extropos.data.local.entity.OrderEntity) {
        val idx = orders.indexOfFirst { it.id == order.id }
        if (idx >= 0) orders[idx] = order
    }

    override suspend fun updateStatus(id: String, status: String, updatedAt: Long) {
        val idx = orders.indexOfFirst { it.id == id }
        if (idx >= 0) orders[idx] = orders[idx].copy(status = status, updatedAt = updatedAt)
    }
}

class InMemoryOrderItemDao : com.extrotarget.extropos.data.local.dao.OrderItemDao {
    private val items = mutableListOf<com.extrotarget.extropos.data.local.entity.OrderItemEntity>()

    override suspend fun getByOrderId(orderId: String): List<com.extrotarget.extropos.data.local.entity.OrderItemEntity> =
        items.filter { it.orderId == orderId }

    override suspend fun insert(vararg items: com.extrotarget.extropos.data.local.entity.OrderItemEntity) {
        items.forEach { i ->
            val existing = this.items.indexOfFirst { it.id == i.id }
            if (existing >= 0) this.items[existing] = i else this.items.add(i)
        }
    }

    override suspend fun update(item: com.extrotarget.extropos.data.local.entity.OrderItemEntity) {
        val idx = items.indexOfFirst { it.id == item.id }
        if (idx >= 0) items[idx] = item
    }

    override suspend fun delete(item: com.extrotarget.extropos.data.local.entity.OrderItemEntity) {
        items.removeIf { it.id == item.id && it.orderId == item.orderId }
    }

    override suspend fun deleteById(orderId: String, itemId: String) {
        items.removeIf { it.orderId == orderId && it.id == itemId }
    }
}

class InMemoryTableDao : com.extrotarget.extropos.data.local.dao.TableDao {
    private val tables = mutableListOf<com.extrotarget.extropos.data.local.entity.TableEntity>()

    override suspend fun getAll(): List<com.extrotarget.extropos.data.local.entity.TableEntity> = tables.toList()

    override suspend fun getById(id: String): com.extrotarget.extropos.data.local.entity.TableEntity? = tables.find { it.id == id }

    override suspend fun getAvailable(): List<com.extrotarget.extropos.data.local.entity.TableEntity> = tables.filter { it.status == "AVAILABLE" }

    override suspend fun getOccupied(): List<com.extrotarget.extropos.data.local.entity.TableEntity> = tables.filter { it.status == "OCCUPIED" }

    override suspend fun upsert(vararg tables: com.extrotarget.extropos.data.local.entity.TableEntity) {
        tables.forEach { t ->
            val idx = this.tables.indexOfFirst { it.id == t.id }
            if (idx >= 0) this.tables[idx] = t else this.tables.add(t)
        }
    }

    override suspend fun updateStatus(id: String, status: String) {
        val idx = tables.indexOfFirst { it.id == id }
        if (idx >= 0) tables[idx] = tables[idx].copy(status = status)
    }

    override suspend fun assignOrder(tableId: String, orderId: String) {
        val idx = tables.indexOfFirst { it.id == tableId }
        if (idx >= 0) tables[idx] = tables[idx].copy(currentOrderId = orderId)
    }
}