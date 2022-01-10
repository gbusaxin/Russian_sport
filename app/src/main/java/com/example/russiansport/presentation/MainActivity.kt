package com.example.russiansport.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.russiansport.data.network.dto.RequestDto
import com.example.russiansport.data.network.dto.ResponseDto
import com.example.russiansport.data.network.retrofit.ApiFactory
import com.example.russiansport.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.webkit.ValueCallback
import android.widget.Toast

import android.content.ActivityNotFoundException

import android.webkit.WebView

import android.os.Build

import android.annotation.TargetApi
import android.webkit.WebChromeClient.FileChooserParams


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiService = ApiFactory.apiService
    private lateinit var webView: WebView

    private val INPUT_FILE_REQUEST_CODE = 1
    private val FILECHOOSER_RESULTCODE = 1
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var mUploadMessage: ValueCallback<Uri>
    private lateinit var mFilePathCallback: ValueCallback<Array<Uri?>?>


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadJsonPicture()

        webView = binding.webView
        val refreshLay = binding.refreshLayout
        val progressBar = binding.progressBar

        val settings = webView.settings

        applyViewSettingsForWebView(settings)
        val locale = ConfigurationCompat.getLocales(Resources.getSystem().configuration)
        val lang = locale[0]
        Log.d("LANGUAGE_CURR", lang.isO3Language)
        val requestDto = RequestDto(lang.isO3Language)
        val responseDto = apiService.sendLocale(requestDto)
        val request = responseDto.enqueue(object : Callback<ResponseDto> {
            @SuppressLint("SetJavaScriptEnabled")
            override fun onResponse(p0: Call<ResponseDto>, p1: Response<ResponseDto>) {
                Log.d("P1BODY", "Ответ - ${p1.body().toString()}")
                if (p1.body()?.response == "no") {
                    startActivity(Intent(this@MainActivity, MenuActivity::class.java))
                } else {
                    supportActionBar?.hide()
                    webView.webViewClient = CustomWebClient(applicationContext, progressBar)
                    webView.webChromeClient = CustomChromeClient()
                    webView.loadUrl(p1.body()?.response ?: "")
                }
            }

            override fun onFailure(p0: Call<ResponseDto>, p1: Throwable) {
                startActivity(Intent(this@MainActivity, MenuActivity::class.java))
                Log.d("ONFAILURE", p1.message.toString())
            }
        })

        // обновление страницы
        refreshLay.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshLay.isRefreshing = true
                Handler(Looper.getMainLooper()).postDelayed({
                    refreshLay.isRefreshing = false
                    webView.loadUrl(webView.url.toString())
                }, 1000)
            }

        })

    }

    //нажатие назад к вебклиенту или приложению
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //настройки webview
    @SuppressLint("SetJavaScriptEnabled")
    private fun applyViewSettingsForWebView(settings: WebSettings) {
        settings.domStorageEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.displayZoomControls = false
        settings.allowFileAccess = true
        settings.allowContentAccess = true
        settings.javaScriptEnabled = true
    }

    //бэкграунд
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mFilePathCallback.onReceiveValue(Array())
    }

    inner class CustomChromeClient : WebChromeClient() {

        override fun onPermissionRequest(request: PermissionRequest?) {
            request?.grant(request.resources)
        }

        fun openFileChooser(uploadMsg: ValueCallback<Uri>, acceptType: String?) {
            mUploadMessage = uploadMsg
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.addCategory(Intent.CATEGORY_OPENABLE)
            i.type = "image/*"
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE)
        }

        override fun onShowFileChooser(
            vw: WebView?, filePathCallback: ValueCallback<Array<Uri?>?>,
            fileChooserParams: FileChooserParams?
        ): Boolean {
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null)
            }
            mFilePathCallback = filePathCallback
            val selectionIntent = Intent(Intent.ACTION_GET_CONTENT)
            selectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
            selectionIntent.type = "*/*"
            val chooserIntent = Intent(Intent.ACTION_CHOOSER)
            chooserIntent.putExtra(Intent.EXTRA_INTENT, selectionIntent)
            startActivityForResult(chooserIntent, 0)
            return true
        }
    }
}