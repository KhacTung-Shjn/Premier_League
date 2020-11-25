package com.example.premier_league.ui.match

import com.example.premier_league.base.BasePresenter
import com.example.premier_league.base.BaseView
import com.sun.premierleague.data.model.MatchItem
import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.data.model.Time

interface MatchContract {
    interface View : BaseView {
        fun showMatch(matches: List<MatchItem>)
        fun showError(error: String)
        fun showNotifications(data: List<MatchNotification>)
        fun showResult(idRes: Int)
        fun addAlarm(matchNotification: MatchNotification)
        fun cancelAlarm(matchNotification: MatchNotification)
    }

    interface Presenter : BasePresenter {
        fun getMatchInformation(time: Time)
        fun getNotifications()
        fun addNotification(matchNotification: MatchNotification)
        fun deleteNotification(matchNotification: MatchNotification)
    }
}
