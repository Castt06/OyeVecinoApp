package com.untels.oyevecino

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.activity_web_instagram.*

class ActivityWebInstagram : AppCompatActivity() {
    //PRIVATE
    private val BASE_URL = "https://www.instagram.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_instagram)
        //WebView
        webViewInstagram.webChromeClient = object : WebChromeClient(){

        }

        webViewInstagram.webViewClient = object : WebViewClient(){

        }
        val settings: WebSettings = webViewInstagram. settings
        settings.javaScriptEnabled = true

        webViewInstagram.loadUrl(BASE_URL)

        btnRegresar_IG.setOnClickListener {
            super.onBackPressed()
        }
    }
    override fun onBackPressed() {
        if(webViewInstagram.canGoBack()){
            webViewInstagram.goBack()
        }else{
            super.onBackPressed()
        }

    }
}