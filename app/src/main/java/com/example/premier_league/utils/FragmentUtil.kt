package com.example.premier_league.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.jetbrains.annotations.NotNull

object FragmentUtil {
    fun addFragmentsToActivity(
        @NotNull fragmentManager: FragmentManager,
        fragments: List<Fragment>,
        frameId: Int,
        activePosition: Int
    ): Fragment {
        val fragmentTransaction = fragmentManager.beginTransaction()

        for (i in fragments.indices) {
            if (i != activePosition)
                fragmentTransaction.add(frameId, fragments[i]).hide(fragments[i])
        }

        fragmentTransaction.add(frameId, fragments[activePosition]).commit()
        return fragments[activePosition]
    }

    fun addCurrentFragment(
        @NotNull fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int
    ) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit()
    }

    fun switchFragmentActivity(
        @NotNull fragmentManager: FragmentManager,
        currentFragment: Fragment,
        nextFragment: Fragment
    ): Fragment {
        val transaction = fragmentManager.beginTransaction()
        transaction.hide(currentFragment)
        transaction.show(nextFragment)
        transaction.commit()
        return nextFragment
    }
}
