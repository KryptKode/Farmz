package com.kryptkode.farmz.navigation.login

import com.kryptkode.farmz.R
import com.kryptkode.farmz.navigation.NavControllerProvider
import javax.inject.Inject

class LoginNavigatorImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider
) : LoginNavigator {

    override fun loginToHome() {
        navControllerProvider.getNavController().navigate(R.id.action_LoginFragment_to_farmers)
    }

}