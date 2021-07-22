package com.buslaev.coderclicker.data.firebase

import com.buslaev.coderclicker.models.ShopModel
import com.buslaev.coderclicker.other.Components
import com.buslaev.coderclicker.other.Components.*
import com.buslaev.coderclicker.other.Constants.CORPUS_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Constants.IDE_PROGRAMS_COLLECTION
import com.buslaev.coderclicker.other.Constants.INTERNETS_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Constants.KEYBOARDS_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Constants.LANGUAGE_COLLECTION
import com.buslaev.coderclicker.other.Constants.MEMORY_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Constants.MONITORS_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Constants.MOTHERBOARD_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Constants.OS_PROGRAMS_COLLECTION
import com.buslaev.coderclicker.other.Constants.PROCESSORS_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Constants.PROJECTS_INCOME_COLLECTION
import com.buslaev.coderclicker.other.Constants.SELLING_INCOME_COLLECTION
import com.buslaev.coderclicker.other.Constants.VIDEOCARDS_COMPONENT_COLLECTION
import com.buslaev.coderclicker.other.Income
import com.buslaev.coderclicker.other.Income.*
import com.buslaev.coderclicker.other.Programs
import com.buslaev.coderclicker.other.Programs.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class ShopDatabase {

    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getLanguages(): List<ShopModel> {
        return try {
            firestore.collection(LANGUAGE_COLLECTION).get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getPrograms(typePrograms: Programs): List<ShopModel> {
        return try {
            val program = when (typePrograms) {
                IDE -> IDE_PROGRAMS_COLLECTION
                OS -> OS_PROGRAMS_COLLECTION
            }
            firestore.collection(program).get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getComponents(typeComponents: Components): List<ShopModel> {
        return try {
            val component = when (typeComponents) {
                PROCESSORS -> PROCESSORS_COMPONENT_COLLECTION
                MOTHERBOARD -> MOTHERBOARD_COMPONENT_COLLECTION
                VIDEOCARDS -> VIDEOCARDS_COMPONENT_COLLECTION
                MEMORY -> MEMORY_COMPONENT_COLLECTION
                MONITORS -> MONITORS_COMPONENT_COLLECTION
                CORPUS -> CORPUS_COMPONENT_COLLECTION
                KEYBOARDS -> KEYBOARDS_COMPONENT_COLLECTION
                INTERNETS -> INTERNETS_COMPONENT_COLLECTION
            }
            firestore.collection(component).get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getIncome(typeIncome: Income): List<ShopModel> {
        return try {
            val income = when (typeIncome) {
                SELLING -> SELLING_INCOME_COLLECTION
                PROJECTS -> PROJECTS_INCOME_COLLECTION
            }
            firestore.collection(income).get().await().toObjects(ShopModel::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}