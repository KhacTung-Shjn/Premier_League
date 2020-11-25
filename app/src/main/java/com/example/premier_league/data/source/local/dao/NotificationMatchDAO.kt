package com.example.premier_league.data.source.local.dao

import com.sun.premierleague.data.model.MatchNotification

interface NotificationMatchDAO {
    fun getFootballNotifications(): List<MatchNotification>
    fun getItemFootballNotification(idMatch: String): MatchNotification
    fun addFootballNotification(matchNotification: MatchNotification): Boolean
    fun deleteFootballNotification(idMatch: String): Boolean
}
