# Session log

**The durable state of this project.** Agent memory is keyed to the working directory and is not guaranteed to exist — this file is. Anything that matters to the next session goes here, not only in memory.

Two parts: **Where we are right now**, rewritten every session, and the **history** below it, which is append-only.

---

# Where we are right now

> **Last updated:** 2026-09-16 (session 2)
> **Phase:** Pre-Phase-0 — planning complete, nothing built
> **Branch:** `main` · **Head:** `daceecb`

## Status

| | |
|---|---|
| Application code | **None.** No Kotlin, no Swift, no per-module build files, no Gradle wrapper |
| Gradle sync | **Does not work yet, on purpose.** Every `include()` in `settings.gradle.kts` is commented out because no module has a build file |
| Documentation | Complete. 11 documents, ~24,000 words, all links verified |
| Legal entity | Not started |
| Developer accounts | Not created |
| Mac / iOS toolchain | Not acquired |
| Supabase project | Not created |

## Next up — do these in order

Everything below is [`PHASE-0.md`](PHASE-0.md). **None of it is feature code**, and Phase 1 must not start until all of it is done.

1. **Apply for a D-U-N-S number** — free, 5–14 business days, and it blocks the Organization enrolments. Longest lead time, so start it first even if nothing else happens this week.
2. **Register the legal entity**, get a business address (not home — it is publicly displayed under the EU DSA) and a business phone number.
3. **Trademark search** on "Pet Care Hub" in US / EU / UK / CA. See [`adr/0001`](adr/0001-provisional-name.md). The name must be settled before the first Play production upload, not before the first beta.
4. **Decide Mac vs cloud macOS**, then get a signed iOS hello-world KMP build onto a physical device via Codemagic. Until this works, nothing else matters.
5. **Spike A** — Navigation 3 on iOS with working interactive swipe-back.
6. **Spike B** — Room KMP + PowerSync + Supabase EU round-trip between two physical devices. Target under 3 seconds; test airplane mode and membership revocation.
7. **Spike C** — exact-alarm consent on Android 14/15/16 with a working inexact fallback, and the iOS ≤40-slot materialiser.
8. **Ask Play** whether a pet-medication reminder qualifies for `USE_EXACT_ALARM`. Expected answer: no. Ship `SCHEDULE_EXACT_ALARM` regardless.

## Blocked on the user — do not decide these alone

- **Final product name** (trademark search pending) → decides the package and bundle ID
- **Mac mini purchase vs cloud macOS rental** — the plan recommends buying
- **Species scope at launch** — recommendation is dog + cat templates with "Other" fully supported
- **Lifetime price** ($79.99 proposed) and the free-tier document cap (20 or 50)
- **Whether to engage EU legal counsel** for GDPR processor agreements and the EAA exemption question

## Open questions carried forward

- ⚠️ The `USE_EXACT_ALARM` reading is inferred from policy language, not an explicit Google statement. Item 8 above resolves it.
- ⚠️ Whether **pet** health data counts as "Health" under Apple's Nutrition Labels or Play's Data safety form. No policy text addresses animals. Current plan: declare as User Content, ship the disclaimer as a hedge, keep the reasoning written down.
- ⚠️ EAA microenterprise exemption varies by member state transposition.
- Every library version in `gradle/libs.versions.toml` is provisional and dated 2026-09-15. Pin and re-verify before the first release branch.

## Advice on record, not yet acted on

**Finish and ship Motivoa before starting Phase 1 here.** Remaining work there is billing, the on-device AI red-team probes, and Play submission — weeks, not months. Two half-shipped apps is worse than one shipped app plus a plan.

---

# History

Append a new entry at the **top of this section** each session. Never edit an older entry — if something turned out to be wrong, say so in the new entry.

---

## Session 2 — 2026-09-16 · repo, structure and self-containment

**Commits:** `694ca91`, `daceecb`

**Done**

- Created the repository at `C:\Users\MuhammadSarwar\AndroidProjects\Pet`, git initialised, committed to `main`.
- Laid out the KMP module graph as empty source sets: 12 `core-*`, 9 `feature-*`, plus `androidApp` and `iosApp`.
- Wrote `settings.gradle.kts`, `build.gradle.kts`, `gradle.properties`, `.gitignore`, and `gradle/libs.versions.toml` with every dependency carrying a version, a risk rating and a provenance note.
- Expanded the plan into a full 972-line `docs/PLAN.md` and mirrored the planning-session research into `docs/research/`.
- Wrote `ARCHITECTURE.md`, `COMPLIANCE.md`, `PHASE-0.md`, five ADRs, and module READMEs for `shared/`, `androidApp/` and `iosApp/`.
- Second pass after review: added `START-HERE.md`, `CONTEXT.md`, `MOTIVOA-REUSE.md`, `GLOSSARY.md`; fixed stale paths in `SUMMARY.md`; verified every relative link resolves.

**Decisions made**

- **Commented out every `include()` in `settings.gradle.kts`.** The directories show the shape, but an uncommented include would fail the Gradle sync on the first missing build file. Uncomment them one at a time in Phase 1.
- **Copy five assets from Motivoa rather than porting it.** The effort table (108 files for resources, 45 for `java.time`, 39 for `Context`, 28 for DI) makes the case.
- Kept the condensed plan as `SUMMARY.md` rather than overwriting it, because it holds the how-we-got-here context.

**Corrected**

- `CompanionChibi.kt` is **1,213 lines**, not the 1,130 the research report stated. All Motivoa paths in `MOTIVOA-REUSE.md` were verified against the working tree rather than trusted from the report.

**Learned**

- The repo could not previously answer "where is Motivoa?" — `CLAUDE.md` referenced its files without giving a path. Fixed. This is the class of gap to watch for: **docs that assume the reader shares the author's context.**

---

## Session 1 — 2026-09-15 · research and plan

**Artifact:** https://claude.ai/artifact/VaMa9VqJyBNvxiUST5zWT8

**Done**

- Explored and rejected three product directions before pets: Trainline-style rail booking, a Vinted-style marketplace, and a pharma B2B SaaS (**declined by the user — do not re-propose**).
- Ran three parallel research passes: store policies, competitors and user research, and the KMP stack plus a Motivoa portability audit. Store figures were scraped live; several hundred verbatim reviews collected.
- Wrote the plan: honest growth ceiling, 12 ranked jobs-to-be-done, competitor scoreboard, 12 product laws, pricing, all 22 screens across 7 facets, architecture, compliance, roadmap.

**Decisions made**

- The thesis: combine shared daily logging with a portable health record. Nobody does both.
- Unlimited pets free forever; per-household pricing; a lifetime tier.
- Supabase EU + PowerSync; append-only care log; reminders as data.
- Sized the business honestly at 100–300K installs and 3–6K paying households, not millions.

**Corrected during the session**

- "Did anyone feed the dog?" is **not** an unserved hook — at least eight apps do it, all tiny. The gap is combining it with records.
- Published pet-app market figures vary 2.3× between research firms; none of them drove the plan.
