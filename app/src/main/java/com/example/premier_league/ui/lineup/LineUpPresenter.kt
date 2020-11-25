package com.example.premier_league.ui.lineup

import com.example.premier_league.R
import com.example.premier_league.data.model.Coach
import com.example.premier_league.data.model.StartingLineup
import com.example.premier_league.data.model.Substitute
import com.sun.premierleague.data.model.*
import com.example.premier_league.data.source.repository.FootballRepository

class LineUpPresenter(
    private val view: LineUpContact.View,
    private val repository: FootballRepository
) : LineUpContact.Presenter {

    override fun getLineUp(systemTeam: String, lineUp: Lineup?, isHome: Boolean) {
        if (lineUp == null) {
            view.showError(R.string.err_load_home_team)
            return
        }
        val diagramTeams = hashMapOf<Int, List<Diagram>>()
        val systemTemps = systemTeam.trim().split(" - ")
        val startLineups = getStartLineups(lineUp, isHome)

        diagramTeams[0] =
            mutableListOf(Diagram(startLineups[0].lineupNumber, startLineups[0].lineupPlayer))
        startLineups.removeAt(0)

        for (systemTemp in systemTemps.indices) {
            val diagrams = mutableListOf<Diagram>()
            val lengthSystemTemps = systemTemps[systemTemp].toInt()
            for (item in 0 until lengthSystemTemps) {
                insertDiagram(
                    diagrams,
                    startLineups[0].lineupNumber,
                    startLineups[0].lineupPlayer
                )
                startLineups.removeAt(0)
            }
            diagramTeams[systemTemp + 1] = diagrams
        }
        showDiagram(diagramTeams, isHome)
    }

    override fun getReserves(
        substituteHomes: List<Substitute>?,
        substituteAways: List<Substitute>?
    ) {
        if (substituteHomes == null || substituteAways == null) {
            view.showError(R.string.err_load_reserves)
            return
        }
        val reserves = mutableListOf<String>()
        for (item in substituteHomes.indices) {
            reserves.add(substituteHomes[item].lineupPlayer)
            reserves.add(substituteAways[item].lineupPlayer)
        }
        view.showReserves(reserves)
    }

    override fun getCoaches(coachHomes: List<Coach>?, coachAways: List<Coach>?) {
        if (coachHomes == null || coachAways == null) {
            view.showError(R.string.err_load_coaches)
            return
        }
        val coaches = mutableListOf<String>()
        for (item in coachHomes.indices) {
            coaches.add(coachHomes[item].lineupPlayer)
            coaches.add(coachAways[item].lineupPlayer)
        }
        view.showCoaches(coaches)
    }

    override fun getMissing(
        missingHomes: List<MissingPlayer>?,
        missingAways: List<MissingPlayer>?
    ) {
        if (missingHomes == null || missingAways == null) {
            view.showError(R.string.err_load_missing)
            return
        }
        val missingPlayer = mutableListOf<String>()
        for (item in missingHomes) {
            missingPlayer.add(item.lineupPlayer)
        }
        for (item in missingAways) {
            missingPlayer.add(item.lineupPlayer)
        }
        view.showMissingPlayer(missingPlayer)
    }

    private fun showDiagram(diagramTeams: Map<Int, List<Diagram>>, isHome: Boolean) {
        val resultDiagrams = mutableListOf<String>().apply {
            this.run {
                add(getNameDiagram(diagramTeams[0]))
                add(getNameDiagram(diagramTeams[1]))
                add(getNameDiagram(diagramTeams[2]))
                add(getNameDiagram(diagramTeams[3]))
            }
        }
        if (diagramTeams.size == 5) {
            resultDiagrams.add(getNameDiagram(diagramTeams[4]))
        } else {
            resultDiagrams.add("")
        }

        if (isHome) {
            view.showHomeLineUp(resultDiagrams)
        } else {
            view.showAwayLineUp(resultDiagrams)
        }
    }

    private fun getNameDiagram(diagrams: List<Diagram>?): String =
        diagrams?.joinToString(separator = "\t\t") {
            "${it.numberClothes}.${it.namePlayer}"
        }.toString().trim()

    private fun insertDiagram(
        diagrams: MutableList<Diagram>,
        number: String,
        name: String
    ) = diagrams.add(Diagram(number, name))


    private fun getStartLineups(lineUp: Lineup, isHome: Boolean): MutableList<StartingLineup> {
        return if (isHome) {
            lineUp.home.startingLineups as MutableList
        } else {
            lineUp.away.startingLineups as MutableList
        }.apply {
            sortBy { it.lineupPosition.toInt() }
        }
    }
}
