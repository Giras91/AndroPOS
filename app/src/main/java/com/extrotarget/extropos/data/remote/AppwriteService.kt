package com.extrotarget.extropos.data.remote

import android.content.Context
import com.extrotarget.extropos.constants.AppwriteConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import io.appwrite.Client
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppwriteService @Inject constructor(
    @ApplicationContext private val context: Context
) {

    val client: Client = Client(context)
        .setEndpoint(AppwriteConfig.APPWRITE_PUBLIC_ENDPOINT)
        .setProject(AppwriteConfig.APPWRITE_PROJECT_ID)
        .setSelfSigned(true) // For development - remove in production

    init {
        // Optional: Add custom headers or configuration
        client.addHeader("X-Appwrite-Key", AppwriteConfig.APPWRITE_API_KEY)
    }
}