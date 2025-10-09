package com.extrotarget.extropos.domain.repository

import com.extrotarget.extropos.domain.model.Product

interface IProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: String): Product?
    suspend fun upsertProduct(product: Product)
}
