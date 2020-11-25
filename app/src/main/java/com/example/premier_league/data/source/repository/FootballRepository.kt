package com.example.premier_league.data.source.repository

import com.example.premier_league.data.model.StatItem
import com.example.premier_league.data.model.TeamItem
import com.example.premier_league.data.model.Time
import com.sun.premierleague.data.model.*
import com.sun.premierleague.data.source.FootballDataSource
import com.example.premier_league.data.source.remote.OnDataLoadedCallback

class FootballRepository private constructor(
    private val local: FootballDataSource.Local,
    private val remote: FootballDataSource.Remote
) : FootballDataSource.Remote, FootballDataSource.Local {
    override fun getMatch(time: Time, callback: OnDataLoadedCallback<List<MatchItem>>) {
        remote.getMatch(time, callback)
    }

    override fun getTeam(idTeam: String, callback: OnDataLoadedCallback<List<TeamItem>>) {
        remote.getTeam(idTeam, callback)
    }

    override fun getStat(callback: OnDataLoadedCallback<List<StatItem>>) {
        remote.getStat(callback)
    }

    override fun getFootballNotifications(callback: OnDataLoadedCallback<List<MatchNotification>>) {
        local.getFootballNotifications(callback)
    }

    override fun getItemFootballNotification(
        idMatch: String,
        callback: OnDataLoadedCallback<MatchNotification>
    ) {
        local.getItemFootballNotification(idMatch, callback)
    }

    override fun addFootballNotification(
        matchNotification: MatchNotification,
        callback: OnDataLoadedCallback<Boolean>
    ) {
        local.addFootballNotification(matchNotification, callback)
    }

    override fun deleteFootballNotification(
        idMatch: String,
        callback: OnDataLoadedCallback<Boolean>
    ) {
        local.deleteFootballNotification(idMatch, callback)
    }

    companion object {
        private var instance: FootballRepository? = null

        fun getInstance(local: FootballDataSource.Local, remote: FootballDataSource.Remote) =
            instance ?: FootballRepository(local, remote).also { instance = it }
    }
}
