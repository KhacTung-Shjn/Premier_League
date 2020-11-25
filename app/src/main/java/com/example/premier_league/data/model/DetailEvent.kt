package com.sun.premierleague.data.model

import android.os.Parcelable
import com.example.premier_league.data.model.SubtitutionItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailEvent(
    var time: String,
    var namePlayer: String,
    var isHome: Boolean,
    var card: String,
    var score: String,
    var substitution: String
) : Parcelable {

    init {
        time = calSumTime(time)
    }

    constructor(home: SubtitutionItem) : this(
        time = home.time,
        namePlayer = "",
        isHome = true,
        card = "",
        score = "",
        substitution = home.substitution
    )

    constructor(card: Card) : this(
        time = card.time,
        namePlayer = card.homeFault,
        isHome = true,
        card = card.card,
        score = "",
        substitution = ""
    )

    constructor(goalscorers: Goalscorer) : this(
        time = goalscorers.time,
        namePlayer = goalscorers.awayScorer,
        isHome = true,
        card = "",
        score = goalscorers.score,
        substitution = ""
    )

    private fun calSumTime(time: String): String {
        if (time.contains("+")) {
            val unitTime = time.split("+")
            return "${unitTime[0].toInt() + unitTime[1].toInt()}"
        }
        return time
    }
}
