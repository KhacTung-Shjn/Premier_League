package com.example.premier_league.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.JobIntentService
import androidx.core.app.NotificationCompat
import com.example.premier_league.R
import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.data.source.remote.OnDataLoadedCallback
import com.example.premier_league.ui.main.MainActivity
import com.example.premier_league.utils.AlarmConst
import com.example.premier_league.utils.RepositoryFactory
import com.example.premier_league.utils.showToast

class AlarmService : JobIntentService() {

    private lateinit var mNotifyManager: NotificationManager

    override fun onHandleWork(intent: Intent) {
        val idMatch = intent.getStringExtra(AlarmConst.EXTRA_MATCH_ID)
        val repository = RepositoryFactory.getRepository(this)
        repository.getFootballNotifications(
            object : OnDataLoadedCallback<List<MatchNotification>> {
                override fun onSuccess(data: List<MatchNotification>) {
                        showNotification(data[data.size - 1])
                }

                override fun onFailure(exception: Exception?) {
                    exception?.let {
                        this@AlarmService.showToast(it.message.toString())
                    }
                }
            })
    }

    private fun showNotification(matchNotification: MatchNotification) {
        val notifyBuilder = getNotificationBuilder(this, matchNotification)
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())

        val repository = RepositoryFactory.getRepository(this)
        repository.deleteFootballNotification(matchNotification.id,
            object : OnDataLoadedCallback<Boolean> {
                override fun onSuccess(data: Boolean) {}

                override fun onFailure(exception: java.lang.Exception?) {
                    exception?.let {
                        this@AlarmService.showToast(it.message.toString())
                    }
                }
            })
    }

    private fun createNotificationChannel(context: Context) {
        mNotifyManager =
            context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                context.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun getNotificationBuilder(
        context: Context?,
        matchNotification: MatchNotification
    ): NotificationCompat.Builder {
        createNotificationChannel(this)
        val notificationIntent = MainActivity.getIntentFromNotification(this)
        val notificationPendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        return NotificationCompat.Builder(context!!, PRIMARY_CHANNEL_ID).apply {
            setContentTitle(context.getString(R.string.app_name))
            setContentText((matchNotification.homeTeamName + "-" + matchNotification.awayTeamName + " " + matchNotification.time))
            setSmallIcon(R.drawable.ic_ring_uncheck)
            setContentIntent(notificationPendingIntent)
            setAutoCancel(true)
            priority = NotificationCompat.PRIORITY_HIGH
        }
    }

    companion object {
        private const val JOB_ID = 98
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        const val ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION"
        private const val NOTIFICATION_ID = 99
        private const val CHANNEL_ID = "1"
        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, AlarmService::class.java, JOB_ID, work)
        }
    }
}
