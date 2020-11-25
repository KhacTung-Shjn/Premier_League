package com.example.premier_league.ui.lineup

import com.example.premier_league.base.BasePresenter
import com.example.premier_league.base.BaseView
import com.example.premier_league.data.model.Coach
import com.sun.premierleague.data.model.Lineup
import com.sun.premierleague.data.model.MissingPlayer
import com.example.premier_league.data.model.Substitute

interface LineUpContact {
    interface View : BaseView {
        fun showReserves(reserves: List<String>)
        fun showCoaches(coaches: List<String>)
        fun showMissingPlayer(missingPlayer: List<String>)
        fun showHomeLineUp(resultDiagrams: List<String>)
        fun showAwayLineUp(resultDiagrams: List<String>)
        fun showError(resId: Int)
    }

    interface Presenter : BasePresenter {
        fun getLineUp(systemTeam: String, lineUp: Lineup?, isHome: Boolean = true)
        fun getReserves(substituteHomes: List<Substitute>?, substituteAways: List<Substitute>?)
        fun getCoaches(coachHomes: List<Coach>?, coachAways: List<Coach>?)
        fun getMissing(missingHomes: List<MissingPlayer>?, missingAways: List<MissingPlayer>?)
    }
}
