package com.example.premier_league.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DetailMatchAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {

    private var fragments = mutableListOf<Fragment>()

    fun replaceData(collection: List<Fragment>?) {
        collection?.let {
            fragments.clear()
            fragments.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]
}
