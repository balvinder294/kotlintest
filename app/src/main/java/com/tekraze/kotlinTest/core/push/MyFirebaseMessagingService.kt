package com.tekraze.kotlinTest.core.push

import com.tekraze.kotlinTest.MyApplication
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        token?.let{(applicationContext as MyApplication).getCore().sendFirebaseToken(it)}

    }
}
