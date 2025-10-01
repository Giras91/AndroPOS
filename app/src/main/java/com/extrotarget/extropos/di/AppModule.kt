package com.extrotarget.extropos.di

import android.app.Application
import android.content.Context
import com.extrotarget.extropos.App
import com.extrotarget.extropos.data.local.InMemoryProductDao
import com.extrotarget.extropos.data.local.InMemoryTicketDao
import com.extrotarget.extropos.data.local.dao.ProductDao
import com.extrotarget.extropos.data.local.dao.TicketDao
import com.extrotarget.extropos.data.remote.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.appwrite.Client
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideAppwriteClient(app: Application): Client {
        return (app as App).appwriteClient
    }

    @Provides
    @Singleton
    fun provideProductDao(): ProductDao {
        return InMemoryProductDao()
    }

    @Provides
    @Singleton
    fun provideTicketDao(): TicketDao {
        return InMemoryTicketDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(): com.extrotarget.extropos.data.local.dao.CategoryDao {
        return com.extrotarget.extropos.data.local.InMemoryCategoryDao()
    }

    @Provides
    @Singleton
    fun provideMenuItemDao(): com.extrotarget.extropos.data.local.dao.MenuItemDao {
        return com.extrotarget.extropos.data.local.InMemoryMenuItemDao()
    }

    @Provides
    @Singleton
    fun provideOrderDao(): com.extrotarget.extropos.data.local.dao.OrderDao {
        return com.extrotarget.extropos.data.local.InMemoryOrderDao()
    }

    @Provides
    @Singleton
    fun provideOrderItemDao(): com.extrotarget.extropos.data.local.dao.OrderItemDao {
        return com.extrotarget.extropos.data.local.InMemoryOrderItemDao()
    }

    @Provides
    @Singleton
    fun provideTableDao(): com.extrotarget.extropos.data.local.dao.TableDao {
        return com.extrotarget.extropos.data.local.InMemoryTableDao()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.extrotarget.com/") // TODO: Update with actual API URL
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}