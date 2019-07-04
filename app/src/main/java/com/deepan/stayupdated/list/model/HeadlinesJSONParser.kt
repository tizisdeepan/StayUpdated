package com.deepan.stayupdated.list.model

import com.deepan.stayupdated.helpers.DateUtil
import org.json.JSONObject

object HeadlinesJSONParser {
    fun getHeadlines(jsonData: String?): ArrayList<Headline> {
        val headlines: ArrayList<Headline> = ArrayList()
        if (!jsonData.isNullOrEmpty()) {
            val jObject = JSONObject(jsonData)
            if (jObject.has("articles")) {
                val articlesArray = jObject.optJSONArray("articles")
                for (i in 0 until articlesArray.length()) {
                    val articlesObject = articlesArray[i] as JSONObject
                    val headline = Headline()
                    if (articlesObject.has("author")) headline.author = articlesObject.optString("author")
                    if (articlesObject.has("title")) headline.title = articlesObject.optString("title")
                    if (articlesObject.has("description")) headline.description = articlesObject.optString("description")
                    if (articlesObject.has("publishedAt")) headline.time = DateUtil().getDateString(articlesObject.optString("publishedAt"))
                    if (articlesObject.has("url")) headline.url = articlesObject.optString("url")
                    if (articlesObject.has("urlToImage")) headline.imageUrl = articlesObject.optString("urlToImage")
                    if (articlesObject.has("source")) {
                        val sourceObject = articlesObject.optJSONObject("source")
                        if (sourceObject.has("id")) headline.sourceId = sourceObject.optString("id")
                        if (sourceObject.has("name")) headline.sourceName = sourceObject.optString("name")
                    }
                    headline.id = headline.time + headline.title
                    headlines.add(headline)
                }
            }
        }
        return headlines
    }
}