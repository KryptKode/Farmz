package com.kryptkode.farmz.app.utils.extension

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView

fun TextView.imeListener(listener:()->Unit){
    setOnEditorActionListener { _, actionId, event ->
        if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
            if (event.action == KeyEvent.ACTION_UP) {
                listener()
                true
            }
        } else if (actionId == EditorInfo.IME_ACTION_DONE) {
            listener()
            true
        }
        false
    }
}