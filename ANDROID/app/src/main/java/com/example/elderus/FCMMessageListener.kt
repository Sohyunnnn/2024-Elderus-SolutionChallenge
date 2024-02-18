package com.example.elderus

import com.google.firebase.messaging.RemoteMessage

interface FCMMessageListener {
    fun onMessageReceived(message:RemoteMessage) :Boolean
}