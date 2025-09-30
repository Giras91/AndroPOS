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

    override suspend fun getTablesByStatus(status: TableStatus): List<Table> {
        return tableDao.getByStatus(status.name).map { it.toDomain() }
    }

    override suspend fun updateTableStatus(tableId: String, status: TableStatus) {
        tableDao.updateStatus(tableId, status.name)
    }

    override suspend fun createTable(table: Table): String {
        val entity = table.toEntity()
        tableDao.insert(entity)
        return table.id
    }

    override suspend fun updateTable(table: Table) {
        val entity = table.toEntity()
        tableDao.update(entity)
    }

    override suspend fun deleteTable(tableId: String) {
        tableDao.deleteById(tableId)
    }

    override suspend fun getAvailableTables(): List<Table> {
        return tableDao.getByStatus(TableStatus.AVAILABLE.name).map { it.toDomain() }
    }

    override suspend fun getOccupiedTables(): List<Table> {
        return tableDao.getByStatus(TableStatus.OCCUPIED.name).map { it.toDomain() }
    }

    private fun TableEntity.toDomain(): Table {
        return Table(
            id = id,
            number = number,
            name = name,
            status = TableStatus.valueOf(status),
            capacity = capacity,
            location = location,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun Table.toEntity(): TableEntity {
        return TableEntity(
            id = id,
            number = number,
            name = name,
            status = status.name,
            capacity = capacity,
            location = location,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}