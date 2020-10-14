package com.kryptkode.flashalerts.screens.splash

import android.os.Bundle
import com.kryptkode.flashalerts.screens.common.activity.BaseActivity

/**
 * Created by kryptkode on 5/21/2020.
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        controllerComponent.inject(this)
        super.onCreate(savedInstanceState)

    }
}