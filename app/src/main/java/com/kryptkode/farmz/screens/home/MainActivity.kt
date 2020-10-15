package com.kryptkode.farmz.screens.home

import android.os.Bundle
import com.kryptkode.farmz.R
import com.kryptkode.farmz.screens.common.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        controllerComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
