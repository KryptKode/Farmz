package com.kryptkode.farmz.screens.common.fragment

import androidx.fragment.app.Fragment
import com.kryptkode.farmz.app.di.controller.ControllerComponent
import com.kryptkode.farmz.screens.common.activity.BaseActivity

/**
 * Created by kryptkode on 5/21/2020.
 */
abstract class BaseFragment : Fragment() {
    val controllerComponent: ControllerComponent by lazy {
        (activity as BaseActivity).controllerComponent
    }
}