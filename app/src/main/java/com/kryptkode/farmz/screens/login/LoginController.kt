package com.kryptkode.farmz.screens.login

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.kryptkode.farmz.app.utils.ToastHelper
import com.kryptkode.farmz.app.utils.Validator
import com.kryptkode.farmz.app.utils.livedata.extension.observeEvent
import com.kryptkode.farmz.navigation.login.LoginNavigator
import com.kryptkode.farmz.screens.login.view.LoginView
import javax.inject.Inject

class LoginController @Inject constructor(
    private val loginNavigator: LoginNavigator,
    private val toastHelper: ToastHelper,
    private val validator: Validator
) : LifecycleObserver, LoginView.Listener {

    private lateinit var viewModel: LoginViewModel
    private lateinit var lifeCycleOwner: LifecycleOwner
    private lateinit var loginView: LoginView

    fun bindView(loginView: LoginView) {
        this.loginView = loginView
    }

    fun bindLifeCycleOwner(lifecycleOwner: LifecycleOwner) {
        this.lifeCycleOwner = lifecycleOwner
    }

    fun bindViewModel(viewModel: LoginViewModel) {
        this.viewModel = viewModel
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        loginView.registerListener(this)
        setupObservers()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        loginView.unregisterListener(this)
    }

    private fun setupObservers() {

        viewModel.showLoading.observeEvent(lifeCycleOwner){
            loginView.showLoading()
        }

        viewModel.hideLoading.observeEvent(lifeCycleOwner){
            loginView.hideLoading()
        }

        viewModel.goToNext.observeEvent(lifeCycleOwner){
            loginNavigator.loginToHome()
        }

        viewModel.showErrorMessage.observeEvent(lifeCycleOwner){
            toastHelper.showMessage(it)
        }

        validator.passwordError.observeEvent(lifeCycleOwner) {
            loginView.showPasswordError(it)
        }

        validator.emailError.observeEvent(lifeCycleOwner) {
            loginView.showEmailError(it)
        }
    }

    override fun onLoginClick(email: String, password: String) {
        loginView.clearErrors()
        if (validator.validateLogin(email, password)) {
            viewModel.login(email, password)
        }
    }
}