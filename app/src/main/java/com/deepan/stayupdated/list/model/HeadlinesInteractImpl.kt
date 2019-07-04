package com.deepan.stayupdated.list.model

import android.util.Log
import com.deepan.stayupdated.helpers.HttpClient
import com.deepan.stayupdated.list.presenter.HeadlinesPresenter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeadlinesInteractImpl(var presenter: HeadlinesPresenter) : HeadlinesInteract {

    private var baseUrl = "https://newsapi.org/v2/top-headlines?apiKey=cef34057f6c54bc394d7f849dab354fd"

    override fun getHeadlines(filter: Filter, page: Int, onSuccess: (ArrayList<Headline>) -> Unit, onFailure: (String) -> Unit) {
        if (page != 0) baseUrl += "&page=$page"
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
                    val jsonData = response.body()?.string()
                    Log.e("RESPONSE", jsonData)
                    val headlines = HeadlinesJSONParser.getHeadlines(jsonData)
                    onSuccess(headlines)
                } catch (e: Exception) {
                    onFailure(e.toString())
                }
            }
        })
    }
}