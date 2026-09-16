# Architecture decision records

One file per decision that would be expensive to reverse. Each records the context, the decision, and what it costs us — including the bad consequences, which are the point.

| # | Decision | Status |
|---|---|---|
| [0001](0001-provisional-name.md) | "Pet Care Hub" is a provisional name | **Provisional** — resolve before the first production upload |
| [0002](0002-supabase-eu-plus-powersync.md) | Supabase (EU region) + PowerSync, not Firebase | Accepted, pending Spike B |
| [0003](0003-append-only-care-log.md) | The care log is append-only | Accepted |
| [0004](0004-reminders-are-data.md) | Reminders are data; the OS holds only a cache | Accepted, pending Spike C |
| [0005](0005-unlimited-pets-free.md) | Unlimited pets in the free tier, priced per household | Accepted |

## Writing a new one

Copy the shape of an existing file: **Status · Date · Context · Decision · Consequences (good and bad) · Revisit if · Related.**

Record a decision here when reversing it would mean changing the data model, the store listing, the pricing promise, or the platform contract. Do not record routine library choices — those live in `gradle/libs.versions.toml` with a comment.
