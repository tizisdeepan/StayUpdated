package com.deepan.stayupdated.list.model.database

import android.content.Context
import com.deepan.stayupdated.list.model.Headline
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HeadlinesDbManager(var ctx: Context) {

    fun getHeadlines(category: String): ArrayList<Headline> {
        val headlines: ArrayList<Headline> = ArrayList()
        val headlinesJson = HeadlinesDbManipulation(ctx).getAllHeadlines(category)
        if (!headlinesJson.isNullOrEmpty()) {
            val h = Gson().fromJson<ArrayList<Headline>>(headlinesJson, object :
                TypeToken<ArrayList<Headline>>() {}.type)
            headlines.addAll(h)
        }
        return headlines
    }
}