package com.example.premier_league.ui.match

import com.example.premier_league.R
import com.sun.premierleague.data.model.MatchItem
import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.data.model.Time
import com.example.premier_league.data.source.repository.FootballRepository
import com.example.premier_league.data.source.remote.OnDataLoadedCallback
import com.example.premier_league.utils.TimeUtils
import java.lang.Exception

class MatchPresenter(
    private val view: MatchContract.View,
    private val repository: FootballRepository
) : MatchContract.Presenter {

    private var time: Time? = null

    override fun start() {
        time = TimeUtils.getTime(DISTANCE_FROM, DISTANCE_TO)
        time?.let { getMatchInformation(it) }
    }

    override fun getMatchInformation(time: Time) {
        view.showLoading()
        repository.getMatch(time, object : OnDataLoadedCallback<List<MatchItem>> {
            override fun onSuccess(data: List<MatchItem>) {
                view.showMatch(data)
                view.hideLoading()
            }

            override fun onFailure(exception: Exception?) {
                view.showError(exception?.message.toString())
                view.hideLoading()
            }
        })
    }

    override fun getNotifications() {
        repository.getFootballNotifications(object : OnDataLoadedCallback<List<MatchNotification>> {
            override fun onSuccess(data: List<MatchNotification>) {
                view.showNotifications(data)
            }

            override fun onFailure(exception: Exception?) {
                exception?.let {
                    view.showError(it.message.toString())
                }
            }
        })
    }

    override fun addNotification(matchNotification: MatchNotification) {
        repository.addFootballNotification(matchNotification,
            object : OnDataLoadedCallback<Boolean> {
                override fun onSuccess(data: Boolean) {
                    if (data) {
                        view.addAlarm(matchNotification)
                        view.showResult(R.string.label_add_notification_success)
                        return
                    }
                    view.showResult(R.string.label_add_notification_fail)
                }

                override fun onFailure(exception: Exception?) {
                    exception?.let {
                        view.showError(it.message.toString())
                    }
                }
            })
    }

    override fun deleteNotification(matchNotification: MatchNotification) {
        repository.deleteFootballNotification(matchNotification.id,
            object : OnDataLoadedCallback<Boolean> {
                override fun onSuccess(data: Boolean) {
                    if (data) {
                        view.cancelAlarm(matchNotification)
                        view.showResult(R.string.label_delete_notification_success)
                        return
                    }
                    view.showResult(R.string.label_delete_notification_fail)
                }

                override fun onFailure(exception: Exception?) {
                    exception?.let {
                        view.showError(it.message.toString())
                    }
                }
            })
    }

    companion object {
        private const val DISTANCE_FROM = 10
        private const val DISTANCE_TO = 10
    }
}
