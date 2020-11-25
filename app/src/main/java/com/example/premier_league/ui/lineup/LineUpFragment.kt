package com.example.premier_league.ui.lineup

import androidx.core.os.bundleOf
import com.example.premier_league.R
import com.example.premier_league.base.BaseFragment
import com.sun.premierleague.data.model.Away
import com.sun.premierleague.data.model.Home
import com.sun.premierleague.data.model.Lineup
import com.example.premier_league.dialog.LoadingDialog
import com.example.premier_league.utils.RepositoryFactory
import kotlinx.android.synthetic.main.fragment_line_up.*

class LineUpFragment : BaseFragment(), LineUpContact.View {

    private var presenter: LineUpContact.Presenter? = null
    private var homeSystem = ""
    private var awaySystem = ""
    private var lineUp: Lineup? = null
    private var homeTeam: Home? = null
    private var awayTeam: Away? = null
    private var myDialog: LoadingDialog? = null
    private val adapterReserve = LineUpAdapter()
    private val adapterCoach = LineUpAdapter()
    private val adapterMissing = LineUpAdapter()

    override val layoutResource: Int
        get() = R.layout.fragment_line_up

    override fun initData() {
        getDataArguments()
        initAdapter()
        initPresenter()
        initDialog()
        presenter?.let {
            it.run {
                getLineUp(homeSystem, lineUp, true)
                getLineUp(awaySystem, lineUp, false)
                getReserves(homeTeam?.substitutes, awayTeam?.substitutes)
                getCoaches(homeTeam?.coach, awayTeam?.coach)
                getMissing(homeTeam?.missingPlayers, awayTeam?.missingPlayers)
            }
        }
    }
    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }
    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    private fun getDataArguments() {
        arguments?.let {
            lineUp = it.getParcelable(BUNDLE_LINEUP)
        }
        lineUp?.let { it ->
            homeSystem = it.homeSystem
            awaySystem = it.awaySystem
            homeTeam = it.home
            awayTeam = it.away
            textNameReferee.text = it.nameReferee
            textNameStadium.text = it.nameStadium
        }
    }

    private fun initAdapter() {
        recyclerReserve.setHasFixedSize(true)
        recyclerCoach.setHasFixedSize(true)
        recyclerMissing.setHasFixedSize(true)
        recyclerReserve.adapter = adapterReserve
        recyclerCoach.adapter = adapterCoach
        recyclerMissing.adapter = adapterMissing
    }

    private fun initPresenter() {
        val context = context ?: return
        val repository = RepositoryFactory.getRepository(context)
        presenter = LineUpPresenter(this, repository)
    }

    override fun showReserves(reserves: List<String>) {
        adapterReserve.replaceData(reserves)
    }

    override fun showCoaches(coaches: List<String>) {
        adapterCoach.replaceData(coaches)
    }

    override fun showMissingPlayer(missingPlayer: List<String>) {
        adapterMissing.replaceData(missingPlayer)
    }

    override fun showHomeLineUp(resultDiagrams: List<String>) {
        textHomeGoalie.text = resultDiagrams[0]
        textHomeRowOne.text = resultDiagrams[1]
        textHomeRowTwo.text = resultDiagrams[2]
        textHomeRowThree.text = resultDiagrams[3]
        textHomeRowFour.text = resultDiagrams[4]
    }

    override fun showAwayLineUp(resultDiagrams: List<String>) {
        textAwayGoalie.text = resultDiagrams[0]
        textAwayRowOne.text = resultDiagrams[1]
        textAwayRowTwo.text = resultDiagrams[2]
        textAwayRowThree.text = resultDiagrams[3]
        textAwayRowFour.text = resultDiagrams[4]
    }

    override fun showError(resId: Int) {
        showMessage(resId)
    }

    companion object {
        private const val BUNDLE_LINEUP = "BUNDLE_LINEUP"

        fun getInstance(lineUp: Lineup?) = LineUpFragment().apply {
            arguments = bundleOf(
                BUNDLE_LINEUP to lineUp
            )
        }
    }
}
