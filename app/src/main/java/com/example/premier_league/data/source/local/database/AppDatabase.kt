package com.example.premier_league.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sun.premierleague.data.model.MatchNotification

class AppDatabase private constructor(context: Context, dbName: String, version: Int) :
    SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_FOOTBALL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_TASK)
        onCreate(db)
    }

    companion object {
        private const val NAME_DB = "football.db"
        private const val VERSION = 1
        private const val CREATE_TABLE_FOOTBALL =
            "CREATE TABLE " + MatchNotification.TABLE_NAME + " ( " +
                    MatchNotification.MATCH_ID + " TEXT NOT NULL, " +
                    MatchNotification.MATCH_DATE + " TEXT, " +
                    MatchNotification.MATCH_TIME + " TEXT, " +
                    MatchNotification.MATCH_HOME_TEAM_ID + " TEXT, " +
                    MatchNotification.MATCH_HOME_TEAM_NAME + " TEXT, " +
                    MatchNotification.MATCH_AWAY_TEAM_ID + " TEXT, " +
                    MatchNotification.MATCH_AWAY_TEAM_NAME + " TEXT, " +
                    " PRIMARY KEY(" + MatchNotification.MATCH_ID + "));"

        private const val DROP_TABLE_TASK =
            "DROP TABLE IF EXISTS " + MatchNotification.TABLE_NAME

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: AppDatabase(
                    context,
                    NAME_DB,
                    VERSION
                ).also { instance = it }
            }
    }
}
