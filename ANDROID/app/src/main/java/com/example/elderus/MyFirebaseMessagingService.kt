package com.example.elderus

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.remoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        //super.onNewToken(token)
        //FCM 토큰이 갱신되었을 때 필요한 작업 수행
        Log.d("FCM Log", "Refreshed token : $token")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
       // super.onMessageReceived(message)
        //FCM 메시지 title과 body를 수신, sendNotification 메서드에 처리
        message.notification?.let { notification ->
            val title = notification.title
            val body = notification.body
            sendNotification(title, body)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(title: String?, body: String?) {
        //알림 채널
        val channelId = "my_channel_id"
        val channelName = "My Channel"
        val importance = NotificationManager.IMPORTANCE_HIGH

        //알람 클릭시 열릴 액티비티
        val intent = Intent(this, MainActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder = NotificationCompat.Builder(this, channelId).setSmallIcon(R.drawable.ic_hospital)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true) // 알림 클릭 시 사라지기
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(NotificationChannel(channelId,channelName, importance))
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
            == PackageManager.PERMISSION_GRANTED){
            notificationManager.notify(0, builder.build())
        }
    }
}