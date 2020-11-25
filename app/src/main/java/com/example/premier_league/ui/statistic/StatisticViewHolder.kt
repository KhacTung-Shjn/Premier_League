package com.example.premier_league.ui.statistic

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import com.example.premier_league.base.BaseViewHolder
import com.example.premier_league.data.model.Statistic
import kotlinx.android.synthetic.main.item_recyclerview_statistic.view.*

class StatisticViewHolder(private val itemView: View) : BaseViewHolder<Statistic>(itemView) {

    override fun onBind(itemStatistic: Statistic) {
        itemView.run {
            textTeamParameter.text = itemStatistic.type
            textHomeTeamParameter.text = itemStatistic.home
            textAwayTeamParameter.text = itemStatistic.away
            setProgressBar(this, itemStatistic.home, itemStatistic.away)
        }
    }

    private fun setProgressBar(view: View, home: String, away: String) {
        val valueHome = when {
            home.contains("%") -> {
                home.split("%")[0].toInt()
            }
            else -> home.toInt()
        }
        val valueAway = when {
            away.contains("%") -> {
                away.split("%")[0].toInt()
            }
            else -> away.toInt()
        }
        val valueTotal = valueHome + valueAway
        view.run {
            progressBarHome.max = valueTotal
            progressBarAway.max = valueTotal
            progressBarHome.progress = valueHome
            progressBarAway.progress = valueAway
            if (valueHome > valueAway) {
                progressBarHome.progressTintList = getColor(COLOR_GREEN_MALACHITE)
                progressBarHome.backgroundTintList = getColor(COLOR_ATHENS_GRAY)
                progressBarAway.progressTintList = getColor(COLOR_AMETHYST_SMOKE)
                progressBarAway.backgroundTintList = getColor(COLOR_ATHENS_GRAY)
            } else {
                progressBarHome.progressTintList = getColor(COLOR_AMETHYST_SMOKE)
                progressBarHome.backgroundTintList = getColor(COLOR_ATHENS_GRAY)
                progressBarAway.progressTintList = getColor(COLOR_GREEN_MALACHITE)
                progressBarHome.backgroundTintList = getColor(COLOR_ATHENS_GRAY)
            }
        }
    }

    private fun getColor(color: String) = ColorStateList.valueOf(Color.parseColor(color))

    companion object {
        const val COLOR_GREEN_MALACHITE = "#16b13a"
        const val COLOR_ATHENS_GRAY = "#EFF1F4"
        const val COLOR_AMETHYST_SMOKE = "#9B94A7"
    }
}
