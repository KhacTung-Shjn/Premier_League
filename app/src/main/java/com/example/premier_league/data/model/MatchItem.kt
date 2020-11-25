package com.sun.premierleague.data.model

import android.os.Parcelable
import com.example.premier_league.data.model.Statistic
import com.example.premier_league.data.model.Substitutions
import com.example.premier_league.utils.map
import kotlinx.android.parcel.Parcelize
import org.json.JSONObject

@Parcelize
data class MatchItem(
    val matchId: String,
    val matchDate: String,
    val matchStatus: String,
    val matchTime: String,
    val matchHomeTeamId: String,
    val matchHomeTeamName: String,
    val matchHomeTeamScore: String,
    val matchAwayTeamName: String,
    val matchAwayTeamId: String,
    val matchAwayTeamScore: String,
    val matchHomeTeamSystem: String,
    val matchAwayTeamSystem: String,
    val matchLive: String,
    val matchRound: String,
    val matchStadium: String,
    val matchReferee: String,
    val teamHomeBadge: String,
    val teamAwayBadge: String,
    val goalscorer: List<Goalscorer>,
    val cards: List<Card>,
    val substitutions: Substitutions,
    val lineup: Lineup,
    val statistics: List<Statistic>,
    var isNotification: Boolean = false
) : Parcelable {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(MATCH_ID),
        jsonObject.getString(MATCH_DATE),
        jsonObject.getString(MATCH_STATUS),
        jsonObject.getString(MATCH_TIME),
        jsonObject.getString(MATCH_HOMETEAM_ID),
        jsonObject.getString(MATCH_HOMETEAM_NAME),
        jsonObject.getString(MATCH_HOMETEAM_SCORE),
        jsonObject.getString(MATCH_AWAYTEAM_NAME),
        jsonObject.getString(MATCH_AWAYTEAM_ID),
        jsonObject.getString(MATCH_AWAYTEAM_SCORE),
        jsonObject.getString(MATCH_HOMETEAM_SYSTEM),
        jsonObject.getString(MATCH_AWAYTEAM_SYSTEM),
        jsonObject.getString(MATCH_LIVE),
        jsonObject.getString(MATCH_ROUND),
        jsonObject.getString(MATCH_STADIUM),
        jsonObject.getString(MATCH_REFEREE),
        jsonObject.getString(TEAM_HOME_BADGE),
        jsonObject.getString(TEAM_AWAY_BADGE),
        jsonObject.getJSONArray(GOAL_SCORER).map(::Goalscorer),
        jsonObject.getJSONArray(CARDS).map(::Card),
        jsonObject.getJSONObject(SUBSTITUTIONS).let(::Substitutions),
        jsonObject.getJSONObject(LINEUP).let(::Lineup),
        jsonObject.getJSONArray(STATISTICS).map(::Statistic)
    )

    companion object {
        const val MATCH_ID = "match_id"
        const val MATCH_DATE = "match_date"
        const val MATCH_STATUS = "match_status"
        const val MATCH_TIME = "match_time"
        const val MATCH_HOMETEAM_ID = "match_hometeam_id"
        const val MATCH_HOMETEAM_NAME = "match_hometeam_name"
        const val MATCH_HOMETEAM_SCORE = "match_hometeam_score"
        const val MATCH_AWAYTEAM_NAME = "match_awayteam_name"
        const val MATCH_AWAYTEAM_ID = "match_awayteam_id"
        const val MATCH_AWAYTEAM_SCORE = "match_awayteam_score"
        const val MATCH_HOMETEAM_SYSTEM = "match_hometeam_system"
        const val MATCH_AWAYTEAM_SYSTEM = "match_awayteam_system"
        const val MATCH_LIVE = "match_live"
        const val MATCH_ROUND = "match_round"
        const val MATCH_STADIUM = "match_stadium"
        const val MATCH_REFEREE = "match_referee"
        const val TEAM_HOME_BADGE = "team_home_badge"
        const val TEAM_AWAY_BADGE = "team_away_badge"
        const val GOAL_SCORER = "goalscorer"
        const val CARDS = "cards"
        const val SUBSTITUTIONS = "substitutions"
        const val LINEUP = "lineup"
        const val STATISTICS = "statistics"
    }
}
