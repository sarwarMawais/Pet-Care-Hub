# CI workflows

Empty on purpose — no workflow exists yet because there is nothing to build. Added in Phase 0 once the module graph has real build files.

## Planned

| Workflow | Trigger | Runs |
|---|---|---|
| `check.yml` | push, PR | `./gradlew allTests` — common, Android and (on a macOS runner) iOS unit tests |
| `android-release.yml` | tag | AAB, Play App Signing, upload to the internal track |
| `ios-release.yml` | tag | Handled by **Codemagic**, not GitHub Actions — see below |

## Why iOS builds live on Codemagic

You cannot build, sign or upload an iOS app from Windows, and this project's developer is on Windows. Three options were assessed in `docs/research/kmp-stack-and-motivoa-reuse.md`:

| Option | Cost | Verdict |
|---|---|---|
| **Codemagic** | 500 free min/mo, then ~$0.10/min | **Chosen.** Purpose-built for mobile, first-class KMP support, handles code signing and App Store Connect upload |
| Xcode Cloud | 25 compute hours included with the $99 Apple Developer Program | Cheapest at low volume, but needs a `ci_post_clone.sh` to install the JDK and run Gradle, and cannot build Android |
| GitHub Actions macOS runners | ~$0.062/min | Works, but you own the Fastlane and code-signing setup yourself |

**CI ships; only a Mac diagnoses.** Crash symbolication, Instruments profiling, VoiceOver testing and App Store screenshots all need interactive Xcode. Budget a used Mac mini in Phase 0.

## Notes for whoever writes these

- **Pin the Xcode version.** A mismatched SDK breaks Kotlin/Native framework linking.
- Release linking is ~10× slower than debug — only CI needs it. Build one architecture locally.
- Enable klib incremental compilation and Kotlin/Native caching (already set in `gradle.properties`).
- Never commit signing keys. `.gitignore` already excludes `*.keystore`, `*.jks`, `keystore.properties` and `GoogleService-Info.plist`.
