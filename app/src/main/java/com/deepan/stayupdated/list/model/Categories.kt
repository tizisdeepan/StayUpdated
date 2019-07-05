package com.deepan.stayupdated.list.model

import android.content.Context
import com.deepan.stayupdated.R

enum class Categories {
    ALL, BUSINESS, ENTERTAINMENT, GENERAL, HEALTH, SCIENCE, SPORTS, TECHNOLOGY;

    fun getValue(ctx: Context): String = when (this) {
        ALL -> ctx.resources.getString(R.string.category_all)
        BUSINESS -> ctx.resources.getString(R.string.category_business)
        ENTERTAINMENT -> ctx.resources.getString(R.string.category_entertainment)
        GENERAL -> ctx.resources.getString(R.string.category_general)
        HEALTH -> ctx.resources.getString(R.string.category_health)
        SCIENCE -> ctx.resources.getString(R.string.category_science)
        SPORTS -> ctx.resources.getString(R.string.category_sports)
        TECHNOLOGY -> ctx.resources.getString(R.string.category_technology)
    }
}