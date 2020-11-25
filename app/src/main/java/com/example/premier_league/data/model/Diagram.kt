package com.sun.premierleague.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Diagram(
    val numberClothes: String,
    val namePlayer: String
) : Parcelable
