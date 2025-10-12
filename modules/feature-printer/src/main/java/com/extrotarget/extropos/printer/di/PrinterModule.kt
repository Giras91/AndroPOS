package com.extrotarget.extropos.printer.di

import android.content.Context
import com.extrotarget.extropos.printer.adapter.PrinterAdapterFactory
import com.extrotarget.extropos.printer.data.IPrinterRepository
import com.extrotarget.extropos.printer.data.PrinterRepository
import com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog
import com.extrotarget.extropos.printer.domain.service.PrinterConfigService
import com.extrotarget.extropos.printer.domain.service.PrinterDetectionService
import com.extrotarget.extropos.printer.domain.service.PrinterService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrinterModule {
    @Binds
    @Singleton
    abstract fun bindPrinterRepository(impl: PrinterRepository): IPrinterRepository

    companion object {
        @Provides
        @Singleton
        fun providePrinterConfigService(@ApplicationContext context: Context): PrinterConfigService {
            return PrinterConfigService(context)
        }

        @Provides
        @Singleton
        fun providePrinterSdkCatalog(): com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog {
            return com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog()
        }

        @Provides
        @Singleton
        fun providePrinterDetectionService(
            @ApplicationContext context: Context,
            sdkCatalog: com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog
        ): PrinterDetectionService {
            return PrinterDetectionService(context, sdkCatalog)
        }

        @Provides
        @Singleton
        fun providePrinterAdapterFactory(@ApplicationContext context: Context): PrinterAdapterFactory {
            return PrinterAdapterFactory(context)
        }

        @Provides
        @Singleton
        fun providePrinterService(
            printerDetectionService: PrinterDetectionService,
            printerConfigService: PrinterConfigService,
            adapterFactory: PrinterAdapterFactory
        ): PrinterService {
            return PrinterService(printerDetectionService, printerConfigService, adapterFactory)
        }
    }
}
