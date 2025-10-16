package com.extrotarget.extropos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.extrotarget.extropos.data.local.dao.*
import com.extrotarget.extropos.data.local.entity.*
import com.extrotarget.extropos.printer.data.PrinterEntity
import com.extrotarget.extropos.printer.data.PrinterDao

@Database(
    entities = [
        CategoryEntity::class,
        MenuItemEntity::class,
        ProductEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        TableEntity::class,
        TableSectionEntity::class,
        TableLayoutEntity::class,
        ReservationEntity::class,
        // Ticketing entities
        com.extrotarget.extropos.data.local.entity.TicketEntity::class,
        com.extrotarget.extropos.data.local.entity.TicketItemEntity::class,
        com.extrotarget.extropos.data.local.entity.TicketTenderEntity::class,
        com.extrotarget.extropos.data.local.entity.TenderEntity::class,
        com.extrotarget.extropos.data.local.entity.DepartmentEntity::class,
        com.extrotarget.extropos.data.local.entity.TaxGroupEntity::class,
        SaleEntity::class,
        SaleItemEntity::class,
        CustomerEntity::class,
        InventoryTransactionEntity::class,
        // Shift entity added for shift tracking
        com.extrotarget.extropos.data.local.entity.ShiftEntity::class,
        PaymentEntity::class,
        // Printers (new)
        com.extrotarget.extropos.printer.data.PrinterEntity::class,
        // New entities to match SQL schema
        UserEntity::class,
        InventoryEntity::class,
        CartEntity::class,
        CartItemEntity::class,
        SyncStateEntity::class,
        KeyValueEntity::class,
        LocalChangeEntity::class
    ],
    version = 8, // Updated version for table sections and layouts
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun menuItemDao(): MenuItemDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun tableDao(): TableDao
    abstract fun tableSectionDao(): TableSectionDao
    abstract fun tableLayoutDao(): TableLayoutDao
    abstract fun reservationDao(): ReservationDao
    abstract fun shiftDao(): com.extrotarget.extropos.data.local.dao.ShiftDao
    abstract fun ticketDao(): com.extrotarget.extropos.data.local.room.RoomTicketDao
    // ...existing code...
    abstract fun printerDao(): com.extrotarget.extropos.printer.data.PrinterDao
    // New DAOs to match SQL schema
    abstract fun userDao(): UserDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun cartDao(): CartDao
    abstract fun syncStateDao(): SyncStateDao
    abstract fun keyValueDao(): KeyValueDao
    abstract fun localChangeDao(): LocalChangeDao

    companion object {
        private const val DB_NAME = "extropos.db"

        fun create(context: Context): AppDatabase {
            // NOTE: For development and to unblock device testing we enable
            // destructive migration fallback. This will wipe and recreate the DB
            // when an unknown/unsupported migration is encountered.
            // Replace with proper migrations before shipping to production.
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8)
                .fallbackToDestructiveMigration()
                .build()
        }

    // Migration from version 1 -> 2: create the `shifts` table used for shift tracking
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create the new shifts table. Columns match the ShiftEntity data class.
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `shifts` (
                      `id` TEXT NOT NULL PRIMARY KEY,
                      `userId` TEXT NOT NULL,
                      `username` TEXT NOT NULL,
                      `startedAt` INTEGER NOT NULL,
                      `endedAt` INTEGER,
                      `notes` TEXT
                    )
                    """.trimIndent()
                )
            }
        }

        // Migration from version 2 -> 3: add indices to speed up sales and sale items queries
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                                // We must transform existing v1/v2 tables to match the v3 schema.
                                // Approach: create new tables with the final schema, copy data with
                                // safe defaults (IFNULL / constants for NOT NULL columns), drop the
                                // old tables and rename the new ones, then recreate indices.

                                // Migrate `sales` table to v3 shape
                                db.execSQL(
                                        """
                                        CREATE TABLE IF NOT EXISTS `__sales_new` (
                                            `id` TEXT NOT NULL PRIMARY KEY,
                                            `receiptNo` TEXT NOT NULL,
                                            `totalAmountCents` INTEGER NOT NULL,
                                            `subtotalCents` INTEGER NOT NULL,
                                            `taxCents` INTEGER NOT NULL,
                                            `discountCents` INTEGER NOT NULL,
                                            `createdAt` INTEGER NOT NULL,
                                            `completedAt` INTEGER,
                                            `customerId` TEXT,
                                            `userId` TEXT NOT NULL,
                                            `paymentMethod` TEXT NOT NULL,
                                            `paymentStatus` TEXT NOT NULL,
                                            `notes` TEXT,
                                            `isTraining` INTEGER NOT NULL
                                        )
                                        """.trimIndent()
                                )

                                // Copy data from old sales; provide defaults where v1 columns were missing
                                db.execSQL(
                                        """
                                        INSERT INTO `__sales_new` (id, receiptNo, totalAmountCents, subtotalCents, taxCents, discountCents, createdAt, completedAt, customerId, userId, paymentMethod, paymentStatus, notes, isTraining)
                                        SELECT
                                            id,
                                            IFNULL(receiptNo, ''),
                                            IFNULL(totalAmountCents, 0),
                                            0,
                                            0,
                                            0,
                                            IFNULL(createdAt, 0),
                                            NULL,
                                            customerId,
                                            IFNULL(userId, ''),
                                            IFNULL(paymentMethod, ''),
                                            IFNULL(paymentStatus, ''),
                                            NULL,
                                            0
                                        FROM `sales`;
                                        """.trimIndent()
                                )

                                // Replace old table
                                db.execSQL("DROP TABLE IF EXISTS `sales`")
                                db.execSQL("ALTER TABLE `__sales_new` RENAME TO `sales`")

                                // Migrate `sale_items` table to v3 shape (rename qty -> quantity, add discountCents)
                                                db.execSQL(
                                                        """
                                                        CREATE TABLE IF NOT EXISTS `__sale_items_new` (
                                                            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                                                            `saleId` TEXT NOT NULL,
                                                            `productId` TEXT NOT NULL,
                                                            `productName` TEXT NOT NULL,
                                                            `quantity` INTEGER NOT NULL,
                                                            `unitPriceCents` INTEGER NOT NULL,
                                                            `totalPriceCents` INTEGER NOT NULL,
                                                            `discountCents` INTEGER NOT NULL,
                                                            `notes` TEXT,
                                                            FOREIGN KEY(`saleId`) REFERENCES `sales`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE,
                                                            FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE RESTRICT
                                                        )
                                                        """.trimIndent()
                                                )

                                // Copy from old sale_items (old schema used `qty` column name)
                                db.execSQL(
                                        """
                                        INSERT INTO `__sale_items_new` (id, saleId, productId, productName, quantity, unitPriceCents, totalPriceCents, discountCents, notes)
                                        SELECT
                                            id,
                                            saleId,
                                            productId,
                                            IFNULL(productName, ''),
                                            COALESCE(qty, 0),
                                            IFNULL(unitPriceCents, 0),
                                            IFNULL(totalPriceCents, 0),
                                            0,
                                            NULL
                                        FROM `sale_items`;
                                        """.trimIndent()
                                )

                                db.execSQL("DROP TABLE IF EXISTS `sale_items`")
                                db.execSQL("ALTER TABLE `__sale_items_new` RENAME TO `sale_items`")

                                // Recreate indices expected in v3 for sale_items only
                                db.execSQL("CREATE INDEX IF NOT EXISTS `index_sale_items_saleId` ON `sale_items`(`saleId`)")
                                db.execSQL("CREATE INDEX IF NOT EXISTS `index_sale_items_productId` ON `sale_items`(`productId`)")

                                // Migrate `customers` to v3 shape (add new columns with defaults)
                                db.execSQL(
                                        """
                                        CREATE TABLE IF NOT EXISTS `__customers_new` (
                                            `id` TEXT NOT NULL,
                                            `name` TEXT NOT NULL,
                                            `email` TEXT,
                                            `phone` TEXT,
                                            `address` TEXT,
                                            `loyaltyPoints` INTEGER NOT NULL,
                                            `totalPurchasesCents` INTEGER NOT NULL,
                                            `isActive` INTEGER NOT NULL,
                                            `createdAt` INTEGER NOT NULL,
                                            `lastVisitAt` INTEGER,
                                            `notes` TEXT,
                                            PRIMARY KEY(`id`)
                                        )
                                        """.trimIndent()
                                )

                                db.execSQL(
                                        """
                                        INSERT INTO `__customers_new` (id, name, email, phone, address, loyaltyPoints, totalPurchasesCents, isActive, createdAt, lastVisitAt, notes)
                                        SELECT
                                            id,
                                            IFNULL(name, ''),
                                            email,
                                            phone,
                                            NULL,
                                            0,
                                            0,
                                            1,
                                            0,
                                            NULL,
                                            NULL
                                        FROM `customers`;
                                        """.trimIndent()
                                )

                                db.execSQL("DROP TABLE IF EXISTS `customers`")
                                db.execSQL("ALTER TABLE `__customers_new` RENAME TO `customers`")

                                // Migrate `inventory_transactions` to v3 shape (id -> autoinc, additional columns)
                                db.execSQL(
                                        """
                                        CREATE TABLE IF NOT EXISTS `__inventory_transactions_new` (
                                            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                                            `productId` TEXT NOT NULL,
                                            `type` TEXT NOT NULL,
                                            `quantityChange` INTEGER NOT NULL,
                                            `quantityAfter` INTEGER NOT NULL,
                                            `referenceSaleId` TEXT,
                                            `userId` TEXT NOT NULL,
                                            `reason` TEXT,
                                            `createdAt` INTEGER NOT NULL,
                                            FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE RESTRICT
                                        )
                                        """.trimIndent()
                                )

                                db.execSQL(
                                        """
                                        INSERT INTO `__inventory_transactions_new` (productId, type, quantityChange, quantityAfter, referenceSaleId, userId, reason, createdAt)
                                        SELECT
                                            productId,
                                            'ADJUSTMENT',
                                            IFNULL(change, 0),
                                            0,
                                            NULL,
                                            '',
                                            reason,
                                            IFNULL(createdAt, 0)
                                        FROM `inventory_transactions`;
                                        """.trimIndent()
                                )

                                db.execSQL("DROP TABLE IF EXISTS `inventory_transactions`")
                                db.execSQL("ALTER TABLE `__inventory_transactions_new` RENAME TO `inventory_transactions`")
                                // Create index on productId expected by v3 schema
                                db.execSQL("CREATE INDEX IF NOT EXISTS `index_inventory_transactions_productId` ON `inventory_transactions`(`productId`)")

                                // Migrate `payments` to v3 shape (id -> autoinc, add missing cols)
                                db.execSQL(
                                        """
                                        CREATE TABLE IF NOT EXISTS `__payments_new` (
                                            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                                            `saleId` TEXT NOT NULL,
                                            `method` TEXT NOT NULL,
                                            `amountCents` INTEGER NOT NULL,
                                            `receivedCents` INTEGER,
                                            `changeCents` INTEGER,
                                            `referenceNo` TEXT,
                                            `status` TEXT NOT NULL,
                                            `createdAt` INTEGER NOT NULL,
                                            FOREIGN KEY(`saleId`) REFERENCES `sales`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE
                                        )
                                        """.trimIndent()
                                )

                                db.execSQL(
                                        """
                                        INSERT INTO `__payments_new` (saleId, method, amountCents, receivedCents, changeCents, referenceNo, status, createdAt)
                                        SELECT
                                            saleId,
                                            method,
                                            IFNULL(amountCents, 0),
                                            NULL,
                                            NULL,
                                            NULL,
                                            '',
                                            IFNULL(createdAt, 0)
                                        FROM `payments`;
                                        """.trimIndent()
                                )

                                db.execSQL("DROP TABLE IF EXISTS `payments`")
                                db.execSQL("ALTER TABLE `__payments_new` RENAME TO `payments`")
                                // Create index expected by v3 schema for payments.saleId
                                db.execSQL("CREATE INDEX IF NOT EXISTS `index_payments_saleId` ON `payments`(`saleId`)")
            }
        }

        // Migration from version 3 -> 4: convert `ticket_tenders.tenderId` column from INTEGER to TEXT
        // Approach: create a new table with the desired schema (tenderId as TEXT), copy rows converting
        // the old integer tenderId values to text, drop the old table and rename the new one.
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create new table with tenderId as TEXT
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `__ticket_tenders_new` (
                      `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                      `ticketId` INTEGER NOT NULL,
                      `tenderId` TEXT NOT NULL,
                      `tenderType` TEXT NOT NULL,
                      `amount` INTEGER NOT NULL,
                      `status` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )

                // Copy data from old table, converting tenderId to text. Use tenderId || '' as a safe conversion.
                // If the old table does not exist this will fail gracefully due to IF EXISTS checks above,
                // but we guard by wrapping the INSERT in a try/catch like behavior by checking for table existence.
                try {
                    db.execSQL(
                        """
                        INSERT INTO `__ticket_tenders_new` (id, ticketId, tenderId, tenderType, amount, status)
                        SELECT
                          id,
                          IFNULL(ticketId, 0),
                          (tenderId || ''),
                          IFNULL(tenderType, ''),
                          IFNULL(amount, 0),
                          IFNULL(status, 0)
                        FROM `ticket_tenders`;
                        """.trimIndent()
                    )

                    db.execSQL("DROP TABLE IF EXISTS `ticket_tenders`")
                    db.execSQL("ALTER TABLE `__ticket_tenders_new` RENAME TO `ticket_tenders`")
                } catch (e: Exception) {
                    // If the old table doesn't exist or copy fails, log and continue (no-op migration)
                    android.util.Log.w("AppDatabase", "MIGRATION_3_4: ticket_tenders migrate skipped: ${e.message}")
                    // Clean up new table if present to avoid partial state
                    try { db.execSQL("DROP TABLE IF EXISTS `__ticket_tenders_new`") } catch (_: Exception) {}
                }
            }
        }

        // Migration from version 4 -> 5: create printers table
        private val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create printers table matching PrinterEntity
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `printers` (
                      `id` TEXT NOT NULL PRIMARY KEY,
                      `name` TEXT NOT NULL,
                      `type` TEXT NOT NULL,
                      `connectionType` TEXT NOT NULL,
                      `address` TEXT NOT NULL,
                      `port` INTEGER,
                      `isDefault` INTEGER NOT NULL DEFAULT 0,
                      `isConnected` INTEGER NOT NULL DEFAULT 0
                    )
                    """.trimIndent()
                )
            }
        }

        // Migration from version 5 -> 6: add new tables and columns to match SQL schema
        private val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Add missing columns to existing tables
                db.execSQL("ALTER TABLE products ADD COLUMN costCents INTEGER")
                db.execSQL("ALTER TABLE products ADD COLUMN barcode TEXT")
                db.execSQL("ALTER TABLE products ADD COLUMN metadata TEXT")

                db.execSQL("ALTER TABLE categories ADD COLUMN parentId TEXT")
                db.execSQL("ALTER TABLE categories ADD COLUMN createdAt INTEGER NOT NULL DEFAULT 0")
                db.execSQL("ALTER TABLE categories ADD COLUMN updatedAt INTEGER NOT NULL DEFAULT 0")

                db.execSQL("ALTER TABLE orders ADD COLUMN currency TEXT NOT NULL DEFAULT 'MYR'")
                db.execSQL("ALTER TABLE orders ADD COLUMN externalId TEXT")
                db.execSQL("ALTER TABLE orders ADD COLUMN metadata TEXT")

                db.execSQL("ALTER TABLE sales ADD COLUMN currency TEXT NOT NULL DEFAULT 'MYR'")
                db.execSQL("ALTER TABLE sales ADD COLUMN externalId TEXT")
                db.execSQL("ALTER TABLE sales ADD COLUMN metadata TEXT")

                // Create new tables
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS users (
                        id TEXT PRIMARY KEY NOT NULL,
                        username TEXT NOT NULL,
                        displayName TEXT,
                        passwordHash TEXT NOT NULL,
                        role TEXT NOT NULL,
                        isActive INTEGER NOT NULL DEFAULT 1,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)
                db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_users_username ON users(username)")

                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS inventory (
                        productId TEXT PRIMARY KEY NOT NULL,
                        quantity INTEGER NOT NULL DEFAULT 0,
                        reserved INTEGER NOT NULL DEFAULT 0,
                        updatedAt INTEGER NOT NULL
                    )
                """)

                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS carts (
                        id TEXT PRIMARY KEY NOT NULL,
                        userId TEXT,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)

                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS cart_items (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        cartId TEXT NOT NULL,
                        productId TEXT,
                        name TEXT NOT NULL,
                        sku TEXT,
                        quantity INTEGER NOT NULL DEFAULT 1,
                        priceCents INTEGER NOT NULL,
                        createdAt INTEGER NOT NULL
                    )
                """)
                db.execSQL("CREATE INDEX IF NOT EXISTS index_cart_items_cartId ON cart_items(cartId)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_cart_items_productId ON cart_items(productId)")

                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS sync_states (
                        entity TEXT PRIMARY KEY NOT NULL,
                        lastSyncedAt INTEGER NOT NULL
                    )
                """)

                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS kv_store (
                        key TEXT PRIMARY KEY NOT NULL,
                        value TEXT,
                        updatedAt INTEGER NOT NULL
                    )
                """)

                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS local_changes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        entity TEXT NOT NULL,
                        entityId TEXT,
                        operation TEXT NOT NULL,
                        payload TEXT,
                        createdAt INTEGER NOT NULL
                    )
                """)
            }
        }

        // Migration from version 6 -> 7: Enhanced table management and reservations
        private val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Add new columns to tables table
                db.execSQL("ALTER TABLE tables ADD COLUMN section TEXT")
                db.execSQL("ALTER TABLE tables ADD COLUMN tableType TEXT")
                db.execSQL("ALTER TABLE tables ADD COLUMN positionX REAL")
                db.execSQL("ALTER TABLE tables ADD COLUMN positionY REAL")
                db.execSQL("ALTER TABLE tables ADD COLUMN width REAL")
                db.execSQL("ALTER TABLE tables ADD COLUMN height REAL")
                db.execSQL("ALTER TABLE tables ADD COLUMN rotation REAL DEFAULT 0.0")
                db.execSQL("ALTER TABLE tables ADD COLUMN assignedServerId TEXT")
                db.execSQL("ALTER TABLE tables ADD COLUMN lastServedAt INTEGER")
                db.execSQL("ALTER TABLE tables ADD COLUMN estimatedOccupancyTime INTEGER")
                db.execSQL("ALTER TABLE tables ADD COLUMN specialNotes TEXT")
                db.execSQL("ALTER TABLE tables ADD COLUMN isReservable INTEGER DEFAULT 1")
                db.execSQL("ALTER TABLE tables ADD COLUMN minimumSpendCents INTEGER")
                db.execSQL("ALTER TABLE tables ADD COLUMN depositRequiredCents INTEGER")
                db.execSQL("ALTER TABLE tables ADD COLUMN createdAt INTEGER DEFAULT 0")
                db.execSQL("ALTER TABLE tables ADD COLUMN updatedAt INTEGER DEFAULT 0")
                db.execSQL("ALTER TABLE tables ADD COLUMN isActive INTEGER DEFAULT 1")

                // Update existing records with default timestamps
                val currentTime = System.currentTimeMillis()
                db.execSQL("UPDATE tables SET createdAt = $currentTime, updatedAt = $currentTime WHERE createdAt = 0")

                // Create table_reservations table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS table_reservations (
                        id TEXT PRIMARY KEY NOT NULL,
                        tableId TEXT NOT NULL,
                        customerName TEXT NOT NULL,
                        customerPhone TEXT,
                        customerEmail TEXT,
                        partySize INTEGER NOT NULL,
                        reservationDateTime INTEGER NOT NULL,
                        durationMinutes INTEGER NOT NULL DEFAULT 120,
                        specialRequests TEXT,
                        status TEXT NOT NULL,
                        createdBy TEXT,
                        depositAmountCents INTEGER,
                        depositPaid INTEGER NOT NULL DEFAULT 0,
                        notes TEXT,
                        createdAt INTEGER NOT NULL DEFAULT 0,
                        updatedAt INTEGER NOT NULL DEFAULT 0,
                        FOREIGN KEY(tableId) REFERENCES tables(id) ON DELETE CASCADE
                    )
                """)

                // Create indices for reservations
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_reservations_tableId ON table_reservations(tableId)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_reservations_reservationDateTime ON table_reservations(reservationDateTime)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_reservations_status ON table_reservations(status)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_reservations_customerPhone ON table_reservations(customerPhone)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_reservations_customerEmail ON table_reservations(customerEmail)")
            }
        }

        // Migration from version 7 -> 8: add table sections and layouts
        private val MIGRATION_7_8 = object : Migration(7, 8) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // Create table_sections table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS table_sections (
                        id TEXT PRIMARY KEY NOT NULL,
                        name TEXT NOT NULL,
                        description TEXT,
                        color TEXT,
                        displayOrder INTEGER NOT NULL DEFAULT 0,
                        isActive INTEGER NOT NULL DEFAULT 1,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)

                // Create table_layouts table
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS table_layouts (
                        id TEXT PRIMARY KEY NOT NULL,
                        name TEXT NOT NULL,
                        description TEXT,
                        isDefault INTEGER NOT NULL DEFAULT 0,
                        layoutData TEXT,
                        createdBy TEXT,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)

                // Create indices
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_sections_displayOrder ON table_sections(displayOrder)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_sections_isActive ON table_sections(isActive)")
                db.execSQL("CREATE INDEX IF NOT EXISTS index_table_layouts_isDefault ON table_layouts(isDefault)")
            }
        }

        // Publicly expose migrations for use in tests
        val ALL_MIGRATIONS = arrayOf(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8)
    }
}
