package com.example.premier_league.utils

import android.content.Context
import com.example.premier_league.data.source.repository.FootballRepository
import com.example.premier_league.data.source.local.NotificationMatchLocalDataSource
import com.example.premier_league.data.source.local.dao.NotificationMatchDAOImpl
import com.example.premier_league.data.source.local.database.AppDatabase
import com.example.premier_league.data.source.remote.RemoteDataSource

object RepositoryFactory {
    fun getRepository(context: Context): FootballRepository {
        val appDatabase = AppDatabase.getInstance(context)
        val notificationMatchDAOImpl = NotificationMatchDAOImpl.getInstance(appDatabase)
        val local = NotificationMatchLocalDataSource.getInstance(notificationMatchDAOImpl)
        val remote = RemoteDataSource()
        return FootballRepository.getInstance(local, remote)
    }
}
