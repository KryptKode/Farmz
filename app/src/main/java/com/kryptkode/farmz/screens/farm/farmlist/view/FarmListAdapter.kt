package com.kryptkode.farmz.screens.farm.farmlist.view

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kryptkode.farmz.screens.common.ViewFactory
import com.kryptkode.farmz.screens.farm.farmlist.itemview.FarmListItemView
import com.kryptkode.farmz.screens.farm.model.UiFarm

class FarmListAdapter (
    private val viewFactory: ViewFactory,
    private val onClickItem: (UiFarm) -> Unit
) : PagingDataAdapter<UiFarm, FarmListAdapter.FarmListViewHolder>(DIFF_UTIL), FarmListItemView.Listener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmListViewHolder {
        val viewHolder = FarmListViewHolder(viewFactory.getFarmItemView(parent))
        viewHolder.farmerListItemView.registerListener(this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: FarmListViewHolder, position: Int) {
        holder.farmerListItemView.bind(getItem(position))
    }

    class FarmListViewHolder(val farmerListItemView: FarmListItemView) :
        RecyclerView.ViewHolder(farmerListItemView.rootView)

    companion object {
        val DIFF_UTIL = object: DiffUtil.ItemCallback<UiFarm>(){
            override fun areContentsTheSame(oldItem: UiFarm, newItem: UiFarm): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: UiFarm, newItem: UiFarm): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onFarmClick(item: UiFarm) {
        onClickItem(item)
    }
}