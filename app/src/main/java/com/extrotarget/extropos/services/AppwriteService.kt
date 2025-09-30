package com.extrotarget.extropos.services

import android.os.AsyncTask
import android.util.Log
import com.extrotarget.extropos.constants.AppwriteConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

class AppwriteService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(AppwriteConfig.APPWRITE_PUBLIC_ENDPOINT)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val healthApi = retrofit.create(HealthApi::class.java)

    fun pingAppwrite(callback: PingCallback) {
        Log.d(TAG, "Attempting to ping Appwrite server...")
        healthApi.getHealth(AppwriteConfig.APPWRITE_API_KEY).enqueue(object : Callback<HealthResponse> {
            override fun onResponse(call: Call<HealthResponse>, response: Response<HealthResponse>) {
                if (response.isSuccessful) {
                    val pingTime = System.currentTimeMillis()
                    Log.d(TAG, "Appwrite ping successful at $pingTime")
                    Log.d(TAG, "Response: ${response.body()}")
                    callback.onSuccess("Ping successful! Server is responding.")
                } else {
                    Log.e(TAG, "Appwrite ping failed with code: ${response.code()}")
                    callback.onFailure(Exception("HTTP ${response.code()}: ${response.message()}"))
                }
            }

            override fun onFailure(call: Call<HealthResponse>, t: Throwable) {
                Log.e(TAG, "Appwrite ping failed", t)
                callback.onFailure(t)
            }
        })
    }

    interface PingCallback {
        fun onSuccess(message: String)
        fun onFailure(error: Throwable)
    }

    companion object {
        private const val TAG = "AppwriteService"
    }
}

interface HealthApi {
    @GET("health")
    fun getHealth(@Header("X-Appwrite-Key") apiKey: String): Call<HealthResponse>
}

@JsonClass(generateAdapter = true)
data class HealthResponse(
    @Json(name = "status") val status: String,
    @Json(name = "version") val version: String
)