package com.deepan.stayupdated.list.view

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.deepan.stayupdated.list.model.Headline
import kotlinx.android.synthetic.main.headline_item.view.*

class VHHeadline(view: View) : RecyclerView.ViewHolder(view) {
    private val headlineCard: CardView = view.headlineCard
    private val title: TextView = view.title
    private val description: TextView = view.description
    private val time: TextView = view.time
    private val image: ImageView = view.image

    fun setData(ctx: Context, headline: Headline) {
        title.text = headline.title
        description.text = headline.description
        time.text = headline.time
        Glide.with(ctx).load(headline.imageUrl).transition(DrawableTransitionOptions.withCrossFade()).into(image)
        headlineCard.preventCornerOverlap = false
    }
}