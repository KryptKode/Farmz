package com.kryptkode.flashalerts.screens.home

import android.os.Bundle
import com.kryptkode.flashalerts.R
import com.kryptkode.flashalerts.screens.common.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        controllerComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
