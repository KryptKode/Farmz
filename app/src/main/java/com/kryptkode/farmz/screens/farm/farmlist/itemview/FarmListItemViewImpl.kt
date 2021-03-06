package com.kryptkode.farmz.screens.farm.farmlist.itemview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kryptkode.farmz.databinding.ItemFarmBinding
import com.kryptkode.farmz.screens.farm.model.UiFarm

class FarmListItemViewImpl (
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmListItemView() {

    private val binding = ItemFarmBinding.inflate(layoutInflater, parent, false)

    @SuppressLint("DefaultLocale")
    override fun bind(item: UiFarm?) {
        if(item != null){
            binding.locationTextView.text = item.location.capitalize()
            binding.nameTextView.text = item.name.capitalize()

            binding.root.setOnClickListener {
                onEachListener {
                    it.onFarmClick(item)
                }
            }
        }
    }

    override val rootView: View
        get() = binding.root
}