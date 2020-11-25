package com.example.premier_league.ui.stats

import android.view.View
import com.example.premier_league.R
import com.example.premier_league.base.BaseViewHolder
import com.example.premier_league.data.model.StatItem
import com.example.premier_league.utils.loadImage
import kotlinx.android.synthetic.main.item_recyclerview_stats.view.*

class StatsViewHolder(
    private val itemView: View,
    onItemClick: (StatItem) -> Unit
) : BaseViewHolder<StatItem>(itemView) {

    private var itemData: StatItem? = null

    init {
        itemView.setOnClickListener {
            itemData?.let {
                onItemClick(it)
            }
        }
    }

    override fun onBind(item: StatItem) {
        itemData = item
        itemView.run {
            textStt.text = item.overallLeaguePosition
            imageTeam.loadImage(item.teamBadge)
            textNameTeam.text = item.teamName
            textTitleNumberMatch.text = item.overallLeaguePayed
            textTitleWin.text = item.overallLeagueW
            textTitleDraw.text = item.overallLeagueD
            textTitleLose.text = item.overallLeagueL
            textTitleGoalDiff.text = context.getString(
                R.string.text_goal_difference,
                item.overallLeagueGf,
                item.overallLeagueGa
            )
            textTitlePoint.text = item.overallLeaguePts
        }
    }
}
