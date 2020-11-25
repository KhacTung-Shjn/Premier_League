package com.example.premier_league.ui.statistic

import com.example.premier_league.R
import com.example.premier_league.data.model.Statistic
import com.example.premier_league.data.model.StatisticData

class StatisticPresenter(
    private val view: StatisticContact.View
) : StatisticContact.Presenter {

    private var statistic: Statistic? = null

    override fun getStatisticInfo(statisticData: StatisticData?) {

        if (statisticData == null) {
            view.showError(R.string.err_load_statistic)
            return
        }

        statisticData?.let {
            val matchHomeTeamName: String = it.matchHomeTeamName
            val matchAwayTeamName: String = it.matchAwayTeamName
            val teamHomeBadge: String = it.teamHomeBadge
            val teamAwayBadge: String = it.teamAwayBadge
            var statistic: List<Statistic> = it.statistic

            view.showTeam(matchHomeTeamName, matchAwayTeamName, teamHomeBadge, teamAwayBadge)
            view.showStatistic(statistic)
        }
    }
}
