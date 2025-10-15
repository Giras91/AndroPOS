# AndroPOS — Printer Setup Worklog & Next Steps

Last updated: 2025-10-13

This file captures the work completed during the current editing session and provides a concise handoff you can pick up in Android Studio.

## What we implemented

- Replaced the old printer navigation target with a new enhanced printer setup flow (`PrinterSetupFragment`).
- Implemented multi-connection UI for printers (USB / Bluetooth / Network) and an Advanced Settings dialog.
- Implemented a working Add Printer dialog that:
  - Shows connection-specific UI and tips (address, port, hints).
  - Offers USB device suggestion probing common paths.
  - Lists paired Bluetooth devices (paired/bonded) for selection.
  - Allows manual IP and port entry for network printers.
  - Calls ViewModel methods to add printers and trigger scans.
- Added Printer Details dialog for status/actions and updated the printer list adapter to include a Details button.
- Wired plumbing: UI → `PrinterSetupViewModel` methods (scan/add), and fixed navigation to open our enhanced `PrinterSetupFragment` from Settings.
- Built and installed debug APK locally and verified installation on a connected device via ADB.

## Files created / edited (where to look)

- app/src/main/java/com/extrotarget/extropos/ui/settings/printer/
  - `PrinterSetupFragment.kt` — main fragment, UI wiring and selection dialog
  - `PrinterSetupViewModel.kt` — ViewModel with scan/add stubs
  - `PrinterModels.kt` — Printer, DiscoveredPrinter, enums (existing but used)

- app/src/main/java/com/extrotarget/extropos/ui/settings/printer/dialogs/
  - `AddPrinterDialogFragment.kt` — enhanced add-printer dialog (USB/Bluetooth/Network support)
  - `PrinterDetailsDialogFragment.kt` — printer info & actions dialog
  - `AdvancedPrinterSettingsDialogFragment.kt` — advanced settings UI
  - `ScanResultsDialogFragment.kt` — simple scan results placeholder

- app/src/main/java/com/extrotarget/extropos/ui/settings/printer/adapters/
  - `PrinterAdapter.kt` — updated to include details button

- Layouts
  - `app/src/main/res/layout/fragment_printer_setup.xml` — main layout (scan + Add button)
  - `app/src/main/res/layout/dialog_add_printer.xml` — add-printer form
  - `app/src/main/res/layout/item_printer.xml` — list item layout

- Navigation
  - `app/src/main/res/navigation/nav_graph_main.xml` — updated to point `printerSetupFragment` to `PrinterSetupFragment`

## What currently works (quick sanity checks)

- Settings → Printer Setup opens the enhanced UI.
- Bottom area shows `Scan Printers` and a visible `➕ Add Printer` button.
- Add Printer menu: choose Bluetooth / Network / USB / Scan / Advanced Settings.
- Add dialog shows appropriate fields and tips for the selected connection type.
- `Add` calls `PrinterSetupViewModel.addPrinter(...)` and shows a success dialog.
- `Details` opens the Printer Details dialog.

## Known gaps / intentionally incomplete areas

These were left as safe, non-destructive stubs or partial implementations and should be finished in Android Studio:

1. Hardware integration
   - USB: currently probes common device paths (`/dev/usb/lp0`, `/dev/ttyUSB*`). Replace with `UsbManager` enumeration + permission requests for robust support.
   - Bluetooth: currently reads paired devices. Add runtime permission handling (`BLUETOOTH_CONNECT`, `BLUETOOTH_SCAN` on Android 12+), active discovery if needed.
   - Network: no discovery (mDNS/UPnP) — only manual IP/port entry is implemented.

2. Persistence
   - `addPrinter()` is wired to the ViewModel but printers are not persisted to Room. Add a repository/DAO and persist printers so they survive app restarts.

3. Advanced Settings
   - Dialog exists but settings are not persisted/applied to drivers. Map fields to a `PrinterConfig` model and persist.

4. Permissions & UX
   - Add and test runtime permission flows for Bluetooth/USB.
   - Improve validation, error handling, loader states during scanning and timeouts.

## Suggested immediate tasks in Android Studio (priority)

1. Persist printers (HIGH)
   - Add Room entity/DAO for printers or extend existing DB schema.
   - Create `IPrinterRepository` implementation and inject into `PrinterSetupViewModel`.
   - Update `loadPrinters()` to read from repository.

2. Permissions (HIGH)
   - Implement runtime permission flows for Bluetooth scan/connect and USB.

3. Hardware scanning (MEDIUM)
   - Replace probe-based USB detection with `UsbManager` enumeration + permission request.
   - Implement improved Bluetooth discovery if needed.
   - Optional: add mDNS/UPnP for network discovery.

4. Advanced settings persistence & apply (MEDIUM)
   - Save advanced settings to DB and use them when connecting/printing.

5. Tests (LOW → MEDIUM)
   - Unit tests for `PrinterSetupViewModel` (add/scan behaviors).
   - Instrumentation tests (navigation + add dialog) as needed.

## Commands used during development

Build debug APK:
```bash
./gradlew assembleDebug
```

Install APK to a device with ADB (replace `<device-id>` if needed):
```bash
adb -s <device-id> install -r app/build/outputs/apk/debug/app-debug.apk
```

Run unit tests:
```bash
./gradlew test
```

Run instrumentation tests (device required):
```bash
./gradlew connectedDebugAndroidTest
```

## Quick tips for continuing in Android Studio

- Open project root `/home/hp/Documents/AndroPOS` in Android Studio and let Gradle sync.
- Use the Layout Editor to inspect `dialog_add_printer.xml` and `fragment_printer_setup.xml` visually while iterating.
- Set breakpoints in `AddPrinterDialogFragment.onCreateDialog()` and `PrinterSetupViewModel.addPrinter()` to debug flows.
- Use Logcat filters for tags you add (e.g., `PrinterSetup`) and test on a real tablet for USB and Bluetooth.

## Acceptance criteria for finishing Add Printer

- Added printers are persisted and show in `PrinterSetupFragment` after app restart.
- Bluetooth & USB flows request necessary permissions and handle errors gracefully.
- Advanced settings are saved and applied to printer driver configuration.

If you want, I can implement the Room persistence and permission flows next (I can do that here and verify builds). Tell me which to prioritize and I'll continue.
