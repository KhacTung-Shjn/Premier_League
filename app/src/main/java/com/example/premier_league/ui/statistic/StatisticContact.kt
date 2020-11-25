package com.example.premier_league.ui.statistic

import com.example.premier_league.base.BasePresenter
import com.example.premier_league.base.BaseView
import com.example.premier_league.data.model.Statistic
import com.example.premier_league.data.model.StatisticData

interface StatisticContact {
    interface View : BaseView {
        fun showTeam(vararg team: String)
        fun showStatistic(statistic: List<Statistic>)
        fun showError(resId: Int)
    }

    interface Presenter : BasePresenter {
        fun getStatisticInfo(statisticData: StatisticData?)
    }
}
