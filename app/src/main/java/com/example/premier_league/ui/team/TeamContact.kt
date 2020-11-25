package com.example.premier_league.ui.team

import com.example.premier_league.base.BasePresenter
import com.example.premier_league.base.BaseView
import com.sun.premierleague.data.model.Coache
import com.sun.premierleague.data.model.Player
import com.example.premier_league.data.model.TeamItem

interface TeamContact {
    interface View : BaseView {
        fun showTeam(data: List<TeamItem>)
        fun showCoach(data: List<Coache>)
        fun showGoalkeeper(data: List<Player>)
        fun showDefender(data: List<Player>)
        fun showMidfielder(data: List<Player>)
        fun showForwarder(data: List<Player>)
        fun showError(error: Any)
    }

    interface Presenter : BasePresenter {
        fun getTeamInformation(idTeam: String)
    }
}
