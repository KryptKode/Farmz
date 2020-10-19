package com.kryptkode.farmz.screens.dashboard.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.extension.beGone
import com.kryptkode.farmz.app.utils.extension.beGoneIf
import com.kryptkode.farmz.app.utils.extension.beVisible
import com.kryptkode.farmz.app.utils.extension.beVisibleIf
import com.kryptkode.farmz.databinding.LayoutDashboardBinding
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.farm.farmlist.view.FarmListAdapter
import com.kryptkode.farmz.screens.farm.model.UiFarm
import com.kryptkode.farmz.screens.farmers.loading.FarmerLoadStateAdapter

class DashboardViewMvcImpl(
    viewFactory: ViewFactory,
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : DashboardViewMvc() {

    private val binding = LayoutDashboardBinding.inflate(layoutInflater, parent, false)

    private val adapter = FarmListAdapter(viewFactory) { item ->
        onEachListener {
            it.onItemClick(item)
        }
    }

    init {

        binding.farms.btnViewAll.setOnClickListener {
            onEachListener {
                it.onViewAllFarmsClick()
            }
        }

        binding.farms.recyclerView.adapter = adapter

        adapter.withLoadStateHeaderAndFooter(
            header = FarmerLoadStateAdapter(viewFactory) { adapter.retry() },
            footer = FarmerLoadStateAdapter(viewFactory) { adapter.retry() }
        )


        adapter.addLoadStateListener { loadState ->
            binding.farms.progressBar.beVisibleIf((loadState.refresh is LoadState.Loading))
            binding.farms.dataGroup.beGoneIf((loadState.refresh is LoadState.Loading))
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

    override fun bindLastCapturedFarmers(count: Int) {
        binding.captureFarmerCard.tvFarmsCount.text = count.toString()
    }

    override fun bindLastCapturedFarmersDate(date: String) {
        binding.captureFarmerCard.tvLastDate.text = getString(R.string.last_captured, date)
    }

    override fun bindLastCapturedFarms(count: Int) {
        binding.capturedFarmCard.tvFarmsCount.text = count.toString()
    }

    override fun bindLastCapturedFarmsDate(date: String) {
        binding.capturedFarmCard.tvLastDate.text = getString(R.string.last_captured, date)
    }

    override suspend fun bindFarms(data: PagingData<UiFarm>) {
        adapter.submitData(data)
    }

    override fun showLastCapturedFarmersLoading() {
        binding.captureFarmerCard.dataGroup.beGone()
        binding.captureFarmerCard.progressBar.beVisible()
    }

    override fun hideLastCapturedFarmersLoading() {
        binding.captureFarmerCard.dataGroup.beVisible()
        binding.captureFarmerCard.progressBar.beGone()
    }

    override fun showLastCapturedFarmsLoading() {
        binding.capturedFarmCard.dataGroup.beGone()
        binding.capturedFarmCard.progressBar.beVisible()
    }

    override fun hidLastCapturedFarmsLoading() {
        binding.capturedFarmCard.dataGroup.beVisible()
        binding.capturedFarmCard.progressBar.beGone()
    }

    override val rootView: View
        get() = binding.root
}