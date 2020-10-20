package com.kryptkode.flashalerts.screens.common.infodialog

import com.kryptkode.farmz.screens.infodialog.Info
import com.kryptkode.farmz.screens.infodialog.InfoDialog

class InfoDialogBuilder {
    private var listener: InfoDialog.Listener? = null
    private val info = Info("", "")

    fun title(title: String): InfoDialogBuilder {
        info.title = title
        return this
    }

    fun message(message: String): InfoDialogBuilder {
        info.message = message
        return this
    }


    fun payload(payload: String): InfoDialogBuilder {
        info.payload = payload
        return this
    }


    fun positiveButtonText(text: String): InfoDialogBuilder {
        info.positiveButtonText = text
        return this
    }

    fun negativeButtonText(text: String): InfoDialogBuilder {
        info.negativeButtonText = text
        return this
    }

    fun neutralButtonText(text: String): InfoDialogBuilder {
        info.neutralButtonText = text
        return this
    }

    fun showPositiveButton(show: Boolean): InfoDialogBuilder {
        info.showPositiveButton = show
        return this
    }

    fun showNegativeButton(show: Boolean): InfoDialogBuilder {
        info.showNegativeButton = show
        return this
    }

    fun showNeutralButton(show: Boolean): InfoDialogBuilder {
        info.showNeutralButton = show
        return this
    }

    fun setListener(listener: InfoDialog.Listener): InfoDialogBuilder {
        this.listener = listener
        return this
    }

    fun buildInfo(): Info {
        return info
    }

    fun build(): InfoDialog {
        return InfoDialog.newInstance(info, listener)
    }
}