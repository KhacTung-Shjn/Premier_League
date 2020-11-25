package com.example.premier_league.data.model

import android.os.Parcelable
import com.example.premier_league.utils.LineupConst
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class StartingLineup(
    val lineupPlayer: String,
    val lineupNumber: String,
    val lineupPosition: String,
    val playerKey: String
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(LineupConst.LINEUP_PLAYER),
        jsonObject.getString(LineupConst.LINEUP_NUMBER),
        jsonObject.getString(LineupConst.LINEUP_POSITION),
        jsonObject.getString(LineupConst.PLAYER_KEY)
    )
}
