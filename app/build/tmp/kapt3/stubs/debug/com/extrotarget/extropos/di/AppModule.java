package com.extrotarget.extropos.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007J\b\u0010\u0019\u001a\u00020\u001aH\u0007J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\bH\u0007J\b\u0010\u001d\u001a\u00020\u001eH\u0007J\b\u0010\u001f\u001a\u00020 H\u0007J\b\u0010!\u001a\u00020\"H\u0007J\b\u0010#\u001a\u00020$H\u0007J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u0010\u001a\u00020\bH\u0007J\u0010\u0010\'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u001eH\u0007J\b\u0010)\u001a\u00020*H\u0007J\b\u0010+\u001a\u00020,H\u0007J\b\u0010-\u001a\u00020.H\u0007J\b\u0010/\u001a\u000200H\u0007\u00a8\u00061"}, d2 = {"Lcom/extrotarget/extropos/di/AppModule;", "", "()V", "provideApiService", "Lcom/extrotarget/extropos/data/remote/ApiService;", "retrofit", "Lretrofit2/Retrofit;", "provideAppDatabase", "Lcom/extrotarget/extropos/data/local/AppDatabase;", "application", "Landroid/app/Application;", "provideAppwriteClient", "Lio/appwrite/Client;", "app", "provideCategoryDao", "Lcom/extrotarget/extropos/data/local/dao/CategoryDao;", "db", "provideContext", "Landroid/content/Context;", "provideCustomerDao", "Lcom/extrotarget/extropos/data/local/dao/CustomerDao;", "provideGuideOverlayManager", "Lcom/extrotarget/extropos/ui/guide/GuideOverlayManager;", "prefs", "Lcom/extrotarget/extropos/ui/main/DashboardPrefs;", "provideInventoryTransactionDao", "Lcom/extrotarget/extropos/data/local/dao/InventoryTransactionDao;", "provideMenuItemDao", "Lcom/extrotarget/extropos/data/local/dao/MenuItemDao;", "provideMoshi", "Lcom/squareup/moshi/Moshi;", "provideOrderDao", "Lcom/extrotarget/extropos/data/local/dao/OrderDao;", "provideOrderItemDao", "Lcom/extrotarget/extropos/data/local/dao/OrderItemDao;", "providePaymentDao", "Lcom/extrotarget/extropos/data/local/dao/PaymentDao;", "provideProductDao", "Lcom/extrotarget/extropos/data/local/dao/ProductDao;", "provideRetrofit", "moshi", "provideSaleDao", "Lcom/extrotarget/extropos/data/local/dao/SaleDao;", "provideSaleItemDao", "Lcom/extrotarget/extropos/data/local/dao/SaleItemDao;", "provideTableDao", "Lcom/extrotarget/extropos/data/local/dao/TableDao;", "provideTicketDao", "Lcom/extrotarget/extropos/data/local/dao/TicketDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context provideContext(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final io.appwrite.Client provideAppwriteClient(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.TicketDao provideTicketDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.OrderDao provideOrderDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.OrderItemDao provideOrderItemDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.TableDao provideTableDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.AppDatabase provideAppDatabase(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.ProductDao provideProductDao(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.AppDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.CategoryDao provideCategoryDao(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.AppDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.MenuItemDao provideMenuItemDao(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.data.local.AppDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.squareup.moshi.Moshi provideMoshi() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideRetrofit(@org.jetbrains.annotations.NotNull()
    com.squareup.moshi.Moshi moshi) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.remote.ApiService provideApiService(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.ui.guide.GuideOverlayManager provideGuideOverlayManager(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.ui.main.DashboardPrefs prefs) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.SaleItemDao provideSaleItemDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.CustomerDao provideCustomerDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.InventoryTransactionDao provideInventoryTransactionDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.PaymentDao providePaymentDao() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.extrotarget.extropos.data.local.dao.SaleDao provideSaleDao() {
        return null;
    }
}