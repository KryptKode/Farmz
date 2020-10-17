package com.kryptkode.farmz.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.kryptkode.farmz.screens.login.view.LoginView
import com.kryptkode.farmz.screens.login.view.LoginViewImpl

class ViewFactory(private val layoutInflater: LayoutInflater) {

    fun getLoginView(parent: ViewGroup? = null): LoginView {
        return LoginViewImpl(layoutInflater, parent)
    }
}