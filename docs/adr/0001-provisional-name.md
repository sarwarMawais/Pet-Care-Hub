# ADR 0001 — "Pet Care Hub" is a provisional name

**Status:** Provisional — must be resolved before Phase 1 ends
**Date:** 2026-09-15

## Context

The working name is "Pet Care Hub" and the package is `com.petcarehub`. Neither has been cleared.

Two problems:

1. **"Pet Care" is descriptive.** Descriptive marks are weak and may be unregistrable on their own. A composite mark held by someone else could still block us.
2. **Apple 4.1 (Copycats) and 5.2.1** reject apps using misleading or copycat names, and Play's Impersonation policy is equivalent. A collision found after launch means a forced rename with the store listing, the domain, the deep links and the App Links `assetlinks.json` all pointing at the old identity.

## Decision

Ship the skeleton under `com.petcarehub`, but treat the name as unresolved:

- Do **not** print the name into store metadata, marketing copy, the paywall, or any exported PDF footer until clearance is done.
- Run a trademark search in **US (USPTO), EU (EUIPO), UK (IPO) and CA** during Phase 0.
- Keep the string in one resource key so a rename is a single edit.
- The Android `applicationId` and iOS bundle ID are the expensive parts to change — an `applicationId` cannot be changed after the first production release without losing every existing install. **Settle the name before the first Play production upload, not before the first beta.**

## Consequences

- A rename during Phase 0 or Phase 1 costs almost nothing.
- A rename after the first production release costs the user base.
- If the search comes back contested, candidates should be distinctive rather than descriptive — a coined or arbitrary word will clear more easily and is worth more later.

## Related

- `PLAN.md` §11 — open decisions
- `COMPLIANCE.md` — metadata and copycat rules
