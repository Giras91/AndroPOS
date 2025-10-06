package com.extrotarget.extropos.data.local

import com.extrotarget.extropos.data.local.dao.*
import com.extrotarget.extropos.data.local.entity.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import java.util.concurrent.ConcurrentHashMap

/**
 * In-memory implementation of SaleItemDao for sandboxed training mode.
 */
class InMemorySaleItemDao : SaleItemDao {
    private val items = ConcurrentHashMap<Long, SaleItemEntity>()
    private val itemsFlow = MutableStateFlow<List<SaleItemEntity>>(emptyList())
    private var nextId = 1L

    override suspend fun getItemsBySaleId(saleId: String): List<SaleItemEntity> {
        return items.values.filter { it.saleId == saleId }
    }

    override fun observeItemsBySaleId(saleId: String): Flow<List<SaleItemEntity>> {
        return itemsFlow.map { list -> list.filter { it.saleId == saleId } }
    }

    override suspend fun insert(item: SaleItemEntity): Long {
        val id = if (item.id == 0L) nextId++ else item.id
        val itemWithId = item.copy(id = id)
        items[id] = itemWithId
        itemsFlow.value = items.values.toList()
        return id
    }

    override suspend fun insertAll(items: List<SaleItemEntity>) {
        items.forEach { insert(it) }
    }

    override suspend fun delete(item: SaleItemEntity) {
        items.remove(item.id)
        itemsFlow.value = items.values.toList()
    }

    override suspend fun deleteAllForSale(saleId: String) {
        items.values.filter { it.saleId == saleId }.forEach { items.remove(it.id) }
        itemsFlow.value = items.values.toList()
    }

    override suspend fun clearAll() {
        items.clear()
        itemsFlow.value = emptyList()
        nextId = 1L
    }
}

/**
 * In-memory implementation of CustomerDao.
 */
class InMemoryCustomerDao : CustomerDao {
    private val customers = ConcurrentHashMap<String, CustomerEntity>()
    private val customersFlow = MutableStateFlow<List<CustomerEntity>>(emptyList())

    override suspend fun getAllActive(): List<CustomerEntity> {
        return customers.values.filter { it.isActive }.sortedBy { it.name }
    }

    override fun observeAllActive(): Flow<List<CustomerEntity>> {
        return customersFlow.map { list -> list.filter { it.isActive }.sortedBy { it.name } }
    }

    override suspend fun getById(id: String): CustomerEntity? = customers[id]

    override suspend fun getByPhone(phone: String): CustomerEntity? {
        return customers.values.firstOrNull { it.phone == phone }
    }

    override suspend fun getByEmail(email: String): CustomerEntity? {
        return customers.values.firstOrNull { it.email == email }
    }

    override suspend fun insert(customer: CustomerEntity) {
        customers[customer.id] = customer
        customersFlow.value = customers.values.toList()
    }

    override suspend fun update(customer: CustomerEntity) {
        customers[customer.id] = customer
        customersFlow.value = customers.values.toList()
    }

    override suspend fun deactivate(id: String) {
        customers[id]?.let { customers[id] = it.copy(isActive = false) }
        customersFlow.value = customers.values.toList()
    }

    override suspend fun clearAll() {
        customers.clear()
        customersFlow.value = emptyList()
    }

    override suspend fun search(query: String): List<CustomerEntity> {
        val lowerQuery = query.lowercase()
        return customers.values.filter {
            it.name.lowercase().contains(lowerQuery) ||
            it.phone?.contains(query) == true
        }
    }
}

/**
 * In-memory implementation of InventoryTransactionDao.
 */
class InMemoryInventoryTransactionDao : InventoryTransactionDao {
    private val transactions = ConcurrentHashMap<Long, InventoryTransactionEntity>()
    private val transactionsFlow = MutableStateFlow<List<InventoryTransactionEntity>>(emptyList())
    private var nextId = 1L

    override suspend fun getByProductId(productId: String): List<InventoryTransactionEntity> {
        return transactions.values.filter { it.productId == productId }.sortedByDescending { it.createdAt }
    }

    override fun observeByProductId(productId: String): Flow<List<InventoryTransactionEntity>> {
        return transactionsFlow.map { list -> 
            list.filter { it.productId == productId }.sortedByDescending { it.createdAt }
        }
    }

    override suspend fun getByType(type: String, limit: Int): List<InventoryTransactionEntity> {
        return transactions.values.filter { it.type == type }
            .sortedByDescending { it.createdAt }
            .take(limit)
    }

    override suspend fun insert(transaction: InventoryTransactionEntity) {
        val id = if (transaction.id == 0L) nextId++ else transaction.id
        val transactionWithId = transaction.copy(id = id)
        transactions[id] = transactionWithId
        transactionsFlow.value = transactions.values.toList()
    }

    override suspend fun insertAll(transactions: List<InventoryTransactionEntity>) {
        transactions.forEach { insert(it) }
    }

