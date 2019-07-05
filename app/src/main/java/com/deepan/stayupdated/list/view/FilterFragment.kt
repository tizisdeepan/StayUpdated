package com.deepan.stayupdated.list.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepan.stayupdated.R
import com.deepan.stayupdated.helpers.FontsConstants
import com.deepan.stayupdated.helpers.FontsHelper
import com.deepan.stayupdated.list.model.Categories
import com.deepan.stayupdated.list.model.Category
import com.deepan.stayupdated.list.model.Filter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : BottomSheetDialogFragment() {

    lateinit var ctx: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ctx = inflater.context
        val contextThemeWrapper = ContextThemeWrapper(ctx, theme)
        return inflater.cloneInContext(contextThemeWrapper).inflate(R.layout.fragment_filter, container, false)
    }

    private val categories: ArrayList<Category> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val filter = arguments?.getParcelable("FILTER") as? Filter

        categories.add(Category(Categories.ALL, filter?.category == Categories.ALL))
        categories.add(Category(Categories.BUSINESS, filter?.category == Categories.BUSINESS))
        categories.add(Category(Categories.ENTERTAINMENT, filter?.category == Categories.ENTERTAINMENT))
        categories.add(Category(Categories.GENERAL, filter?.category == Categories.GENERAL))
        categories.add(Category(Categories.HEALTH, filter?.category == Categories.HEALTH))
        categories.add(Category(Categories.SCIENCE, filter?.category == Categories.SCIENCE))
        categories.add(Category(Categories.SPORTS, filter?.category == Categories.SPORTS))
        categories.add(Category(Categories.TECHNOLOGY, filter?.category == Categories.TECHNOLOGY))

        categoryRecyclerView.layoutManager = LinearLayoutManager(ctx)
        categoryRecyclerView.itemAnimator = null
        updateCategories()

        apply.setOnClickListener {
            var category: Category? = null
            for(c in categories){
                if(c.isSelected) category = c
            }
            (activity as? HeadlinesActivity)?.updateFilterCategory(category)
            dismiss()
        }

        headlineTitle.typeface = FontsHelper[ctx, FontsConstants.BOLD]
        apply.typeface = FontsHelper[ctx, FontsConstants.BOLD]
        categoryLabel.typeface = FontsHelper[ctx, FontsConstants.BOLD]
    }

    private fun updateCategories() {
        categoryRecyclerView.adapter = CategoriesAdapter(categories, this::onCategorySelected)
    }

    private fun onCategorySelected(category: Category) {
        for (c in categories) {
            c.isSelected = c.type == category.type
        }
        updateCategories()
    }
}