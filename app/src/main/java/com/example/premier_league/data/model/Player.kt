package com.sun.premierleague.data.model

import org.json.JSONObject

data class Player(
    val playerKey: Long,
    val playerName: String,
    val playerNumber: String,
    val playerCountry: String,
    val playerType: String,
    val playerAge: String,
    val playerMatchPlayed: String,
    val playerGoals: String,
    val playerYellowCards: String,
    val playerRedCards: String
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getLong(PLAYER_KEY),
        jsonObject.getString(PLAYER_NAME),
        jsonObject.getString(PLAYER_NUMBER),
        jsonObject.getString(PLAYER_COUNTRY),
        jsonObject.getString(PLAYER_TYPE),
        jsonObject.getString(PLAYER_AGE),
        jsonObject.getString(PLAYER_MATCH_PLAYED),
        jsonObject.getString(PLAYER_GOALS),
        jsonObject.getString(PLAYER_YELLOW_CARD),
        jsonObject.getString(PLAYER_RED_CARD)
    )

    companion object {
        const val PLAYER_AGE = "player_age"
        const val PLAYER_NUMBER = "player_number"
        const val PLAYER_COUNTRY = "player_country"
        const val PLAYER_GOALS = "player_goals"
        const val PLAYER_KEY = "player_key"
        const val PLAYER_MATCH_PLAYED = "player_match_played"
        const val PLAYER_NAME = "player_name"
        const val PLAYER_RED_CARD = "player_red_cards"
        const val PLAYER_TYPE = "player_type"
        const val PLAYER_YELLOW_CARD = "player_yellow_cards"
    }
}
