package com.kryptkode.farmz.screens.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.common.fragment.BaseFragment
import javax.inject.Inject

class LoginFragment : BaseFragment(){

    @Inject
    lateinit var viewFactory: ViewFactory

    @Inject
    lateinit var loginController: LoginController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loginView = viewFactory.getLoginView(container)
        lifecycle.addObserver(loginController)
        loginController.bindView(loginView)
        loginController.bindLifeCycleOwner(viewLifecycleOwner)
        loginController.bindViewModel(viewModel)
        return loginView.rootView
    }
}
