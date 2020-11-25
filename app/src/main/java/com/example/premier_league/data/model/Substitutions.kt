package com.example.premier_league.data.model

import android.os.Parcelable
import com.example.premier_league.utils.LineupConst.AWAY
import com.example.premier_league.utils.LineupConst.HOME
import com.example.premier_league.utils.map
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class Substitutions(
    val home: List<SubtitutionItem>,
    val away: List<SubtitutionItem>
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getJSONArray(HOME).map(::SubtitutionItem),
        jsonObject.getJSONArray(AWAY).map(::SubtitutionItem)
    )
}
