package com.example.premier_league.ui.detail

import com.example.premier_league.base.BasePresenter
import com.example.premier_league.base.BaseView
import com.sun.premierleague.data.model.MatchNotification

interface DetailMatchContact {
    interface View : BaseView {
        fun addAlarm(matchNotification: MatchNotification)
        fun showResult(idRes: Int)
        fun showError(err: String)
        fun cancelAlarm(matchNotification: MatchNotification)
    }

    interface Presenter : BasePresenter {
        fun addNotification(matchNotification: MatchNotification)
        fun deleteNotification(matchNotification: MatchNotification)
    }
}
