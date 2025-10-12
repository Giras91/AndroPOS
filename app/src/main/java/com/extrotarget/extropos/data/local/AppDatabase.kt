package com.extrotarget.extropos.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.extrotarget.extropos.data.local.dao.*
import com.extrotarget.extropos.data.local.entity.*

@Database(
    entities = [
        CategoryEntity::class,
        MenuItemEntity::class,
        ProductEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        TableEntity::class,
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
        PaymentEntity::class
    ],
    version = 4,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun menuItemDao(): MenuItemDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun tableDao(): TableDao
    abstract fun shiftDao(): com.extrotarget.extropos.data.local.dao.ShiftDao
    abstract fun ticketDao(): com.extrotarget.extropos.data.local.room.RoomTicketDao

    companion object {
        private const val DB_NAME = "extropos.db"

        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                .build()
        }

    // Migration from version 1 -> 2: create the `shifts` table used for shift tracking
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new shifts table. Columns match the ShiftEntity data class.
                database.execSQL(
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
            override fun migrate(database: SupportSQLiteDatabase) {
                                // We must transform existing v1/v2 tables to match the v3 schema.
                                // Approach: create new tables with the final schema, copy data with
                                // safe defaults (IFNULL / constants for NOT NULL columns), drop the
                                // old tables and rename the new ones, then recreate indices.

                                // Migrate `sales` table to v3 shape
                                database.execSQL(
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
                                database.execSQL(
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
                                database.execSQL("DROP TABLE IF EXISTS `sales`")
                                database.execSQL("ALTER TABLE `__sales_new` RENAME TO `sales`")

                                // Migrate `sale_items` table to v3 shape (rename qty -> quantity, add discountCents)
                                                database.execSQL(
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
                                database.execSQL(
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

                                database.execSQL("DROP TABLE IF EXISTS `sale_items`")
                                database.execSQL("ALTER TABLE `__sale_items_new` RENAME TO `sale_items`")

                                // Recreate indices expected in v3 for sale_items only
                                database.execSQL("CREATE INDEX IF NOT EXISTS `index_sale_items_saleId` ON `sale_items`(`saleId`)")
                                database.execSQL("CREATE INDEX IF NOT EXISTS `index_sale_items_productId` ON `sale_items`(`productId`)")

                                // Migrate `customers` to v3 shape (add new columns with defaults)
                                database.execSQL(
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

                                database.execSQL(
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

                                database.execSQL("DROP TABLE IF EXISTS `customers`")
                                database.execSQL("ALTER TABLE `__customers_new` RENAME TO `customers`")

                                // Migrate `inventory_transactions` to v3 shape (id -> autoinc, additional columns)
                                database.execSQL(
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

                                database.execSQL(
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

                                database.execSQL("DROP TABLE IF EXISTS `inventory_transactions`")
                                database.execSQL("ALTER TABLE `__inventory_transactions_new` RENAME TO `inventory_transactions`")
                                // Create index on productId expected by v3 schema
                                database.execSQL("CREATE INDEX IF NOT EXISTS `index_inventory_transactions_productId` ON `inventory_transactions`(`productId`)")

                                // Migrate `payments` to v3 shape (id -> autoinc, add missing cols)
                                database.execSQL(
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

                                database.execSQL(
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

                                database.execSQL("DROP TABLE IF EXISTS `payments`")
                                database.execSQL("ALTER TABLE `__payments_new` RENAME TO `payments`")
                                // Create index expected by v3 schema for payments.saleId
                                database.execSQL("CREATE INDEX IF NOT EXISTS `index_payments_saleId` ON `payments`(`saleId`)")
            }
        }

        // Migration from version 3 -> 4: convert `ticket_tenders.tenderId` column from INTEGER to TEXT
        // Approach: create a new table with the desired schema (tenderId as TEXT), copy rows converting
        // the old integer tenderId values to text, drop the old table and rename the new one.
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create new table with tenderId as TEXT
                database.execSQL(
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
                    database.execSQL(
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

                    database.execSQL("DROP TABLE IF EXISTS `ticket_tenders`")
                    database.execSQL("ALTER TABLE `__ticket_tenders_new` RENAME TO `ticket_tenders`")
                } catch (e: Exception) {
                    // If the old table doesn't exist or copy fails, log and continue (no-op migration)
                    android.util.Log.w("AppDatabase", "MIGRATION_3_4: ticket_tenders migrate skipped: ${e.message}")
                    // Clean up new table if present to avoid partial state
                    try { database.execSQL("DROP TABLE IF EXISTS `__ticket_tenders_new`") } catch (_: Exception) {}
                }
            }
        }

        // Publicly expose migrations for use in tests
        val ALL_MIGRATIONS = arrayOf(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
    }
}
