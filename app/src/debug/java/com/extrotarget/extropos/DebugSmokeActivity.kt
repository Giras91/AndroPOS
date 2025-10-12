package com.extrotarget.extropos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.extrotarget.extropos.data.local.AppDatabase
import com.extrotarget.extropos.data.local.entity.TicketEntity
import com.extrotarget.extropos.data.local.entity.TicketItemEntity
import com.extrotarget.extropos.data.local.entity.TicketTenderEntity
import kotlinx.coroutines.launch

/**
 * Debug-only activity to perform a quick smoke test on-device.
 * Launch with: adb shell am start -n com.extrotarget.extropos/.DebugSmokeActivity
 */
class DebugSmokeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            try {
                // Use persistent DB (same as app) so we test the real path
                val db = AppDatabase.create(applicationContext)
                val dao = db.ticketDao()

                val now = System.currentTimeMillis()
                val ticket = TicketEntity(
                    ticketType = 0,
                    state = 1,
                    total = 2000L,
                    createdAt = now,
                    updatedAt = now,
                    sessionId = 1
                )

                val ticketRowId = dao.insertTicket(ticket)
                val ticketId = ticketRowId.toInt()

                val item = TicketItemEntity(
                    ticketId = ticketId,
                    itemId = 303,
                    sku = "DBGSKU",
                    quantity = 1,
                    amount = 2000L,
                    cost = 1000L,
                    itemDesc = "Debug item",
                    state = 1
                )
                dao.insertTicketItem(item)

                val tender = TicketTenderEntity(
                    ticketId = ticketId,
                    tenderId = "debug-pm-xyz",
                    tenderType = "card",
                    amount = 2000L,
                    status = 1
                )
                dao.insertTicketTender(tender)

                val readTicket = dao.getTicketById(ticketId)
                val items = dao.getTicketItems(ticketId)
                val tenders = dao.getTicketTenders(ticketId)

                Log.i("SmokeDebug", "Inserted ticket: $readTicket")
                Log.i("SmokeDebug", "Inserted items: $items")
                Log.i("SmokeDebug", "Inserted tenders: $tenders")

                // Export DB file to external cache so it can be pulled for inspection
                try {
                    val dbName = "extropos.db"
                    val dbFile = applicationContext.getDatabasePath(dbName)
                    if (dbFile.exists()) {
                        val outFile = java.io.File(applicationContext.externalCacheDir, "exported_$dbName")
                        dbFile.inputStream().use { input ->
                            outFile.outputStream().use { output ->
                                input.copyTo(output)
                            }
                        }
                        Log.i("SmokeDebug", "Exported DB to: ${outFile.absolutePath}")
                    } else {
                        Log.w("SmokeDebug", "DB file not found at ${dbFile.absolutePath}")
                    }
                } catch (ex: Exception) {
                    Log.e("SmokeDebug", "Failed to export DB: ${ex.message}", ex)
                }
            } catch (e: Exception) {
                Log.e("SmokeDebug", "Error during smoke test: ${e.message}", e)
            } finally {
                // Finish the activity after the test
                finish()
            }
        }
    }
}
