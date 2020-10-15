package com.kryptkode.farmz.screens.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.kryptkode.farmz.app.App
import com.kryptkode.farmz.app.di.controller.ControllerComponent
import com.kryptkode.farmz.app.di.controller.ControllerModule

/**
 * Created by kryptkode on 5/21/2020.
 */
abstract class BaseActivity : AppCompatActivity() {

    val controllerComponent: ControllerComponent by lazy {
        (application as App).applicationComponent.controllerComponent(
            ControllerModule(
                this,
                supportFragmentManager
            )
        )
    }
}