package com.extrotarget.extropos.reporting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.extrotarget.extropos.pdf.PdfGenerationService
import com.extrotarget.extropos.domain.usecase.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ReportingViewModel @Inject constructor(
    private val repository: ReportingRepository,
    private val pdfService: PdfGenerationService,
    private val shiftRepository: ShiftRepository,
    private val getCurrentUser: GetCurrentUserUseCase
) : ViewModel() {

    suspend fun generateTodaysDailyReport(): String? = withContext(Dispatchers.IO) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        val end = cal.timeInMillis

        val sales = repository.getSalesByDateRange(start, end)
        return@withContext pdfService.generateDailySalesReport(sales, Date())
    }

    suspend fun getSalesByProductToday(): Map<String, ProductSummary> = withContext(Dispatchers.IO) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis
        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        val end = cal.timeInMillis

        repository.getSalesByProduct(start, end)
    }

    /** Generate a report (PDF) for an arbitrary date range. Returns the generated file path or null. */
    suspend fun generateReportForRange(startTime: Long, endTime: Long): String? = withContext(Dispatchers.IO) {
        val sales = repository.getSalesByDateRange(startTime, endTime)
        // Use the start date as the report date label
        return@withContext pdfService.generateDailySalesReport(sales, Date(startTime))
    }

    suspend fun getSalesByProductForRange(startTime: Long, endTime: Long): Map<String, ProductSummary> = withContext(Dispatchers.IO) {
        repository.getSalesByProduct(startTime, endTime)
    }

    suspend fun getSalesOverTimeDailyForRange(startTime: Long, endTime: Long): Map<String, Long> = withContext(Dispatchers.IO) {
        repository.getSalesOverTimeDaily(startTime, endTime)
    }

    suspend fun getSalesForRange(startTime: Long, endTime: Long): List<com.extrotarget.extropos.data.local.entity.SaleEntity> = withContext(Dispatchers.IO) {
        repository.getSalesByDateRange(startTime, endTime)
    }

    /** Start a shift for the current user. Returns the created ShiftEntity or null if no user. */
    suspend fun startShift(notes: String? = null): com.extrotarget.extropos.data.local.entity.ShiftEntity? = withContext(Dispatchers.IO) {
        val user = getCurrentUser()
        if (user == null) return@withContext null
        return@withContext shiftRepository.startShift(user.id, user.name, notes)
    }

    /** End the latest open shift for the current user. Returns updated ShiftEntity or null. */
    suspend fun endShiftForCurrentUser(): com.extrotarget.extropos.data.local.entity.ShiftEntity? = withContext(Dispatchers.IO) {
        val user = getCurrentUser()
        if (user == null) return@withContext null
        val latest = shiftRepository.getLatestShiftForUser(user.id) ?: return@withContext null
        if (latest.endedAt != null) return@withContext null
        return@withContext shiftRepository.endShift(latest.id)
    }

    suspend fun getShiftHistoryForCurrentUser(): List<com.extrotarget.extropos.data.local.entity.ShiftEntity> = withContext(Dispatchers.IO) {
        val user = getCurrentUser()
        if (user == null) return@withContext emptyList()
        return@withContext shiftRepository.getAllForUser(user.id)
    }

    suspend fun getShiftReportsForRange(startTime: Long, endTime: Long): List<ShiftReport> = withContext(Dispatchers.IO) {
        repository.getShiftReportsForRange(startTime, endTime)
    }

}
