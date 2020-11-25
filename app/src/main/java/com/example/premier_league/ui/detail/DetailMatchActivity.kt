package com.example.premier_league.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.premier_league.R
import com.example.premier_league.base.BaseActivity
import com.sun.premierleague.data.model.Event
import com.sun.premierleague.data.model.MatchItem
import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.data.model.StatisticData
import com.example.premier_league.dialog.LoadingDialog
import com.example.premier_league.ui.event.EventFragment
import com.example.premier_league.ui.lineup.LineUpFragment
import com.example.premier_league.ui.statistic.StatisticFragment
import com.example.premier_league.ui.team.TeamFragment
import com.example.premier_league.utils.*
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : BaseActivity(), DetailMatchContact.View, View.OnClickListener {

    private var presenter: DetailMatchContact.Presenter? = null
    private var myDialog: LoadingDialog? = null
    private val matchItem: MatchItem? by lazy {
        intent?.getParcelableExtra(MatchConst.EXTRA_MATCH_ITEM)
    }
    private val fragments = mutableListOf<Fragment>()
    private val adapter: DetailMatchAdapter? by lazy {
        DetailMatchAdapter(supportFragmentManager)
    }
    private val idTeam: String? by lazy {
        intent?.getStringExtra("TEST_ID_TEAM")
    }
    private var isNotification: Boolean = false
    private var isClickNotification: Boolean = false

    override val layoutResource: Int
        get() = R.layout.activity_detail_match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        changeSizeWindow()
        initData()
        initDialog()
        initAdapter()
        initPresenter()
        showDetailMatch()

        idTeam?.let {
            FragmentUtil.addCurrentFragment(
                supportFragmentManager,
                TeamFragment.getInstance(it),
                R.id.constraintDetail
            )
        }

        imageBack.setOnClickListener(this)
        imageHomeTeam.setOnClickListener(this)
        imageWayTeam.setOnClickListener(this)
        imageRing.setOnClickListener(this)
    }

    private fun initPresenter() {
        val repository = RepositoryFactory.getRepository(this)
        presenter = DetailMatchPresenter(this, repository)
    }

    private fun initDialog() {
        this?.let { myDialog = LoadingDialog(it) }
    }

    override fun addAlarm(matchNotification: MatchNotification) {
        AlarmUtil.create(
            this,
            matchNotification.id,
            "${matchNotification.date} ${matchNotification.time}"
        )
    }

    override fun showResult(idRes: Int) {
        showToast(idRes)
    }

    override fun showError(err: String) {}

    override fun cancelAlarm(matchNotification: MatchNotification) {
        AlarmUtil.cancel(this)
    }

    override fun showLoading() {
        myDialog?.show()
    }

    override fun hideLoading() {
        myDialog?.dismiss()
    }

    private fun initData() {
        matchItem?.let {
            if (it.matchStatus != MatchConst.FINISHED) {
                textStatusMatch.visibility = View.VISIBLE
                return
            }

            fragments.add(LineUpFragment.getInstance(it.lineup.apply {
                homeSystem = it.matchHomeTeamSystem
                awaySystem = it.matchAwayTeamSystem
                nameReferee = it.matchReferee
                nameStadium = it.matchStadium
            }))

            fragments.add(
                EventFragment.getInstance(
                    Event(it.goalscorer, it.cards, it.substitutions)
                )
            )

            fragments.add(
                StatisticFragment.getInstance(
                    StatisticData(
                        it.matchHomeTeamName,
                        it.matchAwayTeamName,
                        it.teamHomeBadge,
                        it.teamAwayBadge,
                        it.statistics
                    )
                )
            )
        }
    }

    private fun initAdapter() {
        adapter?.replaceData(fragments)
        viewPagerDetailMatch.adapter = adapter
        viewPagerDetailMatch.offscreenPageLimit = 3
        tabDetailMatch.setupWithViewPager(viewPagerDetailMatch)
        viewPagerDetailMatch.currentItem = 1

        tabDetailMatch?.run {
            getTabAt(0)?.setIcon(R.drawable.ic_tshirt)
            getTabAt(1)?.setIcon(R.drawable.ic_event)
            getTabAt(2)?.setIcon(R.drawable.ic_statistic)
        }

    }

    private fun showDetailMatch() {
        matchItem?.let {
            imageRing.visibility =
                if (it.matchStatus == MatchConst.FINISHED) View.GONE else View.VISIBLE
            imageHomeTeam.loadImage(it.teamHomeBadge)
            imageWayTeam.loadImage(it.teamAwayBadge)
            textAwayTeam.text = it.matchAwayTeamName
            textHomeTeam.text = it.matchHomeTeamName
            textResult.text =
                getString(R.string.text_score, it.matchHomeTeamScore, it.matchAwayTeamScore)
            textTimeMatch.text =
                getString(R.string.title_address_match, it.matchStadium, it.matchDate, it.matchTime)
            isNotification = it.isNotification
            imageRing.isSelected = isNotification
        }
    }

    override fun initComponents() {}

    private fun changeSizeWindow() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.imageBack -> {
                if (isClickNotification) {
                    val intent = Intent()
                    intent.putExtra("EXTRA_CLICK_NOTIFICATION", isNotification)
                    setResult(RESULT_OK, intent)
                }
                finish()
            }
            R.id.imageHomeTeam -> {
                matchItem?.let {
                    startActivity(newInstance(this, it.matchHomeTeamId))
                }
            }
            R.id.imageWayTeam -> {
                matchItem?.let {
                    startActivity(newInstance(this, it.matchAwayTeamId))
                }
            }
            R.id.imageRing -> {
                isClickNotification = true
                matchItem?.let {
                    val matchNotification = MatchNotification(
                        it.matchId,
                        it.matchDate,
                        it.matchTime,
                        it.matchHomeTeamId,
                        it.matchHomeTeamName,
                        it.matchAwayTeamId,
                        it.matchAwayTeamName
                    )

                    if (!isNotification) {
                        imageRing.isSelected = true
                        it.isNotification = true
                        presenter?.addNotification(matchNotification)
                    } else {
                        imageRing.isSelected = false
                        it.isNotification = false
                        presenter?.deleteNotification(matchNotification)
                    }
                    isNotification = !isNotification
                }
            }
        }
    }

    override fun onBackPressed() {
        if (isClickNotification) {
            val intent = Intent()
            intent.putExtra("EXTRA_CLICK_NOTIFICATION", isNotification)
            setResult(RESULT_OK, intent)
        }
        super.onBackPressed()
    }

    companion object {
        fun newInstance(context: Context, idTeam: String) =
            Intent(context, DetailMatchActivity::class.java).apply {
                putExtra("TEST_ID_TEAM", idTeam)
            }
    }
}
