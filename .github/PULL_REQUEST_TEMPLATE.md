## Summary

Provide a short, one-line summary of the change and the problem it fixes.

## Type of change
- Bugfix
- Feature
- Refactor
- Docs

## How to test / reproduction steps
Include clear steps to reproduce and verify the change. Run these locally:

```bash
# quick smoke build
./gradlew assembleDebug

# run all unit tests (or scope to module)
./gradlew test
# run a single module's unit tests (example)
./gradlew :modules:feature-product:testDebugUnitTest
```

If this PR touches UI/hardware, run instrumentation tests on a device/emulator:

```bash
./gradlew connectedDebugAndroidTest
```

## Checklist (please check before requesting review)
- [ ] I ran the build and relevant unit tests locally
- [ ] I added/updated unit tests for any new public behavior
- [ ] I added Room migrations and bumped DB version in `AppDatabase` when changing entities
- [ ] I updated Hilt `@Module`/`@InstallIn` entries if DI bindings changed
- [ ] I followed `EDITOR_NOTES.md` (repo root) for edit-time rules
- [ ] I added notes for manual verification steps (if hardware or environment required)

## Files changed / notes for reviewers
List the important files changed and any special verification steps reviewers should run (DB migration, emulators, mock hardware, env vars).

## Assign reviewers
CODEOWNERS will auto-request reviewers; add specific reviewer mentions if needed.

---
If anything in this template is unclear or you need a different checklist for big migrations, mention it in the PR and update the template accordingly.
