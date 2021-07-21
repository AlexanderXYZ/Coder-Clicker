package com.buslaev.coderclicker.models

data class ShopModel(
    val id: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val price: String = "",
    val growth: String = "",
    var purchased: Boolean = false
)
