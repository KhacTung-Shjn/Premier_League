package com.example.premier_league.ui.statistic

import androidx.core.os.bundleOf
import com.example.premier_league.R
import com.example.premier_league.base.BaseFragment
import com.example.premier_league.data.model.Statistic
import com.example.premier_league.data.model.StatisticData
import com.example.premier_league.dialog.LoadingDialog
import com.example.premier_league.utils.loadImage
import com.example.premier_league.utils.showToast
import kotlinx.android.synthetic.main.fragment_statistic.*

class StatisticFragment : BaseFragment(), StatisticContact.View {

    private val adapter = StatisticAdapter()
    private var presenter: StatisticPresenter? = null
    private var statisticData: StatisticData? = null
    private var myDialog: LoadingDialog? = null

    override val layoutResource: Int
        get() = R.layout.fragment_statistic

    override fun initData() {
        getDataArgument()
        initAdapter()
        initPresenter()
        initDialog()
        presenter?.getStatisticInfo(statisticData)
    }

    private fun getDataArgument() {
        arguments?.let {
            statisticData = it.getParcelable(BUNDLE_STATISTIC)
        }
    }

    private fun initAdapter() {
        recyclerStatistic.setHasFixedSize(true)
        recyclerStatistic.adapter = adapter
    }

    private fun initPresenter() {
        presenter = StatisticPresenter(this)
    }

    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    override fun showTeam(vararg team: String) {
        textHomeTeamStatistic.text = team[0]
        textAwayTeamStatistic.text = team[1]
        imageHomeTeamStatistic.loadImage(team[2])
        imageAwayTeamStatistic.loadImage(team[3])
    }

    override fun showStatistic(statistic: List<Statistic>) {
        adapter.replaceData(statistic)
    }

    override fun showError(resId: Int) {
        context?.showToast(resId, 1)
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    companion object {
        private const val BUNDLE_STATISTIC = "BUNDLE_STATISTIC"

        fun getInstance(statisticData: StatisticData) = StatisticFragment().apply {
            arguments = bundleOf(BUNDLE_STATISTIC to statisticData)
        }
    }
}
