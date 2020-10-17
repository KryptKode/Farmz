package com.kryptkode.farmz.screens.common.dialog

import androidx.fragment.app.DialogFragment
import com.kryptkode.farmz.app.di.controller.ControllerComponent
import com.kryptkode.farmz.screens.common.activity.BaseActivity

abstract class BaseDialog : DialogFragment() {
    val controllerComponent: ControllerComponent by lazy {
        (activity as BaseActivity).controllerComponent
    }
}