package com.example.premier_league.ui.stats

import com.example.premier_league.R
import com.example.premier_league.base.BaseFragment
import com.example.premier_league.data.model.StatItem
import com.example.premier_league.ui.detail.DetailMatchActivity
import com.example.premier_league.dialog.LoadingDialog
import com.example.premier_league.utils.NetworkUtil
import com.example.premier_league.utils.RepositoryFactory
import com.example.premier_league.utils.make
import kotlinx.android.synthetic.main.fragment_stats.*

class StatsFragment : BaseFragment(), StatsContact.View, (StatItem) -> Unit {

    private var presenter: StatsContact.Presenter? = null
    private var adapter = StatsAdapter(this)
    private var myDialog: LoadingDialog? = null
    private var isConnection = false

    override val layoutResource: Int
        get() = R.layout.fragment_stats

    override fun initData() {
        initAdapter()
        initPresenter()
        initDialog()
        presenter?.start()
    }

    private fun initAdapter() {
        recyclerStats.setHasFixedSize(true)
        recyclerStats.adapter = adapter.apply {
            onItemClick = { item ->
                context?.let {
                    isConnection = NetworkUtil.isConnection(it)
                    if (!isConnection) {
                        view?.make(getString(R.string.err_internet))
                        return@let
                    }
                    startActivity(DetailMatchActivity.newInstance(it, item.teamId))
                }
            }
        }
    }

    private fun initPresenter() {
        val context = context ?: return
        val repository = RepositoryFactory.getRepository(context)
        presenter = StatsPresenter(this, repository)
    }

    private fun initDialog() {
        context?.let { myDialog = LoadingDialog(it) }
    }

    override fun showStats(data: List<StatItem>) {
        adapter.replaceData(data)
    }

    override fun showError(error: String) {
        //context?.showToast(error)
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    override fun invoke(itemStat: StatItem) {
        context?.let {
            startActivity(DetailMatchActivity.newInstance(it, itemStat.teamId))
        }
    }
}
