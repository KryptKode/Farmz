package com.kryptkode.farmz.screens.editfarmer.editcontactdetails.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.databinding.LayoutEditContactDetailsBinding
import com.kryptkode.farmz.screens.farmers.model.FarmerView

class EditContactDetailsViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : EditContactDetailsView() {

    private val binding = LayoutEditContactDetailsBinding.inflate(layoutInflater, parent, false)

    private lateinit var farmer: FarmerView

    init {
        binding.emailEditText.addTextChangedListener {
            clearEmailError()
        }

        binding.mobileEditText.addTextChangedListener {
            clearMobileError()
        }

        binding.fabSave.setOnClickListener {
            onEachListener {
                it.onSave(
                    farmer.copy(
                        mobileNumber = binding.mobileEditText.text.toString(),
                        emailAddress = binding.emailEditText.text.toString(),

                        )
                )
            }
        }

        binding.tvTitle.setOnClickListener {
            onEachListener {
                it.onBackClick()
            }
        }
    }

    @SuppressLint("DefaultLocale")
    override fun bindFarmer(farmer: FarmerView) {
        this.farmer = farmer
        binding.mobileEditText.setText(farmer.mobileNumber)
        binding.emailEditText.setText(farmer.emailAddress.toLowerCase())
    }

    override fun showMobileError(message: String) {
        binding.mobileInput.error = message
    }

    override fun showEmailError(message: String) {
        binding.emailInput.error = message
    }

    override fun clearErrors() {

    }

    override fun hideLoading() {
        binding.progress.root.beGone()
        binding.fabSave.isEnabled = true
    }

    override fun showLoading() {
        binding.progress.root.beVisible()
        binding.fabSave.isEnabled = false
    }

    private fun clearEmailError() {
        binding.emailInput.error = null
    }

    private fun clearMobileError() {
        binding.mobileInput.error = null
    }

    override val rootView: View
        get() = binding.root
}