package com.buslaev.coderclicker.data.firebase

import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Components
import com.buslaev.coderclicker.other.Components.*
import com.buslaev.coderclicker.other.Constants.COMPONENTS_COLLECTION
import com.buslaev.coderclicker.other.Constants.INCOME_COLLECTION
import com.buslaev.coderclicker.other.Constants.LANGUAGE_COLLECTION
import com.buslaev.coderclicker.other.Constants.PROGRAMS_COLLECTION
import com.buslaev.coderclicker.other.Income
import com.buslaev.coderclicker.other.Income.*
import com.buslaev.coderclicker.other.Programs
import com.buslaev.coderclicker.other.Programs.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class ShopDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val languagesCollection = firestore.collection(LANGUAGE_COLLECTION)
    private val programsCollection = firestore.collection(PROGRAMS_COLLECTION)
    private val componentsCollection = firestore.collection(COMPONENTS_COLLECTION)
    private val incomeCollection = firestore.collection(INCOME_COLLECTION)

    suspend fun getLanguages(): List<ShopModel> {
        return try {
            languagesCollection.get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPrograms(typePrograms: Programs): List<ShopModel> {
        return try {
            when (typePrograms) {
                IDE -> programsCollection.get().await().toObjects(ShopModel::class.java)
                OS -> programsCollection.get().await().toObjects(ShopModel::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getComponents(typeComponents: Components): List<ShopModel> {
        return try {
            when (typeComponents) {
                PROCESSORS -> componentsCollection.get().await().toObjects(ShopModel::class.java)
                MOTHERBOARD -> componentsCollection.get().await().toObjects(ShopModel::class.java)
                VIDEOCARDS -> componentsCollection.get().await().toObjects(ShopModel::class.java)
                MEMORY -> componentsCollection.get().await().toObjects(ShopModel::class.java)
                MONITORS -> componentsCollection.get().await().toObjects(ShopModel::class.java)
                CORPUS -> componentsCollection.get().await().toObjects(ShopModel::class.java)
                KEYBOARDS -> componentsCollection.get().await().toObjects(ShopModel::class.java)
                INTERNETS -> componentsCollection.get().await().toObjects(ShopModel::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getIncome(typeIncome: Income): List<ShopModel> {
        return try {
            when (typeIncome) {
                SELLING -> incomeCollection.get().await().toObjects(ShopModel::class.java)
                PROJECTS -> incomeCollection.get().await().toObjects(ShopModel::class.java)
            }

        } catch (e: Exception) {
            emptyList()
        }
    }
}