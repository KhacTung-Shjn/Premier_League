package com.example.premier_league.ui.team

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.example.premier_league.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.example.premier_league.base.BaseFragment
import com.sun.premierleague.data.model.Coache
import com.sun.premierleague.data.model.Player
import com.example.premier_league.data.model.TeamItem
import com.example.premier_league.dialog.LoadingDialog
import com.example.premier_league.utils.RepositoryFactory
import com.example.premier_league.utils.loadImage
import com.example.premier_league.utils.showToast
import kotlinx.android.synthetic.main.fragment_detail_team.*

class TeamFragment : BaseFragment(), TeamContact.View, View.OnClickListener {

    private var presenter: TeamPresenter? = null
    private val adapterGoalkeeper = TeamAdapter()
    private val adapterDefender = TeamAdapter()
    private val adapterMidfielder = TeamAdapter()
    private val adapterForwarder = TeamAdapter()
    private var myDialog: LoadingDialog? = null

    override val layoutResource get() = R.layout.fragment_detail_team

    override fun initData() {
        initAdapter()
        initPresenter()
        initCollapsingToolbar()
        initDialog()
        arguments?.let {
            val idTeam = it.getString(ID_TEAM)
            presenter?.getTeamInformation(idTeam!!)
        }

        imageBackToolbar.setOnClickListener(this)
    }

    private fun initAdapter() {
        recyclerGoalkeeper.adapter = adapterGoalkeeper
        recyclerPlayerDefend.adapter = adapterDefender
        recyclerPlayerMidfield.adapter = adapterMidfielder
        recyclerPlayerForward.adapter = adapterForwarder
    }

    private fun initPresenter() {
        val context = context ?: return
        val repository = RepositoryFactory.getRepository(context)
        presenter = TeamPresenter(this, repository)
    }

    private fun initCollapsingToolbar() {
        val collapsingToolbarLayout =
            collapsingToolbarTeam as CollapsingToolbarLayout
        val appBarLayout = appBarTeam as AppBarLayout
        var isShow = true
        var scrollRange = -1

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                collapsingToolbarLayout.title = textDetailTeam.text
                isShow = true
            } else if (isShow) {
                collapsingToolbarLayout.title = " "
                isShow = false
            }
            //imageBackToolbar.isVisible = isShow
        })
    }

    override fun showTeam(data: List<TeamItem>) {
        textDetailTeam.text = data.first().teamName
        imageDetailTeam.loadImage(data.first().teamBadge)
    }


    override fun showCoach(coache: List<Coache>) {
        if (coache.isEmpty()) {
            view?.context?.showToast(R.string.err_no_coach)
            return
        }
        val coachOne = coache.first()
        textNameCoachOne.text = coachOne.coachName
        textInfoCoachOne.text = String.format(
            getString(R.string.text_info_coach_format),
            coachOne.coachAge,
            coachOne.coachCountry
        )
        if (coache.size > 1) {
            val coachTwo = coache[1]
            textNameCoachTwo.visibility = View.VISIBLE
            textInfoCoachTwo.visibility = View.VISIBLE
            textNameCoachTwo.text = coachTwo.coachName
            textInfoCoachTwo.text = String.format(
                getString(R.string.text_info_coach_format),
                coachTwo.coachAge,
                coachTwo.coachCountry
            )
        } else {
            imageCoachTwo.visibility = View.INVISIBLE
            textNameCoachTwo.visibility = View.INVISIBLE
            textInfoCoachTwo.visibility = View.INVISIBLE
        }
    }
    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    override fun showGoalkeeper(data: List<Player>) {
        adapterGoalkeeper.replaceData(data)
        recyclerGoalkeeper.scrollToPosition(0)
    }

    override fun showDefender(data: List<Player>) {
        adapterDefender.replaceData(data)
        recyclerPlayerDefend.scrollToPosition(0)
    }

    override fun showMidfielder(data: List<Player>) {
        adapterMidfielder.replaceData(data)
        recyclerPlayerMidfield.scrollToPosition(0)
    }

    override fun showForwarder(data: List<Player>) {
        adapterForwarder.replaceData(data)
        recyclerPlayerForward.scrollToPosition(0)
    }

    override fun showError(error: Any) {
        view?.context?.showToast(error)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imageBackToolbar -> {
                fragmentManager?.let {
                    onClickBack(it)
                }
            }
        }
    }

    private fun onClickBack(fragmentManager: FragmentManager) {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
        } else {
            activity?.finish()
        }
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    companion object {
        private const val ID_TEAM = "idTeam"
        fun getInstance(idTeam: String) = TeamFragment().apply {
            arguments = bundleOf(ID_TEAM to idTeam)
        }
    }
}
