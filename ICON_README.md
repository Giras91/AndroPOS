Place the provided app icon image file into the Android project's resources so it becomes the app launcher icon.

Steps:
1. Copy the PNG file you provided (Gemini_Generated_Image_uowwhpuowwhpuoww.png) into:
   app/src/main/res/drawable-nodpi/extropos_ic_foreground.png

2. Build the debug APK to confirm the adaptive icon compiles:
   ./gradlew :app:assembleDebug -x lint

3. Install on a connected device/emulator:
   adb install -r app/build/outputs/apk/debug/app-debug.apk

Notes:
- I added adaptive icon XML in `app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml` and
  `ic_launcher_round.xml` which use `@drawable/ic_extropos_foreground` as the foreground.
- The placeholder file `app/src/main/res/drawable-nodpi/extropos_ic_foreground.png` currently
  exists as a placeholder; replace it with the actual PNG.
