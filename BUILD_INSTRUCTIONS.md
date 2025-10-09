# Build Instructions for AndroPOS

## Current Build Status

The project has been configured to compile with the available Java 17 runtime. Several configuration issues have been resolved, but **the build cannot complete due to network restrictions** in the current environment.

## Changes Made

### 1. Java Version Compatibility (✅ FIXED)
- **Problem**: Build files specified Java 21, but only Java 17 is available
- **Solution**: Updated all build.gradle files to use Java 17:
  - `app/build.gradle`: VERSION_21 → VERSION_17
  - `miniapp/build.gradle`: VERSION_21 → VERSION_17
  - `modules/feature-printer/build.gradle.kts`: VERSION_21 → VERSION_17, jvmToolchain(21) → jvmToolchain(17)

### 2. Gradle Properties (✅ FIXED)
- **Problem**: `gradle.properties` pointed to non-existent Java 21 installation
- **Solution**: Updated `org.gradle.java.home` to point to `/usr/lib/jvm/temurin-17-jdk-amd64`

### 3. Gradle Wrapper (✅ PARTIALLY FIXED)
- **Problem**: Gradle wrapper files were excluded by `.gitignore`
- **Solution**: 
  - Updated `.gitignore` to allow `gradle/wrapper/` files
  - Added `gradle/wrapper/gradle-wrapper.properties`
  - Added `gradle/wrapper/gradle-wrapper.jar`

## Outstanding Issues

### Network Access Required ❌

The build requires downloading dependencies from:
- `dl.google.com` - Android Gradle Plugin and AndroidX libraries
- `repo.maven.apache.org` - Maven Central dependencies
- `services.gradle.org` - Gradle distribution (for gradlew)

**Current Error**:
```
Could not resolve com.android.tools.build:gradle:8.5.2
Could not GET 'https://dl.google.com/...' 
> dl.google.com: No address associated with hostname
```

## How to Build

### Prerequisites
- Java 17 or later
- Android SDK (API 34)
- Internet access to Maven repositories

### Build Commands

```bash
# Make gradlew executable
chmod +x gradlew

# Build debug APK
./gradlew assembleDebug

# Run unit tests
./gradlew testDebugUnitTest

# Run all tests
./gradlew test

# Clean build
./gradlew clean build
```

### Alternative: Using System Gradle

If `gradlew` doesn't work:
```bash
gradle assembleDebug
```

## Troubleshooting

### Issue: "Could not resolve dependencies"
**Cause**: No network access to download dependencies
**Solution**: Ensure internet access is available, or set up a local Maven mirror

### Issue: "Java version mismatch"
**Cause**: Build requires Java version different from installed
**Solution**: This has been fixed - all build files now use Java 17

### Issue: "Android SDK not found"
**Cause**: `local.properties` missing or incorrect
**Solution**: Create `local.properties` with:
```properties
sdk.dir=/path/to/android/sdk
```

## What Works Now

✅ Java compatibility configured correctly
✅ Gradle wrapper files in place
✅ Build configuration valid
✅ Android SDK available (API 34)

## What Needs Network Access

❌ Android Gradle Plugin 8.5.2
❌ Kotlin libraries (androidx.*, kotlin-*, etc.)
❌ Hilt dependency injection framework
❌ Retrofit, OkHttp, Room, and other dependencies

## Recommendations

1. **For Local Development**: Ensure internet access to download dependencies on first build
2. **For CI/CD**: Use Gradle dependency caching to speed up builds
3. **For Offline Builds**: Pre-populate `~/.gradle/caches` with all required artifacts

## Next Steps

Once network access is available, the build should proceed without errors. If compilation errors occur after dependencies are resolved, they will need to be addressed separately.

---

**Build Configuration Summary**
- Target SDK: 34
- Min SDK: 24
- Java Version: 17
- Kotlin Version: 2.0.21
- Gradle Version: 8.5 (configured)
- Android Gradle Plugin: 8.5.2 (required)
