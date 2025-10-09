package com.extrotarget.extropos.data.repository

import com.extrotarget.extropos.data.local.dao.ProductDao
import com.extrotarget.extropos.data.local.entity.ProductEntity
import com.extrotarget.extropos.domain.model.Product
import com.extrotarget.extropos.domain.repository.IProductRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : IProductRepository {

    override suspend fun getAllProducts(): List<Product> {
        return productDao.getAll().map { it.toDomain() }
    }

    override suspend fun getProductById(id: String): Product? {
        return productDao.getById(id)?.toDomain()
    }

    override suspend fun upsertProduct(product: Product) {
        val now = System.currentTimeMillis()

        // Build the entity with explicit, correctly-typed arguments.
        // Use takeIf { it.isNotBlank() } to convert empty-string defaults into nulls
        // where the DB column is nullable.
        val entity = ProductEntity(
            id = product.id,
            name = product.name,
            priceCents = product.priceCents,
            sku = product.id, // reuse id as a placeholder SKU for now
            stockQuantity = product.stockQuantity,
            categoryId = product.categoryId.takeIf { it.isNotBlank() },
            description = product.description.takeIf { it.isNotBlank() },
            imageUrl = product.imageUrl.takeIf { it.isNotBlank() },
            isActive = product.isAvailable,
            createdAt = now,
            updatedAt = now
        )

        productDao.upsert(entity)
    }

    private fun ProductEntity.toDomain(): Product {
        return Product(
            id = id,
            name = name,
            description = description ?: "",
            priceCents = priceCents,
            categoryId = categoryId ?: "",
            imageUrl = imageUrl ?: "",
            stockQuantity = stockQuantity,
            isAvailable = isActive
        )
    }
}
