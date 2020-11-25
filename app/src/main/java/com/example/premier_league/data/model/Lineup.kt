package com.sun.premierleague.data.model

import android.os.Parcelable
import com.example.premier_league.utils.LineupConst.AWAY
import com.example.premier_league.utils.LineupConst.HOME
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Lineup(
    val away: Away,
    val home: Home
) : Parcelable {

    var homeSystem: String = ""
    var awaySystem: String = ""
    var nameReferee: String = ""
    var nameStadium: String = ""

    constructor(jsonObject: JSONObject) : this(
        jsonObject.getJSONObject(AWAY).let(::Away),
        jsonObject.getJSONObject(HOME).let(::Home)
    )
}
