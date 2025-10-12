package com.extrotarget.extropos.reporting

import com.extrotarget.extropos.data.local.dao.SaleDao
import com.extrotarget.extropos.data.local.dao.SaleItemDao
import com.extrotarget.extropos.data.local.entity.SaleEntity
import com.extrotarget.extropos.data.local.entity.SaleItemEntity
import javax.inject.Inject

/**
 * Lightweight reporting repository that composes DAO queries into analytics-friendly shapes.
 * Keeps logic small and testable.
 */
class ReportingRepository @Inject constructor(
    private val saleDao: SaleDao,
    private val saleItemDao: SaleItemDao,
    private val shiftDao: com.extrotarget.extropos.data.local.dao.ShiftDao
) {

    suspend fun getSalesByDateRange(startTime: Long, endTime: Long): List<SaleEntity> {
        return saleDao.getByDateRange(startTime, endTime)
    }

    suspend fun getSalesCountAndTotal(startTime: Long, endTime: Long): Pair<Int, Long> {
        val count = saleDao.getTransactionCount(startTime, endTime)
        val total = saleDao.getTotalSales(startTime, endTime) ?: 0L
        return count to total
    }

    suspend fun getSaleItemsForSales(saleIds: List<String>): List<SaleItemEntity> {
        if (saleIds.isEmpty()) return emptyList()
        return saleItemDao.getItemsBySaleIds(saleIds)
    }

    /**
     * Aggregate sales by product for given date range.
     */
    suspend fun getSalesByProduct(startTime: Long, endTime: Long): Map<String, ProductSummary> {
        val sales = getSalesByDateRange(startTime, endTime)
        val saleIds = sales.map { it.id }
        val items = getSaleItemsForSales(saleIds)
        val map = mutableMapOf<String, ProductSummary>()
        for (it in items) {
            val cur = map.getOrPut(it.productId) { ProductSummary(it.productId, it.productName, 0, 0L) }
            cur.quantity += it.quantity
            cur.revenueCents += it.totalPriceCents
        }
        return map
    }

    /**
     * Sales over time (daily buckets) — returns map of dateString -> totalCents
     */
    suspend fun getSalesOverTimeDaily(startTime: Long, endTime: Long): Map<String, Long> {
        val sales = getSalesByDateRange(startTime, endTime)
        val df = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
        val map = mutableMapOf<String, Long>()
        for (s in sales) {
            val key = df.format(java.util.Date(s.createdAt))
            map[key] = (map[key] ?: 0L) + s.totalAmountCents
        }
        return map
    }

    suspend fun getRecentSales(limit: Int = 50): List<SaleEntity> = saleDao.getRecent(limit)

    suspend fun getSalesByUser(userId: String, startTime: Long, endTime: Long): List<SaleEntity> =
        saleDao.getByUserId(userId)

    suspend fun getShiftReportsForRange(startTime: Long, endTime: Long): List<ShiftReport> {
        val shifts = shiftDao.getShiftsByStartRange(startTime, endTime)
        val reports = mutableListOf<ShiftReport>()
        // To optimize DB access, fetch all sales that fall in the union of all shift ranges,
        // then fetch all sale items for those sales in one batch and group in-memory.
        val allSaleIds = mutableListOf<String>()
        val shiftRanges = mutableListOf<Pair<com.extrotarget.extropos.data.local.entity.ShiftEntity, Long>>()
        for (s in shifts) {
            val shiftEnd = s.endedAt ?: endTime
            shiftRanges += (s to shiftEnd)
            val sales = saleDao.getByDateRange(s.startedAt, shiftEnd)
            allSaleIds += sales.map { it.id }
        }

        val allItems = if (allSaleIds.isEmpty()) emptyList() else getSaleItemsForSales(allSaleIds)
        // group items by saleId for quick lookup
        val itemsBySale = allItems.groupBy { it.saleId }

        for ((s, shiftEnd) in shiftRanges) {
            val sales = saleDao.getByDateRange(s.startedAt, shiftEnd)
            val count = sales.size
            val total = sales.fold(0L) { acc, it -> acc + it.totalAmountCents }

            val map = mutableMapOf<String, ProductSummary>()
            for (sale in sales) {
                val items = itemsBySale[sale.id] ?: emptyList()
                for (it in items) {
                    val cur = map.getOrPut(it.productId) { ProductSummary(it.productId, it.productName, 0, 0L) }
                    cur.quantity += it.quantity
                    cur.revenueCents += it.totalPriceCents
                }
            }

            val breakdown = map.values.toList()
            reports += ShiftReport(
                shiftId = s.id,
                userId = s.userId,
                username = s.username,
                startedAt = s.startedAt,
                endedAt = s.endedAt,
                salesCount = count,
                salesTotalCents = total,
                productBreakdown = breakdown
            )
        }
        return reports
    }

}

data class ProductSummary(
    val productId: String,
    val productName: String,
    var quantity: Int,
    var revenueCents: Long
)

data class ShiftReport(
    val shiftId: String,
    val userId: String,
    val username: String,
    val startedAt: Long,
    val endedAt: Long?,
    val salesCount: Int,
    val salesTotalCents: Long
    ,
    val productBreakdown: List<ProductSummary>
)

/**
 * Returns a list of ShiftReport for shifts that STARTED within the given date range.
 * For each shift, aggregates sales whose createdAt falls within the shift boundaries
 * (startedAt .. endedAt). If a shift has null endedAt, sales up to the provided endTime
 * or current time are considered.
 */
// (shift report implementation moved into ReportingRepository class)
