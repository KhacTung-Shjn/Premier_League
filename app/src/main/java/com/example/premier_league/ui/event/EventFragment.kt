package com.example.premier_league.ui.event

import androidx.core.os.bundleOf
import com.example.premier_league.R
import com.example.premier_league.base.BaseFragment
import com.sun.premierleague.data.model.DetailEvent
import com.sun.premierleague.data.model.Event
import com.example.premier_league.utils.RepositoryFactory
import kotlinx.android.synthetic.main.fragment_event.*

class EventFragment : BaseFragment(), EventContact.View {

    private var presenter: EventContact.Presenter? = null
    private val adapter = EventAdapter()
    private val event: Event? by lazy {
        arguments?.getParcelable(BUNDLE_EVENT)
    }

    override val layoutResource: Int
        get() = R.layout.fragment_event

    override fun initData() {
        initAdapter()
        initPresenter()
        presenter?.getTimeLineEvent(event)
    }

    private fun initAdapter() {
        recyclerEvent.adapter = adapter
    }

    private fun initPresenter() {
        val context = context ?: return
        val repository = RepositoryFactory.getRepository(context)
        presenter = EventPresenter(this, repository)
    }

    override fun showEvents(detailEvents: List<DetailEvent>) {
        adapter.replaceData(detailEvents)
    }

    override fun showError(resId: Int) {
        showMessage(resId)
    }

    companion object {
        private const val BUNDLE_EVENT = "BUNDLE_EVENT"

        fun getInstance(event: Event) = EventFragment().apply {
            arguments = bundleOf(BUNDLE_EVENT to event)
        }
    }
}
