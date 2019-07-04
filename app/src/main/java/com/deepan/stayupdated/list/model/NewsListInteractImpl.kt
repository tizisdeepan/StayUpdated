package com.deepan.stayupdated.list.model

import com.deepan.stayupdated.helpers.HttpClient
import com.deepan.stayupdated.list.presenter.NewsListPresenter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NewsListInteractImpl(var presenter: NewsListPresenter) : NewsListInteract {

    var baseUrl = "https://newsapi.org/v2/top-headlines?apiKey=cef34057f6c54bc394d7f849dab354fd"

    override fun getHeadlines(filter: Filter, offset: String, onSuccess: (ArrayList<Headline>) -> Unit, onFailure: (String) -> Unit) {
        if (offset.isNotEmpty()) baseUrl += "&page=$offset"
        if (filter.country.isNotEmpty()) baseUrl += "&country=${filter.country}"
        if (filter.pageSize != 0) baseUrl += "&pageSize=${filter.pageSize}"
        if (filter.category.isNotEmpty()) baseUrl += "&category=${filter.category}"
        val request = Request.Builder().url(baseUrl).build()
        HttpClient().makeNewClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                try {

                } catch (e: Exception) {
                    onFailure(e.toString())
                }
            }
        })
    }
}