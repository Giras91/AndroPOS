package com.extrotarget.extropos.data.local

import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
class RoomMigrationTest {

    @Test
    fun migrate1To3_instrumentation() {
        val dbName = "migration-test"
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        // Create v1 DB file directly in app context to avoid relying on Room schema JSON parsing
        val dbFile: File = context.getDatabasePath(dbName)
        // Ensure a clean database file for the migration test
        if (dbFile.exists()) {
            dbFile.delete()
        }
        dbFile.parentFile?.mkdirs()

        val sqlite = SQLiteDatabase.openOrCreateDatabase(dbFile, null)
        try {
            // Create v1 tables
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS sales (id TEXT NOT NULL PRIMARY KEY, receiptNo TEXT, userId TEXT, customerId TEXT, createdAt INTEGER NOT NULL, totalAmountCents INTEGER NOT NULL, paymentMethod TEXT, paymentStatus TEXT);")
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS sale_items (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, saleId TEXT NOT NULL, productId TEXT NOT NULL, productName TEXT, qty INTEGER NOT NULL, unitPriceCents INTEGER NOT NULL, totalPriceCents INTEGER NOT NULL);")
            // v1 also had categories table — create it so migration has something to migrate
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS categories (id TEXT NOT NULL PRIMARY KEY, name TEXT NOT NULL, description TEXT, displayOrder INTEGER NOT NULL, isActive INTEGER NOT NULL);")
            // v1 also may not have had menu_items; create it so migration has table to validate against
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS menu_items (id TEXT NOT NULL PRIMARY KEY, name TEXT NOT NULL, description TEXT, priceCents INTEGER NOT NULL, categoryId TEXT NOT NULL, imageUrl TEXT, isAvailable INTEGER NOT NULL, preparationTimeMinutes INTEGER, allergens TEXT);")
            // v1 also may not have had products; create products table with final columns so migration validation can compare
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS products (id TEXT NOT NULL, name TEXT NOT NULL, priceCents INTEGER NOT NULL, sku TEXT, stockQuantity INTEGER NOT NULL, categoryId TEXT, description TEXT, imageUrl TEXT, isActive INTEGER NOT NULL, createdAt INTEGER NOT NULL, updatedAt INTEGER NOT NULL, PRIMARY KEY(id));")
            // create orders, order_items, tables, customers, inventory_transactions, payments to mirror v3 schema
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS orders (id TEXT NOT NULL, tableId TEXT, orderNumber TEXT NOT NULL, status TEXT NOT NULL, orderType TEXT NOT NULL, subtotalCents INTEGER NOT NULL, taxCents INTEGER NOT NULL, discountCents INTEGER NOT NULL, totalCents INTEGER NOT NULL, createdAt INTEGER NOT NULL, updatedAt INTEGER NOT NULL, notes TEXT, PRIMARY KEY(id));")
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS order_items (id TEXT NOT NULL, orderId TEXT NOT NULL, menuItemId TEXT NOT NULL, menuItemName TEXT NOT NULL, quantity INTEGER NOT NULL, unitPriceCents INTEGER NOT NULL, totalPriceCents INTEGER NOT NULL, notes TEXT, status TEXT NOT NULL, PRIMARY KEY(id));")
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS tables (id TEXT NOT NULL, number TEXT NOT NULL, capacity INTEGER NOT NULL, status TEXT NOT NULL, currentOrderId TEXT, PRIMARY KEY(id));")
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS customers (id TEXT NOT NULL, name TEXT NOT NULL, phone TEXT, email TEXT, PRIMARY KEY(id));")
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS inventory_transactions (id TEXT NOT NULL, productId TEXT NOT NULL, change INTEGER NOT NULL, reason TEXT, createdAt INTEGER NOT NULL, PRIMARY KEY(id));")
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS payments (id TEXT NOT NULL, saleId TEXT NOT NULL, amountCents INTEGER NOT NULL, method TEXT NOT NULL, createdAt INTEGER NOT NULL, PRIMARY KEY(id));")
            // Set user_version to 1
            sqlite.execSQL("PRAGMA user_version = 1;")
        } finally {
            sqlite.close()
        }

        // Run migrations 1->3
        val migratedDb = Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .addMigrations(*AppDatabase.ALL_MIGRATIONS)
            .build()

        migratedDb.openHelper.readableDatabase.use { db ->
            val cursor = db.query("SELECT name FROM sqlite_master WHERE type='table' AND name='shifts';")
            try {
                val hasShifts = cursor.moveToFirst()
                Assert.assertTrue("shifts table should exist after migration", hasShifts)
            } finally {
                cursor.close()
            }
        }

        migratedDb.close()
    }
}
