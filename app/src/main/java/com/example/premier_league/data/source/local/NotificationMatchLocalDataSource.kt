package com.example.premier_league.data.source.local

import com.sun.premierleague.data.model.MatchNotification
import com.sun.premierleague.data.source.FootballDataSource
import com.example.premier_league.data.source.local.dao.NotificationMatchDAO
import com.example.premier_league.data.source.remote.OnDataLoadedCallback

class NotificationMatchLocalDataSource(private val notificationMatchDAO: NotificationMatchDAO) :
    FootballDataSource.Local {

    override fun getFootballNotifications(callback: OnDataLoadedCallback<List<MatchNotification>>) {
        LocalAsyncTask<Unit, List<MatchNotification>>(callback) {
            notificationMatchDAO.getFootballNotifications()
        }.execute(Unit)
    }

    override fun getItemFootballNotification(
        idMatch: String,
        callback: OnDataLoadedCallback<MatchNotification>
    ) {
        LocalAsyncTask<String, MatchNotification>(callback) {
            notificationMatchDAO.getItemFootballNotification(idMatch)
        }.execute(idMatch)
    }

    override fun addFootballNotification(
        matchNotification: MatchNotification,
        callback: OnDataLoadedCallback<Boolean>
    ) {
        LocalAsyncTask<MatchNotification, Boolean>(callback) {
            notificationMatchDAO.addFootballNotification(matchNotification)
        }.execute(matchNotification)
    }

    override fun deleteFootballNotification(
        idMatch: String,
        callback: OnDataLoadedCallback<Boolean>
    ) {
        LocalAsyncTask<String, Boolean>(callback) {
            notificationMatchDAO.deleteFootballNotification(idMatch)
        }.execute(idMatch)
    }

    companion object {
        private var instance: NotificationMatchLocalDataSource? = null

        fun getInstance(notificationMatchDAO: NotificationMatchDAO): NotificationMatchLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: NotificationMatchLocalDataSource(notificationMatchDAO).also {
                    instance = it
                }
            }
    }
}
