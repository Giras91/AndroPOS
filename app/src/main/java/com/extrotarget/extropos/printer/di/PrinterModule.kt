package com.extrotarget.extropos.printer.di

// App-level placeholder to avoid accidental duplicate PrinterModule FQCN in the app module.
// The real DI modules for printers live in modules/feature-printer (PrinterModule) and
// GlobalPrinterModule provides app-specific concrete implementations.

object AppPrinterModulePlaceholder {
    // Intentionally empty. Do not annotate with @Module to avoid Dagger picking this up.
}
