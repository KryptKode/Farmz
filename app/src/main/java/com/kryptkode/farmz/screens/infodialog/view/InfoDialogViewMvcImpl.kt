package com.kryptkode.farmz.screens.infodialog.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.kryptkode.farmz.R
import com.kryptkode.farmz.databinding.DialogInfoBinding
import com.kryptkode.farmz.screens.infodialog.Info

class InfoDialogViewMvcImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup? = null
) : InfoDialogViewMvc() {

    private val binding = DialogInfoBinding.inflate(layoutInflater, parent, false)

    init {
        initListeners()
    }

    private fun initListeners() {
        binding.btnCancel.setOnClickListener {
            onEachListener {
                it.onNegativeButtonClick()
            }
        }

        binding.btnNeutral.setOnClickListener {
            onEachListener {
                it.onNeutralButtonClick()
            }
        }

        binding.btnOk.setOnClickListener {
            onEachListener {
                it.onPositiveButtonClick()
            }
        }
    }

    override fun bindInfo(info: Info) {
        binding.dialogTitle.text = info.title
        binding.dialogMessage.text = info.message

        binding.btnOk.text = info.positiveButtonText ?: getString(R.string.dialog_btn_ok_text)
        binding.btnCancel.text = info.negativeButtonText ?: getString(R.string.dialog_btn_cancel_text)
        binding.btnNeutral.text = info.neutralButtonText

        binding.btnOk.isVisible = info.showPositiveButton
        binding.btnCancel.isVisible = info.showNegativeButton
        binding.btnNeutral.isVisible = info.showNeutralButton
    }

    override val rootView: View
        get() = binding.root
}