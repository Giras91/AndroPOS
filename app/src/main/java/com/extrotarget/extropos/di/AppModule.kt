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
import com.extrotarget.extropos.ui.main.DashboardPrefs
import com.extrotarget.extropos.ui.guide.GuideOverlayManager
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

    // Product DAO provided by Room below

    @Provides
    @Singleton
    fun provideTicketDao(): TicketDao {
        return InMemoryTicketDao()
    }

    // Category and MenuItem DAOs are provided by Room below

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
    fun provideAppDatabase(application: Application): com.extrotarget.extropos.data.local.AppDatabase {
        return com.extrotarget.extropos.data.local.AppDatabase.create(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideProductDao(db: com.extrotarget.extropos.data.local.AppDatabase): ProductDao {
        return db.productDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db: com.extrotarget.extropos.data.local.AppDatabase): com.extrotarget.extropos.data.local.dao.CategoryDao {
        return db.categoryDao()
    }

    @Provides
    @Singleton
    fun provideMenuItemDao(db: com.extrotarget.extropos.data.local.AppDatabase): com.extrotarget.extropos.data.local.dao.MenuItemDao {
        return db.menuItemDao()
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

    @Provides
    @Singleton
    fun provideGuideOverlayManager(prefs: DashboardPrefs): GuideOverlayManager = GuideOverlayManager(prefs)

    // Extended DAOs for comprehensive POS functionality
    @Provides
    @Singleton
    fun provideSaleItemDao(): com.extrotarget.extropos.data.local.dao.SaleItemDao {
        return com.extrotarget.extropos.data.local.InMemorySaleItemDao()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(): com.extrotarget.extropos.data.local.dao.CustomerDao {
        return com.extrotarget.extropos.data.local.InMemoryCustomerDao()
    }

    @Provides
    @Singleton
    fun provideInventoryTransactionDao(): com.extrotarget.extropos.data.local.dao.InventoryTransactionDao {
        return com.extrotarget.extropos.data.local.InMemoryInventoryTransactionDao()
    }

    @Provides
    @Singleton
    fun providePaymentDao(): com.extrotarget.extropos.data.local.dao.PaymentDao {
        return com.extrotarget.extropos.data.local.InMemoryPaymentDao()
    }

    @Provides
    @Singleton
    fun provideSaleDao(): com.extrotarget.extropos.data.local.dao.SaleDao {
        return com.extrotarget.extropos.data.local.InMemorySaleDaoExtended()
    }
}