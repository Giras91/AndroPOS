package com.extrotarget.extropos.training

import com.extrotarget.extropos.data.local.InMemoryProductDao
import com.extrotarget.extropos.data.local.entity.ProductEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

/**
 * Simple unit test for DAO isolation patterns.
 * Tests the delegation logic without full TrainingModeManager integration.
 */
class TrainingSandboxTest {

    @Test
    fun testInMemoryDaoClearFunctionality() = runBlocking {
        val dao = InMemoryProductDao()
        val product = ProductEntity("test-1", "Test Product", 100L, "SKU001", 10)
        
        // Insert and verify
        dao.upsert(product)
        assertEquals(1, dao.getAll().size)
        assertEquals("Test Product", dao.getAll()[0].name)
        
        // Clear and verify empty
        dao.clearAll()
        assertEquals(0, dao.getAll().size)
    }

    @Test
    fun testManualDelegation() = runBlocking {
        val liveDao = InMemoryProductDao()
        val trainingDao = InMemoryProductDao()
        
        val liveProduct = ProductEntity("live-1", "Live Product", 100L, "LIVE001", 5)
        val trainingProduct = ProductEntity("train-1", "Training Product", 200L, "TRAIN001", 3)
        
        // Insert to different stores
        liveDao.upsert(liveProduct)
        trainingDao.upsert(trainingProduct)
        
        // Verify isolation
        assertEquals(1, liveDao.getAll().size)
        assertEquals(1, trainingDao.getAll().size)
        assertEquals("Live Product", liveDao.getAll()[0].name)
        assertEquals("Training Product", trainingDao.getAll()[0].name)
        
        // Clear training, verify live unaffected
        trainingDao.clearAll()
        assertEquals(1, liveDao.getAll().size)
        assertEquals(0, trainingDao.getAll().size)
    }
}