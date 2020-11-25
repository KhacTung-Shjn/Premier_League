package com.example.premier_league.ui.statistic

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.premier_league.R
import com.example.premier_league.base.BaseAdapter
import com.example.premier_league.base.BaseViewHolder
import com.example.premier_league.data.model.Statistic
import com.example.premier_league.ui.statistic.StatisticViewHolder

class StatisticAdapter : BaseAdapter<Statistic>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Statistic> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_statistic, parent, false)
        return StatisticViewHolder(view)
    }
}
