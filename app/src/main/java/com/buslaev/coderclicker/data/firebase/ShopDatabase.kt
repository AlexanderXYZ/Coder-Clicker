package com.buslaev.coderclicker.data.firebase

import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Constants.LANGUAGE_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class ShopDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val languagesCollection = firestore.collection(LANGUAGE_COLLECTION)

    suspend fun getLanguages(): List<ShopModel> {
        return try {
            languagesCollection.get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}