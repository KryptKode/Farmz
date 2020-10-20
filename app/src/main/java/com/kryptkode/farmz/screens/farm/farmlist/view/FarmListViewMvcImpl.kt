package com.kryptkode.farmz.screens.farm.farmlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.extension.beVisibleIf
import com.kryptkode.farmz.databinding.LayoutFarmsBinding
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.farm.model.UiFarm
import com.kryptkode.farmz.screens.farmers.loading.FarmerLoadStateAdapter

class FarmListViewMvcImpl(
    viewFactory: ViewFactory,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmListViewMvc() {
    private val binding = LayoutFarmsBinding.inflate(layoutInflater, parent, false)

    private val adapter = FarmListAdapter(viewFactory) { item ->
        onEachListener {
            it.onItemClick(item)
        }
    }

    init {
        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            onEachListener {
                adapter.refresh()
            }
        }

        adapter.withLoadStateHeaderAndFooter(
            header = FarmerLoadStateAdapter(viewFactory) { adapter.retry() },
            footer = FarmerLoadStateAdapter(viewFactory) { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            // Show loading spinner during initial load or refresh.
            binding.swipeRefresh.isRefreshing = (loadState.refresh is LoadState.Loading)
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.beVisibleIf(loadState.refresh is LoadState.Error)

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let { error ->
                onEachListener {
                    it.onLoadError(error.error.message ?: getString(R.string.error_message_generic))
                }
            }
        }
    }

    override suspend fun bindFarms(farms: PagingData<UiFarm>) {
        adapter.submitData(farms)
    }

    override val rootView: View
        get() = binding.root
}