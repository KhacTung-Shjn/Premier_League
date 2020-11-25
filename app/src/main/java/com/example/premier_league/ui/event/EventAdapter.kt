package com.example.premier_league.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.premier_league.R
import com.example.premier_league.base.BaseAdapter
import com.example.premier_league.base.BaseViewHolder
import com.sun.premierleague.data.model.DetailEvent

class EventAdapter : BaseAdapter<DetailEvent>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DetailEvent> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_event, parent, false)
        return EventViewHolder(view)
    }
}
