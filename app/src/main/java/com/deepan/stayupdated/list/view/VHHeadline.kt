package com.deepan.stayupdated.list.view

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.deepan.stayupdated.detail.DetailActivity
import com.deepan.stayupdated.helpers.FontsConstants
import com.deepan.stayupdated.helpers.FontsHelper
import com.deepan.stayupdated.list.model.Headline
import kotlinx.android.synthetic.main.headline_item.view.*

class VHHeadline(view: View) : RecyclerView.ViewHolder(view) {
    private val headlineCard: CardView = view.headlineCard
    private val title: TextView = view.title
    private val time: TextView = view.time
    private val image: ImageView = view.image

    fun setData(ctx: Context, headline: Headline) {
        title.text = headline.title
        time.text = headline.time
        Glide.with(ctx).load(headline.imageUrl).apply(RequestOptions().centerCrop())
            .transition(DrawableTransitionOptions.withCrossFade()).into(image)
        headlineCard.preventCornerOverlap = false

        headlineCard.setOnClickListener {
            ctx.startActivity(Intent(ctx, DetailActivity::class.java).apply {
                putExtra("URL", headline.url)
            })
        }

        title.typeface = FontsHelper[ctx, FontsConstants.BOLD]
        time.typeface = FontsHelper[ctx, FontsConstants.SEMIBOLD]
    }
}