package com.deepan.stayupdated.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepan.stayupdated.R
import com.deepan.stayupdated.list.model.Headline

class HeadlinesAdapter(var headlines: ArrayList<Headline>) : RecyclerView.Adapter<VHHeadline>() {

    lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHHeadline {
        ctx = parent.context
        return VHHeadline(LayoutInflater.from(parent.context).inflate(R.layout.headline_item, parent, false))
    }

    override fun getItemCount(): Int = headlines.size

    override fun onBindViewHolder(holder: VHHeadline, position: Int) {

    }
}