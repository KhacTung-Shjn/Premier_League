package com.example.premier_league.data.model

import com.sun.premierleague.data.model.Coache
import com.sun.premierleague.data.model.Player
import com.example.premier_league.utils.map
import org.json.JSONObject

data class TeamItem(
    val teamKey: String,
    val teamName: String,
    val teamBadge: String,
    val players: List<Player>,
    val coaches: List<Coache>
){
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(TEAM_KEY),
        jsonObject.getString(TEAM_NAME),
        jsonObject.getString(TEAM_BADGE),
        jsonObject.getJSONArray(PLAYERS).map(::Player),
        jsonObject.getJSONArray(COACHES).map(::Coache)
    )
    companion object{
        const val TEAM_KEY = "team_key"
        const val TEAM_NAME = "team_name"
        const val TEAM_BADGE = "team_badge"
        const val PLAYERS = "players"
        const val COACHES = "coaches"
    }
}
