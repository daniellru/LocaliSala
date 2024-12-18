package com.example.localisala.repository


import com.example.localisala.model.Course
import com.example.localisala.model.Period
import com.example.localisala.model.Subject
import com.example.localisala.model.University
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MainRepository() {

    val db = FirebaseFirestore.getInstance()

    suspend fun getUniversities(): List<University> {
        return try {
            val snapshot = db.collection("universidades").get().await()
            snapshot.documents.map { document->
                University(
                    id = document.id,
                    name = document.getString("name") ?: ""
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getCourses(documentId: String): List<Course> {
       return try{
           val snapshot = db.collection("universidades")
               .document(documentId)
               .collection("courses")
               .get()
               .await()
           snapshot.documents.map { document ->
               Course(
                   id = document.id,
                   name = document.getString("name") ?: ""
               )
           }
       }catch (e: Exception){
           e.printStackTrace()
           emptyList()
       }
    }

    suspend fun getPeriod(universityId: String, courseId: String): List<Period>{
        return try{
            val snapshot = db.collection("universidades")
                .document(universityId)
                .collection("courses")
                .document(courseId)
                .collection("periodos")
                .get()
                .await()
            snapshot.documents.map { document ->
                Period(
                    id = document.id,
                    name = document.getString("name") ?:""
                )
            }
        }catch (e: Exception){
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getSubject(universityId: String, courseId: String, periodId: String): List<Subject>{
        return try {
            val snapshot = db.collection("universidades")
                .document(universityId)
                .collection("courses")
                .document(courseId)
                .collection("periodos")
                .document(periodId)
                .collection("subjects")
                .get()
                .await()
            snapshot.documents.map { document ->
                Subject(
                    subjectName = document.getString("subjectName") ?: "",
                    professor = document.getString("professor") ?: "",
                    classroom = document.getDouble("sala")?.toInt() ?: 0,
                    block = document.getDouble("bloco")?.toInt() ?: 0
                )
            }
        }catch (e: Exception){
            e.printStackTrace()
            emptyList()
        }
    }

}

