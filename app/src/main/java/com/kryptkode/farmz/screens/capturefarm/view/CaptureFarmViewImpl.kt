package com.kryptkode.farmz.screens.capturefarm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.databinding.LayoutCaptureBinding
import com.kryptkode.farmz.screens.capturefarm.model.UiFarmLocation

class CaptureFarmViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : CaptureFarmView(), OnMapReadyCallback {

    private val binding = LayoutCaptureBinding.inflate(layoutInflater, parent, false)

    private var googleMap: GoogleMap? = null

    init {

        binding.mapView.getMapAsync(this)

        binding.farmLocationEditText.addTextChangedListener {
            clearLocationError()
        }

        binding.farmNameEditText.addTextChangedListener {
            clearNameError()
        }

        binding.fabSave.setOnClickListener {
            onEachListener {
                it.onSave(
                    binding.farmNameEditText.text.toString(),
                    binding.farmLocationEditText.text.toString()
                )
            }
        }

        binding.tvTitle.setOnClickListener {
            onEachListener {
                it.onBackClick()
            }
        }

        binding.farmLocationCard.setOnClickListener {
            onEachListener {
                it.onAddLocation()
            }
        }

    }

    override fun showLocationError(message: String) {
        binding.farmLocationInput.error = message
    }

    override fun showNameError(message: String) {
        binding.farmNameInput.error = message
    }

    override fun hideLoading() {
        binding.progress.root.beGone()
        binding.fabSave.isEnabled = true
    }

    override fun showLoading() {
        binding.progress.root.beVisible()
        binding.fabSave.isEnabled = false
    }

    override fun clearErrors() {
        clearLocationError()
        clearNameError()
    }

    private fun clearLocationError() {
        binding.farmLocationInput.error = null
    }

    private fun clearNameError() {
        binding.farmNameInput.error = null
    }

    override fun onSelectLocation(locations: List<UiFarmLocation>) {
        binding.mapView.beVisible()

    }

    override val rootView: View
        get() = binding.root

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map
        googleMap?.disableUserInput()

    }

    private fun GoogleMap.disableUserInput() {
        googleMap?.uiSettings?.isZoomControlsEnabled = false
        googleMap?.uiSettings?.isZoomGesturesEnabled = false
        googleMap?.uiSettings?.isCompassEnabled = false
        googleMap?.uiSettings?.isScrollGesturesEnabledDuringRotateOrZoom = false
        googleMap?.uiSettings?.setAllGesturesEnabled(false)
    }
}