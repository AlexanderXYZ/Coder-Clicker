package com.buslaev.coderclicker.models

data class ShopModel(
    val id: String = "",
    val title: String = "",
    val imageUrl: String = "",
    var price: String = "",
    val growth: String = "",
    var remained: String = "",
    var purchased: Boolean = false,
    var projects: Boolean = false
)
