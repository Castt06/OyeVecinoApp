package com.untels.oyevecino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

class ActivityWeb : AppCompatActivity() {
    //PRIVATE
    private val BASE_URL = "https://www.facebook.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        //WebView
        webView.webChromeClient = object : WebChromeClient(){

        }

        webView.webViewClient = object : WebViewClient(){

        }
        val settings:WebSettings = webView. settings
        settings.javaScriptEnabled = true

        webView.loadUrl(BASE_URL)

        btnRegresar.setOnClickListener {
            super.onBackPressed()
        }

    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }

    }

}