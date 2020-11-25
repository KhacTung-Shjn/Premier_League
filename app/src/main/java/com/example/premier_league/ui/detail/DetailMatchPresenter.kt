package com.example.premier_league.ui.detail

import com.example.premier_league.R
import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.data.source.repository.FootballRepository
import com.example.premier_league.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class DetailMatchPresenter(
    private val view: DetailMatchContact.View,
    private val repository: FootballRepository
) : DetailMatchContact.Presenter {
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
}
