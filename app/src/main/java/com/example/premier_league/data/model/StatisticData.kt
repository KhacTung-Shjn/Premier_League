package com.example.premier_league.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatisticData(
    val matchHomeTeamName: String,
    val matchAwayTeamName: String,
    val teamHomeBadge: String,
    val teamAwayBadge: String,
    val statistic: List<Statistic>
) : Parcelable
