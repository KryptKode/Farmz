package com.kryptkode.farmz.app.utils.extension

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

/**
 * Helper method for creation of [ViewModel], that takes the class as a parameter because using
 * [androidx.fragment.app.viewModels] cannot resolve the reified generic
 */
@MainThread
inline fun <VM : ViewModel> Fragment.viewModels(
    viewModelClass: KClass<VM>,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
) = createViewModelLazy(viewModelClass, { this.viewModelStore }, factoryProducer)