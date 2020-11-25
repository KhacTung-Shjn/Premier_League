package com.sun.premierleague.data.model

import org.json.JSONObject

data class Coache(
    val coachName: String,
    val coachCountry: String,
    val coachAge: String
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(COACH_NAME),
        jsonObject.getString(COACH_COUNTRY),
        jsonObject.getString(COACH_AGE)
    )

    companion object {
        const val COACH_AGE = "coach_age"
        const val COACH_COUNTRY = "coach_country"
        const val COACH_NAME = "coach_name"
    }
}
