package com.extrotarget.extropos.domain.repository

import com.extrotarget.extropos.domain.model.*

interface IMenuRepository {
    suspend fun getAllCategories(): List<Category>
    suspend fun getCategoryById(id: String): Category?
    suspend fun getMenuItemsByCategory(categoryId: String): List<MenuItem>
    suspend fun getMenuItemById(id: String): MenuItem?
    suspend fun getAllMenuItems(): List<MenuItem>
    suspend fun searchMenuItems(query: String): List<MenuItem>
    // Persistence operations for adding/updating
    suspend fun upsertCategory(category: Category)
    suspend fun upsertMenuItem(item: MenuItem)
    // Delete operations
    suspend fun deleteCategoryById(id: String)
}

interface IOrderRepository {
    suspend fun createOrder(order: Order): String // Returns order ID
    suspend fun getOrderById(id: String): Order?
    suspend fun getOrdersByStatus(status: OrderStatus): List<Order>
    suspend fun getOrdersByTable(tableId: String): List<Order>
    suspend fun updateOrder(order: Order)
    suspend fun updateOrderStatus(orderId: String, status: OrderStatus)
    suspend fun addItemToOrder(orderId: String, item: OrderItem)
    suspend fun updateOrderItem(orderId: String, item: OrderItem)
    suspend fun removeOrderItem(orderId: String, itemId: String)
    suspend fun getActiveOrders(): List<Order>
}

interface ITableRepository {
    suspend fun getAllTables(): List<Table>
    suspend fun getTableById(id: String): Table?
    suspend fun updateTableStatus(tableId: String, status: TableStatus)
    suspend fun assignOrderToTable(tableId: String, orderId: String)
    suspend fun getAvailableTables(): List<Table>
    suspend fun getOccupiedTables(): List<Table>
    suspend fun upsertTable(table: Table)
    suspend fun deleteTable(tableId: String)
}

interface ITableSectionRepository {
    suspend fun getAllSections(): List<com.extrotarget.extropos.domain.model.TableSection>
    suspend fun getActiveSections(): List<com.extrotarget.extropos.domain.model.TableSection>
    suspend fun getSectionById(id: String): com.extrotarget.extropos.domain.model.TableSection?
    suspend fun upsertSection(section: com.extrotarget.extropos.domain.model.TableSection)
    suspend fun upsertSections(sections: List<com.extrotarget.extropos.domain.model.TableSection>)
    suspend fun setActive(id: String, isActive: Boolean)
    suspend fun deleteSection(id: String)
}

interface ITableLayoutRepository {
    suspend fun getAll(): List<com.extrotarget.extropos.domain.model.TableLayout>
    suspend fun getById(id: String): com.extrotarget.extropos.domain.model.TableLayout?
    suspend fun getDefault(): com.extrotarget.extropos.domain.model.TableLayout?
    suspend fun upsert(layout: com.extrotarget.extropos.domain.model.TableLayout)
    suspend fun upsert(layouts: List<com.extrotarget.extropos.domain.model.TableLayout>)
    suspend fun setDefault(id: String)
    suspend fun delete(id: String)
}

interface IReservationRepository {
    suspend fun getAllReservations(): List<Reservation>
    suspend fun getReservationById(id: String): Reservation?
    suspend fun getReservationsInTimeRange(startTime: Long, endTime: Long): List<Reservation>
    suspend fun getReservationsForTable(tableId: String, startTime: Long = System.currentTimeMillis()): List<Reservation>
    suspend fun findReservationsByContact(phone: String?, email: String?): List<Reservation>
    suspend fun getReservationsByStatus(status: ReservationStatus): List<Reservation>
    suspend fun getUpcomingReservations(currentTime: Long = System.currentTimeMillis()): List<Reservation>
    suspend fun getWaitlist(): List<Reservation>
    suspend fun getWaitlistCount(): Int
    suspend fun getReservationsWithTableInfo(startDate: Long, endDate: Long): List<Reservation>
    suspend fun getCustomerReservationHistory(phone: String?, email: String?): List<Reservation>
    suspend fun getReservationCountForDate(date: Long): Int
    suspend fun getTotalCoversForDate(date: Long): Int
    suspend fun getDepositRevenueForDate(date: Long): Long?
    suspend fun getTableUtilizationStats(startTime: Long, endTime: Long): Map<String, Int>
    suspend fun countConflictingReservations(tableId: String, startTime: Long, endTime: Long): Int

    // CRUD operations
    suspend fun upsertReservation(reservation: Reservation)
    suspend fun upsertReservations(reservations: List<Reservation>)
    suspend fun updateReservationStatus(id: String, status: ReservationStatus)
    suspend fun updateDepositStatus(id: String, paid: Boolean)
    suspend fun updateReservationNotes(id: String, notes: String?)
    suspend fun deleteReservation(reservation: Reservation)
    suspend fun deleteReservationById(id: String)
    suspend fun confirmFromWaitlist(id: String)
    suspend fun cleanupOldReservations(cutoffTime: Long)
}