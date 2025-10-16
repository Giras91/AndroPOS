package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.TableLayoutDao
import com.extrotarget.extropos.data.local.entity.TableLayoutEntity
import com.extrotarget.extropos.domain.model.TableLayout
import com.extrotarget.extropos.domain.repository.ITableLayoutRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableLayoutRepository @Inject constructor(
    private val dao: TableLayoutDao
) : ITableLayoutRepository {

    override suspend fun getAll(): List<TableLayout> = dao.getAll().map { it.toDomain() }

    override suspend fun getById(id: String): TableLayout? = dao.getById(id)?.toDomain()

    override suspend fun getDefault(): TableLayout? = dao.getDefaultLayout()?.toDomain()

    override suspend fun upsert(layout: TableLayout) {
        dao.upsert(layout.toEntity())
    }

    override suspend fun upsert(layouts: List<TableLayout>) {
        dao.upsert(layouts.map { it.toEntity() })
    }

    override suspend fun setDefault(id: String) {
        dao.clearDefaultLayouts()
        dao.setAsDefault(id)
    }

    override suspend fun delete(id: String) {
        dao.deleteById(id)
    }
}

private fun TableLayoutEntity.toDomain() = TableLayout(
    id = id,
    name = name,
    description = description,
    isDefault = isDefault,
    layoutData = layoutData,
    createdBy = createdBy,
    createdAt = createdAt,
    updatedAt = updatedAt
)

private fun TableLayout.toEntity() = TableLayoutEntity(
    id = id,
    name = name,
    description = description,
    isDefault = isDefault,
    layoutData = layoutData,
    createdBy = createdBy,
    createdAt = createdAt,
    updatedAt = updatedAt
)
