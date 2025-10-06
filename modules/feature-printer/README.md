Feature: Printer SDK integration
===============================

This module contains printer adapters and helpers. It provides several adapters:

- GenericAdapter: raw TCP socket writer (default fallback)
- PosmacAdapter: TCP with Posmac wake sequence
- EpsonAdapter: TCP with Epson init and cut helper
- XPrinterAdapter: TCP with LF wake
- XPrinterSdkAdapter: reflection-based adapter – uses official XPrinter SDK if present, otherwise falls back to GenericAdapter
- PosmacSdkAdapter: reflection-based adapter – uses Posmac SDK if present, otherwise falls back to GenericAdapter

How to add official vendor SDKs
-------------------------------
1) If you have a Maven coordinate for the vendor SDK, add it to `modules/feature-printer/build.gradle.kts` in the `dependencies` section.

Example:

    implementation("com.vendor:printer-sdk:1.2.3")

2) If the vendor provides an AAR file, place it under `modules/feature-printer/libs/` and add:

    dependencies {
        implementation(files("libs/printer-sdk.aar"))
    }

3) The reflection-based adapters attempt to find common class names (e.g. `com.xprinter.sdk.XPrinter` or `com.posmac.sdk.Printer`). If the SDK exposes different class names you can either:
   - Update adapter reflection candidates, or
   - Replace the adapter with an SDK-backed implementation that directly references the SDK classes.

4) After adding the SDK, rebuild the project. The reflection adapters will automatically use SDK methods if detected.

Notes
-----
- Official SDKs often require permissions (USB/Bluetooth) and manifest entries. Add them per vendor docs.
- For production, prefer direct compile-time dependency to get stronger typing and full feature access.
