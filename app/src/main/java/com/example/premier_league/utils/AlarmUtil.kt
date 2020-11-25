package com.example.premier_league.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.premier_league.broadcast.MyBroadcast

object AlarmUtil {
    fun create(context: Context, idMatch: String, timeChoose: String) {
        val intent = Intent(context, MyBroadcast::class.java).apply {
            putExtra(AlarmConst.EXTRA_MATCH_ID, idMatch)
        }
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, 0)

        val timeSelected = TimeUtils.convertDateToMilSeconds(timeChoose)

//        alarmManager?.setExact(
//            AlarmManager.RTC_WAKEUP,
//            timeSelected,
//            pendingIntent
//        )
        alarmManager?.setExact(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 30000,
            pendingIntent
        )
    }

    fun cancel(context: Context) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
        val intent = Intent(context, MyBroadcast::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, 0)
        alarmManager?.cancel(pendingIntent)
    }
}
