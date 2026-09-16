# Start here

**If you are picking this project up with no prior context — human or agent — read this file first.** It tells you what this is, what state it is in, what you are not allowed to change, and what to read next depending on what you have been asked to do.

---

## What this is

**Pet Care Hub** — a household-shared pet care app for Android **and** iOS, built on Kotlin Multiplatform + Compose Multiplatform.

One app that does both of the things the market currently splits in two:

- **The daily loop** — who fed, walked and medicated which pet, and when, shared live across everyone who looks after them.
- **The lifetime record** — vaccinations, medications, weight, vet visits and documents, owned by the family and portable between vets, apps and countries.

Plus exports shaped for the moment someone demands paperwork: a boarding certificate, a new-vet handover, an insurance claim pack, a sitter brief.

**Unlimited pets on one price, forever.** That is the wedge, and it is not negotiable — see [`adr/0005`](adr/0005-unlimited-pets-free.md).

## What state it is in

> **Planning is complete. Implementation has not started. There is no application code in this repository.**

| | |
|---|---|
| Repo contains | Module directory structure, dependency catalogue, documentation |
| Repo does **not** contain | Any Kotlin, Swift, build files per module, or a Gradle wrapper |
| Gradle sync | **Will not work yet.** Every `include()` in `settings.gradle.kts` is commented out on purpose — the directories show the shape, but no module has a build file, so a sync would fail on the first one. Uncomment each as it gets a real build file. |
| Next action | [`PHASE-0.md`](PHASE-0.md) — legal setup, toolchain, three spikes. None of it is feature code. |
| Plan date | 15 September 2026. Research is point-in-time; re-verify versions and store policies before relying on them. |

## Read in this order

**Everyone, first (about 20 minutes):**

1. **[`SESSION-LOG.md`](SESSION-LOG.md) — where the project actually is right now, and what the last session left for you. This is the source of truth for state.**
2. This file
3. [`CONTEXT.md`](CONTEXT.md) — how we got here, who is building it, what has already been decided and closed
4. [`../CLAUDE.md`](../CLAUDE.md) — the twelve product laws and the platform traps. **These are binding.**

**If you are an agent:** [`AGENT-PROMPT.md`](AGENT-PROMPT.md) has the start-of-session prompt, the
required end-of-session protocol, and the definition of done. Memory is keyed to the working
directory, so opening this folder gives you an empty memory — **the repo carries the state.**

**Then, depending on the task:**

| If you were asked to… | Read |
|---|---|
| Understand the product or the market | [`PLAN.md`](PLAN.md) §1–§5 |
| Build or change a screen | [`PLAN.md`](PLAN.md) §6 — all 22 screens, each across UI · UX · Data · Android · iOS · Play · Apple |
| Make a technical decision | [`ARCHITECTURE.md`](ARCHITECTURE.md), then [`adr/`](adr/) |
| Touch sync, the data model, or anything offline | [`ARCHITECTURE.md`](ARCHITECTURE.md) + [`adr/0003`](adr/0003-append-only-care-log.md) |
| Touch reminders or notifications | [`adr/0004`](adr/0004-reminders-are-data.md) + [`PLAN.md`](PLAN.md) §6.7–6.8. **This is the highest-risk area in the product.** |
| Touch pricing, the paywall, or the free tier | [`adr/0005`](adr/0005-unlimited-pets-free.md) + [`PLAN.md`](PLAN.md) §5 |
| Prepare a store submission | [`COMPLIANCE.md`](COMPLIANCE.md) — work the checklist top to bottom |
| Reuse code from the developer's other app | [`MOTIVOA-REUSE.md`](MOTIVOA-REUSE.md) |
| Start work at all | [`PHASE-0.md`](PHASE-0.md) |
| Look up a term you don't recognise | [`GLOSSARY.md`](GLOSSARY.md) |
| Check a claim or find the evidence | [`research/`](research/) — three source reports, ~250 KB, with inline citations |

## What you must not change without asking

These are settled decisions with reasoning recorded. Reversing one is expensive, and several are promises to users we cannot walk back.

1. **Unlimited pets in the free tier.** Six competitors were punished for gating this. Permanent.
2. **Archiving a pet that has died is free** and never consumes a slot, never shows a paywall.
3. **The care log is append-only.** Never mutate a `CareEvent`; edits write a new row, deletes are tombstones.
4. **Reminders are data, not OS alarms.** The schedule lives in the database; the OS holds a rolling cache.
5. **Never declare `USE_EXACT_ALARM`** on Android — it blocks the upload and we do not qualify.
6. **Never retract a free feature.**
7. **The app is never child-directed** in art or copy — that drags us into Play's Families policy and COPPA.
8. **Say "record", never "diagnose".** No dosage calculators, ever.

If a task seems to require breaking one of these, stop and raise it rather than working around it.

## The three things that decide whether this succeeds

Every competitor gets at least one of these wrong. Nothing else matters if these fail:

1. **Sync between household devices** — under 3 seconds on Wi-Fi, offline writes queue and reconcile
2. **Medication reminders that fire on time** — on both platforms, surviving reboot and timezone change
3. **Documents that open offline, in-app, at the vet counter**

## Where things live

```
Pet/                          <- you are here
├── CLAUDE.md                 product laws + platform traps (binding)
├── README.md                 short public-facing overview
├── settings.gradle.kts       the module graph (includes commented out)
├── gradle/libs.versions.toml every dependency, version and risk rating
├── docs/
│   ├── SESSION-LOG.md        current state + what to do next + session history
│   ├── AGENT-PROMPT.md       the session protocol every agent must follow
│   ├── START-HERE.md         this file
│   ├── CONTEXT.md            how we got here, who, constraints
│   ├── PLAN.md               the full plan — 12 sections, 970 lines
│   ├── PHASE-0.md            the actionable next step
│   ├── ARCHITECTURE.md       layering, data model, reminder design
│   ├── COMPLIANCE.md         pre-submission checklist, both stores
│   ├── MOTIVOA-REUSE.md      exact files to copy from the other app
│   ├── GLOSSARY.md           domain and project terms
│   ├── SUMMARY.md            condensed plan, one page
│   ├── plan-page.html        the same plan as a styled page (content is identical to PLAN.md)
│   ├── adr/                  five decisions that are expensive to reverse
│   └── research/             three source reports with citations
├── shared/                   12 core-* + 9 feature-* modules (empty source sets)
├── androidApp/               README explains what belongs here
└── iosApp/                   README explains the three native screens
```

**Related repository:** the same developer's shipped Android app **Motivoa** lives at `C:\Users\MuhammadSarwar\AndroidProjects\Motivoa`. Five assets are worth copying from it — see [`MOTIVOA-REUSE.md`](MOTIVOA-REUSE.md). Do not port it wholesale.

## Conventions

- **Commit straight to `main`.** No feature branches on this repo.
- Kotlin official code style.
- Anything pure goes in `commonTest`.
- Dates: `kotlinx-datetime` only, locale formatting through `core-datetime`'s `expect`.
- Strings: Compose Resources. Never concatenate two looked-up strings — that is grammar, and grammar belongs in the resource file.
- Record a decision in [`adr/`](adr/) when reversing it would mean changing the data model, the store listing, the pricing promise, or the platform contract.

## If you are unsure

The plan is opinionated and the reasoning is written down. If something in the docs contradicts what you have been asked to do, say so rather than silently picking one. If a claim looks out of date — versions, store policies, competitor ratings — it probably is: the research is dated 15 September 2026 and should be re-verified, not assumed.
