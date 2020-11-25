package com.example.premier_league.ui.team

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.premier_league.R
import com.example.premier_league.base.BaseAdapter
import com.example.premier_league.base.BaseViewHolder
import com.example.premier_league.ui.team.TeamViewHolder
import com.sun.premierleague.data.model.Player

class TeamAdapter : BaseAdapter<Player>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Player> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_player, parent, false)
        return TeamViewHolder(view)
    }
}
