package com.extrotarget.extropos.ui.settings.table

import com.extrotarget.extropos.domain.model.TableSection
import com.extrotarget.extropos.domain.repository.ITableSectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class TableSectionsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var mockRepository: ITableSectionRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial load fetches sections and sorts by display order`(): Unit = runTest {
        val sections = listOf(
            TableSection(id = "1", name = "Section C", displayOrder = 3),
            TableSection(id = "2", name = "Section A", displayOrder = 1),
            TableSection(id = "3", name = "Section B", displayOrder = 2)
        )

        runBlocking { `when`(mockRepository.getAllSections()).thenReturn(sections) }

        val viewModel = TableSectionsViewModel(mockRepository)
        advanceUntilIdle()

        val stateSections = viewModel.sections.first()
        assertEquals(3, stateSections.size)
        assertEquals("Section A", stateSections[0].name)
        assertEquals("Section B", stateSections[1].name)
        assertEquals("Section C", stateSections[2].name)
    }

    @Test
    fun `save calls repository and reloads sections`(): Unit = runTest {
        val section = TableSection(id = "1", name = "Test Section")
        val sections = listOf(section)

        runBlocking {
            `when`(mockRepository.upsertSection(section)).thenReturn(Unit)
            `when`(mockRepository.getAllSections()).thenReturn(sections)
        }

        val viewModel = TableSectionsViewModel(mockRepository)
        advanceUntilIdle() // Complete initial load

        viewModel.save(section)
        advanceUntilIdle()

        runBlocking { verify(mockRepository).upsertSection(section) }

        val stateSections = viewModel.sections.first()
        assertEquals(1, stateSections.size)
        assertEquals(section, stateSections[0])
    }

    @Test
    fun `delete calls repository and reloads sections`(): Unit = runTest {
        val sectionId = "test-id"
        val remainingSections = listOf(TableSection(id = "other", name = "Other Section"))

        runBlocking {
            `when`(mockRepository.deleteSection(sectionId)).thenReturn(Unit)
            `when`(mockRepository.getAllSections()).thenReturn(remainingSections)
        }

        val viewModel = TableSectionsViewModel(mockRepository)
        advanceUntilIdle() // Complete initial load

        viewModel.delete(sectionId)
        advanceUntilIdle()

        runBlocking { verify(mockRepository).deleteSection(sectionId) }

        val stateSections = viewModel.sections.first()
        assertEquals(1, stateSections.size)
        assertEquals("Other Section", stateSections[0].name)
    }
}