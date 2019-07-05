package com.deepan.stayupdated.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.deepan.stayupdated.R
import com.deepan.stayupdated.list.model.Category
import com.deepan.stayupdated.list.view.viewholders.VHCategory

class CategoriesAdapter(private var categories: ArrayList<Category>, var onCategorySelected: (category: Category) -> Unit) :
    RecyclerView.Adapter<VHCategory>() {

    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHCategory {
        ctx = parent.context
        return VHCategory(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: VHCategory, position: Int) {
        holder.setData(ctx, categories[holder.adapterPosition])
        holder.categoryFrame.setOnClickListener {
            onCategorySelected(categories[holder.adapterPosition])
        }
    }
}