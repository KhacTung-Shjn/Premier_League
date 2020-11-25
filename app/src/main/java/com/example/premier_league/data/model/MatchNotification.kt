package com.sun.premierleague.data.model

import android.content.ContentValues
import android.database.Cursor

data class MatchNotification(
    val id: String,
    val date: String,
    val time: String,
    val homeTeamId: String,
    val homeTeamName: String,
    val awayTeamId: String,
    val awayTeamName: String
) {

    constructor(cursor: Cursor) : this(
        id = cursor.getString(cursor.getColumnIndex(MATCH_ID)),
        date = cursor.getString(cursor.getColumnIndex(MATCH_DATE)),
        time = cursor.getString(cursor.getColumnIndex(MATCH_TIME)),
        homeTeamId =
        cursor.getString(cursor.getColumnIndex(MATCH_HOME_TEAM_ID)),
        homeTeamName =
        cursor.getString(cursor.getColumnIndex(MATCH_HOME_TEAM_NAME)),
        awayTeamId =
        cursor.getString(cursor.getColumnIndex(MATCH_AWAY_TEAM_ID)),
        awayTeamName =
        cursor.getString(cursor.getColumnIndex(MATCH_AWAY_TEAM_NAME))
    )

    fun getContentValues() = ContentValues().apply {
        put(MATCH_ID, id)
        put(MATCH_DATE, date)
        put(MATCH_TIME, time)
        put(MATCH_HOME_TEAM_ID, homeTeamId)
        put(MATCH_HOME_TEAM_NAME, homeTeamName)
        put(MATCH_AWAY_TEAM_ID, awayTeamId)
        put(MATCH_AWAY_TEAM_NAME, awayTeamName)
    }

    companion object {
        const val MATCH_ID = "match_id"
        const val MATCH_DATE = "match_date"
        const val MATCH_TIME = "match_time"
        const val MATCH_HOME_TEAM_ID = "match_home_team_id"
        const val MATCH_HOME_TEAM_NAME = "match_home_team_name"
        const val MATCH_AWAY_TEAM_NAME = "match_away_team_name"
        const val MATCH_AWAY_TEAM_ID = "match_away_team_id"
        const val TABLE_NAME = "football"
    }
}
