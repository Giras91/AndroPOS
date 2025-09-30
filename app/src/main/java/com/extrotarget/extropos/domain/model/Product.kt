package com.extrotarget.extropos.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String = "",
    val priceCents: Long,
    val categoryId: String,
    val imageUrl: String = "",
    val stockQuantity: Int = 0,
    val isAvailable: Boolean = true
)