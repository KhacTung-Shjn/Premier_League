package com.example.premier_league.data.source.remote

import com.sun.premierleague.data.model.MatchItem
import com.example.premier_league.data.model.StatItem
import com.example.premier_league.data.model.TeamItem
import com.example.premier_league.data.model.Time
import com.sun.premierleague.data.source.FootballDataSource
import com.example.premier_league.data.source.remote.api.ApiConstants
import com.example.premier_league.data.source.remote.api.ApiService
import com.example.premier_league.utils.map
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class RemoteDataSource : FootballDataSource.Remote {

    override fun getMatch(time: Time, callback: OnDataLoadedCallback<List<MatchItem>>) {
        RemoteAsyncTask(callback) {
            getMatch(time)
        }.execute(ApiService.queryMatch(time))
    }

    override fun getTeam(idTeam: String, callback: OnDataLoadedCallback<List<TeamItem>>) {
        RemoteAsyncTask(callback) {
            getTeam(idTeam)
        }.execute(ApiService.queryTeam(idTeam))
    }

    override fun getStat(callback: OnDataLoadedCallback<List<StatItem>>) {
        RemoteAsyncTask(callback) {
            getStat()
        }.execute(ApiService.queryStats())
    }

    private fun getMatch(time: Time): List<MatchItem> =
        JSONArray(makeNetworkCall(URL(ApiService.queryMatch(time)))).map(::MatchItem)

    private fun getTeam(idTeam: String): List<TeamItem> =
        JSONArray(makeNetworkCall(URL(ApiService.queryTeam(idTeam)))).map(::TeamItem)

    private fun getStat(): List<StatItem> =
        JSONArray(makeNetworkCall(URL(ApiService.queryStats()))).map(::StatItem)

    private fun makeNetworkCall(
        url: URL,
        method: String = ApiConstants.METHOD_GET
    ): String {
        var urlConnection: HttpURLConnection? = null
        return try {
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {
                doInput = true
                requestMethod = method
                connect()
            }
            val respondCode = urlConnection.responseCode
            if (respondCode == HttpURLConnection.HTTP_OK) {
                url.openStream().bufferedReader().use(BufferedReader::readText)
            } else throw IOException(urlConnection.responseMessage)
        } finally {
            urlConnection?.disconnect()
        }
    }
}
