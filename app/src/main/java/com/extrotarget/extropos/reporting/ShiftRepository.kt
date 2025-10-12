package com.extrotarget.extropos.reporting

import com.extrotarget.extropos.data.local.dao.ShiftDao
import com.extrotarget.extropos.data.local.entity.ShiftEntity
import java.util.UUID
import javax.inject.Inject

class ShiftRepository @Inject constructor(private val shiftDao: ShiftDao) {

    suspend fun startShift(userId: String, username: String, notes: String? = null): ShiftEntity {
        val shift = ShiftEntity(
            id = UUID.randomUUID().toString(),
            userId = userId,
            username = username,
            startedAt = System.currentTimeMillis(),
            endedAt = null,
            notes = notes
        )
        shiftDao.insert(shift)
        return shift
    }

    suspend fun endShift(shiftId: String): ShiftEntity? {
        val s = shiftDao.getById(shiftId) ?: return null
        val updated = s.copy(endedAt = System.currentTimeMillis())
        shiftDao.update(updated)
        return updated
    }

    suspend fun getLatestShiftForUser(userId: String): ShiftEntity? = shiftDao.getLatestForUser(userId)
    suspend fun getAllForUser(userId: String): List<ShiftEntity> = shiftDao.getAllForUser(userId)
}
