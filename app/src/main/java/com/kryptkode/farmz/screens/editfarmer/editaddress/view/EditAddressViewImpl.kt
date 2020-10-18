package com.kryptkode.farmz.screens.editfarmer.editaddress.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.databinding.LayoutEditAddressBinding
import com.kryptkode.farmz.screens.farmers.model.FarmerView

class EditAddressViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : EditAddressView() {

    private val binding = LayoutEditAddressBinding.inflate(layoutInflater, parent, false)

    private lateinit var farmer: FarmerView

    init {
        binding.addressEditText.addTextChangedListener {
            clearAddressError()
        }

        binding.cityEditText.addTextChangedListener {
            clearCityError()
        }

        binding.lgaEditText.addTextChangedListener {
            clearLgaError()
        }

        binding.stateEditText.addTextChangedListener {
            clearStateError()
        }

        binding.fabSave.setOnClickListener {
            onEachListener {
                it.onSave(
                    farmer.copy(
                        address = binding.addressEditText.text.toString(),
                        city = binding.cityEditText.text.toString(),
                        lga = binding.lgaEditText.text.toString(),
                        state = binding.stateEditText.text.toString(),
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
        binding.addressEditText.setText(farmer.address.capitalize())
        binding.cityEditText.setText(farmer.city.capitalize())
        binding.lgaEditText.setText(farmer.lga.capitalize())
        binding.stateEditText.setText(farmer.state.capitalize())
    }

    override fun showAddressError(message: String) {
        binding.addressInput.error = message
    }

    override fun showCityError(message: String) {
        binding.cityInput.error = message
    }

    override fun showLgaError(message: String) {
        binding.lgaInput.error = message
    }

    override fun showStateError(message: String) {
        binding.stateInput.error = message
    }

    override fun clearErrors() {
        clearAddressError()
        clearCityError()
        clearLgaError()
        clearStateError()
    }

    override fun hideLoading() {
        binding.progress.root.beGone()
        binding.fabSave.isEnabled = true
    }

    override fun showLoading() {
        binding.progress.root.beVisible()
        binding.fabSave.isEnabled = false
    }

    private fun clearAddressError() {
        binding.addressInput.error = null
    }

    private fun clearCityError() {
        binding.cityInput.error = null
    }

    private fun clearLgaError() {
        binding.lgaInput.error = null
    }

    private fun clearStateError() {
        binding.stateInput.error = null
    }


    override val rootView: View
        get() = binding.root
}