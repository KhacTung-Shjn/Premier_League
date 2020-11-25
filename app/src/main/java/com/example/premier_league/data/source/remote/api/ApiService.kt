package com.example.premier_league.data.source.remote.api

import android.net.Uri
import com.example.premier_league.BuildConfig
import com.example.premier_league.data.model.Time

object ApiService {

    fun queryMatch(time: Time) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_FOOTBALL)
        .appendPath("")
        .appendQueryParameter(ApiConstants.ACTION, ApiConstants.GET_EVENT)
        .appendQueryParameter(ApiConstants.FROM, time.startTime)
        .appendQueryParameter(ApiConstants.TO, time.endTime)
        .appendQueryParameter(ApiConstants.LEAGUE_ID, ApiConstants.ID_PREMIER_LEAGUE)
        .appendQueryParameter(ApiConstants.TIMEZONE, ApiConstants.TIMEZONE_VN)
        .appendQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY)
        .toString()

    fun queryStats() = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_FOOTBALL)
        .appendPath("")
        .appendQueryParameter(ApiConstants.ACTION, ApiConstants.GET_STATS)
        .appendQueryParameter(ApiConstants.LEAGUE_ID, ApiConstants.ID_PREMIER_LEAGUE)
        .appendQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY)
        .toString()

    fun queryTeam(idTeam: String) = Uri.Builder().scheme(ApiConstants.SCHEME_HTTPS)
        .authority(ApiConstants.AUTHORITY_API_FOOTBALL)
        .appendPath("")
        .appendQueryParameter(ApiConstants.ACTION, ApiConstants.GET_TEAM)
        .appendQueryParameter(ApiConstants.LEAGUE_ID, ApiConstants.ID_PREMIER_LEAGUE)
        .appendQueryParameter(ApiConstants.TEAM_ID, idTeam)
        .appendQueryParameter(ApiConstants.API_KEY, BuildConfig.API_KEY)
        .toString()
}
