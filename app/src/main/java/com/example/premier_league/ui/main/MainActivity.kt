package com.example.premier_league.ui.main

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.premier_league.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.premier_league.base.BaseActivity
import com.example.premier_league.ui.match.MatchFragment
import com.example.premier_league.ui.setting.SettingFragment
import com.example.premier_league.ui.stats.StatsFragment
import com.example.premier_league.utils.FragmentUtil
import com.example.premier_league.utils.MatchConst.EXTRA_OPEN_FROM_NOTIFICATION
import com.example.premier_league.utils.NetworkUtil
import com.example.premier_league.utils.showToast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var fragments = mutableListOf<Fragment>()
    private var currentFragment: Fragment? = null
    private var isConnection = false
    private var sayBackPress = 0L

    override val layoutResource: Int get() = R.layout.activity_main

    override fun initComponents() {
        bottomNavigationView.setOnNavigationItemSelectedListener(onBottomNavigation)
        bottomNavigationView.selectedItemId = R.id.menuMatch
        initFragments()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    private fun initFragments() {
        fragments.run {
            add(MatchFragment())
            add(StatsFragment())
            add(SettingFragment())
        }
        currentFragment = FragmentUtil.addFragmentsToActivity(
            supportFragmentManager,
            fragments,
            R.id.fragmentContainer,
            0
        )
    }

    private val onBottomNavigation =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            var positionFragment = -1
            isConnection = NetworkUtil.isConnection(this)
            when (menuItem.itemId) {
                R.id.menuMatch -> {
                    positionFragment = 0
                    if (!isConnection) {
                        openFragment(MatchFragment())
                    }
                }
                R.id.menuStat -> {
                    positionFragment = 1
                    if (!isConnection) {
                        openFragment(StatsFragment())
                    }
                }
                R.id.menuSetting -> {
                    positionFragment = 2
                    if (!isConnection) {
                        openFragment(SettingFragment())
                    }
                }
            }
            if (positionFragment != -1 && isConnection) {
                currentFragment?.let {
                    currentFragment = FragmentUtil.switchFragmentActivity(
                        supportFragmentManager,
                        it,
                        fragments[positionFragment]
                    )
                }
            }


            true
        }


    override fun onBackPressed() {
        if (sayBackPress + 3000 > System.currentTimeMillis()) super.onBackPressed() else {
            showToast(R.string.label_press_back_once_again)
            sayBackPress = System.currentTimeMillis()
        }
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)

        fun getIntentFromNotification(context: Context) =
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra(EXTRA_OPEN_FROM_NOTIFICATION, true)
            }
    }
}
