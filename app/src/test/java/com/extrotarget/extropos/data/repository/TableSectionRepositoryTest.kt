package com.extrotarget.extropos.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.extrotarget.extropos.data.local.AppDatabase
import com.extrotarget.extropos.data.local.dao.TableSectionDao
import com.extrotarget.extropos.domain.model.TableSection
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import android.os.Build

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [Build.VERSION_CODES.R])
class TableSectionRepositoryTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: TableSectionDao
    private lateinit var repository: TableSectionRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.tableSectionDao()
        repository = TableSectionRepository(dao)
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun `getAllSections returns empty list when no sections exist`(): Unit = runBlocking {
        val sections = repository.getAllSections()
        assertTrue(sections.isEmpty())
    }

    @Test
    fun `getActiveSections returns empty list when no active sections exist`(): Unit = runBlocking {
        val sections = repository.getActiveSections()
        assertTrue(sections.isEmpty())
    }

    @Test
    fun `upsertSection creates new section`(): Unit = runBlocking {
        val section = TableSection(
            id = "test-1",
            name = "Main Dining",
            description = "Main dining area",
            color = "#FF0000",
            displayOrder = 1,
            isActive = true
        )

        repository.upsertSection(section)

        val sections = repository.getAllSections()
        assertEquals(1, sections.size)
        assertEquals(section, sections[0])
    }

    @Test
    fun `upsertSection updates existing section`(): Unit = runBlocking {
        val originalSection = TableSection(
            id = "test-1",
            name = "Main Dining",
            description = "Main dining area",
            color = "#FF0000",
            displayOrder = 1,
            isActive = true
        )

        repository.upsertSection(originalSection)

        val updatedSection = originalSection.copy(
            name = "Updated Main Dining",
            description = "Updated description"
        )

        repository.upsertSection(updatedSection)

        val sections = repository.getAllSections()
        assertEquals(1, sections.size)
        assertEquals("Updated Main Dining", sections[0].name)
        assertEquals("Updated description", sections[0].description)
    }

    @Test
    fun `upsertSections creates multiple sections`(): Unit = runBlocking {
        val sections = listOf(
            TableSection(id = "test-1", name = "Section 1", displayOrder = 1),
            TableSection(id = "test-2", name = "Section 2", displayOrder = 2),
            TableSection(id = "test-3", name = "Section 3", displayOrder = 3)
        )

        repository.upsertSections(sections)

        val retrievedSections = repository.getAllSections()
        assertEquals(3, retrievedSections.size)
        assertEquals(sections.sortedBy { it.displayOrder }, retrievedSections.sortedBy { it.displayOrder })
    }

    @Test
    fun `getSectionById returns correct section`(): Unit = runBlocking {
        val section = TableSection(id = "test-1", name = "Test Section")
        repository.upsertSection(section)

        val retrieved = repository.getSectionById("test-1")
        assertNotNull(retrieved)
        assertEquals(section, retrieved)
    }

    @Test
    fun `getSectionById returns null for non-existent section`(): Unit = runBlocking {
        val retrieved = repository.getSectionById("non-existent")
        assertNull(retrieved)
    }

    @Test
    fun `getActiveSections returns only active sections`(): Unit = runBlocking {
        val activeSection = TableSection(id = "active-1", name = "Active Section", isActive = true)
        val inactiveSection = TableSection(id = "inactive-1", name = "Inactive Section", isActive = false)

        repository.upsertSection(activeSection)
        repository.upsertSection(inactiveSection)

        val activeSections = repository.getActiveSections()
        assertEquals(1, activeSections.size)
        assertEquals(activeSection, activeSections[0])
    }

    @Test
    fun `setActive updates section active status`(): Unit = runBlocking {
        val section = TableSection(id = "test-1", name = "Test Section", isActive = true)
        repository.upsertSection(section)

        repository.setActive("test-1", false)

        val retrieved = repository.getSectionById("test-1")
        assertNotNull(retrieved)
        assertFalse(retrieved!!.isActive)

        val activeSections = repository.getActiveSections()
        assertTrue(activeSections.isEmpty())
    }

    @Test
    fun `deleteSection removes section`(): Unit = runBlocking {
        val section = TableSection(id = "test-1", name = "Test Section")
        repository.upsertSection(section)

        repository.deleteSection("test-1")

        val sections = repository.getAllSections()
        assertTrue(sections.isEmpty())

        val retrieved = repository.getSectionById("test-1")
        assertNull(retrieved)
    }

    @Test
    fun `deleteSection does nothing for non-existent section`(): Unit = runBlocking {
        val section = TableSection(id = "test-1", name = "Test Section")
        repository.upsertSection(section)

        repository.deleteSection("non-existent")

        val sections = repository.getAllSections()
        assertEquals(1, sections.size)
    }
}