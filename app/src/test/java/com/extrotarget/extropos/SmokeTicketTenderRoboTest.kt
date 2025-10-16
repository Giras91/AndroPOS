package com.extrotarget.extropos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import android.os.Build
import com.extrotarget.extropos.data.local.AppDatabase
import com.extrotarget.extropos.data.local.entity.TicketEntity
import com.extrotarget.extropos.data.local.entity.TicketItemEntity
import com.extrotarget.extropos.data.local.entity.TicketTenderEntity
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.P])
class SmokeTicketTenderRoboTest {

    @Test
    fun createTicket_addTender_and_print() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        try {
            val dao = db.ticketDao()

            runBlocking {
                val now = System.currentTimeMillis()
                val ticket = TicketEntity(
                    ticketType = 0,
                    state = 1,
                    total = 1500L,
                    createdAt = now,
                    updatedAt = now,
                    sessionId = 1
                )
                val ticketRowId = dao.insertTicket(ticket)
                val ticketId = ticketRowId.toInt()

                val item = TicketItemEntity(
                    ticketId = ticketId,
                    itemId = 202,
                    sku = "ROBOTSKU",
                    quantity = 1,
                    amount = 1500L,
                    cost = 800L,
                    itemDesc = "Robo item",
                    state = 1
                )
                dao.insertTicketItem(item)

                val tender = TicketTenderEntity(
                    ticketId = ticketId,
                    tenderId = "robo-pm-1",
                    tenderType = "cash",
                    amount = 1500L,
                    status = 1
                )
                dao.insertTicketTender(tender)

                val readTicket = dao.getTicketById(ticketId)
                val items = dao.getTicketItems(ticketId)
                val tenders = dao.getTicketTenders(ticketId)

                println("[RoboTest] Inserted ticket: $readTicket")
                println("[RoboTest] Inserted items: $items")
                println("[RoboTest] Inserted tenders: $tenders")
            }
        } finally {
            db.close()
        }
    }
}
