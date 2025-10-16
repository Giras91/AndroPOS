package com.extrotarget.extropos.data.local.dao

import androidx.room.*
import com.extrotarget.extropos.data.local.entity.*

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE isActive = 1 ORDER BY displayOrder ASC")
    suspend fun getAllActive(): List<CategoryEntity>

    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getById(id: String): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg categories: CategoryEntity)

    @Query("UPDATE categories SET isActive = :isActive WHERE id = :id")
    suspend fun updateActiveStatus(id: String, isActive: Boolean)
}

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items WHERE isAvailable = 1")
    suspend fun getAllAvailable(): List<MenuItemEntity>

    @Query("SELECT * FROM menu_items WHERE categoryId = :categoryId AND isAvailable = 1")
    suspend fun getByCategory(categoryId: String): List<MenuItemEntity>

    @Query("SELECT * FROM menu_items WHERE id = :id")
    suspend fun getById(id: String): MenuItemEntity?

    @Query("SELECT * FROM menu_items WHERE name LIKE '%' || :query || '%' AND isAvailable = 1")
    suspend fun searchByName(query: String): List<MenuItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: MenuItemEntity)

    @Query("UPDATE menu_items SET isAvailable = :isAvailable WHERE id = :id")
    suspend fun updateAvailability(id: String, isAvailable: Boolean)
}

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders WHERE status IN ('PENDING', 'CONFIRMED', 'PREPARING', 'READY') ORDER BY createdAt DESC")
    suspend fun getActiveOrders(): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE status = :status ORDER BY createdAt DESC")
    suspend fun getByStatus(status: String): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE tableId = :tableId ORDER BY createdAt DESC")
    suspend fun getByTable(tableId: String): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getById(id: String): OrderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(order: OrderEntity): Long

    @Update
    suspend fun update(order: OrderEntity)

    @Query("UPDATE orders SET status = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateStatus(id: String, status: String, updatedAt: Long = System.currentTimeMillis())
}

@Dao
interface OrderItemDao {
    @Query("SELECT * FROM order_items WHERE orderId = :orderId")
    suspend fun getByOrderId(orderId: String): List<OrderItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg items: OrderItemEntity)

    @Update
    suspend fun update(item: OrderItemEntity)

    @Delete
    suspend fun delete(item: OrderItemEntity)

    @Query("DELETE FROM order_items WHERE orderId = :orderId AND id = :itemId")
    suspend fun deleteById(orderId: String, itemId: String)
}

@Dao
interface TableDao {
    @Query("SELECT * FROM tables ORDER BY number ASC")
    suspend fun getAll(): List<TableEntity>

    @Query("SELECT * FROM tables WHERE id = :id")
    suspend fun getById(id: String): TableEntity?

    @Query("SELECT * FROM tables WHERE status = 'AVAILABLE' ORDER BY number ASC")
    suspend fun getAvailable(): List<TableEntity>

    @Query("SELECT * FROM tables WHERE status = 'OCCUPIED' ORDER BY number ASC")
    suspend fun getOccupied(): List<TableEntity>

    @Query("SELECT * FROM tables WHERE status = 'RESERVED' ORDER BY number ASC")
    suspend fun getReserved(): List<TableEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg tables: TableEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(tables: List<TableEntity>)

