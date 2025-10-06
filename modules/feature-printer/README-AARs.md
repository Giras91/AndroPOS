Drop-in vendor AAR installation

Place vendor SDK AAR files (for example `xprinter-sdk.aar` or `posmac-sdk.aar`) into this module's `libs/` directory: `modules/feature-printer/libs/`.

This module is configured to automatically include any `.aar` files placed in `libs/` via the Gradle file. Example usage:

1. Create `modules/feature-printer/libs/` if it doesn't exist.
2. Copy `xprinter-sdk.aar` and/or `posmac-sdk.aar` into the `libs/` directory.
3. Rebuild the project: `./gradlew :modules:feature-printer:assembleDebug :app:assembleDebug`.

Notes:
- Committing vendor AARs to source control is optional. If you prefer not to commit binaries, keep them locally and add them to `.gitignore`.
- If the vendor AAR provides a different package name than the reflective adapters expect, you'll need to update `XPrinterSdkAdapter`/`PosmacSdkAdapter` or replace them with typed adapters that import the SDK's classes.

If you'd like, I can download and add specific AARs for you if you provide direct download URLs or upload the files here.
