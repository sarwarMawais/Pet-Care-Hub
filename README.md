# Pet Care Hub

A household-shared pet care app for Android and iOS, built on Kotlin Multiplatform.

One app that does **both** of the things the market currently splits in two:

- **The daily loop** — who fed, walked and medicated which pet, and when, shared live across everyone who looks after them.
- **The lifetime record** — vaccinations, medications, weight, vet visits and documents, owned by the pet's family and portable between vets, apps and countries.

Plus the exports people actually need at the moment someone demands paperwork: a boarding certificate, a new-vet handover, an insurance claim pack, a sitter brief.

**Unlimited pets on one price. Forever.**

---

## Status

> **Planning complete. Implementation has not started.**
>
> This repository currently contains the project structure, the dependency catalogue and the documentation. There is no application code yet.

| | |
|---|---|
| **New here?** | **[`docs/START-HERE.md`](docs/START-HERE.md) — read this first.** What to read, in what order, and what you must not change |
| Context | [`docs/CONTEXT.md`](docs/CONTEXT.md) — how we got here, who is building it, what is already decided |
| Plan | [`docs/PLAN.md`](docs/PLAN.md) — the full plan: research, competitors, every screen, architecture, compliance, roadmap |
| Next step | [`docs/PHASE-0.md`](docs/PHASE-0.md) — legal, toolchain, three spikes |
| Architecture | [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md) |
| Store compliance | [`docs/COMPLIANCE.md`](docs/COMPLIANCE.md) — the pre-submission checklist |
| Decisions | [`docs/adr/`](docs/adr/) — one file per locked decision |
| Code reuse | [`docs/MOTIVOA-REUSE.md`](docs/MOTIVOA-REUSE.md) — exact files to copy from the developer's other app |
| Glossary | [`docs/GLOSSARY.md`](docs/GLOSSARY.md) |
| Research | [`docs/research/`](docs/research/) — the three source reports, dated 2026-09-15 |
| Working agreements | [`CLAUDE.md`](CLAUDE.md) — the twelve product laws, binding |

Next action is **Phase 0** in the plan. None of it is feature code: register the legal entity, get the toolchain producing a signed iOS build, and run the three spikes that decide the architecture.

---

## Getting started

Nothing to build yet. When Phase 0 begins:

```bash
# Generate the Gradle wrapper (not committed yet — no wrapper jar in the repo)
gradle wrapper --gradle-version 9.2

# Android
./gradlew :androidApp:assembleDebug

# iOS — requires macOS and Xcode 26+. Cannot be built from Windows.
# See docs/PLAN.md "The Windows problem".
```

## Module layout

```
shared/
  core-model          pure data classes — depends on nothing
  core-common         Result, dispatchers (expect), Clock, UUID (expect)
  core-datetime       kotlinx-datetime + expect locale-aware formatting
  core-designsystem   palette (accentFill rule), type, motion tokens, atoms
  core-database       Room entities/DAOs + expect builder (App Group path on iOS)
  core-datastore      DataStore Preferences + expect path
  core-network        Ktor + Supabase client
  core-sync           PowerSync schema, event model, conflict policy
  core-notifications  expect Scheduler — the rolling-window materialiser
  core-billing        RevenueCat wrapper, Entitlement model
  core-ai             interface AiEngine + UnsupportedAiEngine (the default)
  core-files          FileKit wrapper, compression, expect share sheet

  feature-pets · feature-log · feature-reminders · feature-household
  feature-record · feature-documents · feature-export · feature-paywall
  app-ui              Navigation 3 graph, scaffold, theme host

androidApp/           Application, Glance widgets, FCM service
iosApp/               SwiftUI shell, ComposeUIViewController host, WidgetKit, Sign in with Apple
```

**Layering rule:** `feature-*` → `core-*` → `core-model`. No feature depends on another feature; cross-feature coordination goes through `core-model` events. `expect`/`actual` lives only in `core-*` modules — never in a feature. This is the single discipline that keeps iOS from bleeding into product code.

## Licence

Proprietary. All rights reserved.
