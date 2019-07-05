package com.deepan.stayupdated

import com.deepan.stayupdated.helpers.HttpClient
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.junit.Test
import java.io.IOException
import junit.framework.TestCase.assertTrue

class HttpClientTest {

    @Test
    fun testHttpClient() {
        val client = HttpClient().makeNewClient
        client.newCall(Request.Builder().url("https://google.com").build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                assertTrue(e.toString().isNotEmpty())
            }

            override fun onResponse(call: Call, response: Response) {
                assertTrue(!response.body()?.string().isNullOrEmpty())
            }
        })
    }
}