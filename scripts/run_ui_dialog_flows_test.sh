#!/usr/bin/env bash
set -euo pipefail

PKG="com.extrotarget.extropos"
RUNNER="com.extrotarget.extropos.HiltTestRunner"
TEST_CLASS="com.extrotarget.extropos.ui.tests.UiDialogFlowsTest"

ADB_DEVICE_FLAG=${1:-}

cd "$(dirname "$0")/.."

./gradlew :app:assembleDebug :app:assembleAndroidTest

if [ -n "$ADB_DEVICE_FLAG" ]; then
  adb -s "$ADB_DEVICE_FLAG" uninstall ${PKG} || true
  adb -s "$ADB_DEVICE_FLAG" uninstall ${PKG}.test || true
  adb -s "$ADB_DEVICE_FLAG" install -r -d app/build/outputs/apk/debug/app-debug.apk
  adb -s "$ADB_DEVICE_FLAG" install -r -d app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
  adb -s "$ADB_DEVICE_FLAG" shell am instrument -w -e class ${TEST_CLASS} ${PKG}.test/${RUNNER}
else
  adb uninstall ${PKG} || true
  adb uninstall ${PKG}.test || true
  adb install -r -d app/build/outputs/apk/debug/app-debug.apk
  adb install -r -d app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
  adb shell am instrument -w -e class ${TEST_CLASS} ${PKG}.test/${RUNNER}
fi
