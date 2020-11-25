package com.example.premier_league.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.premier_league.R
import com.example.premier_league.base.BaseAdapter
import com.example.premier_league.base.BaseViewHolder
import com.example.premier_league.data.model.StatItem

class StatsAdapter(
    var onItemClick: (StatItem) -> Unit = { _ -> }
) : BaseAdapter<StatItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<StatItem> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_stats, parent, false)
        return StatsViewHolder(view, onItemClick)
    }
}
