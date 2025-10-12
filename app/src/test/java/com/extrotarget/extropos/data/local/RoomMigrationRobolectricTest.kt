package com.extrotarget.extropos.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import android.os.Build
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.R])
class RoomMigrationRobolectricTest {

    @Test
    fun migrate1To3_robolectric() {
        val dbName = "migration-test-robo"

        // Use Robolectric app context to create a pre-migration (v1) sqlite database file
        val context = org.robolectric.RuntimeEnvironment.getApplication()
        val dbFile = context.getDatabasePath(dbName)
        dbFile.parentFile?.mkdirs()

        // Create the v1 schema (sales + sale_items) and set user_version = 1
        val sqlite = android.database.sqlite.SQLiteDatabase.openOrCreateDatabase(dbFile, null)
        try {
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS sales (id TEXT NOT NULL PRIMARY KEY, receiptNo TEXT, userId TEXT, customerId TEXT, createdAt INTEGER NOT NULL, totalAmountCents INTEGER NOT NULL, paymentMethod TEXT, paymentStatus TEXT)")
            sqlite.execSQL("CREATE TABLE IF NOT EXISTS sale_items (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, saleId TEXT NOT NULL, productId TEXT NOT NULL, productName TEXT, qty INTEGER NOT NULL, unitPriceCents INTEGER NOT NULL, totalPriceCents INTEGER NOT NULL)")
            // ensure sqlite user_version reflects v1
            sqlite.execSQL("PRAGMA user_version = 1")
        } finally {
            sqlite.close()
        }

        val migrated = Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .addMigrations(*AppDatabase.ALL_MIGRATIONS)
            .build()

        migrated.openHelper.readableDatabase.use {
            // If open succeeds, migrations applied
            val cursor = it.query("SELECT name FROM sqlite_master WHERE type='table' AND name='shifts';")
            try {
                val hasShifts = cursor.moveToFirst()
                Assert.assertTrue("shifts table should exist after migration", hasShifts)
            } finally {
                cursor.close()
            }
        }

        migrated.close()
    }
}
