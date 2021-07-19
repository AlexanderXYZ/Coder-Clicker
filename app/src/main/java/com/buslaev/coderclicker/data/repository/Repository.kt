package com.buslaev.coderclicker.data.repository

import com.buslaev.coderclicker.data.firebase.ShopDatabase
import com.buslaev.coderclicker.models.ShopModel

class Repository {
    private var shopDatabase:ShopDatabase = ShopDatabase()

    suspend fun getLanguages():List<ShopModel> {
        return shopDatabase.getLanguages()
    }

}