package com.kryptkode.farmz.screens.login.view

import com.kryptkode.farmz.screens.common.view.BaseObservableViewMvc

abstract class LoginView : BaseObservableViewMvc<LoginView.Listener>() {

    interface Listener {
        fun onLoginClick(email: String, password: String)
    }

    abstract fun showEmailError(message:String)
    abstract fun showPasswordError(message:String)
    abstract fun clearErrors()
    abstract fun showLoading()
    abstract fun hideLoading()
}