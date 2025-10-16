package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.TableSectionDao
import com.extrotarget.extropos.data.local.entity.TableSectionEntity
import com.extrotarget.extropos.domain.model.TableSection
import com.extrotarget.extropos.domain.repository.ITableSectionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TableSectionRepository @Inject constructor(
    private val dao: TableSectionDao
) : ITableSectionRepository {

    override suspend fun getAllSections(): List<TableSection> = dao.getAll().map { it.toDomain() }

    override suspend fun getActiveSections(): List<TableSection> = dao.getAllActive().map { it.toDomain() }

    override suspend fun getSectionById(id: String): TableSection? = dao.getById(id)?.toDomain()

    override suspend fun upsertSection(section: TableSection) {
        dao.upsert(section.toEntity())
    }

    override suspend fun upsertSections(sections: List<TableSection>) {
        dao.upsert(sections.map { it.toEntity() })
    }

    override suspend fun setActive(id: String, isActive: Boolean) {
        dao.updateActiveStatus(id, isActive)
    }

    override suspend fun deleteSection(id: String) {
        dao.deleteById(id)
    }

    private fun TableSectionEntity.toDomain() = TableSection(
        id = id,
        name = name,
        description = description,
        color = color,
        displayOrder = displayOrder,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    private fun TableSection.toEntity() = TableSectionEntity(
        id = id,
        name = name,
        description = description,
        color = color,
        displayOrder = displayOrder,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
