package com.example.premier_league.ui.setting

import android.net.Uri
import com.example.premier_league.base.BasePresenter

interface SettingContract {
    interface View{
        fun showAboutApp()
        fun shareWithFriend(message: String, attachment : Uri)
        fun feedBack(subject: String?)
        fun changeLanguage()
    }

    interface Presenter: BasePresenter {
    }
}
