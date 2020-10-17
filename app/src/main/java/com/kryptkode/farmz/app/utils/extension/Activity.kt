package com.kryptkode.farmz.app.utils.extension

import android.app.Activity
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.core.view.children
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import kotlin.reflect.KClass

/**
 * Created by kryptkode on 11/8/2019.
 */
fun Activity.hideKeyBoardOnTouchOfNonEditableViews() {
    val root = findViewById<ViewGroup>(android.R.id.content)
    for (view in root.children) {
        if (view !is TextInputLayout) {
            view.setOnTouchListener { _, _ ->
                view.hideKeyboard()
                false
            }
        }
    }
}

/**
 * Helper method for creation of [ViewModel], that takes the class as a parameter because using
 * [androidx.fragment.app.viewModels] cannot resolve the reified generic
 */
@MainThread
inline fun < VM : ViewModel> ComponentActivity.viewModels(
    viewModelClass: KClass<VM>,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(viewModelClass, { viewModelStore }, factoryPromise)
}