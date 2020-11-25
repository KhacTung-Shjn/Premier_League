package com.example.premier_league.ui.stats

import com.example.premier_league.base.BasePresenter
import com.example.premier_league.base.BaseView
import com.example.premier_league.data.model.StatItem

interface StatsContact {
    interface View : BaseView {
        fun showStats(data: List<StatItem>)
        fun showError(error: String)
    }

    interface Presenter : BasePresenter {
        fun getStatsInformation()
    }
}
