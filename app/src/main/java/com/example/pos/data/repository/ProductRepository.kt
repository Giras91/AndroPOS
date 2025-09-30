package com.example.pos.data.repository

import com.example.pos.data.local.dao.ProductDao
import com.example.pos.data.local.entity.ProductEntity
import com.example.pos.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

interface IProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductById(id: String): Product?
    suspend fun upsert(product: Product)
    suspend fun searchProducts(query: String): List<Product>
}

@Singleton
class ProductRepository @Inject constructor(
    private val dao: ProductDao
) : IProductRepository {

    override suspend fun getAllProducts(): List<Product> {
        return dao.getAll().map { entity ->
            Product(
                id = entity.id,
                name = entity.name,
                priceCents = entity.priceCents,
                sku = entity.sku,
                stockQuantity = entity.stockQuantity,
                barcode = entity.sku, // Using SKU as barcode for now
                category = null // TODO: Add category support
            )
        }
    }

    override suspend fun getProductById(id: String): Product? {
        return dao.getById(id)?.let { entity ->
            Product(
                id = entity.id,
                name = entity.name,
                priceCents = entity.priceCents,
                sku = entity.sku,
                stockQuantity = entity.stockQuantity,
                barcode = entity.sku,
                category = null
            )
        }
    }

    override suspend fun upsert(product: Product) {
        val entity = ProductEntity(
            id = product.id,
            name = product.name,
            priceCents = product.priceCents,
            sku = product.sku,
            stockQuantity = product.stockQuantity
        )
        dao.upsert(entity)
    }

    override suspend fun searchProducts(query: String): List<Product> {
        val allProducts = getAllProducts()
        return allProducts.filter { product ->
            product.name.contains(query, ignoreCase = true) ||
            product.sku?.contains(query, ignoreCase = true) == true ||
            product.barcode?.contains(query, ignoreCase = true) == true
        }
    }
}