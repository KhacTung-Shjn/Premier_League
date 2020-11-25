package com.sun.premierleague.data.model

import android.os.Parcelable
import com.example.premier_league.data.model.Coach
import com.example.premier_league.data.model.StartingLineup
import com.example.premier_league.data.model.Substitute
import com.example.premier_league.utils.LineupConst.COACH
import com.example.premier_league.utils.LineupConst.MISSING_PLAYERS
import com.example.premier_league.utils.LineupConst.STARTING_LINEUPS
import com.example.premier_league.utils.LineupConst.SUBSTITUTES
import com.example.premier_league.utils.map
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Home(
    val coach: List<Coach>,
    val missingPlayers: List<MissingPlayer>,
    val startingLineups: List<StartingLineup>,
    val substitutes: List<Substitute>
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getJSONArray(COACH).map(::Coach),
        jsonObject.getJSONArray(MISSING_PLAYERS).map(::MissingPlayer),
        jsonObject.getJSONArray(STARTING_LINEUPS).map(::StartingLineup),
        jsonObject.getJSONArray(SUBSTITUTES).map(::Substitute)
    )
}
