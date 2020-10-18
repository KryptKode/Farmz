package com.kryptkode.farmz.app.utils.extension

import android.text.InputFilter
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import com.kryptkode.farmz.R

fun TextView.imeListener(listener: () -> Unit) {
    setOnEditorActionListener { _, actionId, event ->
        if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
            if (event.action == KeyEvent.ACTION_UP) {
                listener()
            }
        } else if (actionId == EditorInfo.IME_ACTION_DONE) {
            listener()
        }
        false
    }
}

fun AutoCompleteTextView.bindData(texts: List<String>?) {
    val adapter =
        ArrayAdapter(context, R.layout.drop_down_menu_popup_item, texts ?: listOf())
    setAdapter(adapter)
}

fun EditText.setCapsInputFilter() {
    val existingFilters = filters
    val newFilters = existingFilters.copyOf(existingFilters.size + 1)
    newFilters[existingFilters.size] = InputFilter.AllCaps()
    filters = newFilters
}