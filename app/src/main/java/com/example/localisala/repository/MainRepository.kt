package com.example.localisala.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.localisala.model.Period
import com.example.localisala.model.University
import com.google.android.gms.tasks.Tasks
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class MainRepository {

    val db = FirebaseFirestore.getInstance()

    suspend fun getUniversities(): List<University> {
        return try {
            val snapshot = db.collection("universidades").get().await()
            snapshot.documents.mapNotNull { it.toObject(University::class.java) }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

