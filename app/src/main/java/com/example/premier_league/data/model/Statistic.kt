package com.example.premier_league.data.model

import android.os.Parcelable
import com.example.premier_league.utils.LineupConst.AWAY
import com.example.premier_league.utils.LineupConst.HOME
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Statistic(
    val type: String,
    val home: String,
    val away: String
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(TYPE),
        jsonObject.getString(HOME),
        jsonObject.getString(AWAY)
    )

    companion object {
        const val TYPE = "type"
    }
}
