package com.extrotarget.extropos.printer

import com.extrotarget.extropos.printer.data.IPrinterLocalRepository
import com.extrotarget.extropos.printer.data.IPrinterScanner
import com.extrotarget.extropos.printer.data.PrinterEntity
import com.extrotarget.extropos.ui.settings.printer.ConnectionType
import com.extrotarget.extropos.ui.settings.printer.DiscoveredPrinter
import com.extrotarget.extropos.ui.settings.printer.Printer
import com.extrotarget.extropos.ui.settings.printer.PrinterSetupViewModel
import com.extrotarget.extropos.ui.settings.printer.PrinterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PrinterSetupViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // Fake local repository backed by MutableStateFlow
    class FakeLocalRepo : IPrinterLocalRepository {
        private val flow = MutableStateFlow<List<PrinterEntity>>(emptyList())

        override fun observePrinters() = flow

        override suspend fun getAll(): List<PrinterEntity> = flow.value

        override suspend fun upsert(printer: PrinterEntity) {
            val list = flow.value.toMutableList()
            val idx = list.indexOfFirst { it.id == printer.id }
            if (idx >= 0) list[idx] = printer else list.add(printer)
            flow.value = list
        }

        override suspend fun deleteById(id: String) {
            flow.value = flow.value.filter { it.id != id }
        }

        override suspend fun setDefault(id: String) {
            flow.value = flow.value.map { it.copy(isDefault = it.id == id) }
        }
    }

    class FakeScanner : IPrinterScanner {
        override suspend fun scanBluetooth(): List<DiscoveredPrinter> {
            return listOf(DiscoveredPrinter("BT-Prn", "AA:BB", ConnectionType.BLUETOOTH, "Maker"))
        }

        override suspend fun scanUsb(): List<DiscoveredPrinter> {
            return listOf(DiscoveredPrinter("USB-Prn", "/dev/usb/lp0", ConnectionType.USB, "Maker"))
        }

        override suspend fun scanNetwork(): List<DiscoveredPrinter> {
            return listOf(DiscoveredPrinter("Net-Prn", "192.168.1.50", ConnectionType.NETWORK, "Maker"))
        }
    }

    @Test
    fun addPrinter_persistsAndUpdatesState() = runTest {
        val repo = FakeLocalRepo()
        val scanner = FakeScanner()
        val vm = PrinterSetupViewModel(repo, scanner)

        // Add a printer
        vm.addPrinter("MyPrinter", PrinterType.RECEIPT, ConnectionType.USB, "/dev/usb/lp0", null)

        // Advance until idle to process coroutines
        testScheduler.advanceUntilIdle()

        val printers = vm.printers.value
        assertEquals(1, printers.size)
        assertEquals("MyPrinter", printers[0].name)
        assertEquals("/dev/usb/lp0", printers[0].address)
    }

    @Test
    fun scanForPrinters_returnsScannerResults() = runTest {
        val repo = FakeLocalRepo()
        val scanner = FakeScanner()
        val vm = PrinterSetupViewModel(repo, scanner)

        vm.scanForPrinters()
        testScheduler.advanceUntilIdle()

        val results = vm.scanResults.value
        // Expect concatenated results from bluetooth, usb, network
        assertTrue(results.any { it.name == "BT-Prn" })
        assertTrue(results.any { it.name == "USB-Prn" })
        assertTrue(results.any { it.name == "Net-Prn" })
    }

    @Test
    fun setDefault_updatesDefaultFlag() = runTest {
        val repo = FakeLocalRepo()
        val scanner = FakeScanner()
        val vm = PrinterSetupViewModel(repo, scanner)

        // Add two printers
        vm.addPrinter("P1", PrinterType.RECEIPT, ConnectionType.USB, "addr1", null)
        vm.addPrinter("P2", PrinterType.RECEIPT, ConnectionType.USB, "addr2", null)
        testScheduler.advanceUntilIdle()

        val first = vm.printers.value.firstOrNull() ?: throw AssertionError("no printers")
        vm.setDefaultPrinter(first)
        testScheduler.advanceUntilIdle()

        val printers = vm.printers.value
        val defaultCount = printers.count { it.isDefault }
        assertEquals(1, defaultCount)
    }
}

