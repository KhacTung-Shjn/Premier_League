package com.example.premier_league.ui.lineup

import android.view.View
import com.example.premier_league.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_recyclerview_line_up.view.*

class LineUpViewHolder(private val itemView: View) : BaseViewHolder<String>(itemView) {
    override fun onBind(item: String) {
        itemView.textPlayer.text = item
    }
}
