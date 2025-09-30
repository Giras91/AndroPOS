package com.extrotarget.extropos.domain.model

data class MenuItem(
    val id: String,
    val categoryId: String,
    val name: String,
    val description: String = "",
    val priceCents: Long,
    val imageUrl: String = "",
    val preparationTimeMinutes: Int = 0,
    val allergens: List<String> = emptyList(),
    val isAvailable: Boolean = true
)