package com.kryptkode.farmz.screens.infodialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kryptkode.farmz.screens.common.DialogViewFactory
import com.kryptkode.farmz.screens.common.dialog.BaseDialog
import com.kryptkode.farmz.screens.common.dialog.DialogEventBus
import com.kryptkode.farmz.screens.infodialog.view.InfoDialogViewMvc
import javax.inject.Inject

class InfoDialog : BaseDialog(), InfoDialogViewMvc.Listener {

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var dialogViewFactory: DialogViewFactory

    private lateinit var viewMvc: InfoDialogViewMvc

    private var listener: Listener? = null

    private val args by navArgs<InfoDialogArgs>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        viewMvc = dialogViewFactory.getInfoDialogView()
        bindViews()
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(viewMvc.rootView)
        return dialog.create()
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
    }

    private fun bindViews() {
        viewMvc.bindInfo(args.infoParms)
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    override fun onPositiveButtonClick() {
        listener?.onPositiveButtonClick()
        dialogEventBus.postEvent(InfoEvent(InfoEvent.Button.POSITIVE, args.infoParms.payload))
        dismiss()
    }

    override fun onNeutralButtonClick() {
        listener?.onNeutralButtonClick()
        dialogEventBus.postEvent(InfoEvent(InfoEvent.Button.NEUTRAL, args.infoParms.payload))
        dismiss()
    }

    override fun onNegativeButtonClick() {
        listener?.onNegativeButtonClick()
        dialogEventBus.postEvent(InfoEvent(InfoEvent.Button.NEGATIVE, args.infoParms.payload))
        dismiss()
    }

    abstract class Listener {
        open fun onPositiveButtonClick() {}
        open fun onNeutralButtonClick() {}
        open fun onNegativeButtonClick() {}
    }

    companion object {
        private const val INFO_KEY = "info.key"
        fun newInstance(info: Info, listener: Listener? = null): InfoDialog {
            val dialog = InfoDialog()
            dialog.listener = listener
            val args = Bundle()
            args.putParcelable(INFO_KEY, info)
            dialog.arguments = args
            return dialog
        }
    }
}