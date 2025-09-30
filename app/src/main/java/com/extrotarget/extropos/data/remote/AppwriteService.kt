package com.extrotarget.extropos.data.remote

import com.extrotarget.extropos.constants.AppwriteConfig
import io.appwrite.Client
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppwriteService @Inject constructor() {

    val client: Client = Client()
        .setEndpoint(AppwriteConfig.APPWRITE_PUBLIC_ENDPOINT)
        .setProject(AppwriteConfig.APPWRITE_PROJECT_ID)
        .setSelfSigned(true) // For development - remove in production

    init {
        // Optional: Add custom headers or configuration
        client.addHeader("X-Appwrite-Key", AppwriteConfig.APPWRITE_API_KEY)
    }
}