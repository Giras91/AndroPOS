package com.extrotarget.extropos

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.extrotarget.extropos.data.local.AppDatabase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/**
 * Migration test for MIGRATION_3_4 which converts ticket_tenders.tenderId from INTEGER to TEXT
 */
@RunWith(AndroidJUnit4::class)
class Migration3To4Test {

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migrate3To4_convertsTenderIdToText() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        // Create a v3 database file by executing SQL that represents the old schema
        val dbName = "migration-test.db"
        val db = helper.createDatabase(dbName, 3).apply {
            // Create the old ticket_tenders table with integer tenderId and insert a row
            execSQL(
                """
                CREATE TABLE IF NOT EXISTS `ticket_tenders` (
                  `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                  `ticketId` INTEGER NOT NULL,
                  `tenderId` INTEGER NOT NULL,
                  `tenderType` TEXT NOT NULL,
                  `amount` INTEGER NOT NULL,
                  `status` INTEGER NOT NULL
                )
                """.trimIndent()
            )

            // Insert a sample row where tenderId is integer
            execSQL("INSERT INTO ticket_tenders (ticketId, tenderId, tenderType, amount, status) VALUES (1, 42, 'card', 1000, 1)")

            close()
        }

        // Run migration to version 4
        val migrated = helper.runMigrationsAndValidate(dbName, 4, true, *AppDatabase.ALL_MIGRATIONS)

        // Open the migrated DB and check the ticket_tenders.tenderId column type/value
        val migratedDbFile = context.getDatabasePath(dbName)
        val conn = androidx.sqlite.db.SupportSQLiteDatabase::class.java
        // Use android.database.sqlite.SQLiteDatabase to query
        val sqlite = android.database.sqlite.SQLiteDatabase.openDatabase(migratedDbFile.path, null, android.database.sqlite.SQLiteDatabase.OPEN_READONLY)
        val cursor = sqlite.rawQuery("SELECT tenderId FROM ticket_tenders LIMIT 1", null)
        try {
            if (cursor.moveToFirst()) {
                val tenderIdValue = cursor.getString(0)
                // After migration, the integer 42 should be preserved as the string "42"
                assert(tenderIdValue == "42") { "Expected tenderId to be '42' but was '$tenderIdValue'" }
            } else {
                throw AssertionError("No rows found in ticket_tenders after migration")
            }
        } finally {
            cursor.close()
            sqlite.close()
        }
    }
}
