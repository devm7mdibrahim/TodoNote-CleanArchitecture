package com.devM7mdibrahim.task.features.notifications

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.devM7mdibrahim.task.features.ui.MainActivity
import com.devM7mdibrahim.task.features.utils.TODOS_CHANNEL_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class TodoAlertWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        scheduleNotification()
        return Result.success()
    }

    private fun scheduleNotification() {
        val intent = PendingIntent.getActivity(
            context, 0, Intent(context, MainActivity::class.java), 0
        )
        val builder =
            NotificationCompat.Builder(context, TODOS_CHANNEL_ID)
                .setContentText("check your todos")
                .setContentTitle("alert!")
                .setDefaults(Notification.DEFAULT_SOUND)
                .setLights(-0xff0100, 1000, 1000)
                .setAutoCancel(true)
                .setContentIntent(intent)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, builder.build())
    }
}
