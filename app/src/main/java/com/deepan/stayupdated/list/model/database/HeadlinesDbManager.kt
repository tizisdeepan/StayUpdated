package com.deepan.stayupdated.list.model.database

import android.content.Context
import com.deepan.stayupdated.list.model.Headline

class HeadlinesDbManager(var ctx: Context) {

    fun getHeadlines(category: String): ArrayList<Headline> {
        val constants = HeadlinesDbConstants(category)
        val headlines: ArrayList<Headline> = ArrayList()
        val cursor = HeadlinesDbManipulation(ctx, constants).getAllHeadlines()
        if (cursor != null && cursor.count != 0) {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val headline = Headline()
                headline.id = cursor.getString(HeadlinesColumnInfo.id)
                headline.sourceId = cursor.getString(HeadlinesColumnInfo.sourceId)
                headline.sourceName = cursor.getString(HeadlinesColumnInfo.sourceName)
                headline.title = cursor.getString(HeadlinesColumnInfo.title)
                headline.description = cursor.getString(HeadlinesColumnInfo.description)
                headline.url = cursor.getString(HeadlinesColumnInfo.url)
                headline.imageUrl = cursor.getString(HeadlinesColumnInfo.imageUrl)
                headline.time = cursor.getString(HeadlinesColumnInfo.time)
                headlines.add(headline)
                cursor.moveToNext()
            }
        }
        return headlines
    }
}