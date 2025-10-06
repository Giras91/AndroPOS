package com.extrotarget.extropos.training

import com.extrotarget.extropos.data.local.InMemoryProductDao
import com.extrotarget.extropos.data.local.InMemoryTicketDao
import com.extrotarget.extropos.data.local.dao.ProductDao
import com.extrotarget.extropos.data.local.dao.TicketDao
import com.extrotarget.extropos.data.local.entity.ProductEntity
import com.extrotarget.extropos.data.local.entity.TicketEntity
import com.extrotarget.extropos.data.local.entity.TicketItemEntity
import com.extrotarget.extropos.data.local.entity.TicketTenderEntity

/** Delegates DAO calls to either live or training in-memory store based on TrainingModeManager. */
class DelegatingProductDao(
    private val live: InMemoryProductDao,
    private val training: InMemoryProductDao,
    private val mode: TrainingModeManager
) : ProductDao {
    private fun active(): InMemoryProductDao = if (mode.isTraining()) training else live
    override suspend fun getAll(): List<ProductEntity> = active().getAll()
    override suspend fun upsert(vararg product: ProductEntity) = active().upsert(*product)
    override suspend fun getById(id: String): ProductEntity? = active().getById(id)
}

class DelegatingTicketDao(
    private val live: InMemoryTicketDao,
    private val training: InMemoryTicketDao,
    private val mode: TrainingModeManager
) : TicketDao {
    private fun active(): InMemoryTicketDao = if (mode.isTraining()) training else live
    override suspend fun getTicketById(id: Int): TicketEntity? = active().getTicketById(id)
    override suspend fun getTicketsByState(state: String): List<TicketEntity> = active().getTicketsByState(state)
    override suspend fun getTicketItems(ticketId: Int): List<TicketItemEntity> = active().getTicketItems(ticketId)
    override suspend fun getTicketTenders(ticketId: Int): List<TicketTenderEntity> = active().getTicketTenders(ticketId)
    override suspend fun insertTicket(ticket: TicketEntity): Long = active().insertTicket(ticket)
    override suspend fun insertTicketItem(item: TicketItemEntity): Long = active().insertTicketItem(item)
    override suspend fun insertTicketTender(tender: TicketTenderEntity): Long = active().insertTicketTender(tender)
    override suspend fun updateTicket(ticket: TicketEntity) = active().updateTicket(ticket)
    override suspend fun deleteTicket(id: Int) = active().deleteTicket(id)
    override suspend fun getAllTenders() = active().getAllTenders()
    override suspend fun getAllDepartments() = active().getAllDepartments()
    override suspend fun getAllTaxGroups() = active().getAllTaxGroups()
    override suspend fun getCurrentTicket() = active().getCurrentTicket()
    override suspend fun getCurrentTicketId() = active().getCurrentTicketId()
    override suspend fun updateTicketState(ticketId: Int, state: String, updatedAt: Long) = active().updateTicketState(ticketId, state, updatedAt)
    override suspend fun clearTicket(ticketId: Int) = active().clearTicket(ticketId)
    override suspend fun updateTicketItemQuantity(itemId: Int, quantity: Int, amount: Double) = active().updateTicketItemQuantity(itemId, quantity, amount)
    override suspend fun deleteTicketItem(itemId: Int) = active().deleteTicketItem(itemId)
}

class DelegatingCategoryDao(
    private val live: com.extrotarget.extropos.data.local.InMemoryCategoryDao,
    private val training: com.extrotarget.extropos.data.local.InMemoryCategoryDao,
    private val mode: TrainingModeManager
) : com.extrotarget.extropos.data.local.dao.CategoryDao {
    private fun active() = if (mode.isTraining()) training else live
    override suspend fun getAllActive() = active().getAllActive()
    override suspend fun getById(id: String) = active().getById(id)
    override suspend fun upsert(vararg categories: com.extrotarget.extropos.data.local.entity.CategoryEntity) = active().upsert(*categories)
    override suspend fun updateActiveStatus(id: String, isActive: Boolean) = active().updateActiveStatus(id, isActive)
}

