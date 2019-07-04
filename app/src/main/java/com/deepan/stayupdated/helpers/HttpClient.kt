package com.deepan.stayupdated.helpers

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class HttpClient {
    val makeNewClient = OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES).build()
}