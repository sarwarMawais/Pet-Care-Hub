# Agent prompt & session protocol

Two things live here:

1. **[The prompt](#the-prompt)** — paste it at the start of a session, or just point an agent at this file.
2. **[The protocol](#the-protocol)** — what every session must do at the start, during, and at the end. This is the part that keeps the project coherent across agents that share no memory.

---

## The prompt

> You are working on **Pet Care Hub**, a household-shared pet care app for Android and iOS built on Kotlin Multiplatform. The repository is at `C:\Users\MuhammadSarwar\AndroidProjects\Pet`.
>
> **Before doing anything, read these four files in order:**
>
> 1. `docs/SESSION-LOG.md` — where the project actually is right now, and what the last session left for you. **This is the source of truth for state.**
> 2. `docs/START-HERE.md` — reading order, current state, and the eight things you must not change
> 3. `CLAUDE.md` — the twelve product laws and the platform traps. These are binding.
> 4. `docs/PLAN.md` — the full plan. Read the sections relevant to your task; the reading-order table in `START-HERE.md` says which.
>
> Then check `git log --oneline -10` to see what actually happened recently, and compare it against what the session log claims. **If they disagree, trust the git history and fix the log.**
>
> Rules of engagement:
>
> - **Do not break a locked decision.** They are listed in `START-HERE.md` and recorded in `docs/adr/`. If your task appears to require breaking one, stop and say so rather than working around it.
> - **Do not re-propose rejected directions.** Rail booking, C2C marketplaces and pharma B2B were all considered and closed — pharma was explicitly declined by the user. `docs/CONTEXT.md` has the reasoning.
> - **Do not modify the Motivoa repository.** Read from it, copy out of it, leave it alone.
> - **The research is dated 15 September 2026.** Library versions, store policies and competitor figures should be re-verified, not assumed. Items marked ⚠️ in the docs are open questions, not facts.
> - **Commit straight to `main`.** No feature branches on this repo.
> - **Report honestly.** If something does not work, say so with the output. If you skipped a step, say which. Never claim a thing is done without having verified it.
> - Anything that needs the user's decision is listed under "Blocked on the user" in the session log. Do not settle those alone.
>
> **At the end of the session you must run the end-of-session protocol in `docs/AGENT-PROMPT.md`** — update the session log, update memory, commit, and write explicit next steps for whoever picks this up next.
>
> Now tell me what state you found the project in and what you propose to do this session, before you start doing it.

---

## The protocol

### Why this exists

Claude Code's memory is keyed to the **working directory**. Opening `Pet/` gives you a *fresh, empty memory* — it does not inherit anything written while working in `Motivoa/`. Other agents and other tools may have no memory at all.

So: **the repository carries the state. Memory is a convenience layer on top.** If a fact only exists in memory, treat it as lost.

### At the start of every session

1. Read `docs/SESSION-LOG.md` → "Where we are right now"
2. `git log --oneline -10` and `git status` — does reality match the log?
3. Read `CLAUDE.md` and the parts of `docs/PLAN.md` your task touches
4. **State what you found and what you plan to do, and get agreement before starting.** Do not begin multi-step work on an assumption about scope.

### During the session

- Record a new **ADR** whenever you make a decision that would be expensive to reverse — one that changes the data model, the store listing, the pricing promise, or a platform contract. Copy the shape of an existing file in `docs/adr/`: Status · Date · Context · Decision · Consequences (good **and** bad) · Revisit if · Related. Add it to `docs/adr/README.md`.
- **Update the docs in the same commit as the change.** A doc that describes something the code no longer does is worse than no doc. `CLAUDE.md`, `ARCHITECTURE.md` and `PLAN.md` §6 are the ones that go stale first.
- If you discover a doc is **wrong**, fix it and note the correction in the session log. Do not silently work around it.
- If a spike or an experiment produces a number — sync latency, reminder punctuality, a denial rate — **write the number down**. Measured facts are the most valuable thing a session produces and the easiest to lose.

### At the end of every session — required

Run all five. None is optional.

**1. Update `docs/SESSION-LOG.md`**

- Rewrite the **"Where we are right now"** block: status table, next-up list, blocked-on-user, open questions. It must be accurate as of this moment, not aspirational.
- Add a new entry at the **top of the History section** with: date, commits, what was done, decisions made, anything corrected, anything learned. Never edit an older entry — if something turned out to be wrong, say so in the new one.

**2. Write explicit next steps**

In the "Next up" block, as an ordered list. Each item must be specific enough to act on without you:

- ✅ *"Uncomment `:shared:core-model` in settings.gradle.kts, add its build.gradle.kts using the kotlin-multiplatform plugin, verify `./gradlew :shared:core-model:build` passes"*
- ❌ *"Continue with the modules"*

If you were mid-task when the session ended, say exactly where you stopped and what the next keystroke is.

**3. Update the docs you invalidated**

Check each: `START-HERE.md` (status), `PHASE-0.md` (tick completed boxes), `ARCHITECTURE.md`, `COMPLIANCE.md`, `gradle/libs.versions.toml` (if you pinned or bumped anything), `docs/adr/` (new decisions).

**4. Update memory**

Write to the memory directory for the working directory you are in. Keep it **short and pointer-shaped** — the repo holds the detail:

- A `project`-type memory saying what Pet Care Hub is, where the repo is, and that **`docs/SESSION-LOG.md` is the source of truth for state**
- A one-line pointer in `MEMORY.md`
- Update the existing memory rather than creating a duplicate
- Do **not** duplicate the plan into memory. If memory and the repo disagree, the repo wins.

**5. Commit**

Everything, to `main`, with a message that explains *why* rather than restating the diff. End with:

```
Co-Authored-By: Claude Opus 5 (1M context) <noreply@anthropic.com>
```

Then tell the user, in your final message: what you did, what you verified versus what you did not, what is now next, and anything you need a decision on.

---

## Definition of done

A task is done when **all** of these are true. "The code compiles" is not done.

1. It works, and you have **run it** — not reasoned that it should work
2. Tests exist for the logic and pass; you have pasted real output, not a summary of it
3. The docs that describe it are updated in the same commit
4. It does not break any of the eight locked decisions
5. Any new expensive-to-reverse decision has an ADR
6. The session log reflects it

If you could not verify something, **say which part and why** rather than rounding up to "done".

---

## Things that will waste a session if you forget them

| | |
|---|---|
| Gradle sync fails on a fresh clone | On purpose. Every `include()` is commented out until modules have build files. Uncomment as you go |
| No Gradle wrapper is committed | Run `gradle wrapper --gradle-version 9.2` first |
| iOS cannot be built from this machine | Windows. Needs a Mac or Codemagic. See `PHASE-0.md` |
| `java.time`, `java.util.UUID`, `java.io.File`, `Dispatchers.IO` | Compile fine on Android, break the moment iOS is added |
| `USE_EXACT_ALARM` | Never declare it. It blocks the Play upload and we do not qualify |
| `READ_MEDIA_*` in the merged manifest | KMP image libraries inject these transitively. Check the **merged** manifest, not yours |
| Library versions in `libs.versions.toml` | Provisional, dated 2026-09-15. Verify before pinning |
| Anything marked ⚠️ in the docs | An open question, not a fact |

---

## Where the source of truth lives

| Question | Answer |
|---|---|
| What state is the project in? | `docs/SESSION-LOG.md` |
| What are we building, and why? | `docs/PLAN.md`, `docs/CONTEXT.md` |
| What am I not allowed to change? | `docs/START-HERE.md`, `CLAUDE.md`, `docs/adr/` |
| How is it built? | `docs/ARCHITECTURE.md` |
| What do the stores require? | `docs/COMPLIANCE.md` |
| What do I do next? | `docs/SESSION-LOG.md` → "Next up", then `docs/PHASE-0.md` |
| Where is the evidence for a claim? | `docs/research/` |
| What does this word mean? | `docs/GLOSSARY.md` |
| What code can I reuse? | `docs/MOTIVOA-REUSE.md` |
