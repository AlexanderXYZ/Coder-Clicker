package com.buslaev.coderclicker.data.firebase

import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Constants.COMPONENTS_COLLECTION
import com.buslaev.coderclicker.other.Constants.INCOME_COLLECTION
import com.buslaev.coderclicker.other.Constants.LANGUAGE_COLLECTION
import com.buslaev.coderclicker.other.Constants.PROGRAMS_COLLECTION
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
    suspend fun getProgramsIde(): List<ShopModel> {
        return try {
            programsCollection.get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
    suspend fun getProgramsOs(): List<ShopModel> {
        return try {
            programsCollection.get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
    suspend fun getComponents(): List<ShopModel> {
        return try {
            componentsCollection.get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
    suspend fun getIncome(): List<ShopModel> {
        return try {
            incomeCollection.get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}