package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.TableDao
import com.extrotarget.extropos.data.local.entity.TableEntity
import com.extrotarget.extropos.domain.model.Table
import com.extrotarget.extropos.domain.model.TableStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableRepository @Inject constructor(
    private val tableDao: TableDao
) : com.extrotarget.extropos.domain.repository.ITableRepository {

    override suspend fun getAllTables(): List<Table> {
        return tableDao.getAll().map { it.toDomain() }
    }

    override suspend fun getTableById(id: String): Table? {
        return tableDao.getById(id)?.toDomain()
    }

    override suspend fun updateTableStatus(tableId: String, status: TableStatus) {
        tableDao.updateStatus(tableId, status.name)
    }

    override suspend fun assignOrderToTable(tableId: String, orderId: String) {
        // TableDao exposes assignOrder(tableId, orderId)
        tableDao.assignOrder(tableId, orderId)
    }

    override suspend fun getAvailableTables(): List<Table> {
        return tableDao.getAvailable().map { it.toDomain() }
    }

    override suspend fun getOccupiedTables(): List<Table> {
        return tableDao.getOccupied().map { it.toDomain() }
    }

    // Map TableEntity -> Table (convert number:String -> Int)
    private fun TableEntity.toDomain(): Table {
        val num = number.toIntOrNull() ?: 0
        return Table(
            id = id,
            number = num,
            capacity = capacity,
            status = try { TableStatus.valueOf(status) } catch (e: Exception) { TableStatus.AVAILABLE },
            currentOrderId = currentOrderId
        )
    }

    // Map Table -> TableEntity (convert number:Int -> String)
    private fun Table.toEntity(): TableEntity {
        return TableEntity(
            id = id,
            number = number.toString(),
            capacity = capacity,
            status = status.name,
            currentOrderId = currentOrderId
        )
    }
}