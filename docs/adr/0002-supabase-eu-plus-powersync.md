# ADR 0002 — Supabase (EU region) + PowerSync, not Firebase

**Status:** Accepted, pending the Phase 0 Spike B result
**Date:** 2026-09-15

## Context

The app needs: auth (Apple + Google), a relational store with per-household access control, file storage for documents and photos, realtime updates between household members, and **offline-first reads and writes**. It ships to EU users and holds records about their household and their vet.

Three candidate stacks were assessed (full survey in `research/kmp-stack-and-motivoa-reuse.md`):

**Firebase.** No official Google KMP SDK. The community GitLive SDK publishes its own coverage numbers: **Firestore 23%, Analytics 16%, Cloud Messaging 5%.** For a Firestore-heavy sync app that is a hard ceiling — anything off the happy path becomes hand-written `expect`/`actual` over the native SDKs. Firestore's per-read pricing is also the wrong shape for a realtime-listener app with chatty household updates. And while Firestore has an EU multi-region, **Auth, FCM and Cloud Functions do not all offer equivalent EU residency**, which is an awkward paragraph in a DPA for a health-adjacent app.

**Supabase.** Genuinely multiplatform Kotlin client, BOM-managed, listed as the official Kotlin client in Supabase's own docs. Auth covers Google and Apple natively. Postgres RLS is exactly the right shape for household scoping. **You pick an EU region per project**, and because it is open source there is a self-host escape hatch.

**Sync layer.** Supabase Realtime alone works if we hand-roll an outbox with queueing, retry and partial sync. PowerSync provides that between local SQLite and Postgres for roughly $50/month.

## Decision

**Supabase in an EU region, with PowerSync as the sync layer, and Room/SQLite locally.**

- Auth: Supabase Auth, native Apple sign-in on iOS, Credential Manager on Android.
- Data: Postgres with **RLS filtering every table by `household_id` membership**, enforced server-side.
- Storage: private bucket under `household_id/`, signed URLs.
- Sync: PowerSync partial sync on `WHERE household_id IN (my memberships)`.

## Consequences

**Good**

- EU data residency is a single project setting, not an architecture argument. Decisive for GDPR on a health-adjacent app.
- RLS gives one place where access control is true, so a revoked sitter stops receiving data because the server says so.
- PowerSync's queue and retry is the insurance policy against writing an outbox ourselves — historically where shared-care apps fail (see DogLog's sync reviews).
- Every part is KMP-native rather than a wrapper.

**Bad**

- More moving parts than Firebase: three vendors instead of one.
- supabase-kt is community-maintained; PowerSync's Kotlin SDK is young. Both are wrapped behind `core-network` and `core-sync` so either can be replaced.
- Supabase is a US company, so CLOUD Act exposure persists despite EU hosting. Note it in the DPA; do not pretend otherwise.
- Cost at scale is driven by **photo storage and egress**, not rows. Compress client-side, cap the free tier.

## Revisit if

Spike B (two physical devices, a real round-trip, airplane mode in the middle) shows latency above ~3 seconds on Wi-Fi, or PowerSync's conflict behaviour surprises us. The fallback is Supabase Realtime plus a hand-written outbox — more work, one fewer vendor.

## Related

- `ARCHITECTURE.md` — sync section
- `PLAN.md` §7, §10 Phase 0
