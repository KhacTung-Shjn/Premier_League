package com.example.premier_league.base

interface BaseView {
    fun showLoading(){}
    fun hideLoading(){}
    fun showMessage(data: Any) {}
}
