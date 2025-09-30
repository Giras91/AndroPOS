package com.extrotarget.extropos.data.local.entity

data class ProductEntity(
    val id: String,
    val name: String,
    val priceCents: Long,
    val sku: String?,
    val stockQuantity: Int
)