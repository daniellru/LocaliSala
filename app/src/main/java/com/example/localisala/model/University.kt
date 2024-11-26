package com.example.localisala.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
@Parcelize
data class University(
    val id: String = "",
    val name: String = ""
) : Parcelable
