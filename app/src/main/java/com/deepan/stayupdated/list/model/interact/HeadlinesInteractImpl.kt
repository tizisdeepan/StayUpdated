package com.deepan.stayupdated.list.model.interact

import android.util.Log
import com.deepan.stayupdated.helpers.HttpClient
import com.deepan.stayupdated.list.model.Categories
import com.deepan.stayupdated.list.model.Filter
import com.deepan.stayupdated.list.model.Headline
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class HeadlinesInteractImpl : HeadlinesInteract {

    override fun getHeadlines(filter: Filter, page: Int, onSuccess: (ArrayList<Headline>) -> Unit, onFailure: (String) -> Unit) {
        var baseUrl = "https://newsapi.org/v2/top-headlines?apiKey=cef34057f6c54bc394d7f849dab354fd&country=us&pageSize=25"
        if (page != 1) baseUrl += "&page=$page"
        if (filter.category != Categories.ALL) baseUrl += "&category=${filter.category}"
        val request = Request.Builder().url(baseUrl).build()
        Log.e("UTL INFO", baseUrl)
        HttpClient().makeNewClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailure("Something went wrong!")
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonData = response.body()?.string()
                    Log.e("Response", jsonData)
                    val headlines = HeadlinesJSONParser.getHeadlines(jsonData)
                    onSuccess(headlines)
                } catch (e: Exception) {
                    onFailure("Something went wrong!")
                }
            }
        })
    }
}