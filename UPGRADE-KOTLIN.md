# Kotlin Forward Upgrade Plan (Beyond 2.1.0)

This document outlines a safe, incremental path to move AndroPOS from the current **stabilized Kotlin 2.1.0 + Java 21** setup to a newer Kotlin release (e.g. 2.2.x or later) once the ecosystem processors fully support the newer Kotlin metadata.

## Current Constraints
- Annotation processors (Hilt 2.52, Room 2.7.0) reject Kotlin metadata `2.2.0` (`Provided Metadata instance has version 2.2.0, while maximum supported version is 2.1.0`).
- We presently force Kotlin artifacts to 2.1.0 and `kotlinx-metadata-jvm:0.7.0` via root `build.gradle` resolutionStrategy.

## Goals
1. Remove hard version forcing for Kotlin stdlib, reflect, and metadata.
2. Adopt latest stable Kotlin (>= target version) with full kapt/XProcessing compatibility.
3. Keep build reproducible and green at each step.
4. Avoid mixing metadata versions across modules during transition.

## Preconditions Checklist
- [ ] New releases of Hilt & Room list compatibility with Kotlin target version (changelog / release notes).
- [ ] kotlinx-metadata-jvm (direct or shaded) used by processors supports the new metadata version.
- [ ] AGP version in use (8.13.0) is compatible with the target Kotlin version (verify release matrix).

## Step-by-Step Migration
### Step 1: Create an Upgrade Branch
```
git checkout -b chore/upgrade-kotlin-2.2
```

### Step 2: Bump Kotlin Plugin & Stdlib
1. In `settings.gradle` pluginManagement block: set plugin versions to target (e.g. `2.2.0`).
2. In root `build.gradle` buildscript classpath, bump `kotlin-gradle-plugin`.
3. Remove Kotlin version force entries temporarily (leave metadata force in place until kapt passes).

### Step 3: Update Annotation Processors
Upgrade sequentially (adjust versions as available when performing the real upgrade):
- Hilt: `2.52` -> (example future) `>= 2.5x` release that declares Kotlin target support.
- Room: `2.7.0` -> (example future) `>= 2.7.x` or `2.8.x` containing upgraded XProcessing.

### Step 4: Trial Build (Expect Possible Metadata Error)
Run:
```
./gradlew clean :app:kaptDebugKotlin --stacktrace
```
If metadata mismatch persists:
- Add/adjust an explicit dependency: `kapt("org.jetbrains.kotlinx:kotlinx-metadata-jvm:<latest>")`.
- Inspect processor JARs (look for shaded `kotlinx/metadata/jvm/internal/JvmReadUtils`) to confirm embedded version.

### Step 5: Remove Forced Kotlin Resolution
Once kapt succeeds without the metadata mismatch:
1. Remove `resolutionStrategy.force` entries for Kotlin libs.
2. Keep only entries that are still needed (ideally none).
3. Re-run full assemble + unit tests.

### Step 6: Toolchain & Compiler Options Validation
Ensure all modules:
- Use `jvmTarget = JVM_21`.
- Use K2 compiler (optional, when stable) by enabling in gradle.properties if desired: `kotlin.experimental.tryK2=true` (ONLY after verifying plugin stability).

### Step 7: Regression Testing
Run:
```
./gradlew clean assembleDebug testDebugUnitTest
```
Optional instrumentation:
```
./gradlew connectedDebugAndroidTest
```

### Step 8: Dependency Audit
Use:
```
./gradlew :app:dependencies --configuration debugCompileClasspath > deps.txt
```
Check for stray Kotlin 2.1.x artifacts alongside 2.2.x; none should remain.

### Step 9: Remove Upgrade Doc & Update README
- Delete `UPGRADE-KOTLIN.md` (or move to `docs/changes/`).
- Amend README Java/Kotlin section to reflect new baseline.

### Step 10: Merge & Tag
```
git checkout main
git merge --no-ff chore/upgrade-kotlin-2.2
./gradlew clean build
git tag -a v<newVersion> -m "Kotlin <newVersion> upgrade"
git push origin main --tags
```

## Rollback Plan
If kapt failures or runtime issues occur:
1. Revert Kotlin plugin & stdlib to 2.1.0.
2. Re-add force block (if removed).
3. Re-run build; verify green before continuing feature work.

## Observability Suggestions
- Add a lightweight CI matrix job running `:app:kaptDebugKotlin` explicitly.
- Add a dependency version lock (Gradle version catalogs + `./gradlew dependencies --write-locks`).

## Risk Mitigation
| Risk | Mitigation |
|------|------------|
| Processor rejects metadata | Upgrade processor or keep Kotlin at 2.1.0 until supported |
| Mixed metadata versions across modules | Single atomic upgrade branch; clean builds only |
| Hidden transitive Kotlin downgrade | Enforce via version catalog or constraints during transition |
| Performance regressions with new Kotlin | Benchmark critical hot paths (if any) after upgrade |

## Quick Status Command Bundle
```bash
./gradlew --version
./gradlew :app:dependencyInsight --dependency kotlin-stdlib
./gradlew :app:kaptDebugKotlin --stacktrace
```

---
Prepared to streamline future Kotlin upgrades while keeping production builds stable.
