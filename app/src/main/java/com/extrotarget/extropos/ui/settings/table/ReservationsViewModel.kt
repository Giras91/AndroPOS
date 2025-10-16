package com.extrotarget.extropos.ui.settings.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.domain.model.Reservation
import com.extrotarget.extropos.domain.model.ReservationStatus
import com.extrotarget.extropos.domain.repository.IReservationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReservationsViewModel @Inject constructor(
    private val repo: IReservationRepository
) : ViewModel() {

    private val _reservations = MutableStateFlow<List<Reservation>>(emptyList())
    val reservations: StateFlow<List<Reservation>> = _reservations

    private val _upcomingReservations = MutableStateFlow<List<Reservation>>(emptyList())
    val upcomingReservations: StateFlow<List<Reservation>> = _upcomingReservations

    private val _waitlist = MutableStateFlow<List<Reservation>>(emptyList())
    val waitlist: StateFlow<List<Reservation>> = _waitlist

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _selectedDate = MutableStateFlow<Long?>(null)
    val selectedDate: StateFlow<Long?> = _selectedDate

    init { loadAll() }

    fun loadAll() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _reservations.value = repo.getAllReservations().sortedBy { it.reservationDateTime }
                _upcomingReservations.value = repo.getUpcomingReservations().sortedBy { it.reservationDateTime }
                _waitlist.value = repo.getWaitlist().sortedBy { it.createdAt }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadReservationsForDate(date: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val startOfDay = getStartOfDay(date)
                val endOfDay = getEndOfDay(date)
                _reservations.value = repo.getReservationsInTimeRange(startOfDay, endOfDay)
                    .sortedBy { it.reservationDateTime }
                _selectedDate.value = date
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun save(reservation: Reservation) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.upsertReservation(reservation)
                loadAll()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateStatus(id: String, status: ReservationStatus) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.updateReservationStatus(id, status)
                loadAll()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateDepositStatus(id: String, paid: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.updateDepositStatus(id, paid)
                loadAll()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateNotes(id: String, notes: String?) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.updateReservationNotes(id, notes)
                loadAll()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.deleteReservationById(id)
                loadAll()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun confirmFromWaitlist(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repo.confirmFromWaitlist(id)
                loadAll()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getReservationById(id: String): Reservation? {
        return _reservations.value.find { it.id == id }
    }

    suspend fun checkTableAvailability(tableId: String, startTime: Long, endTime: Long): Boolean {
        return try {
            val conflicts = repo.countConflictingReservations(tableId, startTime, endTime)
            conflicts == 0
        } catch (e: Exception) {
            _error.value = e.message
            false
        }
    }

    private fun getStartOfDay(date: Long): Long {
        val calendar = Calendar.getInstance().apply { timeInMillis = date }
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private fun getEndOfDay(date: Long): Long {
        val calendar = Calendar.getInstance().apply { timeInMillis = date }
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }
}