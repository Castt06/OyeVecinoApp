package com.untels.oyevecino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_instagram.*
import kotlinx.android.synthetic.main.activity_web_instagram.webViewInstagram
import kotlinx.android.synthetic.main.activity_web_twitter.*

class ActivityWebTwitter : AppCompatActivity() {
    //PRIVATE
    private val BASE_URL = "https://www.twitter.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_twitter)
        //WebView
        webViewTwitter.webChromeClient = object : WebChromeClient(){

        }

        webViewTwitter.webViewClient = object : WebViewClient(){

        }
        val settings: WebSettings = webViewTwitter. settings
        settings.javaScriptEnabled = true

        webViewTwitter.loadUrl(BASE_URL)

        btnRegresar_Twitter.setOnClickListener {
            super.onBackPressed()
        }
    }
    override fun onBackPressed() {
        if(webViewTwitter.canGoBack()){
            webViewTwitter.goBack()
        }else{
            super.onBackPressed()
        }

    }
}