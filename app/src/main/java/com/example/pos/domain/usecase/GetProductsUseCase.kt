package com.example.pos.domain.usecase

import com.example.pos.data.repository.IProductRepository
import com.example.pos.domain.model.Product

class GetProductsUseCase(private val repo: IProductRepository) {
    suspend operator fun invoke(): List<Product> = repo.getAllProducts()
}