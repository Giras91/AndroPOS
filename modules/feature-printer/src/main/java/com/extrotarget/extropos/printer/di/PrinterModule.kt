package com.extrotarget.extropos.printer.di

import com.extrotarget.extropos.printer.data.IPrinterRepository
import com.extrotarget.extropos.printer.data.PrinterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PrinterModule {
    @Binds
    @Singleton
    abstract fun bindPrinterRepository(impl: PrinterRepository): IPrinterRepository
}
