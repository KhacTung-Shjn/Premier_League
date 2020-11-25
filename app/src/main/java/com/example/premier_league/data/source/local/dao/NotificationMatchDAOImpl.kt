package com.example.premier_league.data.source.local.dao

import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.data.source.local.database.AppDatabase

class NotificationMatchDAOImpl private constructor(appDatabase: AppDatabase) :
    NotificationMatchDAO {

    private val writableDatabase = appDatabase.writableDatabase
    private val readableDatabase = appDatabase.readableDatabase

    override fun getFootballNotifications(): List<MatchNotification> {
        val listNotificationMatches = mutableListOf<MatchNotification>()
        val mCursor =
            readableDatabase.query(MatchNotification.TABLE_NAME, null, null, null, null, null, null)
        mCursor.moveToFirst()
        while (!mCursor.isAfterLast) {
            listNotificationMatches.add(MatchNotification(mCursor))
            mCursor.moveToNext()
        }
        mCursor.close()
        return listNotificationMatches
    }

    override fun getItemFootballNotification(idMatch: String): MatchNotification {
        val mCursor = readableDatabase.query(
            false, MatchNotification.TABLE_NAME, null, MatchNotification.MATCH_ID + "= ?",
            arrayOf(idMatch), null, null, null, null
        )
        return MatchNotification(mCursor)
    }

    override fun addFootballNotification(matchNotification: MatchNotification): Boolean {
        val result = writableDatabase.insert(
            MatchNotification.TABLE_NAME,
            null,
            matchNotification.getContentValues()
        )
        return result > 0
    }

    override fun deleteFootballNotification(idMatch: String): Boolean {
        return writableDatabase.delete(
            MatchNotification.TABLE_NAME,
            MatchNotification.MATCH_ID + "= ?",
            arrayOf(idMatch)
        ) > 0
    }

    companion object {
        private var instance: NotificationMatchDAOImpl? = null

        fun getInstance(appDatabase: AppDatabase):
                NotificationMatchDAOImpl =
            instance ?: synchronized(this) {
                instance ?: NotificationMatchDAOImpl(appDatabase).also {
                    instance = it
                }
            }
    }
}
