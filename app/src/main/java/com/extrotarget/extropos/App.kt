package com.extrotarget.extropos

import android.app.Application
import com.extrotarget.extropos.constants.AppwriteConfig
import dagger.hilt.android.HiltAndroidApp
import io.appwrite.Client

@HiltAndroidApp
class App : Application() {

    lateinit var appwriteClient: Client
        private set

    override fun onCreate() {
        super.onCreate()

        // Initialize Appwrite client
        appwriteClient = Client(this)
            .setEndpoint(AppwriteConfig.APPWRITE_PUBLIC_ENDPOINT)
            .setProject(AppwriteConfig.APPWRITE_PROJECT_ID)
            .setSelfSigned(true) // For development, remove in production
    }
}