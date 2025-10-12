package com.extrotarget.extropos.di

import android.content.Context
import com.extrotarget.extropos.data.repository.ReceiptSettingsRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReceiptSettingsModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .create()
    }

    @Provides
    @Singleton
    fun provideReceiptSettingsRepository(
        @ApplicationContext context: Context,
        gson: Gson
    ): ReceiptSettingsRepository {
        return ReceiptSettingsRepository(context, gson)
    }
}