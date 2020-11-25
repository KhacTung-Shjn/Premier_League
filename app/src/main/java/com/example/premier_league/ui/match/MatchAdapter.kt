package com.example.premier_league.ui.match

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.premier_league.R
import com.example.premier_league.base.BaseAdapter
import com.example.premier_league.base.BaseViewHolder
import com.sun.premierleague.data.model.MatchItem
import com.sun.premierleague.data.model.MatchNotification

class MatchAdapter : BaseAdapter<MatchItem>() {

    private var notifications = mutableListOf<MatchNotification>()
    var onItemClick: (MatchItem) -> Unit = { _ -> }
    var onRingClick: (MatchNotification, Boolean) -> Unit = { _, _ -> }

    fun replaceNotifications(notifications: List<MatchNotification>?) {
        notifications?.let {
            this.notifications.clear()
            this.notifications.addAll(notifications)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MatchItem> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_match, parent, false)
        return MatchViewHolder(view, notifications, onItemClick, onRingClick)
    }
}
