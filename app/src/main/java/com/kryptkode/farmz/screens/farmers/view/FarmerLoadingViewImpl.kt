package com.kryptkode.farmz.screens.farmers.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import com.kryptkode.farmz.app.utils.extension.beVisibleIf
import com.kryptkode.farmz.databinding.ItemFarmerFooterBinding

class FarmerLoadingViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmerLoadingView() {

    private val binding = ItemFarmerFooterBinding.inflate(layoutInflater, parent, false)

    init {
        binding.retryButton.setOnClickListener {
            onEachListener {
                it.onRetry()
            }
        }
    }

    override fun bindLoadState(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }

        binding.progressBar.beVisibleIf(loadState is LoadState.Loading)
        binding.retryButton.beVisibleIf(loadState !is LoadState.Loading)
        binding.errorMsg.beVisibleIf(loadState !is LoadState.Loading)
    }


    override val rootView: View
        get() = binding.root
}