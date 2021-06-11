package com.devM7mdibrahim.task.features.ui

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.devM7mdibrahim.task.BuildConfig
import com.devM7mdibrahim.task.R
import com.devM7mdibrahim.task.features.utils.TODOS_CHANNEL_ID
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class MyApplication : Application(){

    private lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)

        notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createTodosAlertNotificationChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createTodosAlertNotificationChannel() {


        val mChannel = NotificationChannel(
            TODOS_CHANNEL_ID,
            "todos_alert_channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        mChannel.run {
            description = "prayer times notification channel"
            enableLights(true)
            lightColor = getColor(R.color.white)
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            setShowBadge(false)
        }

        notificationManager.createNotificationChannel(mChannel)
    }
}