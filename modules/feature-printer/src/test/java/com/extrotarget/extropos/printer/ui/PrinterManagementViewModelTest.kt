package com.extrotarget.extropos.printer.ui

import com.extrotarget.extropos.printer.domain.model.DetectedPrinter
import com.extrotarget.extropos.printer.domain.model.PrinterSdk
import com.extrotarget.extropos.printer.domain.service.PrinterConnectionStatus
import com.extrotarget.extropos.printer.domain.service.PrinterService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.resetMain
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PrinterManagementViewModelTest {

    // test dispatchers will be created inside each test to avoid early Main dispatcher init

    // Use a Mockito mock to avoid needing Android Contexts or subclassing final classes
    private fun createMockService(): PrinterService {
        val mockService: PrinterService = mock()

        whenever(mockService.getAvailableSdks()).thenReturn(
            listOf(
                PrinterSdk(
                    id = "dantsu-escpos",
                    name = "DantSu ESC/POS",
                    vendor = "DantSu",
                    version = "1.0",
                    description = "ESC/POS adapter",
                    connectionTypes = listOf(com.extrotarget.extropos.printer.domain.model.ConnectionType.NETWORK)
                )
            )
        )

        // Stub suspend function inside a runBlocking so it compiles in unit test source
        kotlinx.coroutines.runBlocking {
            whenever(mockService.scanAllPrinters()).thenReturn(
                listOf(DetectedPrinter("TestPrinter", com.extrotarget.extropos.printer.domain.model.ConnectionType.NETWORK, "192.0.2.1", compatibleSdks = emptyList()))
            )
        }

        return mockService
    }

    @Test
    fun loadsSdksAndScans() = runTest {
        // Use the test scheduler provided by runTest
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
        try {
            val service = createMockService()
            val vm = PrinterManagementViewModel(service)

            vm.scanForPrinters()

            // advance until coroutines complete
            testScheduler.advanceUntilIdle()

            val printers = vm.printers.value
            assertTrue(printers.isNotEmpty())

            val sdks = vm.sdks.value
            assertTrue(sdks.isNotEmpty())
            assertEquals("DantSu ESC/POS", sdks.first().name)
        } finally {
            kotlinx.coroutines.Dispatchers.resetMain()
        }
    }
}
