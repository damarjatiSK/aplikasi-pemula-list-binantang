package com.example.appanimal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Animal(
    val name: String,
    val description: String,
    val photo: Int,
    val category: String,
    val food: String,
    val heavy: String,
    val areaDistribution: String
) : Parcelable
