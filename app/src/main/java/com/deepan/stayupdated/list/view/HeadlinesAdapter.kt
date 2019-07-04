package com.deepan.stayupdated.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.deepan.stayupdated.R
import com.deepan.stayupdated.list.model.Headline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.launch

class HeadlinesAdapter(var headlines: ArrayList<Headline>) : RecyclerView.Adapter<VHHeadline>() {

    lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHHeadline {
        ctx = parent.context
        return VHHeadline(LayoutInflater.from(parent.context).inflate(R.layout.headline_item, parent, false))
    }

    override fun getItemCount(): Int = headlines.size

    override fun onBindViewHolder(holder: VHHeadline, position: Int) {
        holder.setData(ctx, headlines[holder.adapterPosition])
    }

    lateinit var diffResult: DiffUtil.DiffResult
    fun updateItems(newList: ArrayList<Headline>) {
        diffResult = DiffUtil.calculateDiff(PostsDiffCallback(this@HeadlinesAdapter.headlines, newList))
        dispatchItems(newList, diffResult)
    }

    private fun dispatchItems(newList: ArrayList<Headline>, diffResult: DiffUtil.DiffResult) {
        diffResult.dispatchUpdatesTo(this@HeadlinesAdapter)
        headlines.clear()
        headlines.addAll(newList)
    }

    class PostsDiffCallback(private var oldList: ArrayList<Headline>, private var newList: ArrayList<Headline>) :
        DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = try {
            oldList[oldItemPosition].id == newList[newItemPosition].id
        } catch (e: Exception) {
            false
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

        @Nullable
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
            super.getChangePayload(oldItemPosition, newItemPosition)
    }
}