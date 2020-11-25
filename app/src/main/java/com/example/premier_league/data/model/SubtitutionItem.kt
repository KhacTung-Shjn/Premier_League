package com.example.premier_league.data.model

import android.os.Parcelable
import com.example.premier_league.utils.MatchConst.TIME
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class SubtitutionItem(
    val time: String,
    val substitution: String
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(TIME),
        jsonObject.getString(SUBSTITUTION)
    )

    companion object {
        const val SUBSTITUTION = "substitution"
    }
}
