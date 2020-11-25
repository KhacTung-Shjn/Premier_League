package com.example.premier_league.ui.event

import com.example.premier_league.base.BasePresenter
import com.example.premier_league.base.BaseView
import com.sun.premierleague.data.model.DetailEvent
import com.sun.premierleague.data.model.Event

interface EventContact {
    interface View : BaseView {
        fun showEvents(detailEvents: List<DetailEvent>)
        fun showError(resId: Int)
    }

    interface Presenter : BasePresenter {
        fun getTimeLineEvent(event: Event?)
    }
}
