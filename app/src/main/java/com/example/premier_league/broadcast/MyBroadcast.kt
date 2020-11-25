package com.example.premier_league.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.premier_league.service.AlarmService

class MyBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, work: Intent) {
        AlarmService.enqueueWork(context, work)
    }
}
