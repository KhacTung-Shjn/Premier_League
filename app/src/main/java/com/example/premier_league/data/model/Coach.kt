package com.example.premier_league.data.model

import android.os.Parcelable
import com.example.premier_league.utils.LineupConst.LINEUP_NUMBER
import com.example.premier_league.utils.LineupConst.LINEUP_PLAYER
import com.example.premier_league.utils.LineupConst.LINEUP_POSITION
import com.example.premier_league.utils.LineupConst.PLAYER_KEY
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Coach(
    val lineupNumber: String,
    val lineupPlayer: String,
    val lineupPosition: String,
    val playerKey: String
) :Parcelable{
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(LINEUP_NUMBER),
        jsonObject.getString(LINEUP_PLAYER),
        jsonObject.getString(LINEUP_POSITION),
        jsonObject.getString(PLAYER_KEY)
    )
}
