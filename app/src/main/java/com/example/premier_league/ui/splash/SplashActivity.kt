package com.example.premier_league.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.premier_league.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(MainActivity.getIntent(this))
        finish()
    }
}
