package com.example.localisala.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Period(
    val id: String = "",
    val name: String = ""
) : Parcelable
