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
        // Do NOT set a global API key header here. When a user session is created,
        // requests must use the session auth and not an API key. Setting both
        // results in Appwrite rejecting requests with: "API key and session used
        // in the same request." If you need server-side/admin calls, create a
        // separate client configured with the API key on the server side only.
    }
}