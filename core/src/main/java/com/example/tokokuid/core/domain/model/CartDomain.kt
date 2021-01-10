package com.example.tokokuid.core.domain.model

data class CartDomain(
    val id_item: Int,
    val name_item: String,
    val price_item: Int,
    val weight_item: Int,
    val url_picture_item: Int,
    val description: String
)
