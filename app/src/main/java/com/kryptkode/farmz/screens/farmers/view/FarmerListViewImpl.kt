package com.kryptkode.farmz.screens.farmers.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.extension.beVisibleIf
import com.kryptkode.farmz.databinding.LayoutFarmersBinding
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.farmers.list.FarmersListAdapter
import com.kryptkode.farmz.screens.farmers.loading.FarmerLoadStateAdapter
import com.kryptkode.farmz.screens.farmers.model.FarmerView

class FarmerListViewImpl(
    viewFactory: ViewFactory,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : FarmerListView() {

    private val binding = LayoutFarmersBinding.inflate(layoutInflater, parent, false)

    private val adapter = FarmersListAdapter(viewFactory) { item ->
        onEachListener {
            it.onItemClick(item)
        }
    }

    init {

        binding.retryButton.setOnClickListener { adapter.retry() }
        binding.swipeRefresh.setOnRefreshListener { adapter.refresh() }

        binding.recyclerView.adapter = adapter
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

    override suspend fun bindFarmers(items: PagingData<FarmerView>) {
        adapter.submitData(items)
    }

    override val rootView: View
        get() = binding.root
}