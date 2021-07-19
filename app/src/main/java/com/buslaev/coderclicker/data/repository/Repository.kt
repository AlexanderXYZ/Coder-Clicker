package com.buslaev.coderclicker.data.repository

import com.buslaev.coderclicker.data.firebase.ShopDatabase
import com.buslaev.coderclicker.models.ShopModel

class Repository {
    private var shopDatabase: ShopDatabase = ShopDatabase()

    suspend fun getLanguages(): List<ShopModel> {
        return shopDatabase.getLanguages()
    }

    suspend fun getProgramsIde(): List<ShopModel> {
        return shopDatabase.getProgramsIde()
    }
    suspend fun getProgramsOs(): List<ShopModel> {
        return shopDatabase.getProgramsOs()
    }

    suspend fun getComponents(): List<ShopModel> {
        return shopDatabase.getComponents()
    }

    suspend fun getIncome(): List<ShopModel> {
        return shopDatabase.getIncome()
    }

}