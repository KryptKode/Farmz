package com.kryptkode.farmz.screens.farm.capturefarm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.kryptkode.farmz.app.utils.extension.*
import com.kryptkode.farmz.databinding.LayoutCaptureBinding
import com.kryptkode.farmz.screens.farm.model.UiFarmLocation

class CaptureFarmViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : CaptureFarmView() {

    private val binding = LayoutCaptureBinding.inflate(layoutInflater, parent, false)

    private var points: MutableList<LatLng> = mutableListOf()

    init {

        initMap()

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


    private fun initMap() {
        binding.map.getMapAsync { map ->
            map.disableUserInput()
            if (points.isNotEmpty()) {
                val latLngBounds = LatLngBounds.builder()
                points.onEach {
                    latLngBounds.include(it)
                }
                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        latLngBounds.build().center,
                        16f
                    )
                )
                map.drawPolygon(binding.root.context, points)

                map.setOnMapClickListener {
                    onEachListener {
                        it.onAddLocation()
                    }
                }
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

    override fun getMap(): MapView {
        return binding.map
    }

    private fun clearLocationError() {
        binding.farmLocationInput.error = null
    }

    private fun clearNameError() {
        binding.farmNameInput.error = null
    }

    override fun onSelectLocation(locations: List<UiFarmLocation>) {
        points.clear()
        points.addAll(locations.map {
            LatLng(it.latitude, it.longitude)
        })

        binding.map.beVisibleIf(points.isNotEmpty())
    }

    override val rootView: View
        get() = binding.root
}