class DelegatingMenuItemDao(
    private val live: com.extrotarget.extropos.data.local.InMemoryMenuItemDao,
    private val training: com.extrotarget.extropos.data.local.InMemoryMenuItemDao,
    private val mode: TrainingModeManager
) : com.extrotarget.extropos.data.local.dao.MenuItemDao {
    private fun active() = if (mode.isTraining()) training else live
    override suspend fun getAllAvailable() = active().getAllAvailable()
    override suspend fun getByCategory(categoryId: String) = active().getByCategory(categoryId)
    override suspend fun getById(id: String) = active().getById(id)
    override suspend fun searchByName(query: String) = active().searchByName(query)
    override suspend fun upsert(vararg items: com.extrotarget.extropos.data.local.entity.MenuItemEntity) = active().upsert(*items)
    override suspend fun updateAvailability(id: String, isAvailable: Boolean) = active().updateAvailability(id, isAvailable)
}

class DelegatingOrderDao(
    private val live: com.extrotarget.extropos.data.local.InMemoryOrderDao,
    private val training: com.extrotarget.extropos.data.local.InMemoryOrderDao,
    private val mode: TrainingModeManager
) : com.extrotarget.extropos.data.local.dao.OrderDao {
    private fun active() = if (mode.isTraining()) training else live
    override suspend fun getActiveOrders() = active().getActiveOrders()
    override suspend fun getByStatus(status: String) = active().getByStatus(status)
    override suspend fun getByTable(tableId: String) = active().getByTable(tableId)
    override suspend fun getById(id: String) = active().getById(id)
    override suspend fun insert(order: com.extrotarget.extropos.data.local.entity.OrderEntity) = active().insert(order)
    override suspend fun update(order: com.extrotarget.extropos.data.local.entity.OrderEntity) = active().update(order)
    override suspend fun updateStatus(id: String, status: String, updatedAt: Long) = active().updateStatus(id, status, updatedAt)
}

class DelegatingOrderItemDao(
    private val live: com.extrotarget.extropos.data.local.InMemoryOrderItemDao,
    private val training: com.extrotarget.extropos.data.local.InMemoryOrderItemDao,
    private val mode: TrainingModeManager
) : com.extrotarget.extropos.data.local.dao.OrderItemDao {
    private fun active() = if (mode.isTraining()) training else live
    override suspend fun getByOrderId(orderId: String) = active().getByOrderId(orderId)
    override suspend fun insert(vararg items: com.extrotarget.extropos.data.local.entity.OrderItemEntity) = active().insert(*items)
    override suspend fun update(item: com.extrotarget.extropos.data.local.entity.OrderItemEntity) = active().update(item)
    override suspend fun delete(item: com.extrotarget.extropos.data.local.entity.OrderItemEntity) = active().delete(item)
    override suspend fun deleteById(orderId: String, itemId: String) = active().deleteById(orderId, itemId)
}

class DelegatingTableDao(
    private val live: com.extrotarget.extropos.data.local.InMemoryTableDao,
    private val training: com.extrotarget.extropos.data.local.InMemoryTableDao,
    private val mode: TrainingModeManager
) : com.extrotarget.extropos.data.local.dao.TableDao {
    private fun active() = if (mode.isTraining()) training else live
    override suspend fun getAll() = active().getAll()
    override suspend fun getById(id: String) = active().getById(id)
    override suspend fun getAvailable() = active().getAvailable()
    override suspend fun getOccupied() = active().getOccupied()
    override suspend fun upsert(vararg tables: com.extrotarget.extropos.data.local.entity.TableEntity) = active().upsert(*tables)
    override suspend fun updateStatus(id: String, status: String) = active().updateStatus(id, status)
    override suspend fun assignOrder(tableId: String, orderId: String) = active().assignOrder(tableId, orderId)
}
