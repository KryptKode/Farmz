package com.kryptkode.farmz.app.utils.extension

import android.app.Activity
import android.view.ViewGroup
import androidx.core.view.children
import com.google.android.material.textfield.TextInputLayout

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