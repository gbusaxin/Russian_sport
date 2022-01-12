package com.example.russiansport.presentation

import android.app.Application
import com.onesignal.OneSignal
import com.onesignal.OneSignalNotificationManager

class SignalInit : Application() {

    companion object{
        private const val ONESIGNAL_APP_ID = "3128ed86-0ac4-4dbf-b6a2-9cccde3fd627"
    }

    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

    }

}