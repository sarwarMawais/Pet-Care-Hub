# ADR 0003 — The care log is append-only

**Status:** Accepted
**Date:** 2026-09-15

## Context

Multiple people log care for the same pet, often at the same time, often offline. Someone feeds the dog in a basement flat with no signal while their partner marks the same feed done upstairs.

The category's best shared-care app (DogLog, 100K+ installs, 4.5★) fails exactly here, and its users describe it precisely:

> *"Doesn't sync properly across devices… I get a text from my wife asking if the last time the dog went potty was 6 hours ago and when I look at my log she says nevermind it just popped up."*

A shared-care app whose sync is eventually-consistent-on-app-restart is broken at the premise level. Any design that requires merging concurrent mutations of the same row will produce that experience.

## Decision

**Model the care log as an immutable, append-only event stream.** Conflicts are made impossible by construction rather than resolved after the fact.

```
CareEvent
  id            UUID generated on the CLIENT
  household_id  sync + RLS scope
  pet_id
  author_id     surfaced in the UI on every row
  kind          FED | WALKED | DOSE | PEE | POO | WEIGHT | NOTE | custom
  occurred_at   the real time of the event
  created_at    server-assigned
  payload       typed per kind
  supersedes    nullable — an edit writes a NEW row pointing at the old
  deleted_at    nullable — tombstone
```

Rules:

- **Never mutate a `CareEvent`.** An edit inserts a new row with `supersedes` set.
- **Never hard-delete.** Set `deleted_at`, so a late-syncing delete cannot resurrect a row.
- Client-generated UUIDs so offline inserts need no server round-trip to get an identity.
- `occurred_at` is the truth for display and ordering; `created_at` is for debugging and sync.
- **Last-writer-wins applies only to mutable, low-contention objects** — `Pet`, `ScheduleRule`, `Household` — which carry `updated_at` and are rarely edited concurrently.

## Consequences

**Good**

- Two phones logging offline **cannot conflict**. The merge is a union of inserts.
- History is preserved, which a health record wants anyway — "who changed this weight, and when" is a feature, not a side effect.
- Attribution on every row comes free, and it is the thing users actually asked for: *"we can see the entries from each other so we don't double feed or doubt whether or not he's had care."*
- Reminder cancellation across the household becomes a query over events, not a distributed mutation.

**Bad**

- The table grows monotonically. A pet logged 8×/day for 15 years is ~44,000 rows — trivial for SQLite, but pagination in the UI is mandatory from day one, not an optimisation.
- "Current state" is always a fold over events. Materialise views (latest weight, last fed) rather than recomputing in the UI.
- Edits are more expensive to write and slightly more complex to render (resolve the supersedes chain).
- Storage cleanup for a deleted household must sweep tombstones deliberately — account deletion cannot just `DELETE WHERE`.

## Related

- `ARCHITECTURE.md` — data model
- `CLAUDE.md` — architecture rules
- `research/competitors-and-users.md` — the DogLog sync reviews
