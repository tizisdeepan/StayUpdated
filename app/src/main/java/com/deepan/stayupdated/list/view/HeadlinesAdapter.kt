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

    }

    private var eventActor =
        GlobalScope.actor<ArrayList<Headline>>(capacity = Channel.CONFLATED) { for (list in channel) updateItemsInternal(list) }

    fun updateItems(newList: ArrayList<Headline>) {
        val tempList: ArrayList<Headline> = ArrayList()
        tempList.addAll(newList)
        eventActor.offer(tempList)
    }

    lateinit var diffResult: DiffUtil.DiffResult
    private fun updateItemsInternal(newList: ArrayList<Headline>) {
        diffResult = DiffUtil.calculateDiff(PostsDiffCallback(this@HeadlinesAdapter.headlines, newList))
        dispatchItems(newList, diffResult)
    }

    private fun dispatchItems(newList: ArrayList<Headline>, diffResult: DiffUtil.DiffResult) {
        GlobalScope.launch(Dispatchers.Main) {
            diffResult.dispatchUpdatesTo(this@HeadlinesAdapter)
            headlines.clear()
            headlines.addAll(newList)
        }
    }

    class PostsDiffCallback(private var oldList: ArrayList<Headline>, private var newList: ArrayList<Headline>) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = true

        @Nullable
        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
            super.getChangePayload(oldItemPosition, newItemPosition)
    }
}