    override suspend fun clearAll() {
        transactions.clear()
        transactionsFlow.value = emptyList()
        nextId = 1L
    }

    override suspend fun getByDateRange(startTime: Long, endTime: Long): List<InventoryTransactionEntity> {
        return transactions.values.filter { it.createdAt in startTime..endTime }
            .sortedByDescending { it.createdAt }
    }
}

/**
 * In-memory implementation of PaymentDao.
 */
class InMemoryPaymentDao : PaymentDao {
    private val payments = ConcurrentHashMap<Long, PaymentEntity>()
    private val paymentsFlow = MutableStateFlow<List<PaymentEntity>>(emptyList())
    private var nextId = 1L

    override suspend fun getBySaleId(saleId: String): List<PaymentEntity> {
        return payments.values.filter { it.saleId == saleId }
    }

    override fun observeBySaleId(saleId: String): Flow<List<PaymentEntity>> {
        return paymentsFlow.map { list -> list.filter { it.saleId == saleId } }
    }

    override suspend fun getByMethodAndDateRange(method: String, startTime: Long, endTime: Long): List<PaymentEntity> {
        return payments.values.filter { 
            it.method == method && it.createdAt in startTime..endTime 
        }
    }

    override suspend fun insert(payment: PaymentEntity): Long {
        val id = if (payment.id == 0L) nextId++ else payment.id
        val paymentWithId = payment.copy(id = id)
        payments[id] = paymentWithId
        paymentsFlow.value = payments.values.toList()
        return id
    }

    override suspend fun insertAll(payments: List<PaymentEntity>) {
        payments.forEach { insert(it) }
    }

    override suspend fun update(payment: PaymentEntity) {
        payments[payment.id] = payment
        paymentsFlow.value = payments.values.toList()
    }

    override suspend fun deleteAllForSale(saleId: String) {
        payments.values.filter { it.saleId == saleId }.forEach { payments.remove(it.id) }
        paymentsFlow.value = payments.values.toList()
    }

    override suspend fun clearAll() {
        payments.clear()
        paymentsFlow.value = emptyList()
        nextId = 1L
    }
}

/**
 * Extended in-memory implementation of SaleDao.
 */
class InMemorySaleDaoExtended : SaleDao {
    private val sales = ConcurrentHashMap<String, SaleEntity>()
    private val salesFlow = MutableStateFlow<List<SaleEntity>>(emptyList())

    override suspend fun getRecent(limit: Int): List<SaleEntity> {
        return sales.values.sortedByDescending { it.createdAt }.take(limit)
    }

    override suspend fun getById(id: String): SaleEntity? = sales[id]

    override suspend fun getByReceiptNo(receiptNo: String): SaleEntity? {
        return sales.values.firstOrNull { it.receiptNo == receiptNo }
    }

    override suspend fun getByDateRange(startTime: Long, endTime: Long): List<SaleEntity> {
        return sales.values.filter { it.createdAt in startTime..endTime }
            .sortedByDescending { it.createdAt }
    }

    override suspend fun getByCustomerId(customerId: String): List<SaleEntity> {
        return sales.values.filter { it.customerId == customerId }
            .sortedByDescending { it.createdAt }
    }

    override suspend fun getByUserId(userId: String, limit: Int): List<SaleEntity> {
        return sales.values.filter { it.userId == userId }
            .sortedByDescending { it.createdAt }
            .take(limit)
    }

    override suspend fun getByPaymentMethod(method: String, startTime: Long, endTime: Long): List<SaleEntity> {
        return sales.values.filter { 
            it.paymentMethod == method && it.createdAt in startTime..endTime 
        }
    }

    override fun observeByTrainingMode(isTraining: Boolean): Flow<List<SaleEntity>> {
        return salesFlow.map { list -> 
            list.filter { it.isTraining == isTraining }.sortedByDescending { it.createdAt }
        }
    }

    override suspend fun insert(sale: SaleEntity) {
        sales[sale.id] = sale
        salesFlow.value = sales.values.toList()
    }

    override suspend fun update(sale: SaleEntity) {
        sales[sale.id] = sale
        salesFlow.value = sales.values.toList()
    }

    override suspend fun delete(id: String) {
        sales.remove(id)
        salesFlow.value = sales.values.toList()
    }

    override suspend fun deleteAllTraining() {
        sales.values.filter { it.isTraining }.forEach { sales.remove(it.id) }
        salesFlow.value = sales.values.toList()
    }

    override suspend fun clearAll() {
        sales.clear()
        salesFlow.value = emptyList()
    }

    override suspend fun getTotalSales(startTime: Long, endTime: Long): Long? {
        return sales.values
            .filter { it.createdAt in startTime..endTime && it.paymentStatus == "PAID" }
            .sumOf { it.totalAmountCents }
            .takeIf { it > 0 }
    }

    override suspend fun getTransactionCount(startTime: Long, endTime: Long): Int {
        return sales.values.count { it.createdAt in startTime..endTime }
    }
}
