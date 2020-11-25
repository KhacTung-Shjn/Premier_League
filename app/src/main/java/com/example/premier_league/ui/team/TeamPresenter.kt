package com.example.premier_league.ui.team

import com.example.premier_league.R
import com.sun.premierleague.data.model.Coache
import com.sun.premierleague.data.model.Player
import com.example.premier_league.data.model.TeamItem
import com.example.premier_league.data.source.repository.FootballRepository
import com.example.premier_league.data.source.remote.OnDataLoadedCallback
import java.lang.Exception

class TeamPresenter(
    private val view: TeamContact.View,
    private val repository: FootballRepository
) : TeamContact.Presenter {

    override fun getTeamInformation(idTeam: String) {
        view.showLoading()
        repository.getTeam(idTeam, object : OnDataLoadedCallback<List<TeamItem>> {
            override fun onSuccess(data: List<TeamItem>) {
                if (data.isEmpty()) {
                    view.showError(R.string.err_no_data)
                    view.hideLoading()
                    return
                }
                view.showTeam(data)

                val coaches: List<Coache> = data.first().coaches
                view.showCoach(coaches)

                val players: List<Player> = data.first().players
                if (players.isEmpty()) {
                    view.showError(R.string.err_no_player)
                    view.hideLoading()
                    return
                }
                val defenders = filterData(players, DEFENDERS)
                val forwarders = filterData(players, FORWARDS)
                val goalkeepers = filterData(players, GOALKEEPERS)
                val midfielders = filterData(players, MIDFIELDERS)
                view.run {
                    showGoalkeeper(goalkeepers)
                    showDefender(defenders)
                    showMidfielder(midfielders)
                    showForwarder(forwarders)
                }
                view.hideLoading()
            }

            override fun onFailure(exception: Exception?) {
                exception?.let {
                    view.showError(it)
                    view.hideLoading()
                }
            }
        })
    }

    private fun filterData(list: List<Player>, type: String) =
        list.filter { item -> item.playerType == type }

    companion object {
        const val DEFENDERS = "Defenders"
        const val GOALKEEPERS = "Goalkeepers"
        const val MIDFIELDERS = "Midfielders"
        const val FORWARDS = "Forwards"
    }
}
