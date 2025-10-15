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

    @Provides
    @Singleton
    fun providePrinterRepository(impl: com.extrotarget.extropos.printer.data.PrinterRepository): com.extrotarget.extropos.printer.data.IPrinterRepository = impl

    @Provides
    @Singleton
    fun providePrinterLocalRepository(impl: com.extrotarget.extropos.printer.data.PrinterLocalRepository): com.extrotarget.extropos.printer.data.IPrinterLocalRepository = impl

    @Provides
    @Singleton
    fun providePrinterScanner(impl: com.extrotarget.extropos.printer.data.PrinterScannerImpl): com.extrotarget.extropos.printer.data.IPrinterScanner = impl
}