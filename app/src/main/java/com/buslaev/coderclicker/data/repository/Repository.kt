package com.buslaev.coderclicker.data.repository

import com.buslaev.coderclicker.data.firebase.ShopDatabase
import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Components
import com.buslaev.coderclicker.other.Income
import com.buslaev.coderclicker.other.Programs

class Repository {
    private var shopDatabase: ShopDatabase = ShopDatabase()

    suspend fun getLanguages(): List<ShopModel> {
        return shopDatabase.getLanguages()
    }

    suspend fun getPrograms(typePrograms: Programs): List<ShopModel> {
        return shopDatabase.getPrograms(typePrograms)
    }

    suspend fun getComponents(typeComponents: Components): List<ShopModel> {
        return shopDatabase.getComponents(typeComponents)
    }

    suspend fun getIncome(typeIncome: Income): List<ShopModel> {
        return shopDatabase.getIncome(typeIncome)
    }

}