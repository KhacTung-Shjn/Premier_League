package com.example.premier_league.ui.match

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.premier_league.R
import com.example.premier_league.base.BaseFragment
import com.sun.premierleague.data.model.MatchItem
import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.ui.detail.DetailMatchActivity
import com.example.premier_league.dialog.LoadingDialog
import com.example.premier_league.utils.*
import kotlinx.android.synthetic.main.fragment_match.*

class MatchFragment : BaseFragment(), MatchContract.View {

    private var presenter: MatchPresenter? = null
    private var myDialog: LoadingDialog? = null
    private var isConnection = false
    private val adapter = MatchAdapter()
    private val notifications = mutableListOf<MatchNotification>()

    override val layoutResource: Int
        get() = R.layout.fragment_match

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_action_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initData() {
        setHasOptionsMenu(true)
        initAdapter()
        initPresenter()
        initDialog()
        if (!isConnection) {
            view?.make(getString(R.string.err_internet))
            return
        }
        presenter?.getNotifications()
        presenter?.start()
    }

    private fun initAdapter() {
        recyclerMatches.adapter = adapter.apply {
            onItemClick = {
                showLoading()
                startActivityForResult(Intent(context, DetailMatchActivity::class.java).apply {
                    putExtra(MatchConst.EXTRA_MATCH_ITEM, it)
                }, 99)
            }

            onRingClick = { it, isCheck ->
                if (isCheck)
                    presenter?.addNotification(it)
                else presenter?.deleteNotification(it)
            }
        }
    }

    private fun initPresenter() {
        val context = context ?: return
        val repository = RepositoryFactory.getRepository(context)
        presenter = MatchPresenter(this, repository)
        isConnection = NetworkUtil.isConnection(context)
    }

    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        context?.let {
            TimeUtils.getDatePickerDialog(it) { time ->
                presenter?.getMatchInformation(
                    time
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    override fun showMatch(matches: List<MatchItem>) {
        if (matches.isEmpty()) {
            view?.make("Nodata")
        }
        for (item in matches) {
            if (isCheckRing(item.matchId, notifications)) {
                item.isNotification = true
            }
        }
        adapter.replaceData(matches)
        recyclerMatches.scrollToPosition(0)
    }

    override fun showError(error: String) {
        context?.showToast(R.string.err_no_match_in_this_time, 1)
    }

    override fun showNotifications(data: List<MatchNotification>) {
        notifications.clear()
        notifications.addAll(data)
    }

    override fun showResult(idRes: Int) {
        adapter.notifyDataSetChanged()
        context?.showToast(idRes)
    }

    override fun addAlarm(matchNotification: MatchNotification) {
        context?.let {
            AlarmUtil.create(
                it,
                matchNotification.id,
                "${matchNotification.date} ${matchNotification.time}"
            )
        }
    }

    override fun cancelAlarm(matchNotification: MatchNotification) {
        context?.let {
            AlarmUtil.cancel(it)
        }
    }

    private fun isCheckRing(idMatch: String, itemsNotification: List<MatchNotification>): Boolean {
        for (item in itemsNotification) {
            if (item.id == idMatch) return true
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 99) {
            if (resultCode == Activity.RESULT_OK) {
                presenter?.getNotifications()
                presenter?.start()
            }
        }
        hideLoading()
    }
}
