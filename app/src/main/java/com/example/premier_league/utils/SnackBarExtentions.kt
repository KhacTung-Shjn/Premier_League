package com.example.premier_league.utils

import android.view.View
import com.example.premier_league.R
import com.google.android.material.snackbar.Snackbar

fun View.make(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).setAnchorView(R.id.bottomNavigationView).show()
}
