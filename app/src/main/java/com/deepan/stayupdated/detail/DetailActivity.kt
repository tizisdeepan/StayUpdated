package com.deepan.stayupdated.detail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.deepan.stayupdated.R
import kotlinx.android.synthetic.main.activity_detail.*
import android.webkit.WebView
import android.webkit.WebChromeClient
import android.webkit.WebViewClient

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        titleColor = resources.getColor(R.color.white)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        progress.max = 100

        val url = intent.extras?.getString("URL") ?: ""
        webview.settings.javaScriptEnabled = true
        webview.webChromeClient = MyWebViewClient()
        webview.webViewClient = WebViewClient()
        webview.loadUrl(url)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private inner class MyWebViewClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            this@DetailActivity.setValue(newProgress)
            super.onProgressChanged(view, newProgress)
        }
    }

    fun setValue(progress: Int) {
        if (progress == 100) this.progress.visibility = View.GONE else this.progress.visibility = View.VISIBLE
        this.progress.progress = progress
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}