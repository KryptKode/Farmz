package com.kryptkode.farmz.screens.farmers.loading

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.farmers.view.FarmerLoadingView

class FarmerLoadStateAdapter ( private val viewFactory: ViewFactory, private val retry: () -> Unit
) : LoadStateAdapter<FarmerLoadStateAdapter.FarmerLoadStateViewHolder>(),
    FarmerLoadingView.Listener {

    override fun onBindViewHolder(holder: FarmerLoadStateViewHolder, loadState: LoadState) {
        holder.view.bindLoadState(loadState)
    }
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FarmerLoadStateViewHolder {
        val viewHolder = FarmerLoadStateViewHolder.create(viewFactory.getFarmerLoadingView(parent))
        viewHolder.view.registerListener(this)
        return viewHolder
    }

    class FarmerLoadStateViewHolder (val view: FarmerLoadingView) : RecyclerView.ViewHolder(view.rootView) {
        companion object {
            fun create(view: FarmerLoadingView): FarmerLoadStateViewHolder {
                return FarmerLoadStateViewHolder(view)
            }
        }
    }

    override fun onRetry() {
        retry()
    }
}