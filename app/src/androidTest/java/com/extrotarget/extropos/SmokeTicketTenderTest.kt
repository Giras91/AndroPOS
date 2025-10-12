package com.extrotarget.extropos

import android.content.Context
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.room.Room
import com.extrotarget.extropos.data.local.AppDatabase
import com.extrotarget.extropos.data.local.entity.TicketEntity
import com.extrotarget.extropos.data.local.entity.TicketItemEntity
import com.extrotarget.extropos.data.local.entity.TicketTenderEntity
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SmokeTicketTenderTest {

    @Test
    fun createTicket_addTender_and_log() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Use an in-memory database for a quick smoke test to avoid migrations and persistent state
        val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val dao = db.ticketDao()

        runBlocking {
            // Create a ticket
            val now = System.currentTimeMillis()
            val ticket = TicketEntity(
                ticketType = 0,
                state = 1,
                total = 1000L,
                createdAt = now,
                updatedAt = now,
                sessionId = 1
            )

            val ticketRowId = dao.insertTicket(ticket)
            val ticketId = ticketRowId.toInt()

            // Add an item
            val item = TicketItemEntity(
                ticketId = ticketId,
                itemId = 101,
                sku = "TESTSKU",
                quantity = 1,
                amount = 1000L,
                cost = 500L,
                itemDesc = "Test item",
                state = 1
            )
            dao.insertTicketItem(item)

            // Add a tender (use string tenderId to exercise migration shape)
            val tender = TicketTenderEntity(
                ticketId = ticketId,
                tenderId = "pm-test-123",
                tenderType = "card",
                amount = 1000L,
                status = 1
            )
            dao.insertTicketTender(tender)

            // Read back and log
            val readTicket = dao.getTicketById(ticketId)
            val items = dao.getTicketItems(ticketId)
            val tenders = dao.getTicketTenders(ticketId)

            Log.i("SmokeTest", "Inserted ticket: $readTicket")
            Log.i("SmokeTest", "Inserted items: $items")
            Log.i("SmokeTest", "Inserted tenders: $tenders")
        }
    }
}
