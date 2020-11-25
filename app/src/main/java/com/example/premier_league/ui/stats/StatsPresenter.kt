package com.example.premier_league.ui.stats

import com.example.premier_league.data.model.StatItem
import com.example.premier_league.data.source.repository.FootballRepository
import com.example.premier_league.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class StatsPresenter(
    private val view: StatsContact.View,
    private val repository: FootballRepository
) : StatsContact.Presenter {

    override fun start() {
        getStatsInformation()
    }

    override fun getStatsInformation() {
        view.showLoading()
        repository.getStat(object : OnDataLoadedCallback<List<StatItem>> {
            override fun onSuccess(data: List<StatItem>) {
                view.showStats(data)
                view.hideLoading()
            }

            override fun onFailure(exception: Exception?) {
                exception?.let {
                    view.showError(it.message.toString())
                    view.hideLoading()
                }
            }
        })
    }
}
