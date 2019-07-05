package com.deepan.stayupdated.list.view.viewholders

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.deepan.stayupdated.R
import com.deepan.stayupdated.helpers.FontsConstants
import com.deepan.stayupdated.helpers.FontsHelper
import com.deepan.stayupdated.list.model.Categories
import com.deepan.stayupdated.list.model.Category
import kotlinx.android.synthetic.main.category_item.view.*

class VHCategory(view: View) : RecyclerView.ViewHolder(view) {

    val categoryFrame: FrameLayout = view.categoryFrame
    private val categorySelectionFrame: RelativeLayout = view.categorySelectionFrame
    private val categoryLabel: TextView = view.categoryLabel
    private val selectedIv: ImageView = view.selectedIv

    fun setData(ctx: Context, category: Category) {
        categoryLabel.text = when (category.type) {
            Categories.ALL -> ctx.resources.getString(R.string.category_all)
            Categories.BUSINESS -> ctx.resources.getString(R.string.category_business)
            Categories.ENTERTAINMENT -> ctx.resources.getString(R.string.category_entertainment)
            Categories.GENERAL -> ctx.resources.getString(R.string.category_general)
            Categories.HEALTH -> ctx.resources.getString(R.string.category_health)
            Categories.SCIENCE -> ctx.resources.getString(R.string.category_science)
            Categories.SPORTS -> ctx.resources.getString(R.string.category_sports)
            Categories.TECHNOLOGY -> ctx.resources.getString(R.string.category_technology)
        }

        if (category.isSelected) {
            selectedIv.visibility = View.VISIBLE
            categoryLabel.setTextColor(Color.parseColor("#000000"))
            categorySelectionFrame.setBackgroundResource(R.drawable.textview_filter_white)
        } else {
            selectedIv.visibility = View.GONE
            categoryLabel.setTextColor(Color.parseColor("#ffffff"))
            categorySelectionFrame.setBackgroundResource(0)
        }

        categoryLabel.typeface = FontsHelper[ctx, FontsConstants.BOLD]
    }
}