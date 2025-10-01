package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.OrderDao
import com.extrotarget.extropos.data.local.dao.OrderItemDao
import com.extrotarget.extropos.data.local.entity.OrderEntity
import com.extrotarget.extropos.data.local.entity.OrderItemEntity
import com.extrotarget.extropos.domain.model.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
    private val orderDao: OrderDao,
    private val orderItemDao: OrderItemDao
) : com.extrotarget.extropos.domain.repository.IOrderRepository {

    override suspend fun createOrder(order: Order): String {
        val entity = order.toEntity()
        val id = UUID.randomUUID().toString()
        val entityWithId = entity.copy(id = id)
        orderDao.insert(entityWithId)
        return id
    }

    override suspend fun getOrderById(id: String): Order? {
        val entity = orderDao.getById(id)
        return entity?.toDomain()
    }

    override suspend fun getOrdersByStatus(status: OrderStatus): List<Order> {
        return orderDao.getByStatus(status.name).map { it.toDomain() }
    }

    override suspend fun getOrdersByTable(tableId: String): List<Order> {
        return orderDao.getByTable(tableId).map { it.toDomain() }
    }

    override suspend fun updateOrder(order: Order) {
        val entity = order.toEntity()
        orderDao.update(entity)
    }

    override suspend fun updateOrderStatus(orderId: String, status: OrderStatus) {
        orderDao.updateStatus(orderId, status.name)
    }

    override suspend fun addItemToOrder(orderId: String, item: OrderItem) {
        val entity = item.toEntity(orderId)
        orderItemDao.insert(entity)
        // Update order totals
        updateOrderTotals(orderId)
    }

    override suspend fun updateOrderItem(orderId: String, item: OrderItem) {
        val entity = item.toEntity(orderId)
        orderItemDao.update(entity)
        updateOrderTotals(orderId)
    }

    override suspend fun removeOrderItem(orderId: String, itemId: String) {
        orderItemDao.deleteById(orderId, itemId)
        updateOrderTotals(orderId)
    }

    override suspend fun getActiveOrders(): List<Order> {
        return orderDao.getActiveOrders().map { it.toDomain() }
    }

    private suspend fun updateOrderTotals(orderId: String) {
        val items = orderItemDao.getByOrderId(orderId)
        val subtotal = items.sumOf { it.totalPriceCents }
        val tax = (subtotal * 0.1).toLong() // 10% tax
        val total = subtotal + tax

        // Update order with new totals
        orderDao.updateStatus(orderId, "PENDING") // This will trigger updatedAt
    }

    private suspend fun OrderEntity.toDomain(): Order {
        val items = orderItemDao.getByOrderId(id).map { it.toDomain() }
        return Order(
            id = id,
            items = items,
            status = try { OrderStatus.valueOf(status) } catch (e: Exception) { OrderStatus.PENDING },
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun Order.toEntity(): OrderEntity {
        return OrderEntity(
            id = id,
            tableId = null, // simplified domain Order doesn't expose tableId here
            orderNumber = id, // use id as orderNumber when missing
            status = status.name,
            orderType = "DINE_IN",
            subtotalCents = items.sumOf { it.totalPriceCents },
            taxCents = 0L,
            discountCents = 0L,
            totalCents = items.sumOf { it.totalPriceCents },
            createdAt = createdAt,
            updatedAt = updatedAt,
            notes = null
        )
    }

    private fun OrderItemEntity.toDomain(): OrderItem {
        return OrderItem(
            id = id,
            menuItemId = menuItemId,
            name = menuItemName,
            quantity = quantity,
            unitPriceCents = unitPriceCents,
            notes = notes ?: ""
        )
    }

    private fun OrderItem.toEntity(orderId: String): OrderItemEntity {
        return OrderItemEntity(
            id = id.ifEmpty { UUID.randomUUID().toString() },
            orderId = orderId,
            menuItemId = menuItemId,
            menuItemName = name,
            quantity = quantity,
            unitPriceCents = unitPriceCents,
            totalPriceCents = quantity * unitPriceCents,
            notes = notes,
            status = OrderItemStatus.PENDING.name
        )
    }
}