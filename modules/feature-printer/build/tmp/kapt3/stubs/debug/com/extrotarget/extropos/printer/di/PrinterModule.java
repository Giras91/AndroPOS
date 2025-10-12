package com.extrotarget.extropos.printer.di;

import android.content.Context;
import com.extrotarget.extropos.printer.adapter.PrinterAdapterFactory;
import com.extrotarget.extropos.printer.data.IPrinterRepository;
import com.extrotarget.extropos.printer.data.PrinterRepository;
import com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog;
import com.extrotarget.extropos.printer.domain.service.PrinterConfigService;
import com.extrotarget.extropos.printer.domain.service.PrinterDetectionService;
import com.extrotarget.extropos.printer.domain.service.PrinterService;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\b"}, d2 = {"Lcom/extrotarget/extropos/printer/di/PrinterModule;", "", "()V", "bindPrinterRepository", "Lcom/extrotarget/extropos/printer/data/IPrinterRepository;", "impl", "Lcom/extrotarget/extropos/printer/data/PrinterRepository;", "Companion", "feature-printer_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class PrinterModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.extrotarget.extropos.printer.di.PrinterModule.Companion Companion = null;
    
    public PrinterModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.extrotarget.extropos.printer.data.IPrinterRepository bindPrinterRepository(@org.jetbrains.annotations.NotNull()
    com.extrotarget.extropos.printer.data.PrinterRepository impl);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u001a\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\fH\u0007J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0004H\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/extrotarget/extropos/printer/di/PrinterModule$Companion;", "", "()V", "providePrinterAdapterFactory", "Lcom/extrotarget/extropos/printer/adapter/PrinterAdapterFactory;", "context", "Landroid/content/Context;", "providePrinterConfigService", "Lcom/extrotarget/extropos/printer/domain/service/PrinterConfigService;", "providePrinterDetectionService", "Lcom/extrotarget/extropos/printer/domain/service/PrinterDetectionService;", "sdkCatalog", "Lcom/extrotarget/extropos/printer/domain/catalog/PrinterSdkCatalog;", "providePrinterSdkCatalog", "providePrinterService", "Lcom/extrotarget/extropos/printer/domain/service/PrinterService;", "printerDetectionService", "printerConfigService", "adapterFactory", "feature-printer_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.extrotarget.extropos.printer.domain.service.PrinterConfigService providePrinterConfigService(@dagger.hilt.android.qualifiers.ApplicationContext()
        @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog providePrinterSdkCatalog() {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.extrotarget.extropos.printer.domain.service.PrinterDetectionService providePrinterDetectionService(@dagger.hilt.android.qualifiers.ApplicationContext()
        @org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.printer.domain.catalog.PrinterSdkCatalog sdkCatalog) {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.extrotarget.extropos.printer.adapter.PrinterAdapterFactory providePrinterAdapterFactory(@dagger.hilt.android.qualifiers.ApplicationContext()
        @org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @dagger.Provides()
        @javax.inject.Singleton()
        @org.jetbrains.annotations.NotNull()
        public final com.extrotarget.extropos.printer.domain.service.PrinterService providePrinterService(@org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.printer.domain.service.PrinterDetectionService printerDetectionService, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.printer.domain.service.PrinterConfigService printerConfigService, @org.jetbrains.annotations.NotNull()
        com.extrotarget.extropos.printer.adapter.PrinterAdapterFactory adapterFactory) {
            return null;
        }
    }
}