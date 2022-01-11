package com.example.russiansport.presentation

import android.R.attr
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import android.view.KeyEvent
import android.view.View
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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiService = ApiFactory.apiService
    private lateinit var webView: WebView

    private val INPUT_FILE_REQUEST_CODE = 1
    private val FILECHOOSER_RESULTCODE = 1
    private val TAG = MainActivity::class.java.simpleName
    private var mUploadMessage: ValueCallback<Uri>? = null
    private var mCapturedImageURI: Uri? = null
    private var mFilePathCallback: ValueCallback<Array<Uri>>? = null
    private var mCameraPhotoPath: String? = null


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
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 19) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        val prefs = applicationContext.getSharedPreferences(packageName, Activity.MODE_PRIVATE)
        val str = prefs.getString("LastUrl", "")
        if (str != null) {
            supportActionBar?.hide()
            webView.webViewClient = CustomWebClient(applicationContext, progressBar)
            webView.webChromeClient = CustomChromeClient()
            webView.loadUrl(str)
        } else {
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
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode !== INPUT_FILE_REQUEST_CODE || mFilePathCallback == null) {
                super.onActivityResult(requestCode, resultCode, data)
                return
            }
            var results: Array<Uri>? = null
            // Check that the response is a good one
            if (resultCode === RESULT_OK) {
                if (attr.data == null) {
                    // If there is not data, then we may have taken a photo
                    if (mCameraPhotoPath != null) {
                        results = arrayOf(Uri.parse(mCameraPhotoPath))
                    }
                } else {
                    val dataString: String? = data?.getDataString()
                    if (dataString != null) {
                        results = arrayOf(Uri.parse(dataString))
                    }
                }
            }
            mFilePathCallback?.onReceiveValue(results)
            mFilePathCallback = null
        } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            if (requestCode != FILECHOOSER_RESULTCODE || mUploadMessage == null) {
                super.onActivityResult(requestCode, resultCode, data)
                return
            }
            if (requestCode == FILECHOOSER_RESULTCODE) {
                if (null == this.mUploadMessage) {
                    return
                }
                var result: Uri? = null
                try {
                    if (resultCode != RESULT_OK) {
                        result = null
                    } else {
                        // retrieve from the private variable if the intent is null
                        result = data?.let { mCapturedImageURI ?: it.data }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "ACTIVITY_MAIN", e)
                }
                mUploadMessage!!.onReceiveValue(result)
                mUploadMessage = null
            }
        }
        return
    }

    override fun onPause() {
        super.onPause()
        val prefs = applicationContext.getSharedPreferences(packageName, Activity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("LastUrl", webView.url)
        editor.commit()
    }

    //check user's history if empty -> post to server, if not empty -> load last url
    override fun onResume() {
        super.onResume()
        if (webView != null) {
            val prefs = applicationContext.getSharedPreferences(packageName, Activity.MODE_PRIVATE)
            val str = prefs.getString("LastUrl", "")
            if (str != null) {
                webView.loadUrl(str)
            }
        }
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
        settings.userAgentString = settings.userAgentString.replace("; wv","")
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

    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir: File = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )
        return File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
    }

    inner class CustomChromeClient : WebChromeClient() {

        //android 5.0
        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooser: FileChooserParams
        ): Boolean {
            mFilePathCallback?.let { it.onReceiveValue(null) }
            mFilePathCallback = filePath
            var takePicIntent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePicIntent?.resolveActivity(packageManager) != null) {
                var photoFile: File? = null
                try {
                    photoFile = createImageFile()
                    takePicIntent.putExtra("PhotoFile", mCameraPhotoPath)
                } catch (e: IOException) {
                    Log.e(TAG, "CANNOT TO CREATE AN IMAGE FILE", e)
                }
                if (photoFile != null) {
                    mCameraPhotoPath = "file:" + photoFile.absolutePath
                    takePicIntent.putExtra(
                        MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile)
                    )
                } else {
                    takePicIntent = null
                }
            }
            val contentSelectionIntent = Intent(Intent.ACTION_GET_CONTENT)
            contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE)
            contentSelectionIntent.setType("image/*")
            var intentArray: Array<Intent>? = null
            if (takePicIntent != null) {
                intentArray = arrayOf(takePicIntent)
            } else {
                intentArray = arrayOf()
            }
            val chooserIntent = Intent(Intent.ACTION_CHOOSER)
            chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent)
            chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray)
            startActivityForResult(chooserIntent, INPUT_FILE_REQUEST_CODE)
            return true
        }

        //android 3.0
        fun openFileChooser(uploadMsg: ValueCallback<Uri>?, acceptType: String) {
            mUploadMessage = uploadMsg
            val imageStorageDir = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
                ), "AndroidExampleFolder"
            )
            if (!imageStorageDir.exists()) {
                // Create AndroidExampleFolder at sdcard
                imageStorageDir.mkdirs()
            }
            // Create camera captured image file path and name
            val file = File(
                imageStorageDir.toString() + File.separator + "IMG_"
                        + System.currentTimeMillis().toString() + ".jpg"
            )
            mCapturedImageURI = Uri.fromFile(file)
            // Camera capture image intent
            val captureIntent = Intent(
                MediaStore.ACTION_IMAGE_CAPTURE
            )
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI)
            val i = Intent(Intent.ACTION_GET_CONTENT)
            i.addCategory(Intent.CATEGORY_OPENABLE)
            i.type = "image/*"
            // Create file chooser intent
            // Create file chooser intent
            val chooserIntent = Intent.createChooser(i, "Image Chooser")
            // Set camera intent to file chooser
            // Set camera intent to file chooser
            chooserIntent.putExtra(
                Intent.EXTRA_INITIAL_INTENTS, arrayOf<Parcelable>(captureIntent)
            )
            // On select image call onActivityResult method of activity
            // On select image call onActivityResult method of activity
            startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE)
        }

        // openFileChooser for Android < 3.0
        fun openFileChooser(uploadMsg: ValueCallback<Uri>?) {
            openFileChooser(uploadMsg, "")
        }

        //openFileChooser for other Android versions
        fun openFileChooser(
            uploadMsg: ValueCallback<Uri>?,
            acceptType: String?,
            capture: String?
        ) {
            openFileChooser(uploadMsg, acceptType!!)
        }
    }
}