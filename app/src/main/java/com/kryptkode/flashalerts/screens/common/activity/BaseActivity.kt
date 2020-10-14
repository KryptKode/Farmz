package com.kryptkode.flashalerts.screens.common.activity

import androidx.appcompat.app.AppCompatActivity
import com.kryptkode.flashalerts.app.App
import com.kryptkode.flashalerts.app.di.controller.ControllerComponent
import com.kryptkode.flashalerts.app.di.controller.ControllerModule

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