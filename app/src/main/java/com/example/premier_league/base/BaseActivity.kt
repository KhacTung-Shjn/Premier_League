package com.example.premier_league.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.premier_league.R
import com.example.premier_league.utils.showToast

abstract class BaseActivity : AppCompatActivity(), BaseView {

    @get: LayoutRes
    protected abstract val layoutResource: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initComponents()
    }

    protected abstract fun initComponents()

    protected fun openFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit()

    override fun showMessage(data: Any) {
        baseContext.showToast(data)
    }
}
