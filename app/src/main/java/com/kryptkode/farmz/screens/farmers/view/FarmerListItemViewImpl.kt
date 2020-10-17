package com.kryptkode.farmz.screens.farmers.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.databinding.ItemFarmerBinding
import com.kryptkode.farmz.screens.common.imageloader.ImageLoader
import com.kryptkode.farmz.screens.farmers.model.FarmerView

class FarmerListItemViewImpl(
    private val imageLoader: ImageLoader,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmerListItemView() {

    private val binding = ItemFarmerBinding.inflate(layoutInflater, parent, false)

    @SuppressLint("DefaultLocale")
    override fun bindFarmer(item: FarmerView?) {
        if (item != null) {
            binding.shimmer.shimmerRoot.beGone()
            binding.shimmer.shimmerRoot.stopShimmer()
            imageLoader.load(item.passportPhoto, binding.image)
            binding.nameTextView.text = item.firstName.capitalize().plus(item.surname.capitalize())
            binding.locationTextView.text =
                item.state.capitalize().plus(",").plus(item.city.capitalize())
            binding.root.setOnClickListener {
                onEachListener {
                    it.onItemClick(item)
                }
            }
        } else {
            binding.shimmer.shimmerRoot.beVisible()
            binding.shimmer.shimmerRoot.startShimmer()
        }
    }

    override val rootView: View
        get() = binding.root
}