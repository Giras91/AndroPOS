package com.extrotarget.extropos.data.local.dao

import com.extrotarget.extropos.data.local.entity.ProductEntity

interface ProductDao {
    suspend fun getAll(): List<ProductEntity>
    suspend fun upsert(vararg product: ProductEntity)
    suspend fun getById(id: String): ProductEntity?
}