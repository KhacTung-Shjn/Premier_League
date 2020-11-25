package com.example.premier_league.ui.match

import android.view.View
import com.example.premier_league.R
import com.example.premier_league.base.BaseViewHolder
import com.sun.premierleague.data.model.MatchItem
import com.sun.premierleague.data.model.MatchNotification
import com.example.premier_league.utils.loadImage
import kotlinx.android.synthetic.main.item_recyclerview_match.view.*

class MatchViewHolder(
    private val itemView: View,
    private val notifications: List<MatchNotification>,
    onItemClick: (MatchItem) -> Unit,
    private val onRingClick: (MatchNotification, Boolean) -> Unit
) : BaseViewHolder<MatchItem>(itemView) {
    private var itemData: MatchItem? = null
    private var itemsNotification = mutableListOf<MatchNotification>()

    init {
        itemView.setOnClickListener {
            itemData?.let {
                onItemClick(it)
            }
        }
        itemsNotification.addAll(notifications)
    }

    override fun onBind(matchItem: MatchItem) {
        itemData = matchItem
        itemView.run {
            if (matchItem.isNotification) {
                imageRing.setBackgroundResource(R.drawable.ic_ring_checked)
            } else {
                imageRing.setBackgroundResource(R.drawable.ic_ring_uncheck)
            }

            textDate.text = matchItem.matchDate
            textScore.text = context.getString(
                R.string.text_score,
                matchItem.matchHomeTeamScore,
                matchItem.matchAwayTeamScore
            )
            textTime.text = matchItem.matchTime
            val boo = matchItem.matchStatus.isEmpty()
            textTime.visibility = getVisibility(boo)
            imageRing.visibility = getVisibility(boo)
            textScore.visibility = getVisibility(!boo)
            textHomeTeam.text = matchItem.matchHomeTeamName
            textAwayTeam.text = matchItem.matchAwayTeamName
            imageHomeTeam.loadImage(matchItem.teamHomeBadge)
            imageAwayTeam.loadImage(matchItem.teamAwayBadge)
        }

        itemView.imageRing.setOnClickListener {
            val matchNotification = MatchNotification(
                matchItem.matchId,
                matchItem.matchDate,
                matchItem.matchTime,
                matchItem.matchHomeTeamId,
                matchItem.matchHomeTeamName,
                matchItem.matchAwayTeamId,
                matchItem.matchAwayTeamName
            )
            if (!matchItem.isNotification) {
                matchItem.isNotification = true
                onRingClick(matchNotification, true)
            } else {
                matchItem.isNotification = false
                onRingClick(matchNotification, false)
            }
        }
    }

    private fun getVisibility(isShow: Boolean) = if (isShow) View.VISIBLE else View.INVISIBLE
}
