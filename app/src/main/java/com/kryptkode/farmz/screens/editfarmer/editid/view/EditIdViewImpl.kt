package com.kryptkode.farmz.screens.editfarmer.editid.view

import android.annotation.SuppressLint
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.app.utils.extension.bindData
import com.kryptkode.farmz.databinding.LayoutEditIdBinding
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.farmers.model.FarmerView

class EditIdViewImpl(
    private val imageLoader: ImageLoader,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : EditIdView() {

    private val binding = LayoutEditIdBinding.inflate(layoutInflater, parent, false)

    private lateinit var farmer: FarmerView

    init {

        binding.imagePic.setOnClickListener {
            onEachListener {
                it.onChangeIdPic()
            }
        }

        binding.imageFingerprint.setOnClickListener {
            onEachListener {
                it.onChangeFingerPrintPic()
            }
        }

        binding.fabSave.setOnClickListener {
            onEachListener {
                it.onSave(
                    farmer.copy(
                        idType = binding.idTypeEditText.text.toString(),
                        idNumber = binding.idNumEditText.text.toString(),
                        issueDate = binding.issueDateEditText.text.toString(),
                        expiryDate = binding.expiryDateEditText.text.toString(),
                        registrationNumber = binding.regNoEditText.text.toString(),
                        bvn = binding.bvnEditText.text.toString()
                    )
                )
            }
        }

        binding.tvTitle.setOnClickListener {
            onEachListener {
                it.onBackClick()
            }
        }

        binding.issueDateEditText.setOnClickListener {
            onEachListener {
                it.onChooseIssueDate(binding.issueDateEditText.text.toString())
            }
        }

        binding.issueDateEditText.inputType = InputType.TYPE_NULL


        binding.expiryDateEditText.setOnClickListener {
            onEachListener {
                it.onChooseExpiryDate(binding.expiryDateEditText.text.toString())
            }
        }

        binding.expiryDateEditText.inputType = InputType.TYPE_NULL

        binding.idTypeEditText.addTextChangedListener {
            clearIdTypeError()
        }

        binding.idNumEditText.addTextChangedListener {
            clearIdNumberError()
        }

        binding.issueDateEditText.addTextChangedListener {
            clearIdIssueDateError()
        }

        binding.expiryDateEditText.addTextChangedListener {
            clearIdExpiryDateError()
        }

        binding.regNoEditText.addTextChangedListener {
            clearRegNumError()
        }

        binding.bvnEditText.addTextChangedListener {
            clearBvnError()
        }
    }

    @SuppressLint("DefaultLocale")
    override fun bindFarmer(farmer: FarmerView) {
        this.farmer = farmer
        binding.idTypeEditText.setText(farmer.idType.capitalize(), false)
        binding.idNumEditText.setText(farmer.idNumber.capitalize())
        binding.issueDateEditText.setText(farmer.issueDate.capitalize())
        binding.expiryDateEditText.setText(farmer.expiryDate.capitalize())
        binding.regNoEditText.setText(farmer.registrationNumber.capitalize())
        binding.bvnEditText.setText(farmer.bvn.capitalize())

        bindIdPhoto(farmer.idImage)
        bindFingerPrintPhoto(farmer.fingerprint)
    }

    override fun bindFingerPrintPhoto(photoUri: String) {
        imageLoader.load(photoUri, binding.imageFingerprint)
    }

    override fun bindIdPhoto(photoUri: String) {
        imageLoader.load(photoUri, binding.imagePic)
    }

    override fun showBvnError(message: String) {
        binding.bvnInput.error = message
    }

    override fun showRegNumError(message: String) {
        binding.regNoInput.error = message
    }

    override fun showIdTypeError(message: String) {
        binding.idTypeInput.error = message
    }

    override fun showIdNumberError(message: String) {
        binding.idNumInput.error = message
    }

    override fun showIdExpiryDateError(message: String) {
        binding.expiryDateInput.error = message
    }

    override fun showIdIssueDateError(message: String) {
        binding.issueDateInput.error = message
    }

    override fun bindIdTypeItems(idTypes: List<String>) {
        binding.idTypeEditText.bindData(idTypes)
    }

    override fun onIssueDateSelected(date: String) {
        binding.issueDateEditText.setText(date)
    }

    override fun onExpiryDateSelected(date: String) {
        binding.expiryDateEditText.setText(date)
    }

    override fun clearErrors() {
        clearIdTypeError()
        clearIdNumberError()
        clearIdIssueDateError()
        clearIdExpiryDateError()
        clearRegNumError()
        clearBvnError()
    }

    private fun clearIdTypeError() {
        binding.idTypeInput.error = null
    }

    private fun clearIdNumberError() {
        binding.idNumInput.error = null
    }

    private fun clearIdIssueDateError() {
        binding.issueDateInput.error = null
    }

    private fun clearIdExpiryDateError() {
        binding.expiryDateInput.error = null
    }

    private fun clearRegNumError() {
        binding.regNoInput.error = null
    }

    private fun clearBvnError() {
        binding.bvnInput.error = null
    }

    override fun hideLoading() {
        binding.progress.root.beGone()
        binding.fabSave.isEnabled = true
    }

    override fun showLoading() {
        binding.progress.root.beVisible()
        binding.fabSave.isEnabled = false
    }


    override val rootView: View
        get() = binding.root
}