# Editor Notes — quick reference

Short rules to follow when editing or adding code in this repo.

## Quick commands

```bash
# quick smoke build
./gradlew assembleDebug

# run all unit tests (or scope to module)
./gradlew test
# run a single module's unit tests (example)
./gradlew :modules:feature-product:testDebugUnitTest

# instrumentation tests (device/emulator)
./gradlew connectedDebugAndroidTest
```

## Schema / Room edits
- Add a Room Migration and bump DB version in `AppDatabase` (search for `Room.databaseBuilder`).
- Add DAO tests for any changed queries/upserts.
- DEV-only: document if using destructive migration.

## DI (Hilt)
- Update or add `@Module` / `@InstallIn` classes under `app/src/main/java/.../di` or `modules/*/di`.
- Keep `I`-prefixed interfaces stable; changing an interface requires updating all use-cases & ViewModels.

## Conventions to preserve
- Repositories use `I` prefix (e.g. `IProductRepository`) and are injected via Hilt.
- Use-cases are single-responsibility classes injected into ViewModels (e.g., `GetProductsUseCase`).
- ViewModels use coroutines + `StateFlow`. Use `viewModelScope.launch { ... }`.
- Monetary values stored and handled in cents (`Long`). Do not use floating point.

## Tests & verification
- Run focused module tests before repo-wide tests: `:module:testDebugUnitTest`.
- Add at least one unit test for new public behavior.
- Use mocks/abstractions for hardware-dependent logic.

## Commit & PR hygiene
- Keep commits small and focused.
- PR description should list build/test commands executed and mention DB migrations.

## Formatting & safety
- Follow existing Kotlin formatting; avoid mass reformatting.
- Keep vendor SDK wrappers isolated under `hardware/` or Java-compatible layers.
