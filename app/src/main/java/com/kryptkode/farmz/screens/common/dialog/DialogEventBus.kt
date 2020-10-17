package com.kryptkode.farmz.screens.common.dialog

import com.kryptkode.farmz.app.common.BaseObservable

class DialogEventBus : BaseObservable<DialogEventBus.Listener>() {

    interface Listener {
        fun onDialogEvent(event: Any?)
    }

    fun postEvent(event: Any?) {
        onEachListener {
            it.onDialogEvent(event)
        }
    }
}