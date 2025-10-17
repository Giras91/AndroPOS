# MIGRATION_8_9 — Tables schema corrective migration

Summary

This note documents the corrective migration added in `AppDatabase.MIGRATION_8_9` (v8 → v9).

Problem

Some on-device pre-v8 databases contained variants of the `tables` table where columns were missing or had different `NOT NULL` or `DEFAULT` definitions. Room's strict schema verification (used by `MigrationTestHelper`) will reject a migration if the final database schema does not match the exported schema JSON in `app/schemas/.../9.json`.

Fix applied

- `MIGRATION_8_9` now:
  - Creates a new `__tables_new` table whose DDL matches Room's exported schema for version 9 (column names, affinities, and nullability), avoiding unexpected `DEFAULT` clauses that previously caused mismatches.
  - Detects existing columns in the current `tables` table and builds an INSERT SELECT to copy rows; when a column is missing it substitutes a safe default value so data is preserved and the new NOT NULL columns are populated.
  - Swaps the tables by dropping the old table and renaming the new table.

Why this is safe

- Creating a new table and copying rows ensures we can precisely control column definitions including nullability and types.
- Explicit default substitutions guarantee that NOT NULL constraints are satisfied without losing pre-existing data.
- If the migration fails the code rethrows the exception so migration won't silently succeed in a bad state.

Testing

- A focused instrumentation test `AppDatabaseMigrationTest` was added/used locally to validate the migration using a pulled pre-v8 DB asset (`app/src/androidTest/assets/extropos_pre_v8.db`). The test runs `MigrationTestHelper` to apply `MIGRATION_8_9` and verifies the final schema matches the exported schema.
- A GitHub Actions workflow was added at `.github/workflows/migration-test.yml` to run this test in CI on an Android emulator.

Notes for maintainers

- If you change the `TableEntity` fields (nullability, types or names), update the migration and exported schema via Room's `exportSchema` so the migration logic and the exported JSON remain consistent.
- Prefer the create-new-table/copy/drop/rename pattern for complex schema changes (adding NOT NULL columns) to keep migrations deterministic.

Local run (developer)

1. Build and run the single migration instrumentation test on a connected device or emulator:

```bash
./gradlew :app:connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.extrotarget.extropos.data.local.AppDatabaseMigrationTest --no-daemon --console=plain
```

2. The test uses the asset at `app/src/androidTest/assets/extropos_pre_v8.db`. If you want to use a different pre-migration DB, replace that file before running the test.

CI notes

- The CI workflow `.github/workflows/migration-test.yml` runs the same focused test on an Android emulator. The workflow provisions an emulator (API 31 x86_64), builds the app and runs the single instrumentation class.
- Storing large binary DB snapshots in the repo is generally discouraged. Alternatives:
  - Store the pre-migration DB in release artifacts and download it in the workflow before the test run.
  - Use Git LFS if you must keep the DB in the repo.
  - Generate a pre-migration DB programmatically in a unit-test if possible, which is faster and keeps CI light-weight.
