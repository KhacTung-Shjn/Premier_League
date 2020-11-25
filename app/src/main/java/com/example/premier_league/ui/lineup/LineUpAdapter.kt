package com.example.premier_league.ui.lineup

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.premier_league.R
import com.example.premier_league.base.BaseAdapter
import com.example.premier_league.base.BaseViewHolder

class LineUpAdapter : BaseAdapter<String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<String> {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview_line_up, parent, false)
        return LineUpViewHolder(view)
    }
}
