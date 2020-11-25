package com.example.premier_league.data.model

import org.json.JSONObject

class StatItem(
    val teamId: String,
    val teamName: String,
    val overallLeaguePosition: String,
    val overallLeaguePayed: String,
    val overallLeagueW: String,
    val overallLeagueD: String,
    val overallLeagueL: String,
    val overallLeagueGf: String,
    val overallLeagueGa: String,
    val overallLeaguePts: String,
    val teamBadge: String,
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(TEAM_ID),
        jsonObject.getString(TEAM_NAME),
        jsonObject.getString(OVERALL_LEAGUE_POSITION),
        jsonObject.getString(OVERALL_LEAGUE_PAYED),
        jsonObject.getString(OVERALL_LEAGUE_W),
        jsonObject.getString(OVERALL_LEAGUE_D),
        jsonObject.getString(OVERALL_LEAGUE_L),
        jsonObject.getString(OVERALL_LEAGUE_GF),
        jsonObject.getString(OVERALL_LEAGUE_GA),
        jsonObject.getString(OVERALL_LEAGUE_PTS),
        jsonObject.getString(TEAM_BADGE)
    )

    companion object {
        const val TEAM_ID = "team_id"
        const val TEAM_NAME = "team_name"
        const val OVERALL_LEAGUE_POSITION = "overall_league_position"
        const val OVERALL_LEAGUE_PAYED = "overall_league_payed"
        const val OVERALL_LEAGUE_W = "overall_league_W"
        const val OVERALL_LEAGUE_D = "overall_league_D"
        const val OVERALL_LEAGUE_L = "overall_league_L"
        const val OVERALL_LEAGUE_GF = "overall_league_GF"
        const val OVERALL_LEAGUE_GA = "overall_league_GA"
        const val OVERALL_LEAGUE_PTS = "overall_league_PTS"
        const val TEAM_BADGE = "team_badge"
    }
}
