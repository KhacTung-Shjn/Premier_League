package com.sun.premierleague.data.model

import android.os.Parcelable
import com.example.premier_league.data.model.Substitutions
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    var goalScorers: List<Goalscorer>,
    var cards: List<Card>,
    var substitutions: Substitutions
) : Parcelable