    @Query("UPDATE tables SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: String, status: String)

    @Query("UPDATE tables SET currentOrderId = :orderId, status = 'OCCUPIED' WHERE id = :tableId")
    suspend fun assignOrder(tableId: String, orderId: String)

    @Query("UPDATE tables SET currentOrderId = NULL, status = 'AVAILABLE' WHERE id = :tableId")
    suspend fun clearOrder(tableId: String)

    @Query("DELETE FROM tables WHERE id = :tableId")
    suspend fun delete(tableId: String)

    @Delete
    suspend fun delete(table: TableEntity)

    // Advanced filtering queries
    @Query("SELECT * FROM tables WHERE section = :sectionId AND isActive = 1 ORDER BY number ASC")
    suspend fun getBySection(sectionId: String): List<TableEntity>

    @Query("SELECT * FROM tables WHERE tableType = :tableType AND isActive = 1 ORDER BY number ASC")
    suspend fun getByType(tableType: String): List<TableEntity>

    @Query("SELECT * FROM tables WHERE capacity >= :minCapacity AND capacity <= :maxCapacity AND isActive = 1 ORDER BY capacity ASC")
    suspend fun getByCapacityRange(minCapacity: Int, maxCapacity: Int): List<TableEntity>

    @Query("SELECT * FROM tables WHERE isSmokingAllowed = :smokingAllowed AND isActive = 1 ORDER BY number ASC")
    suspend fun getBySmokingPreference(smokingAllowed: Boolean): List<TableEntity>

    @Query("SELECT * FROM tables WHERE isAccessible = 1 AND isActive = 1 ORDER BY number ASC")
    suspend fun getAccessibleTables(): List<TableEntity>

    @Query("SELECT * FROM tables WHERE hasPowerOutlet = 1 AND isActive = 1 ORDER BY number ASC")
    suspend fun getTablesWithPowerOutlets(): List<TableEntity>

    @Query("SELECT * FROM tables WHERE priority >= :minPriority AND isActive = 1 ORDER BY priority DESC, number ASC")
    suspend fun getByMinimumPriority(minPriority: Int): List<TableEntity>

    @Query("SELECT DISTINCT section FROM tables WHERE section IS NOT NULL AND isActive = 1 ORDER BY section ASC")
    suspend fun getAllSections(): List<String>

    @Query("SELECT DISTINCT tableType FROM tables WHERE tableType IS NOT NULL AND isActive = 1 ORDER BY tableType ASC")
    suspend fun getAllTableTypes(): List<String>

    // Availability checking
    @Query("""
        SELECT t.* FROM tables t
        LEFT JOIN table_reservations r ON t.id = r.tableId
        AND r.reservationDateTime < :endTime
        AND (r.reservationDateTime + (r.durationMinutes * 60000)) > :startTime
        AND r.status IN ('CONFIRMED', 'PENDING')
        WHERE t.status = 'AVAILABLE'
        AND t.capacity >= :partySize
        AND t.isActive = 1
        AND r.id IS NULL
        ORDER BY t.priority DESC, t.capacity ASC
    """)
    suspend fun findAvailableTablesForTimeSlot(
        startTime: Long,
        endTime: Long,
        partySize: Int
    ): List<TableEntity>

    // Bulk operations
    @Query("UPDATE tables SET isActive = :isActive, updatedAt = :updatedAt WHERE section = :sectionId")
    suspend fun updateSectionActiveStatus(sectionId: String, isActive: Boolean, updatedAt: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM tables WHERE isActive = 1")
    suspend fun getActiveTableCount(): Int

    @Query("SELECT COUNT(*) FROM tables WHERE status = :status AND isActive = 1")
    suspend fun getTableCountByStatus(status: String): Int
}

@Dao
interface ReservationDao {
    @Query("SELECT * FROM table_reservations WHERE reservationDateTime >= :startTime AND reservationDateTime <= :endTime ORDER BY reservationDateTime ASC")
    suspend fun getReservationsInTimeRange(startTime: Long, endTime: Long): List<ReservationEntity>

    @Query("SELECT * FROM table_reservations WHERE tableId = :tableId AND reservationDateTime >= :startTime ORDER BY reservationDateTime ASC")
    suspend fun getReservationsForTable(tableId: String, startTime: Long = System.currentTimeMillis()): List<ReservationEntity>

    @Query("SELECT * FROM table_reservations WHERE customerPhone = :phone OR customerEmail = :email ORDER BY reservationDateTime DESC")
    suspend fun findReservationsByContact(phone: String?, email: String?): List<ReservationEntity>

    @Query("SELECT * FROM table_reservations WHERE status = :status ORDER BY reservationDateTime ASC")
    suspend fun getReservationsByStatus(status: String): List<ReservationEntity>

    @Query("SELECT * FROM table_reservations WHERE id = :id")
    suspend fun getById(id: String): ReservationEntity?

    @Query("SELECT * FROM table_reservations WHERE reservationDateTime >= :currentTime ORDER BY reservationDateTime ASC")
    suspend fun getUpcomingReservations(currentTime: Long = System.currentTimeMillis()): List<ReservationEntity>

    @Query("SELECT COUNT(*) FROM table_reservations WHERE tableId = :tableId AND reservationDateTime >= :startTime AND reservationDateTime <= :endTime AND status IN ('CONFIRMED', 'PENDING')")
    suspend fun countConflictingReservations(tableId: String, startTime: Long, endTime: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg reservations: ReservationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(reservations: List<ReservationEntity>)

    @Query("UPDATE table_reservations SET status = :status, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateStatus(id: String, status: String, updatedAt: Long = System.currentTimeMillis())

    @Query("UPDATE table_reservations SET depositPaid = :paid, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateDepositStatus(id: String, paid: Boolean, updatedAt: Long = System.currentTimeMillis())

    @Query("UPDATE table_reservations SET notes = :notes, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateNotes(id: String, notes: String?, updatedAt: Long = System.currentTimeMillis())

    @Delete
    suspend fun delete(reservation: ReservationEntity)

    @Query("DELETE FROM table_reservations WHERE id = :id")
    suspend fun deleteById(id: String)

    // Cleanup old reservations
    @Query("DELETE FROM table_reservations WHERE reservationDateTime < :cutoffTime AND status != 'COMPLETED'")
    suspend fun cleanupOldReservations(cutoffTime: Long)

    // Waitlist functionality
    @Query("SELECT * FROM table_reservations WHERE status = 'WAITLIST' ORDER BY createdAt ASC")
    suspend fun getWaitlist(): List<ReservationEntity>

    @Query("SELECT COUNT(*) FROM table_reservations WHERE status = 'WAITLIST'")
    suspend fun getWaitlistCount(): Int

    @Query("UPDATE table_reservations SET status = 'CONFIRMED', updatedAt = :updatedAt WHERE id = :id")
    suspend fun confirmFromWaitlist(id: String, updatedAt: Long = System.currentTimeMillis())

    // Advanced booking features
    @Query("""
        SELECT r.* FROM table_reservations r
        INNER JOIN tables t ON r.tableId = t.id
        WHERE r.reservationDateTime >= :startDate
        AND r.reservationDateTime <= :endDate
        AND r.status IN ('CONFIRMED', 'PENDING', 'SEATED')
        ORDER BY r.reservationDateTime ASC
    """)
    suspend fun getReservationsWithTableInfo(startDate: Long, endDate: Long): List<ReservationEntity>

    @Query("""
        SELECT r.*, t.number as tableNumber, t.capacity, t.section
        FROM table_reservations r
        INNER JOIN tables t ON r.tableId = t.id
        WHERE r.customerPhone = :phone OR r.customerEmail = :email
        ORDER BY r.reservationDateTime DESC
        LIMIT 10
    """)
    suspend fun getCustomerReservationHistory(phone: String?, email: String?): List<ReservationEntity>

    @Query("SELECT COUNT(*) FROM table_reservations WHERE DATE(reservationDateTime / 1000, 'unixepoch') = DATE(:date / 1000, 'unixepoch') AND status IN ('CONFIRMED', 'SEATED')")
    suspend fun getReservationCountForDate(date: Long): Int

    @Query("SELECT SUM(partySize) FROM table_reservations WHERE DATE(reservationDateTime / 1000, 'unixepoch') = DATE(:date / 1000, 'unixepoch') AND status IN ('CONFIRMED', 'SEATED')")
    suspend fun getTotalCoversForDate(date: Long): Int

    // Revenue tracking for reservations
    @Query("SELECT SUM(depositAmountCents) FROM table_reservations WHERE depositPaid = 1 AND DATE(createdAt / 1000, 'unixepoch') = DATE(:date / 1000, 'unixepoch')")
    suspend fun getDepositRevenueForDate(date: Long): Long?

    // Table utilization - simplified to return counts
    @Query("SELECT COUNT(DISTINCT tableId) FROM table_reservations WHERE reservationDateTime >= :startTime AND reservationDateTime <= :endTime AND status IN ('CONFIRMED', 'SEATED')")
    suspend fun getReservedTableCount(startTime: Long, endTime: Long): Int

    @Query("SELECT COUNT(*) FROM tables WHERE isActive = 1")
    suspend fun getTotalActiveTableCount(): Int
}

@Dao
interface TableSectionDao {
    @Query("SELECT * FROM table_sections WHERE isActive = 1 ORDER BY displayOrder ASC")
    suspend fun getAllActive(): List<TableSectionEntity>

    @Query("SELECT * FROM table_sections WHERE id = :id")
    suspend fun getById(id: String): TableSectionEntity?

    @Query("SELECT * FROM table_sections ORDER BY displayOrder ASC")
    suspend fun getAll(): List<TableSectionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg sections: TableSectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(sections: List<TableSectionEntity>)

    @Query("UPDATE table_sections SET isActive = :isActive, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateActiveStatus(id: String, isActive: Boolean, updatedAt: Long = System.currentTimeMillis())

    @Delete
    suspend fun delete(section: TableSectionEntity)

    @Query("DELETE FROM table_sections WHERE id = :id")
    suspend fun deleteById(id: String)
}

@Dao
interface TableLayoutDao {
    @Query("SELECT * FROM table_layouts ORDER BY name ASC")
    suspend fun getAll(): List<TableLayoutEntity>

    @Query("SELECT * FROM table_layouts WHERE isDefault = 1 LIMIT 1")
    suspend fun getDefaultLayout(): TableLayoutEntity?

    @Query("SELECT * FROM table_layouts WHERE id = :id")
    suspend fun getById(id: String): TableLayoutEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg layouts: TableLayoutEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(layouts: List<TableLayoutEntity>)

    @Query("UPDATE table_layouts SET isDefault = 0 WHERE isDefault = 1")
    suspend fun clearDefaultLayouts()

    @Query("UPDATE table_layouts SET isDefault = 1, updatedAt = :updatedAt WHERE id = :id")
    suspend fun setAsDefault(id: String, updatedAt: Long = System.currentTimeMillis())

    @Delete
    suspend fun delete(layout: TableLayoutEntity)

    @Query("DELETE FROM table_layouts WHERE id = :id")
    suspend fun deleteById(id: String)
}