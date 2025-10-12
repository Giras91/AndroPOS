package com.extrotarget.extropos.printer.di

import com.extrotarget.extropos.printer.service.GlobalPrinterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlobalPrinterModule {
    
    @Provides
    @Singleton
    fun provideGlobalPrinterService(): GlobalPrinterService {
        return GlobalPrinterService()
    }
}