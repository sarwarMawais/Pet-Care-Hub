# Context — how we got here

Written so that anyone picking this up cold understands *why* the project is shaped this way, and does not waste time re-proposing ideas that have already been considered and closed.

---

## Who is building this

A solo developer. Facts that constrain every decision in the plan:

| | |
|---|---|
| Experience | Strong Android — Kotlin, Compose, Room, offline-first, on-device AI, custom Canvas drawing, six-locale i18n. Shipped a sophisticated Android app (Motivoa) |
| **iOS experience** | **None yet.** This is their first cross-platform project |
| **Machine** | **Windows 11.** Cannot build, sign or upload iOS without a Mac — this is the single biggest practical blocker and Phase 0 exists partly to solve it |
| Time | Roughly 25 focused hours a week. All roadmap durations assume this |
| Budget | Bootstrapped. No funding, no team, no marketing spend |
| Other commitments | Motivoa is near-shippable and should be finished first — see "Sequencing" below |

The plan is sized for that reality. It is not a plan for a funded team, and it should not be rewritten as one.

## How the idea was chosen

Four directions were explored in sequence. Three were rejected for specific, recorded reasons. **Do not re-propose them.**

| Direction | Why it was rejected |
|---|---|
| **Trainline-style rail booking** | Not an app problem. Selling UK rail tickets requires an RDG third-party retailer licence, RSP system accreditation, bonding, settlement obligations and working capital — 12–24 months and a funded company. It is a regulated financial business that happens to have an app |
| **Vinted-style C2C marketplace** | Technically buildable (Mangopay or Stripe Connect carry the licence), but the wall is two-sided liquidity plus unit economics. 8% of a €12 t-shirt is €0.96. Marketplaces need distribution the developer does not have |
| **Pharma B2B SaaS** | Recommended twice — best $/effort, lowest churn, and the developer works in pharma so distribution was already solved. **Explicitly declined by the user.** They do not want to build a pharma app. Closed |
| **Pet care** ✅ | Chosen. Consumer, emotional spend, lifelong retention, works in every target market, and the research found a real validated gap |

The user's stated criteria, in their words: something people **love**, **pay for easily**, and **keep using** — long-term, subscription-based, and maintainable solo.

## Why pets, specifically

- **Market:** ~95M US households own a pet (71.6%); UK 62%; Europe 49%. Global pet care ~$289B in 2026. The pet *apps* slice is ~$3.5B growing to ~$6.7B by 2035, with subscription/DTC the fastest-growing model at 17.4% CAGR.
- **Emotional spend:** people do not price-shop for their dog.
- **Retention:** a dog lives 12–15 years. Multi-pet households multiply naturally.
- **The gap is real and documented:** nobody combines shared daily logging with a portable health record. Users prove it by installing 3–5 apps in sequence trying to assemble one.
- **The incumbent is wounded:** 11pets has 500K+ installs and a **2.2★** rating after a €69/yr paywall and a botched migration. Thousands of users are publicly saying they are leaving. They publish an official data-export file, and **no competitor has written an importer.**

## What was rejected *within* the pet idea

| Rejected | Why |
|---|---|
| **"Pet health passport"** as the framing | A vaccination record gets opened twice a year. Retention comes from the daily loop, not the filing cabinet. The record accumulates as a byproduct |
| **"Did anyone feed the dog?"** as the killer hook | Corrected during research — this is *not* an unserved niche. At least eight apps do it (I Fed the Pet, Pet Feeder, PetPilot, Kibbl, DogSync, Who Fed The Dog?, Fed?, Pawfolio). They are all tiny. The gap is combining it with records, not the feature itself |
| Per-pet or per-person pricing | Every observed instance produced an uninstall or a 1★ review. See [`adr/0005`](adr/0005-unlimited-pets-free.md) |
| Chasing millions of users | No standalone pet-record app has passed 1M through consumer distribution. The plan is sized honestly at 100–300K installs |

## What is honestly uncertain

Written down so nobody mistakes optimism for evidence:

- **Distribution is the real risk**, not the code. The only proven route past 1M installs in this category is vet-clinic distribution, which we do not have at launch.
- **The thesis is a bet.** Both halves — daily logging and health records — are separately well-served. The bet is that *combining them* is what people want. The research says so; it has not been tested with a shipped product.
- **Reminder reliability on Android** may be worse in the wild than in testing if users deny exact alarms at scale. If beta shows >30% denial, the architecture flips to push-primary — a foundational change.
- **iOS quality from a first-time iOS developer** is a genuine risk: text input, VoiceOver and swipe-back are the known weak spots of Compose on iOS.
- **Market-size figures should be distrusted.** Published "pet care apps market" numbers range from $1.2B to $2.8B for 2025 depending on the vendor — a 2.3× spread. None of them drove this plan.

## Sequencing against the developer's other project

**Motivoa** (`C:\Users\MuhammadSarwar\AndroidProjects\Motivoa`) is a near-shippable Android habit and mood app by the same developer. Remaining work there: billing, the on-device AI red-team probes, and Play submission tasks — weeks, not months.

**Recommendation on record: finish and ship Motivoa before starting Phase 1 here.** Two half-shipped apps is worse than one shipped app plus a plan. Motivoa also contributes five reusable assets to this project — see [`MOTIVOA-REUSE.md`](MOTIVOA-REUSE.md).

## Decisions already locked

Recorded in [`adr/`](adr/) with full reasoning:

| # | Decision |
|---|---|
| 0001 | "Pet Care Hub" is provisional — trademark not cleared, must resolve before the first production upload |
| 0002 | Supabase (EU region) + PowerSync, not Firebase |
| 0003 | The care log is append-only |
| 0004 | Reminders are data; the OS holds only a cache |
| 0005 | Unlimited pets in the free tier, priced per household |

Plus the twelve product laws in [`../CLAUDE.md`](../CLAUDE.md), each of which answers a documented competitor failure.

## Decisions still needing the user

Do not settle these alone:

- **The final name** and therefore the package and bundle ID (trademark search pending)
- **Species scope at launch** — recommendation is dog and cat templates with "Other" fully supported from day one, since exotics keepers are vocal and under-served
- **Lifetime price** ($79.99 proposed) and whether the free-tier document cap is 20 or 50
- **Whether to engage EU legal counsel** for GDPR processor agreements and the European Accessibility Act exemption question
- **Whether to buy a Mac mini or rent cloud macOS** — the plan recommends buying

## Provenance of the research

Three reports in [`research/`](research/), all dated **15 September 2026**:

| File | What it is |
|---|---|
| `competitors-and-users.md` | Play and App Store listings scraped live; several hundred verbatim reviews; Reddit threads. Primary data, not secondhand summaries |
| `store-policies.md` | Apple App Review Guidelines and Google Play Developer Policy Center read directly. Items the author could not verify are flagged ⚠️ — **treat those as open questions, not facts** |
| `kmp-stack-and-motivoa-reuse.md` | KMP library survey with versions and maturity risk, plus a read-only audit of the Motivoa codebase |

All three carry inline citations. Where a claim in `PLAN.md` matters to a decision you are making, follow it back to the source — and check whether it has aged.
