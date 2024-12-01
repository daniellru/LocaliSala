package com.example.localisala.model

import com.google.firebase.firestore.GeoPoint

data class Subject(
    val subjectName: String = "",
    val professor: String = "",
    val block: Int = 0,
    val classroom: Int = 0,
    var isExpandable: Boolean = false
)
