package com.example.russiansport.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.os.ConfigurationCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.russiansport.R
import com.example.russiansport.data.network.dto.RequestDto
import com.example.russiansport.data.network.dto.ResponseDto
import com.example.russiansport.data.network.retrofit.ApiFactory
import com.example.russiansport.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val apiService = ApiFactory.apiService

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadJsonPicture()

        val webView = binding.webView
        val refreshLay = binding.refreshLayout
        val progressBar = binding.progressBar

        val settings = webView.settings

        val locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)
        val lang = locale[0]
        Log.d("LANGUAGE_CURR", lang.isO3Language)
        val requestDto = RequestDto(lang.isO3Language)
        val responseDto = apiService.sendLocale(requestDto)
        val request = responseDto.enqueue(object : Callback<ResponseDto> {
            @SuppressLint("SetJavaScriptEnabled")
            override fun onResponse(p0: Call<ResponseDto>, p1: Response<ResponseDto>) {
                Log.d("P1BODY","Ответ - ${p1.body().toString()}")
                if(p1.body()?.response == "no"){
                    startActivity(Intent(this@MainActivity,MenuActivity::class.java))
                }else{
                    supportActionBar?.hide()
                    with(settings){
                        javaScriptEnabled = true
                        domStorageEnabled = true
                    }
                    webView.webViewClient = CustomWebClient(applicationContext,progressBar)
                    webView.loadUrl(p1.body()?.response?:"")
                }
            }

            override fun onFailure(p0: Call<ResponseDto>, p1: Throwable) {
                startActivity(Intent(this@MainActivity,MenuActivity::class.java))
                Log.d("ONFAILURE",p1.message.toString())
            }
        })

        refreshLay.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                refreshLay.isRefreshing = true
                Handler(Looper.getMainLooper()).postDelayed({
                    refreshLay.isRefreshing = false
                    webView.loadUrl(webView.url.toString())
                },2500)
            }

        })

    }

    private fun loadJsonPicture() {
        try {
            Glide.with(this).load("http://95.217.132.144/russian_sport/background_image.jpg")
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(dr: Drawable, tran: Transition<in Drawable?>?) {
                        binding.constraintLayout.background = dr
                    }

                    override fun onLoadCleared(p0: Drawable?) {

                    }
                })
        } catch (e: Exception) {
        }
    }
}