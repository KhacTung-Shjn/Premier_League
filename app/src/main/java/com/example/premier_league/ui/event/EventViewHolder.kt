package com.example.premier_league.ui.event

import android.view.View
import androidx.core.view.isVisible
import com.example.premier_league.base.BaseViewHolder
import com.sun.premierleague.data.model.DetailEvent
import com.example.premier_league.utils.LineupConst
import kotlinx.android.synthetic.main.item_recyclerview_event.view.*

class EventViewHolder(private val itemView: View) : BaseViewHolder<DetailEvent>(itemView) {
    override fun onBind(item: DetailEvent) {
        itemView.run {
            textTimeLine.text = when (item.time.length) {
                1 -> " ${item.time} "
                else -> item.time
            }
            if (item.isHome) {
                viewHomeHorizontal.isVisible = true
                when {
                    item.score.isNotEmpty() -> {
                        textHomeGoal.run {
                            isVisible = true
                            text = item.namePlayer
                        }
                    }
                    item.substitution.isNotEmpty() -> {
                        textHomeIn.run {
                            isVisible = true
                            text = item.substitution.split("|")[0]
                        }
                        textHomeOut.run {
                            isVisible = true
                            text = item.substitution.split("|")[1]
                        }
                    }
                    item.card.isNotEmpty() -> {
                        if (item.card == LineupConst.YELLOW_CARD) {
                            textHomeYellowCard.run {
                                isVisible = true
                                text = item.namePlayer
                            }
                        }
                        if (item.card == LineupConst.RED_CARD) {
                            textHomeRedCard.run {
                                isVisible = true
                                text = item.namePlayer
                            }
                        }
                    }
                }
            } else {
                viewAwayHorizontal.isVisible = true
                when {
                    item.score.isNotEmpty() -> {
                        textAwayGoal.run {
                            isVisible = true
                            text = item.namePlayer
                        }
                    }
                    item.substitution.isNotEmpty() -> {
                        textAwayIn.run {
                            isVisible = true
                            text = item.substitution.split("|")[0]
                        }
                        textAwayOut.run {
                            isVisible = true
                            text = item.substitution.split("|")[1]
                        }
                    }
                    item.card.isNotEmpty() -> {
                        if (item.card == LineupConst.YELLOW_CARD) {
                            textAwayYellowCard.run {
                                isVisible = true
                                text = item.namePlayer
                            }
                        }
                        if (item.card == LineupConst.RED_CARD) {
                            textAwayRedCard.run {
                                isVisible = true
                                text = item.namePlayer
                            }
                        }
                    }
                }
            }
        }
    }
}
