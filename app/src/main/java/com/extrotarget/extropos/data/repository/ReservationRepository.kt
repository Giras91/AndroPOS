package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.ReservationDao
import com.extrotarget.extropos.data.local.entity.ReservationEntity
import com.extrotarget.extropos.domain.model.Reservation
import com.extrotarget.extropos.domain.model.ReservationStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReservationRepository @Inject constructor(
    private val reservationDao: ReservationDao
) : com.extrotarget.extropos.domain.repository.IReservationRepository {

    override suspend fun getAllReservations(): List<Reservation> {
        return reservationDao.getReservationsInTimeRange(0L, Long.MAX_VALUE).map { it.toDomain() }
    }

    override suspend fun getReservationById(id: String): Reservation? {
        return reservationDao.getById(id)?.toDomain()
    }

    override suspend fun getReservationsInTimeRange(startTime: Long, endTime: Long): List<Reservation> {
        return reservationDao.getReservationsInTimeRange(startTime, endTime).map { it.toDomain() }
    }

    override suspend fun getReservationsForTable(tableId: String, startTime: Long): List<Reservation> {
        return reservationDao.getReservationsForTable(tableId, startTime).map { it.toDomain() }
    }

    override suspend fun findReservationsByContact(phone: String?, email: String?): List<Reservation> {
        return reservationDao.findReservationsByContact(phone, email).map { it.toDomain() }
    }

    override suspend fun getReservationsByStatus(status: ReservationStatus): List<Reservation> {
        return reservationDao.getReservationsByStatus(status.name).map { it.toDomain() }
    }

    override suspend fun getUpcomingReservations(currentTime: Long): List<Reservation> {
        return reservationDao.getUpcomingReservations(currentTime).map { it.toDomain() }
    }

    override suspend fun getWaitlist(): List<Reservation> {
        return reservationDao.getWaitlist().map { it.toDomain() }
    }

    override suspend fun getWaitlistCount(): Int {
        return reservationDao.getWaitlistCount()
    }

    override suspend fun getReservationsWithTableInfo(startDate: Long, endDate: Long): List<Reservation> {
        return reservationDao.getReservationsWithTableInfo(startDate, endDate).map { it.toDomain() }
    }

    override suspend fun getCustomerReservationHistory(phone: String?, email: String?): List<Reservation> {
        return reservationDao.getCustomerReservationHistory(phone, email).map { it.toDomain() }
    }

    override suspend fun getReservationCountForDate(date: Long): Int {
        return reservationDao.getReservationCountForDate(date)
    }

    override suspend fun getTotalCoversForDate(date: Long): Int {
        return reservationDao.getTotalCoversForDate(date)
    }

    override suspend fun getDepositRevenueForDate(date: Long): Long? {
        return reservationDao.getDepositRevenueForDate(date)
    }

    override suspend fun getTableUtilizationStats(startTime: Long, endTime: Long): Map<String, Int> {
        val reservedTables = reservationDao.getReservedTableCount(startTime, endTime)
        val totalTables = reservationDao.getTotalActiveTableCount()
        return mapOf("reservedTables" to reservedTables, "totalTables" to totalTables)
    }

    override suspend fun countConflictingReservations(tableId: String, startTime: Long, endTime: Long): Int {
        return reservationDao.countConflictingReservations(tableId, startTime, endTime)
    }

    override suspend fun upsertReservation(reservation: Reservation) {
        reservationDao.upsert(reservation.toEntity())
    }

    override suspend fun upsertReservations(reservations: List<Reservation>) {
        reservationDao.upsert(reservations.map { it.toEntity() })
    }

    override suspend fun updateReservationStatus(id: String, status: ReservationStatus) {
        reservationDao.updateStatus(id, status.name)
    }

    override suspend fun updateDepositStatus(id: String, paid: Boolean) {
        reservationDao.updateDepositStatus(id, paid)
    }

    override suspend fun updateReservationNotes(id: String, notes: String?) {
        reservationDao.updateNotes(id, notes)
    }

    override suspend fun deleteReservation(reservation: Reservation) {
        reservationDao.delete(reservation.toEntity())
    }

    override suspend fun deleteReservationById(id: String) {
        reservationDao.deleteById(id)
    }

    override suspend fun confirmFromWaitlist(id: String) {
        reservationDao.confirmFromWaitlist(id)
    }

    override suspend fun cleanupOldReservations(cutoffTime: Long) {
        reservationDao.cleanupOldReservations(cutoffTime)
    }

    // Map ReservationEntity -> Reservation
    private fun ReservationEntity.toDomain(): Reservation {
        return Reservation(
            id = id,
            tableId = tableId,
            tableNumber = tableNumber ?: "",
            customerName = customerName,
            customerPhone = customerPhone,
            customerEmail = customerEmail,
            partySize = partySize,
            reservationDateTime = reservationDateTime,
            durationMinutes = durationMinutes,
            status = try { ReservationStatus.valueOf(status) } catch (e: Exception) { ReservationStatus.PENDING },
            specialRequests = specialRequests,
            depositRequired = depositRequired,
            depositAmountCents = depositAmountCents,
            depositPaid = depositPaid,
            notes = notes,
            createdBy = createdBy,
            assignedServerId = assignedServerId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    // Map Reservation -> ReservationEntity
    private fun Reservation.toEntity(): ReservationEntity {
        return ReservationEntity(
            id = id,
            tableId = tableId,
            tableNumber = tableNumber,
            customerName = customerName,
            customerPhone = customerPhone,
            customerEmail = customerEmail,
            partySize = partySize,
            reservationDateTime = reservationDateTime,
            durationMinutes = durationMinutes,
            status = status.name,
            specialRequests = specialRequests,
            depositRequired = depositRequired,
            depositAmountCents = depositAmountCents,
            depositPaid = depositPaid,
            notes = notes,
            createdBy = createdBy,
            assignedServerId = assignedServerId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}