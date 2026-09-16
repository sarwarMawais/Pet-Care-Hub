# Pet Care Hub — Product Plan v1

**One record for the whole household, on both phones.**

A shared daily care log (who fed, walked, medicated — and when) fused with a portable lifetime health record, unlimited pets on one price, and exports shaped for the moment someone demands a document: a new vet, a boarding kennel, an insurer, a border.

| | |
|---|---|
| **Status** | Plan only — implementation not started |
| **Plan date** | 15 September 2026 |
| **Research date** | 15 September 2026 (all store figures are point-in-time) |
| **Markets** | US · CA · UK · EU |
| **Stack** | Kotlin Multiplatform + Compose Multiplatform |
| **Stores** | Google Play + Apple App Store |
| **Published page** | https://claude.ai/artifact/VaMa9VqJyBNvxiUST5zWT8 |

---

## Table of contents

1. [The honest verdict](#1-the-honest-verdict)
2. [What owners actually want](#2-what-owners-actually-want)
3. [Competitors](#3-competitors)
4. [Product laws](#4-product-laws)
5. [Pricing and packaging](#5-pricing-and-packaging)
6. [Screen by screen](#6-screen-by-screen)
7. [Architecture and stack](#7-architecture-and-stack)
8. [What Motivoa gives us](#8-what-motivoa-gives-us)
9. [Store compliance](#9-store-compliance)
10. [Roadmap](#10-roadmap)
11. [Risks and open decisions](#11-risks-and-open-decisions)
12. [Sources](#12-sources)

---

## 1. The honest verdict

### Will this get millions of users?

**Not on its own.** No standalone pet-record app has ever passed one million installs through consumer distribution. The only one over 1M — PetDesk — is sold to veterinary clinics, who invite their clients. Everything that relied on the app store alone stalled between 100K and 500K.

| Figure | What it is |
|---|---|
| **2.2★** | 11pets — the incumbent, on 500K+ installs. Its own users are publicly leaving over a €69/yr paywall and a botched migration. |
| **1M+** | PetDesk — the only record app past a million. Distributed by vets, and owners cannot edit their own data. |
| **~$1.8M** | Dogo's estimated ARR at 5M downloads. The realistic ceiling for a consumer pet app — and Dogo sells training, a stronger emotional hook than record-keeping. |
| **3–5** | Apps the typical owner installs in sequence looking for one that does logging + records + sharing + documents. None does. |

### Will people pay?

**Yes** — and the reviews say exactly for what:

- Unlimited pets
- A medical crisis (cancer, seizures, diabetes, post-surgery)
- Removing ads
- Exports and documents that actually open
- Graphs on custom metrics
- Supporting a visible developer who replies — two users offered to donate unprompted
- A one-time lifetime option, requested across five different apps' reviews and refused by all of them

**They refuse** when the price exceeds their daily-app benchmark (*"$5 is a small fee. $50+ is ridiculous"*), when basic multi-pet is the paywall, or when a static utility demands *"yet another subscription."*

### What this plan is actually betting on

The gap is real and validated: **nobody combines shared daily logging with a real health record**, and users prove it by serially installing apps to assemble one. The incumbent has 500K installs, a 2.2★ rating, a documented data-export file, and **no competitor offers an import path**. The best-loved apps are single developers with 100K installs, broken sync, and no widgets.

**Realistic target: 100K–300K installs and 3,000–6,000 paying households in 24 months** (~$90K/yr net), growing through:

- ASO on high-intent queries ("pet care tracker", "shared dog feeding tracker", "pet vaccination records")
- An "Import from 11pets" bridge — currently unclaimed
- The household-invite loop: every user recruits their partner, their kids, their sitter
- Later, a vet and sitter channel

That is a durable indie business, not a unicorn. Plan for it, and let anything more be upside.

### The three things that must be boringly correct

None of the rest matters if these fail, because they are the three things every competitor gets wrong:

1. **Sync between household devices**
2. **Medication reminders that fire on time**
3. **Documents you can open at the vet counter with no signal**

---

## 2. What owners actually want

Evidence base: several hundred verbatim Play and App Store reviews scraped on 15 September 2026, plus Reddit recommendation threads. The loudest signal in the whole dataset is people being told to **build a spreadsheet** — the top-voted advice in r/LifeProTips for tracking pet vaccinations. That is a product opportunity stated as a workaround.

> *"I'm looking for something that can be transferable and used independently of a specific vet office."*
> — r/Pets. The PetDesk critique, stated as a requirement, by someone who has never used PetDesk.

### Jobs to be done, ranked by evidence

| # | Job | Strength | Evidence |
|---|---|---|---|
| 1 | **Don't let me screw up the medication or vaccine schedule** | Very strong | Cancer/seizure/diabetes owners; "keeps us from making medication mistakes"; "don't rely on your vet to remind you" |
| 2 | **Let my household see what's done so we don't double-feed** | Very strong | "Six people plus two dogs in our pack"; whiteboards and spiral notebooks being replaced |
| 3 | **One portable record not hostage to my vet or my app vendor** | Very strong | 11pets lockouts after 7+ years; PetDesk owners cannot edit their own data |
| 4 | **Produce the document someone is demanding, right now** | Very strong | "During an emergency I could not retrieve the records, just when I needed them most"; boarding kennels; borders |
| 5 | **Store the actual PDFs and photos — and let me VIEW them** | Strong | PetNoter's #1 praise; PetnotePlus lets you upload but not open; DogNote's top request |
| 6 | **Handle all my animals without charging per head** | Strong | Fosters at 15-pet caps; 30 reptiles; rats; horses; livestock |
| 7 | **Brief my pet sitter without writing a novel** | Strong | Daycare staff, family sitters, multi-household switching |
| 8 | **Show me trends so I catch problems early** | Strong | Weight, seizures, glucose — a diabetic-dog owner said he'd pay for graphs |
| 9 | Survive the puppy phase | Moderate | Potty prediction; "big buttons are nice when you're half asleep" |
| 10 | Keep and enjoy the memories | Moderate | Monthly albums; gotcha day; archiving a pet that has died |
| 11 | Track what this animal costs me | Moderate | Expense trackers praised as a delighted surprise |
| 12 | Get me through pet-travel paperwork | Moderate (high value, low awareness) | UK→EU AHC costs £150–250 **per trip** with hard 10-day and 21-day windows |

### What converts, and what kills

**They pay for:**

- A specific medical crisis — cancer, seizures, diabetes, surgery recovery
- Genuinely unlimited pets
- Removing ads
- Exports and a document vault that works
- Graphs on custom metrics
- Supporting a responsive indie developer
- A one-time lifetime option

**They uninstall over:**

- **Any pet-count paywall** — 1, 3, 5 or 15 pets. Every observed instance produced an uninstall or a 1★ review
- Price above the daily-app benchmark: *"$5 is a small fee, $50+ is ridiculous"*
- Retracting free features or breaking a "lifetime" promise
- Sync that needs an app restart; reminders 10–60 minutes late
- Documents that upload but won't open; save buttons hidden behind the keyboard
- A dead pet consuming a paid slot
- Support email that bounces

### Price points users named out loud

| Signal | Source |
|---|---|
| "$5 is a small fee. $50+ is ridiculous." | 11pets |
| "I'd be willing to pay a one-time fee ($5-$10) for an ad free version" | dogcat.app |
| "€69 a year… more than I pay for subscriptions on apps I use daily" | 11pets |
| "$70 CAD ANNUALLY… I'd rather use an excel spreadsheet FOR FREE" | 11pets |
| "$40 a year… I can't spend that much a year" | DogLog |
| "the premium price is very inexpensive" | PetnotePlus |
| "if you want the premium it's very reasonably priced" ($2.99/mo) | PetNoter |

**Reaction to per-pet pricing is uniformly hostile.** Every single instance found produced a downgrade or an uninstall. The counter-example proves it: a dogcat.app user assumed multi-pet was paywalled and complained, and the developer replied *"You can add unlimited pets for free, that is not part of the subscription :)"*. That app sits at 4.3★ across 6,390 reviews.

### Features people love anywhere

- **Monthly photo albums / growth timelines** — the single most emotionally cited feature: *"a picture from every month since I adopted my cat."*
- **Likes and comments on logged events** — turns a chore log into a family feed (DogLog).
- **Gotcha day** alongside birthday.
- **Supply inventory that warns before you run out** of food or a monthly preventative.
- **Widgets, Watch and Siri for one-tap logging** — the Android widget is the most-requested missing feature in the category's best shared-care app.
- **Multi-household switching for sitters** — the one good idea in an otherwise zero-traction app.
- **Barcode food scanning checked against logged allergies** — genuinely novel (Petio).

---

## 3. Competitors

Store figures scraped live on 15 September 2026. The market splits cleanly into two camps — daily coordination with no records, and records with no daily loop — plus a vet-owned CRM. Nobody bridges them.

| App | Camp | Play | iOS | Pricing | Why they lose |
|---|---|---|---|---|---|
| **11pets** | Records | 2.2★ · 5.7K · 500K+ | 3.3★ · 77 | €69/yr; gates reminders *and* multi-pet | Price shock on a "lifetime" base, data loss on migration, lockouts, "databasey" IA, ignored support |
| **PetDesk** | Vet CRM | 4.8★ · 31.7K · 1M+ | — | Free to owners; clinic pays | Owners can't edit anything; reminders won't clear; dies when you change vets |
| **Pet Care Tracker** (dogcat.app) | Both, thin | 4.3★ · 6.4K · 100K+ | — | Ads; unlimited pets free | Solo dev, dated UI, no time-of-day recurrence, no bulk actions. **Wins ASO** for all three key queries |
| **DogLog** | Daily log | 4.5★ · 1.3K · 100K+ | 4.8★ · 1.3K | $3.99/mo · $39.99/yr | Sync needs an app restart; notifications 10–60 min late; 3-dog pack cap; dogs only |
| **PetnotePlus** | Records | 4.5★ · 1.6K · 50K+ | 4.6★ · 15 | 5 pets free | Documents upload but can't be viewed; blocky UI; a reported total data-loss incident |
| **PetNoter** | Records | 4.3★ · 148 · 10K+ | 4.2★ · 17 | $14.99/yr · $59.99 life; 3 pets free | Document upload and export both paywalled; support email bounced |
| **DogNote** | Daily log | 4.3★ · 256 · 10K+ | 4.7★ · 904 | ~$4.99/mo; 1 pet free | Android is a second-class citizen; no document vault; nag-to-rate |
| **GreatPetCare** (ex-Pawprint) | Records | 2.3★ · 342 · 10K+ | — | Premium + $10 per record request | Charges for what your vet gives free; broken uploads; save buttons off-screen |
| **Notepet** | Meds | 2.6★ · 43 · 1K+ | — | Subscription | Alerts die past one medication; requires network |
| Who Fed The Dog?, I Fed the Pet, Kibbl, PetPilot, Pet Feeder | Feeding only | ≤1K each | — | Mixed | Single-purpose toys; PetPilot/Fed? are iCloud-only (no Android); Kibbl on v1.0 since 2022 |

### The 11pets story, in their users' words

This is the most important competitive fact in the plan. 500K+ installs and a 2.2★ rating.

> *"I have 3 years worth of data logged for my dog, and now they're forcing me to pay $70 CAD ANNUALLY… I'd rather use an excel spreadsheet FOR FREE."*

> *"Used to love this app for YEARS. But advertising free forever and then not allowing people who've had it from beginning of it for free and forcing payment isn't right… locking me out of literally years worth of info isn't cool. $5 is a small fee. $50+ is ridiculous."*

> *"They won't let me receive a password reset, won't let me log into my account where my three dogs information has been stored for 7+ years!"*

> *"DO NOT PUT WEIGHT INTO APP! Any weight entered is converted into the wrong weight on 'shareable' petsitter report… I emailed customer service multiple times with no response."*

The company's own public developer reply: *"we needed to update the app as soon as possible to help animal shelters find homes with some new features. We apologize for making the experience worse for your own pet."*

It ships frequently — this is **not** an abandoned app. It is an actively-maintained app that destroyed its own goodwill, which is far more exploitable: 500K installs of people who have proven they will keep records daily for seven years, and who are currently, loudly, looking for an exit.

### What nobody does well

1. **Daily log + real health record in one app.** The thesis, validated by users installing 3–5 apps in sequence.
2. **Reliable multi-device sync for shared care.** The best shared-care app's users describe the failure in painful detail: *"I get a text from my wife asking if the last time the dog went potty was 6 hours ago."*
3. **Documents you can open.** PetDesk can't save them, DogNote has none, PetnotePlus can't display them, GreatPetCare's save button is off-screen.
4. **Purpose-shaped exports.** Everyone ships "a PDF." Nobody ships a boarding vaccination certificate, an insurance claim pack, a new-vet handover or a sitter brief as distinct artifacts.
5. **Pet-travel paperwork** despite AHCs costing £150–250 per trip with computable date windows.
6. **Bereavement.** Archiving a deceased pet either isn't possible or consumes a paid slot.
7. **Correct reminders.** Late, dead past one med, won't clear, monthly firing daily, recurring tasks deleting their own future — across six apps.
8. **Quiet hours.** "Every 3 hours" wakes people at 3am.
9. **Bulk operations.** No "mark done for all pets", no shared medication across pets.
10. **Android/iOS parity**, and **an 11pets import bridge** — unclaimed.

### What the giants can and cannot do

- **Chewy** (10M+ installs) owns purchase and prescription fulfilment and will never be a neutral, exportable record.
- **Rover**'s care log exists only inside a paid booking — no help for your mum or your neighbour.
- **Tractive** has passive GPS, activity and heart-rate data no phone app can fake — and cannot hold a PDF or coordinate who gave the 8pm pill.
- **Petco / PetSmart** only work inside their own stores, and both sit at 3.8★.

The giants own *transactions* (buying, booking, locating). None owns the **portable, owner-controlled, multi-carer lifetime record**, because that asset has no captive revenue stream attached to it. That is precisely the gap.

### Growth reality

| Tier | Apps |
|---|---|
| 10M+ | Chewy (retail) |
| 5M+ | PetSmart (retail), Dogo (training) |
| 1M+ | **PetDesk (vet-distributed)**, Tractive (hardware), Petco (retail) |
| 500K+ | 11pets |
| 100K+ | DogLog, dogcat.app, VitusVet, Pawp |
| 50K+ | PetnotePlus |
| 10K+ | PetNoter, DogNote, GreatPetCare, Pet Parents |
| ≤1K | Notepet, Who Fed The Dog?, Vet Record, I Fed the Pet, Kibbl |

**What actually drove growth for the ones that grew:**

1. **Vet-channel distribution** (PetDesk, VitusVet) — the only proven route past 1M.
2. **ASO on high-intent long-tail queries** (dogcat.app) — one unpaid solo developer reached 100K+ installs and now ranks #1 for all three key queries. The most replicable playbook for a new entrant.
3. **Community launch** — dogcat.app launched publicly on Reddit asking *"what is missing?"*.
4. **Ferocious developer responsiveness as a growth loop** — dogcat.app fixed a bug within hours of a review and the user rewrote it to 5★. In a category where 11pets ignores support email and PetNoter's address bounced, **replying is a differentiator.**

**Treat top-down market numbers with suspicion.** Published "pet care apps market" figures range from $1.2B to $2.8B for 2025 depending on the vendor — a 2.3× spread means none of them should drive a plan.

---

## 4. Product laws

Each of these is a direct answer to a documented failure in the category. They are not preferences; break one and the reviews already tell you what happens.

| # | Law | Why |
|---|---|---|
| 01 | **Unlimited pets in the free tier. Forever.** | Six apps were punished for gating this. It is the sharpest wedge against the entire field and the one promise we never retract. |
| 02 | **Archiving a pet that has died is free, dignified, and never consumes a slot.** | No paywall, no upsell, no promotional notification for a memorialised pet. A paywall shown to a grieving owner ends up on social media. |
| 03 | **Sync is near-real-time and attributed.** | Every log line shows who and when. If your partner logs a feed, it appears on your phone before you can ask. Eventually-consistent-on-restart is broken at the premise. |
| 04 | **Reminders are data, not alarms.** | The schedule lives in the shared database; the OS holds only a rolling window of the next 7–14 days. Exact clock times, quiet hours, repeat-until-done, snooze/skip/gave-early, and an honest fallback when Android denies exact alarms. |
| 05 | **Documents open offline, in-app, at the vet counter.** | Stored locally after first sync, previewed inline, never "sent to your phone to view." |
| 06 | **Exports are shaped for the job.** | Boarding certificate, new-vet handover, insurance claim pack, sitter brief, travel readiness — distinct, named artifacts, not one generic PDF. |
| 07 | **Every dropdown has a free-text fallback.** | Rescue mixes exist; so do arthropods and horses. Species-scoped tasks never leak across animals. |
| 08 | **Offline-first for reads and writes.** | An emergency vet visit at 2am on a rural road is the moment the record must open. Notepet lost users to a network blip. |
| 09 | **Never retract a free feature. Never break a lifetime promise.** | The 11pets extinction event, in one line. |
| 10 | **Reply to every review and support email within 24 hours.** | Measurably a growth channel here — a fix within hours converted 1★ to 5★. It is also an Apple 1.2 obligation. |
| 11 | **Never appeal to children in art or copy.** | Cute is fine; a cartoon mascot with big eyes in a bubbly font drags us into Play's Families policy and COPPA. Adult audience, 16+ terms. |
| 12 | **Say "record," never "diagnose."** | No dosage calculators, no "detects," no "vet-approved." Every AI or trend output carries "consult your vet." Health copy is a compliance surface. |

---

## 5. Pricing and packaging

Anchored on what users named out loud: *$5 is small, $50+ is ridiculous*; PetNoter's $14.99/yr is called "very reasonably priced"; DogLog's $39.99/yr draws "I can't spend that much." The category's most under-served demand is a one-time purchase. We offer it — it converts the subscription-fatigued, and Apple treats a non-consumable lifetime unlock as ordinary IAP.

| Tier | Price | Includes | Why this line |
|---|---|---|---|
| **Free** | $0 | Unlimited pets · daily log · household of up to 3 people · full health record · reminders · 20 documents · 1 export per month · widgets · archive | Everything the incumbent gates. Generous enough to earn 4.5★ and reviews; the free tier *is* the marketing budget. |
| **Household** (annual) | **$29.99/yr**<br>€29.99 · £24.99 | Unlimited household members & time-boxed sitter access · unlimited documents · all shaped exports · claim packs · travel readiness · monthly albums · custom typed fields & graphs · CSV | Under the $39.99 pain line, above PetNoter. Priced per household, never per pet or per person. 7-day trial. |
| Household (monthly) | $3.99/mo | Same | Exists for the crisis buyer (post-surgery, new puppy). Annual shown first and largest. |
| **Lifetime** | $79.99 one-time | Same, forever, for this household | The demand nobody serves. ~2.7 years of annual; priced so annual remains the default choice. |

### Unit economics at the realistic target

4,000 paying households × ~$27 blended ≈ **$108K/yr gross**; at the 15% small-business tier on both stores ≈ **$92K net**, against ~$1.5K/yr of Supabase + PowerSync + CI. Photos are the only cost that scales; compress client-side and cap free-tier storage. **Break-even on infrastructure at roughly 60 subscribers.**

### Paywall rules (both stores, non-negotiable)

- Billed amount is the most prominent price — the annual total must be visually larger than the "/month" equivalent
- Explicit auto-renew sentence
- Trial end shown as an exact date ("Free until 29 September 2026, then $29.99/year")
- Restore Purchases button
- Terms of Use and Privacy Policy links on the sheet itself
- Visible close control
- CTA states the obligation to pay: "Subscribe — $29.99/year"
- Cancellation reachable from Settings in two taps, deep-linked to the store's subscription centre
- No guilt screens, no fake X, no pre-ticked boxes, no benefit reduction after the first period
- Enable Apple Family Sharing (irreversible, and a natural fit for a household product)
- **Do not bother with EU alternative billing at launch** — the fee stack nets to near zero at this scale

---

## 6. Screen by screen

Every screen is specified across the same seven facets so nothing is forgotten on either platform.

- **Both** = one Compose Multiplatform implementation
- **Android** / **iOS** = where a platform diverges
- **v1** ships at launch; **v2** follows once sync and reminders are proven in the field

### 6.1 First run and onboarding — Both · v1

Get a pet on screen in under 60 seconds and make the household invite feel like the point, not a setting. No account required to start.

| Facet | Detail |
|---|---|
| **UI** | Three steps, one screen each: **Add your pet** (name, species, photo — everything else optional), **Who else looks after them?** (share-sheet invite link, skippable), **Done — here's Today**. Large photo well at top, one primary button, progress as three dots. Species picker shows dog, cat, then "Other" with free text. |
| **UX** | Local-only until the user chooses to sync or invite. Never ask for notifications, camera or an account here. "Import from 11pets / a spreadsheet" is a quiet link on step one. The disclaimer ("record-keeping tool, not a medical device — consult your vet") appears as one calm line on the final step, acknowledged by continuing. |
| **Data** | Creates a `Pet` plus a local `Household` with the device user as owner. Persists to Room immediately; sync begins only after sign-in. |
| **Android** | Photo via `PickVisualMedia` (Photo Picker — no permission) or `ACTION_IMAGE_CAPTURE`. Predictive back enabled between steps. |
| **iOS** | `PHPickerViewController` (no permission, no purpose string). Interactive swipe-back wired between steps — prototype in week 1. |
| **Play** | Prominent-disclosure screen is required *before* the first cloud upload, not here. Adult target audience; no child-directed art. |
| **Apple** | 5.1.1(v): the app must be usable without login. 2.1: the reviewer must be able to reach everything — pre-seeded demo household in the review notes. |

### 6.2 Sign in and account — Both · v1

Sync and household sharing need an identity. Offered when the user first invites someone or turns on sync — never as a wall.

| Facet | Detail |
|---|---|
| **UI** | Sheet with three equal buttons: **Continue with Apple**, **Continue with Google**, **Continue with email** (magic link with a 6-digit code fallback). One line: "Your local data stays; signing in just adds sync." |
| **UX** | Local pets merge into the new account, never overwritten. The email path avoids passwords entirely. If a user opens an invite link before signing in, we hold the invite and complete it after. |
| **Data** | Supabase Auth. User row plus membership row. Sitters invited by link become full data subjects — serve them the privacy notice at join. |
| **Android** | Credential Manager for Google; Apple sign-in via web OAuth (works, slightly clunkier). |
| **iOS** | Native `ASAuthorizationController` (a SwiftUI screen, not Compose). Store the Apple refresh token for later revocation. |
| **Play** | Account creation triggers the deletion requirement: in-app delete plus a public web URL declared in Data safety. |
| **Apple** | 4.8: because we offer Google, **Sign in with Apple is mandatory**. 5.1.1(v): deletion in-app, with token revocation via Apple's REST API. |

### 6.3 Today — the household feed — Both · v1

The daily-loop screen. Answers "what's done, what's due, who did it" in one glance for every pet, and offers one-tap logging.

| Facet | Detail |
|---|---|
| **UI** | Top: pet avatar row (scrollable, tap to filter, long-press to reorder; drawn character avatars from the Motivoa rig). Then **Due now** cards (meds, feeds, treatments) with a big "Done" and a smaller "Skip / Later". Then the timeline: attributed entries — "Sara · fed Luna · 8:02". Persistent quick-log FAB. Sleepy-thumb targets ≥ 48dp everywhere. |
| **UX** | Overdue items rise and turn amber (with an icon — never colour alone). "Mark done for all pets" appears when a task is shared across animals. Pull-to-refresh is unnecessary: the feed is live. The day-one empty state shows a pre-filled sample entry, labelled as an example. |
| **Data** | Reads `CareEvent` (immutable, UUID, author, occurredAt) plus materialised `DueItem`s from schedule rules. Realtime subscription scoped by `household_id`. |
| **Android** | Glance widget mirrors the "due now" list. Notification channel "Household activity" for partner-logged events (default: silent). |
| **iOS** | WidgetKit small/medium widgets; a Live Activity for an active dog walk is v2 and strictly functional (Apple 4.5.3). |
| **Play** | Content on this feed is UGC in a closed group: every entry needs a "Report" in its overflow menu. |
| **Apple** | 1.2 applies; report and block reachable from here. 2.5.16: widget content must match this screen. |

### 6.4 Quick Log sheet — Both · v1

Two taps from anywhere to "fed", "walked", "gave meds", "peed", "pooped", "weighed" — with optional detail, never required detail.

| Facet | Detail |
|---|---|
| **UI** | Bottom sheet: pet selector (pre-selected if filtered), grid of large icon tiles, time chip defaulting to now (tap to backdate), optional note/photo/amount row collapsed. Custom event types appear after the defaults; the user can pin and rename them. |
| **UX** | Tap a tile → logged → sheet closes → toast "Fed Luna · 8:02 · Undo". Retroactive entry, edit and delete via swipe on the feed. Amount fields remember the last value per pet. Species-scoped: a nail-trim tile never appears on the aquarium. |
| **Data** | Appends a `CareEvent`; conflict-free by construction. Edits write a new version row; deletes are tombstones. Photos compressed to ≤ 1600px before upload. |
| **Android** | Also launchable from the widget and from a notification action ("Done") without opening the app. |
| **iOS** | App Intents so Siri and interactive widgets can "Log that I fed Luna". Haptic on confirm. |
| **Play** | Camera via intent needs no permission; if an in-app camera is built later, `CAMERA` becomes a runtime permission with rationale. |
| **Apple** | Specific `NSCameraUsageDescription` string; generic strings are an automatic rejection. |

### 6.5 Pet profile — Both · v1

The pet's home page: identity, essentials someone else might need in a hurry, and the doors into record, documents, journal and exports.

| Facet | Detail |
|---|---|
| **UI** | Hero photo or drawn character (user's choice), name, species/breed (free text allowed), age computed from birthday *and* "gotcha day". Essentials card: microchip, weight (latest plus trend arrow), allergies, insurer and policy number, vet contact. Tabs: **Record · Meds · Documents · Journal**. Overflow: Export, Share with sitter, Archive. |
| **UX** | Every field optional; every field editable in place. Multiple photos per pet. Weight unit per pet (kg / lb / g). An "Emergency card" action shows a full-screen high-contrast summary for handing to a stranger or an ER vet. |
| **Data** | `Pet` with typed optional fields; `Weight` series; `Contact` rows for vets and insurers. |
| **Android** | Shortcut ("Open Luna") via `ShortcutManager` for multi-pet homes. |
| **iOS** | Spotlight indexing of pets; Dynamic Type must scale the essentials card to 200% without clipping. |
| **Play** | Breed reference art must be licensed or original — no scraped images; keep licence PDFs on file. |
| **Apple** | 5.2.1 the same; 2.3.8 — screenshots of this screen must be 4+-appropriate (no wound photos). |

### 6.6 Health record — Both · v1

The portable lifetime record: vaccinations with expiry, conditions, vet visits, labs, weight chart — owner-controlled, never hostage to a clinic.

| Facet | Detail |
|---|---|
| **UI** | Sections in a single scroll: **Vaccinations** (name, given, due, batch; grouped names like "Lepto4" stay grouped; overdue badged), **Visits** (date, clinic, reason, notes, attached invoice), **Conditions** (active/resolved), **Labs** (value + unit + reference), **Weight** chart with tappable points. Add via a single "+" that asks what kind. |
| **UX** | Vaccination templates by species pre-fill names and typical intervals but never assert a schedule — copy says "typical interval; your vet decides." Edit without delete-and-recreate. Each visit can spawn a claim-pack export directly. |
| **Data** | `Vaccination`, `Visit`, `Condition`, `LabResult`, `Weight` — all with `pet_id`, author, source (manual / import). Vaccination protocol text is written by us, not copied from kennel clubs or manufacturers. |
| **Android** | Weight chart in Compose Canvas; tabular-nums for values. |
| **iOS** | The same Canvas chart; verify VoiceOver reads chart points via explicit semantics. |
| **Play** | Health apps declaration: answer "no health features" (the form is human-oriented) *and* ship the not-a-medical-device disclaimer as a hedge. Do not touch Health Connect. |
| **Apple** | Nutrition Labels: declare as User Content, not Health (documented reasoning). Age-rating questionnaire: answer the medical/wellness question honestly → expect 13+. |

### 6.7 Medications and schedules — Both · v1

Job #1. Courses that pause, taper and cycle; exact clock times; quiet hours; "gave it early" tolerance; visible to the whole household.

| Facet | Detail |
|---|---|
| **UI** | Medication card: name, dose **as text** (never calculated), times-of-day chips, days pattern (daily / every N days / specific weekdays / monthly on date), start–end or ongoing, "Pause" toggle. Parasite preventatives are the same object with a monthly pattern and a supply counter ("3 doses left"). An adherence strip shows the last 14 days as dots. |
| **UX** | A due dose shows on Today for everyone; when one person marks it done, the others' reminder cancels within seconds. "Given early/late" keeps the true time. Snooze offers 15/30/60 min. Quiet hours default 22:00–07:00 per household, with an explicit "this one is critical — ring anyway" flag per medication. |
| **Data** | `ScheduleRule` rows (pet, item, times, pattern, window). Occurrences are *derived*, never stored as alarms. A `DoseEvent` is a `CareEvent` subtype. |
| **Android** | `SCHEDULE_EXACT_ALARM` only (never `USE_EXACT_ALARM` — the upload is blocked). Check `canScheduleExactAlarms()` before every schedule; WorkManager re-materialises the 7–14 day window; inexact fallback with an honest in-app banner if denied. Never request a battery-optimisation exemption. |
| **iOS** | Hard cap of 64 pending local notifications: use `UNCalendarNotificationTrigger` for regular series, a rolling window for the rest, re-materialise on foreground and BGAppRefresh. Long-horizon items (annual boosters) via server push as a backstop. |
| **Play** | Reminder-reliability disclaimer on this screen. No dosage calculator of any kind. Separate notification channel "Medication". |
| **Apple** | 1.4.2 forbids dose calculators for anyone but manufacturers and hospitals — dose is a free-text field. 5.1.2(i): never require notifications to be enabled to use the app. |

### 6.8 Reliable reminders — permissions and health check — Both · v1

The one screen that explains, asks and proves. Shown the first time a schedule is created; reachable from Settings forever.

| Facet | Detail |
|---|---|
| **UI** | A short explanation in the pet's name ("So we can remind you about Luna's 8pm tablet"), a single primary button per permission, and a live **status card**: notifications on/off, exact alarms granted/denied, battery restrictions detected, and the next 3 scheduled reminders listed with times. A "Send test reminder in 10 seconds" button. |
| **UX** | Contextual ask, never on cold launch. Pre-prompt before the system dialog. If denied twice, the screen shows a deep link to system settings and what will degrade ("reminders may arrive up to 15 minutes late"). The self-check runs on every foreground and surfaces a dismissible banner if the OS has silently revoked something. |
| **Data** | Stores permission-state snapshots so support can see what a user's phone actually allowed. No personal data. |
| **Android** | `POST_NOTIFICATIONS` runtime ask; `ACTION_REQUEST_SCHEDULE_EXACT_ALARM`; listen for `ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED`. Port Motivoa's `ReminderHealthProbe` pattern. |
| **iOS** | `requestAuthorization` once, after the pre-prompt; consider `.provisional` for low-stakes household-activity nudges; show the 64-slot budget only in a debug menu. |
| **Play** | Restricted-permission declarations must match the manifest; `USE_EXACT_ALARM` is limited to alarm-clock and calendar apps and we do not qualify — submit a pre-launch policy question to confirm. |
| **Apple** | No `UIBackgroundModes` we do not genuinely use. 5.1.2(i): notifications are optional to use the app. |

### 6.9 Documents vault and viewer — Both · v1

Vet invoices, prescriptions, lab PDFs, insurance policy, microchip certificate — stored, previewed, openable offline, and linkable to visits. **The most consistently botched high-value feature in the category.**

| Facet | Detail |
|---|---|
| **UI** | Grid of document cards with thumbnail, title, date, tags (Invoice · Prescription · Lab · Insurance · Vaccination · Other), linked visit. Full-screen viewer with pinch-zoom, page indicator, share and "Add to export". Capture flow: camera with edge-crop, or pick a PDF/image. |
| **UX** | The save button is always above the keyboard and never behind system bars (a GreatPetCare reviewer diagnosed their CSS — we do not get that review). Documents download to the device on first sync and stay cached. Title auto-suggested from date plus tag. Multi-select to attach several to one claim. |
| **Data** | `Document` row (pet, tag, visit_id, storage path, size, sha256) plus an object in Supabase Storage under `household_id/`, private bucket with signed URLs. Free tier 20 docs; paid unlimited. Client-side compression for images; PDFs stored as-is (≤ 20 MB). |
| **Android** | `ACTION_OPEN_DOCUMENT` (SAF) for PDFs — no storage permission. In-app PDF rendering via `PdfRenderer`. Strip `READ_MEDIA_*` from the merged manifest. |
| **iOS** | Native `QLPreviewController` hosted inside Compose for the viewer — better than re-implementing. `PHPickerViewController` for images, `UIDocumentPickerViewController` for PDFs. |
| **Play** | Prominent disclosure plus affirmative consent before the first document leaves the device. Data safety: "Files and docs", encrypted in transit. |
| **Apple** | Privacy manifest reason codes for file-timestamp and disk-space APIs. Nutrition Label: User Content → Photos/Videos and Other User Content. |

### 6.10 Household and members — Both · v1

Who can see and log for these pets. Invite by link, roles, removal — and the Apple 4.3(b) answer: genuinely uncommon multi-user real-time sharing.

| Facet | Detail |
|---|---|
| **UI** | Member list with avatar, role chip (**Owner · Member · Sitter**), last active. "Invite" opens the system share sheet with a link and a short message. Pending invites listed with expiry and a revoke button. Per-member overflow: change role, remove, report. |
| **UX** | The invitee taps the link → the app opens (or the store, then the app) → sees "Sara invited you to look after Luna and Milo" → accepts terms → joins. No contacts access, ever. An owner leaving must transfer ownership or dissolve the household — the flow is explicit, since account deletion depends on it. |
| **Data** | `Membership` (user, household, role, expires_at). Invite tokens: single-use, 7-day expiry, revocable, validated server-side. Postgres RLS: every table filtered by `household_id` membership. |
| **Android** | Verified **App Links** via `assetlinks.json`. Never `READ_CONTACTS` (the Contact Picker policy lands Jan 2027 anyway). |
| **iOS** | **Universal Links** via `apple-app-site-association`; no custom URL schemes (hijackable). |
| **Play** | UGC in a closed group: in-app report for content and users is explicitly required. Terms accepted before first upload; consent logged with version and timestamp. |
| **Apple** | 1.2 has no private-group exemption: report, block, filtering and published contact info are all required. The invite model is identified and consented — not "anonymous chat". |

### 6.11 Sitter mode and the sitter brief — Both · v1

Job #7. A time-boxed guest who sees exactly what to do today, can log it, and loses access automatically when you are home. Also the feature that turns every holiday into a referral.

| Facet | Detail |
|---|---|
| **UI** | For the owner: "Add a sitter" → dates → what they may see (care schedule always; health record and documents optional) → link. For the sitter: a simplified Today with the routine as a checklist, feeding amounts, medication instructions, vet and emergency contacts, "House notes", and a big "Something's wrong — call owner" button. Professional sitters get a household switcher. |
| **UX** | The sitter never sees the paywall, never gets marketing, and never needs an account beyond a name and email. The owner sees a live feed of what the sitter logged. Access ends at the chosen time; the sitter is told when. |
| **Data** | `Membership.role = SITTER` with `expires_at` enforced in RLS, not just the UI. The sitter brief is also exportable as a PDF for non-app sitters. |
| **Android** | Sitter-role widget shows only today's checklist. |
| **iOS** | The same; a Live Activity for "walk in progress" is v2. |
| **Play** | Time-limited access is data minimisation — say so in the privacy policy. The sitter is a data subject: privacy notice at join. |
| **Apple** | Named in the review notes as the differentiator (4.3(b) defence). Demo: provide a second reviewer account already joined as a sitter. |

### 6.12 Exports — shaped for the job — Both · v1 (travel pack v2)

Job #4. Not "a PDF" — five named artifacts, each laid out for the person who will read it.

| Facet | Detail |
|---|---|
| **UI** | Export picker as cards: **Boarding certificate** (vaccinations with expiry, microchip, vet, emergency contact), **New-vet handover** (full record, chronological), **Insurance claim pack** (selected visit + itemised invoice + relevant history + condition timeline + proof-of-payment slot), **Sitter brief**, **Full backup** (CSV + JSON + documents zip). Date range and pet selection on each. Preview before share. |
| **UX** | One tap from a visit → claim pack pre-filled with that visit. Generated on-device; shared via the system sheet; nothing is emailed by us. Footer on every PDF: *"Generated by the pet owner from self-reported records. Not a medical document. Consult your veterinarian."* Free tier: one export per month; paid: unlimited. |
| **Data** | A pure `ReportBuilder` in common code (port Motivoa's builder/generator split) → PdfKmp renderer, or `expect fun render()` with platform actuals as the fallback. Tagged/accessible PDFs are an explicit engineering task (EAA). |
| **Android** | `android.graphics.pdf.PdfDocument` actual (already proven in Motivoa). Share via `FileProvider`. |
| **iOS** | `UIGraphicsPDFRenderer` actual; share via `UIActivityViewController`. |
| **Play** | Export is a digital feature — gating it behind the paid tier must go through Play Billing. No insurer logos without permission. |
| **Apple** | 3.1.1 the same. Never take referral fees from insurers without regulatory advice (FCA / IDD) — neutral export only. |

### 6.13 Travel readiness — Both · v2

Job #12, and a feature no competitor offers: compute the UK↔EU (later US/CA) date windows from records we already hold.

| Facet | Detail |
|---|---|
| **UI** | Pick destination and travel date → a checklist with computed status: microchip before rabies vaccine ✓/✗, rabies ≥ 21 days before travel, AHC must be issued within 10 days of travel (shows the window), tapeworm treatment 24–120h before UK return, valid-vaccination expiry. Each item links to the record that satisfies it, or an "Add record" button. |
| **UX** | Framed as "paperwork readiness", never as legal advice; each rule shows its source and "rules change — confirm with your vet and gov.uk". A reminder is offered for the AHC appointment window. |
| **Data** | Rules as versioned data (country pair → constraints), updatable remotely without an app release. Pure functions, fully unit-tested in `commonTest`. |
| **Play** | Content is informational; keep the disclaimer. No government logos. |
| **Apple** | 2.3.1: do not ship this dormant — either it is in the build and the screenshots, or it is not. |

### 6.14 Journal and monthly album — Both · v1 (albums paid)

Job #10 and the most emotionally cited feature in the category. This is what converts a chore log into something nobody deletes.

| Facet | Detail |
|---|---|
| **UI** | Chronological photo feed per pet with captions; auto-generated **monthly album** pages ("Luna · March 2027", weight that month, notable events); "On this day" card on Today; birthday and gotcha-day markers; household members can react and comment on entries. |
| **UX** | Photos taken in the Quick Log flow appear here automatically. Comments are threaded and attributed (DogNote's "can't tell who wrote what" complaint). Albums exportable as a PDF booklet (paid). |
| **Data** | `JournalEntry` = `CareEvent` with media; `Reaction` and `Comment` rows. Photos private to the household. |
| **Android** | Coil 3 with disk cache; Photo Picker for imports. |
| **iOS** | The same; shared photo-picker code path. |
| **Play** | UGC: report on every photo and comment; block member. |
| **Apple** | 1.2 the same. Age-rating "social media" questions: a private, invite-only feed → answer no, and be ready to justify it. |

### 6.15 Paywall — Both · v1

One shared Compose paywall driven by RevenueCat offerings, identical on both platforms, built to pass Apple 3.1.2 and Play's subscription policy on the first submission.

| Facet | Detail |
|---|---|
| **UI** | Header names the household ("Everything for the Ahmed household"). Three plan cards — **Annual** pre-selected and visually dominant with the full billed price largest ("$29.99 / year"), monthly, lifetime. Feature list as short verbs. Beneath the CTA, plain text: trial length and exact end date, renewal sentence, cancel instructions. Footer: Restore Purchases · Terms · Privacy. Close control top-right, always visible. |
| **UX** | Appears only when the user hits a paid boundary ("Add a 4th household member", "21st document", "second export this month") with that boundary named. Never on launch, never after a memorialised pet, never to sitters. Purchase success is a quiet confirmation, not a celebration screen. Cancellation is two taps from Settings. |
| **Data** | RevenueCat entitlement "household" cached locally; the entitlement is per **household** — one purchase unlocks every member's device. |
| **Android** | Play Billing 8 via purchases-kmp. Cancellation deep link: `play.google.com/store/account/subscriptions?sku=…&package=…`. |
| **iOS** | StoreKit 2 via purchases-kmp; Introductory Offer configured in App Store Connect for the trial; Family Sharing enabled (irreversible); Restore wired. |
| **Play** | Disclose terms, price, cycle and auto-renew "without additional action"; no SKU named "Free Trial"; localised prices everywhere; never reduce benefits after the first period; no dismiss-less prompts. |
| **Apple** | 3.1.2: billed amount most prominent; trial duration plus post-trial price; Restore; ToU and Privacy links in-app *and* in metadata; Small Business Program enrolment (15%). |

### 6.16 Settings, privacy and account deletion — Both · v1

The compliance screen that also earns trust: theme, units, quiet hours, notification channels, data export, delete account, disclaimers, support.

| Facet | Detail |
|---|---|
| **UI** | Grouped list: **Household** (quiet hours, default units, first day of week), **Notifications** (per-channel toggles plus the reminder health check), **Appearance** (explicit light/dark/system toggle), **Data** (Export everything, Import, Storage used), **Subscription** (manage/cancel — two taps), **Privacy** (policy, terms, community guidelines, "Delete account"), **Help** (support email, in-app message, about, disclaimer). |
| **UX** | "Delete account" asks what to do with the household (transfer to another member or dissolve), then confirms once, then deletes — no survey, no guilt. "Export everything" produces the full backup zip first and offers it. Support messages get a promised 24-hour reply. |
| **Data** | Deletion removes the user, memberships, and — if dissolving — all household data and storage objects; processors instructed to delete; retention exceptions named in the policy. Consent log kept 3 years (California ARL). |
| **Android** | Public deletion web page URL declared in Data safety; the same page linked here. |
| **iOS** | Revoke the Sign in with Apple token on deletion; the optional Privacy Choices URL points to the same page. |
| **Play** | Both in-app deletion **and** a public, login-free, direct web link are required. The privacy policy must be HTTPS, non-PDF, public, and name the company and app. |
| **Apple** | 5.1.1(v): a real in-app control, not a mailto. 1.2: published contact info. Standard EULA link at the end of the store description. |

### 6.17 Archive and memorial — Both · v1

Law #2. A pet that has died leaves Today, keeps every record and photo, never counts against anything, and is never used to sell.

| Facet | Detail |
|---|---|
| **UI** | From the profile overflow: "Luna has passed away" → date → a calm confirmation. The profile becomes a memorial: hero photo, years together, the album, the full record still readable and exportable. Muted palette; no badges, no streaks. An "Archived" section at the bottom of the pet row, collapsed by default. Also a plain "Archive (rehomed / no longer in my care)" variant. |
| **UX** | All reminders for that pet cancel instantly across the household. No promotional notifications ever again for that pet. Restoring from archive is one tap. Export and delete offered without pressure. |
| **Data** | `Pet.status = MEMORIAL \| ARCHIVED`, `passed_on`. Excluded from due-item materialisation. |
| **Android** | The widget drops the pet from its rotation. |
| **iOS** | The same; Spotlight entry updated. |
| **Play** | Not a policy surface — a reputation one. Never gate. |
| **Apple** | Screenshots of this screen must stay tasteful (2.3.8). If a memorial page is ever shareable publicly, it becomes public UGC and needs moderation. |

### 6.18 Lost-pet poster — Both · v2

Data we already hold — photo, microchip, description, contacts — assembled into a printable poster and a shareable page in the worst hour of an owner's year.

| Facet | Detail |
|---|---|
| **UI** | "Luna is missing" → confirm last-seen area (free text, no GPS) → choose photo → poster preview (A4/Letter PDF plus a square social image) → share sheet plus an optional public link with a "Found" form. |
| **UX** | Zero paywall. The contact shown is a choice (phone, email, or the app's relay). The link expires in 90 days and is revocable; "Luna is home" closes it and thanks the sharers. |
| **Data** | The poster page is rendered server-side from a snapshot; free text is length-limited and URL-stripped; the image passes safe-search moderation before going live; rate-limited per account; `noindex`. |
| **Android** | Save the poster to Photos via `MediaStore` (no permission on API 29+). |
| **iOS** | `NSPhotoLibraryAddUsageDescription` with a specific string, only if we save to the library. |
| **Play** | Public UGC: in-app report on the poster, moderation, revocability. No location permission — we never use GPS. |
| **Apple** | 1.2 public-UGC obligations; 5.1.1(viii) — never compile a "found pets" directory from public data. |

### 6.19 Widgets, Watch and Wear — Android + iOS · widgets v1, wearables v2

The most-requested missing feature in the category's best shared-care app, and a concrete Apple 4.3(b) differentiator. The one place the view layer is written twice.

| Facet | Detail |
|---|---|
| **UI** | **Small:** one pet — "Fed 3h ago · Meds 8pm" with a one-tap Log button. **Medium:** due-now list for the household. **Large:** today's timeline. Sensible redacted placeholder for the widget gallery. |
| **UX** | Interactive: tapping "Done" logs without opening the app and updates every household member's widget within seconds. Works for the free tier — a widget that is only an upsell risks rejection. |
| **Data** | Both widgets read the same Room database the app writes (Android: direct; iOS: App Group container). Formatting logic shared in Kotlin; only the view is native. |
| **Android** | `androidx.glance` 1.2 — port Motivoa's `StreakWidget` pattern and `WidgetTheme` contrast rule. Wear OS tile in v2 (must be 64-bit, mentioned in the listing). |
| **iOS** | WidgetKit extension in SwiftUI calling the KMP framework via App Group; App Intents for interactivity; watchOS complication "last fed" in v2 (SwiftUI; entitlement synced from the phone). |
| **Play** | Wear quality guidelines apply if shipped; tiles and complications must be mentioned in the listing. |
| **Apple** | 2.5.16 widgets relate to app content; 3.1.7 no ads in extensions; 3.1.2(a) the subscription must work on Watch (sync the entitlement, no Watch paywall). |

### 6.20 Import — from 11pets, spreadsheets and rivals — Both · v1

An unclaimed acquisition channel: 500K+ installs at 2.2★, thousands publicly leaving, an official export file, and no parser anywhere.

| Facet | Detail |
|---|---|
| **UI** | "Switch from another app" → pick source (11pets export, PetnotePlus CSV, generic CSV, Pet Parents) → file picker → mapping preview (pets found, records found, anything we could not read) → import → summary. Plus a marketing landing page: "Leaving 11pets? Bring 7 years of records with you." |
| **UX** | Never silently drop a field — unmapped data lands in notes with its original label (GreatPetCare's "duplicated and incorrect" migration is the anti-pattern). Weight units are verified, not assumed (11pets' own bug). Import is reversible for 7 days. |
| **Data** | Parsers in `commonMain`, fuzz-tested against real export samples gathered in beta. Source recorded on every imported row. |
| **Android** | SAF `ACTION_OPEN_DOCUMENT` — no storage permission. |
| **iOS** | `UIDocumentPickerViewController`; also accept "Open in…" from Files and Mail. |
| **Play** | **Never put "11pets" in the title, keywords or description** — competitor-brand stuffing is a metadata violation and a takedown trigger. The landing page lives on our website, not the listing. |
| **Apple** | 4.1 / 5.2.1 the same; no competitor names in metadata. Supporting their file format is fine. |

### 6.21 Report, block and community guidelines — Both · v1

Not a feature anyone asked for; a feature both stores require the moment two people share a feed. Build it once, wire it everywhere.

| Facet | Detail |
|---|---|
| **UI** | "Report" in the overflow of every photo, note, comment and log entry; "Report member" and "Remove & block" on every member row; a short reason picker; confirmation with the 24-hour commitment. A Community Guidelines page reachable from Settings and the website. |
| **UX** | Reporting never leaves the app. Blocked identities cannot be re-invited. The household owner can purge a removed member's contributions or keep them attributed. |
| **Data** | `Report` rows → an abuse inbox with SLA tracking. Lightweight server-side image scan on upload; full moderation only on public surfaces (lost-pet pages). |
| **Play** | UGC policy for closed groups: in-app reporting for content and users; terms accepted before creating content; objectionable content defined. |
| **Apple** | 1.2: filter, report, block, published contact info, timely response — no private-group exemption. |

### 6.22 Beyond the field — features nobody has yet

| Feature | Why it matters |
|---|---|
| **Supply counter that turns into a reminder** | "3 flea doses left" → "Order more" reminder 10 days before you run out. Praised in 11pets' one surviving positive review; nobody else has it. |
| **Vet-visit prep sheet** | Before an appointment: one page of what changed since the last visit — weight delta, new symptoms logged, adherence gaps, questions you noted. Rules-based in v1; on-device AI summary in v1.1 where the device supports it, with "consult your vet" inline. |
| **Professional mode for sitters and walkers** | One account, many households, a today-list across all of them. Turns every professional into a distribution channel to every client they have. |
| **The drawn companion** | Motivoa's Canvas creature rig — nine species, coats, moods, zero assets — becomes an optional avatar that reflects the pet's day: content after a walk, expectant near meal time. Adult art direction, never a mascot. A delight layer no record app has. |
| **Trends that speak plainly** | "Milo has lost 6% since March" as an observation, never a diagnosis. Graphs on any custom metric — the feature a diabetic-dog owner said he would pay for. |
| **Wearable bridge (v3)** | Import Tractive activity and sleep as read-only context on the timeline — a real user asked 11pets for exactly this. |

---

## 7. Architecture and stack

Kotlin Multiplatform with Compose Multiplatform for every screen except three that are better native on iOS: **Sign in with Apple**, the **document viewer** (`QLPreviewController`), and **long-form text entry** until CMP's native text input leaves experimental. Widgets are the one deliberate double-write.

Everything platform-specific hides behind an interface in a `core-*` module; feature modules contain no `expect`/`actual` at all.

| Concern | Choice | Version | Risk | Why |
|---|---|---|---|---|
| UI | Compose Multiplatform | 1.12 | Low | iOS stable since 1.8; concurrent rendering default. Accessibility is manual — every custom-drawn thing needs `semantics {}`. |
| Navigation | Navigation 3 (multiplatform) | 1.1.1 | Medium | Google's direction; hand-register `NavKey` serializers off-JVM. Fallback: Decompose if iOS swipe-back fights us in the week-1 spike. |
| Local DB | Room KMP | 2.8.x | Low | Same migration discipline as Motivoa's 12 versions; iOS via the bundled SQLite driver. Stay off `androidx.room3` until after first ship. |
| Prefs | DataStore Preferences | 1.2.x | Low | One `expect` for the file path. |
| Backend | **Supabase — EU region** | supabase-kt 3.x | Medium | Auth (Apple + Google native), Postgres with RLS by `household_id`, Storage, Realtime. EU residency is the decisive GDPR win over Firebase. |
| Sync | **PowerSync** | 1.13.x | Medium | Offline-first queue, retry, partial sync between Room/SQLite and Postgres. The insurance policy against hand-rolling an outbox. |
| Billing | RevenueCat purchases-kmp | ≥3.0 | Low | Bundles the iOS SDK (no CocoaPods). One shared Compose paywall driven by Offerings; skip the iOS-only paywall UI. |
| Reminders | Alarmee + our own scheduler | — | Medium | The repeating API fits medication; we still write the exact-alarm consent dance ourselves. |
| Push | FCM + APNs (thin actuals) | — | Low | Only for "your partner logged a dose" and long-horizon backstops — **never** the primary medication reminder. |
| Files & images | FileKit · Coil 3 · Peekaboo | — | Low | Native pickers, no storage permissions; client-side compression. |
| PDF | PdfKmp, platform-actual fallback | 1.x | Medium | Young library; Motivoa already proves the Android `PdfDocument` actual. |
| DI | Koin + annotations | 4.1 | Low | Shortest bridge from Hilt for a solo developer. |
| Dates | kotlinx-datetime | 1.0 | Low | Locale-aware formatting needs a 30-line `expect` (Motivoa's `DateFormats` documents why). |
| i18n | Compose Resources | — | Low | Same `strings.xml` shape as Motivoa; re-point the audit scripts. Launch locales: en, de, fr, es. |
| Crash / analytics | Sentry KMP · Aptabase | — | Low | No IDFA, so no ATT prompt; no Firebase Analytics. |
| On-device AI | `AiEngine` interface; ML Kit GenAI + Apple Foundation Models actuals | v1.1 | Medium | Rules-based default for 100% of users; AI is progressive enhancement. On-device = not "collected", no third-party-AI disclosure. |
| CI / iOS builds | Codemagic (+ a used Mac mini) | — | **High** | You cannot build, sign or upload iOS from Windows. CI ships; only a Mac diagnoses. |

### Module graph

```
shared/
  core-model         pure data classes — everything depends on this, it depends on nothing
  core-common        Result, dispatchers (expect), Clock, UUID (expect)
  core-datetime      kotlinx-datetime + expect locale formatting
  core-designsystem  palette (accentFill rule), type, motion tokens, atoms   <- Motivoa
  core-database      Room 2.8 entities/DAOs + expect builder (App Group path on iOS)
  core-datastore     DataStore Preferences + expect path
  core-network       Ktor + Supabase client
  core-sync          PowerSync schema, event model, conflict policy
  core-notifications expect Scheduler — rolling-window materialiser
  core-billing       RevenueCat wrapper, Entitlement
  core-ai            interface AiEngine + UnsupportedAiEngine (default)      <- Motivoa
  core-files         FileKit, compression, expect share sheet

  feature-pets · feature-log · feature-reminders · feature-household
  feature-record · feature-documents · feature-export · feature-paywall
  app-ui             Nav3 graph, scaffold, theme host

androidApp/          Application, Glance widgets, FCM service
iosApp/              SwiftUI shell, ComposeUIViewController, WidgetKit,
                     Sign in with Apple, APNs
```

**Layering rule:** `feature-*` → `core-*` → `core-model`. No feature depends on another feature; cross-feature coordination goes through `core-model` events. Keeping `expect`/`actual` out of feature modules entirely is the single discipline that stops iOS bleeding into product code.

### The two designs that decide whether this works

**Sync: an append-only event log**

- A care log is append-mostly. Every log line is an immutable `CareEvent` with a client UUID, `occurred_at`, `author_id`, `household_id`.
- Inserts merge by union — **no conflicts are possible**. Edits are new version rows; deletes are tombstones; last-writer-wins applies only to the rare same-row edit.
- Mutable things (pet profile, schedule rules) are few and low-contention; LWW with `updated_at` is acceptable there.
- PowerSync syncs `WHERE household_id IN (my memberships)`; RLS enforces the same server-side so a revoked sitter's device stops receiving data.
- **Target: a partner's log appears on your phone in under 3 seconds on Wi-Fi**; offline writes queue and reconcile without user action.

**Reminders: schedule as data, OS as cache**

- `ScheduleRule` rows are the truth. Occurrences are computed, never stored as alarms.
- A materialiser keeps a rolling window (7 days on Android, ≤ 40 slots on iOS) of OS notifications; it re-runs on foreground, background refresh, reboot, timezone change, and after any household member marks a dose done.
- Android: `SCHEDULE_EXACT_ALARM` when granted, inexact plus a visible banner when not; WorkManager drives re-materialisation.
- iOS: calendar triggers for regular series; server push as a backstop for long-horizon boosters.
- A self-check (Motivoa's `ReminderHealthProbe`) compares what should be queued with what the OS says is queued, and tells the user when they differ.

### Top KMP pitfalls for an Android developer going to iOS

1. **You need a Mac** — not for CI, but for debugging, profiling, VoiceOver testing and App Store screenshots.
2. **`Dispatchers.IO` does not exist on Kotlin/Native.** You need an `expect val ioDispatcher`.
3. **`java.time` is gone.** Every `LocalDate.now()`, `ChronoUnit.DAYS.between()` and `DateTimeFormatter` is a rewrite.
4. **`Context` is everywhere and nowhere.** Every `Context`-taking constructor is an `expect`/`actual` or a hand-injected platform object.
5. **iOS back-gesture is not free.** The interactive edge-swipe must be explicitly wired. Prototype before building 20 screens.
6. **Accessibility is opt-in on iOS Compose.** Skiko draws pixels; VoiceOver sees only what `semantics {}` declares.
7. **iOS build times will shock you**, and release linking is ~10× debug. Keep the shared module lean.
8. **iOS caps pending local notifications at 64**; Android 14+ denies exact alarms by default.
9. **Every small library is one maintainer.** Wrap each behind your own interface so replacing it is a single file.
10. **Resources do not port automatically.** `R.string` → `Res.string` is mechanical but total.
11. **`System.currentTimeMillis()`, `java.util.UUID`, `java.io.File`, `SimpleDateFormat`, `Locale`** all compile on Android and fail the moment iOS is added.

---

## 8. What Motivoa gives us

The existing Android app is 311 files, single-module, Hilt + Room 2.8 + Compose. Its most valuable parts are already KMP-shaped. Five things are worth carrying over, in order of value per line ported.

| # | Asset | Where | Why it is worth carrying |
|---|---|---|---|
| 1 | **The Canvas creature rig** | `companion/ui/CompanionChibi.kt` (1,130 lines) + `CompanionCreatures.kt`, `CompanionOutfits.kt`, `CompanionCharacter.kt` | Pure `DrawScope` on a 256-unit artboard. **Zero assets, zero `android.*` imports, zero resource references.** Nine species with coats and markings, a face rig, outfits, held props, a documented secondary-motion model, and ~20 unit tests. Drops into Compose Multiplatform Canvas essentially unchanged. For a pet app this is almost literally the product's delight layer — months of work, already done. |
| 2 | **The `CompanionAiEngine` interface** | `companion/CompanionAiEngine.kt` | A correctly-shaped `expect`/`actual` boundary written before anyone asked for one: models `Availability.{Ready, NeedsDownload, Downloading, Unsupported}`, a download progress flow, a streaming `reply(prompt): Flow<String>`, and `close()`. `UnsupportedCompanionAiEngine` makes "no AI" a first-class, tested path. Rename it, add an iOS actual over Apple Foundation Models, and the on-device AI architecture is done. |
| 3 | **The `accentFill` palette contrast system** | `ui/theme/MotivoaPalette.kt` + `widget/WidgetTheme.kt` + `ui/theme/PaletteContrastTest.kt` | Any of 13 colours works in either theme because of a hard-won, measured WCAG rule: never fill a surface with `accent`, always `accentFill = if (isLight) accentDeep else accent`, because 9 of 13 themes fail AA otherwise (one measured at 2.95:1). A unit test pins it, and the gradient variant derives its far stop by darkening so a future 14th theme cannot reintroduce the bug. Institutional knowledge you cannot re-derive cheaply, and it is pure Compose. |
| 4 | **Room migration discipline** | `data/local/AppDatabase.kt`, `app/schemas/`, `androidTest/.../MigrationTest.kt` | Twelve versions, every `ADD COLUMN` guarded by an idempotent `hasColumn()` PRAGMA check, `IF NOT EXISTS` on creates, exported schemas under version control and wired into tests, plus the `seedMutex` fix for a real onCreate/onOpen race that duplicated 9,883 rows on a Galaxy S25. For a health-records app where data loss is unforgivable, the discipline is worth more than any specific code. |
| 5 | **Reminder policy and the i18n architecture** | `notifications/SmartNotificationManager.kt`, `ReminderHealthProbe.kt`, `utils/Strings.kt`, `utils/DateFormats.kt` | Distinct notification IDs so reminders do not replace each other in the shade; distinct PendingIntent request codes so an action cannot hijack a body tap; a health probe that asks the scheduler what is actually queued. Plus the `Strings` rule — *an engine may only look up an id, never concatenate two looked-up sentences or case-change one, because that is grammar and grammar belongs in the resource file*. Both are policy, not code, and both survive the port intact. |

### Sized blockers, if code were ported rather than re-written

| Blocker | Scale |
|---|---|
| `R.string` / `R.drawable` / `R.raw` → Compose Resources | **108 files** |
| `java.time` → kotlinx-datetime | **45 files** |
| `android.content.Context` in constructors or params | **39 files** |
| Any `android.*` import | **65 files** |
| Hilt / `javax.inject` → Koin | **28 files** |

**Recommendation: start Pet Care Hub as a fresh multi-module KMP project and *copy in* the five assets above**, rather than porting Motivoa wholesale. Motivoa's own modularisation happens later, on its own timeline, and can borrow this project's module graph.

---

## 9. Store compliance

Condensed from the full policy audit (`docs/research/store-policies.md`, 15 September 2026, against Apple's App Review Guidelines and Google Play's Developer Policy Center). Anything marked **verify** comes from secondary sources or ambiguous policy text.

### The eight risks most likely to bite

| Risk | Store | Severity | Answer |
|---|---|---|---|
| Medication reminders vs Android exact alarms — we do not qualify for `USE_EXACT_ALARM` | Play | **Critical** | `SCHEDULE_EXACT_ALARM` + consent flow + honest inexact fallback. Spike before any feature work. |
| Paywall disclosure (Apple 3.1.2) — the most common subscription rejection | Apple | **Critical** | Price most prominent, renewal sentence, trial end date, Restore, ToU + Privacy on the sheet. |
| Health apps declaration is mandatory for every app; pet health is not addressed by the policy | Play | High | Declare "no health features", ship the disclaimer anyway, keep written reasoning. **Verify.** |
| Household sharing is UGC — no private-group exemption exists | Both | High | Report on every item, report/block member, terms before first upload, 24-hour abuse inbox. |
| 12 testers / 14 days closed test for new personal Play accounts | Play | High | **Register as an Organization** (D-U-N-S, ~2 weeks) — skips it entirely. |
| EU DSA trader status must be declared or the app is removed in the EU | Both | High | Business address and phone (publicly shown). **Never a home address.** |
| European Accessibility Act — law, not store policy, enforceable since 28 Jun 2025 | EU | High | VoiceOver/TalkBack labels, 200% type, 4.5:1 contrast, 48dp targets, accessibility statement. Microenterprise exemption likely — **verify per country**. |
| Apple 4.3(b) (June 2026): apps "indistinguishable" from existing ones in crowded categories rejected **or removed** | Apple | High | Ship household sharing, sitter access, interactive widgets and claim-pack exports in v1; say so in review notes; keep shipping. |

### Checklist — both stores

**Legal entity and accounts**

- [ ] Register a legal entity and obtain a **D-U-N-S number** (free, allow ~2 weeks)
- [ ] Enrol as an **Organization** on Apple ($99/yr) and Google Play ($25 one-time)
- [ ] Declare **EU DSA trader status** in both consoles, with a business address and phone
- [ ] Complete developer identity verification on both platforms

**Legal documents** (all HTTPS, non-PDF, globally reachable, before submission)

- [ ] Privacy Policy — what is collected, how, all uses, third-party sharing (including any AI provider), retention and deletion, how to revoke consent, contact info, company and app name
- [ ] Terms of Use / EULA — objectionable-content prohibition, 16+ age minimum, auto-renewal terms, disclaimer of veterinary advice
- [ ] Community Guidelines
- [ ] **Public account-deletion page**, no login required, direct link
- [ ] Accessibility statement (EAA)
- [ ] Support email and support URL, published on both listings and in-app

**Paywall**

- [ ] Subscription name, duration and what you get
- [ ] Full billed price as the most prominent pricing element
- [ ] Explicit auto-renewal statement
- [ ] Trial duration, exact end date, price after, how to cancel before charging
- [ ] Restore Purchases button
- [ ] Terms of Use and Privacy Policy links
- [ ] Visible close/dismiss control
- [ ] Cancellation path in Settings, ≤ 2 taps, deep-linked to the store's subscription centre; no guilt screens
- [ ] Full localisation of all price and term strings
- [ ] CTA labelled with the obligation to pay
- [ ] Never gate the free tier behind notifications, ATT, or social actions

**Accounts and data**

- [ ] In-app "Delete Account" — deletes account plus data, not a deactivation, not a mailto
- [ ] Household-owner deletion semantics defined (transfer or dissolve)
- [ ] Revoke Sign in with Apple tokens via Apple's REST API on deletion
- [ ] Data safety form ⟷ Nutrition Labels ⟷ privacy policy ⟷ actual behaviour: all four consistent
- [ ] TLS everywhere, including image and PDF upload/download

**UGC and sharing**

- [ ] ToS acceptance gate before first upload; log consent, version and timestamp
- [ ] Report content on every photo, note and log entry
- [ ] Report user, block/remove member in household management
- [ ] Abuse inbox with a 24-hour response commitment
- [ ] Server-side image moderation on any publicly shareable asset
- [ ] Invite tokens scoped, expiring, single-use, revocable; sitter limits enforced server-side
- [ ] Verified App Links / Universal Links — no custom schemes

**Health-adjacent content**

- [ ] "Not a medical device / consult a vet" disclaimer: first run, Settings, every AI output, every exported PDF footer
- [ ] **No dosage calculator.** No diagnostic claims. Ban "diagnose / treat / prescribe / clinically proven / vet-approved / detects" from all copy
- [ ] Reminder-reliability disclaimer for medication

**Metadata and assets**

- [ ] No price or promo words, no "#1"/"Best", no emojis, no ALL CAPS, no CTAs in title, icon or screenshots
- [ ] Move "Unlimited pets on one price" to the long description and the paywall only
- [ ] Screenshots reflect the shipped build only
- [ ] Licence documentation on file for every third-party image, illustration and font
- [ ] Trademark clearance on the final name in US / EU / UK / CA
- [ ] **No competitor brand names** in keywords, title or description

**Accessibility (EAA)**

- [ ] VoiceOver/TalkBack labels on all controls, paywall included
- [ ] Dynamic Type / font scaling to 200% without clipping
- [ ] Contrast ≥ 4.5:1; never convey status by colour alone
- [ ] Touch targets ≥ 44pt / 48dp; Reduce Motion respected
- [ ] Tagged/accessible exported PDFs

### Checklist — Google Play only

- [ ] **Data safety form** (+ deletion URL, in-app deletion answer, encryption in transit)
- [ ] **Health apps declaration** — mandatory for every app
- [ ] **Target audience and content** → adult age groups only; **do not** opt into Designed for Families
- [ ] **Content rating** (IARC questionnaire) — unrated apps are prohibited
- [ ] **Ads declaration** → no ads
- [ ] **App access** → demo credentials and step-by-step instructions for household sharing
- [ ] **AI-generated content declaration** for any AI-made store-listing asset
- [ ] Declare `SCHEDULE_EXACT_ALARM`; **do NOT declare `USE_EXACT_ALARM`** (upload will be blocked)
- [ ] Implement `canScheduleExactAlarms()` guard, request flow, working inexact fallback, revoked-permission broadcast handling
- [ ] **Remove** `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_EXTERNAL_STORAGE` from all version codes — check the **merged** manifest for transitive injections
- [ ] Do **not** request `REQUEST_IGNORE_BATTERY_OPTIMIZATIONS` or `READ_CONTACTS`; no location permissions of any kind
- [ ] `POST_NOTIFICATIONS` requested contextually, with rationale, graceful denial, separate channels
- [ ] Prominent disclosure plus affirmative consent before the first cloud upload
- [ ] Target **API 36**; **16 KB page-size** compliance audited across every native `.so`; **Play Billing 8.0.0+**; AAB plus Play App Signing
- [ ] Title ≤ 30 chars; 2–8 phone screenshots

### Checklist — Apple only

- [ ] **Privacy Nutrition Labels** completed; pet health records declared as **User Content → Other User Content**, not Health (reasoning documented)
- [ ] Privacy Policy URL; User Privacy Choices URL pointing at the deletion page
- [ ] **Age rating questionnaire** including medical/wellness and (required from Sept 2026) social-media questions. Expect 13+. **Not** the Kids Category
- [ ] **EU DSA trader status** verified
- [ ] License Agreement: standard Apple EULA linked at the end of the description, or a custom one uploaded
- [ ] **App Review Information**: two demo accounts (one already in the other's household), pre-seeded data, a live invite link, a numbered walkthrough script, IAP sandbox notes, "no location or contacts used"
- [ ] Enrol in the **Small Business Program** (15%)
- [ ] Enable **Family Sharing** on the subscription (irreversible)
- [ ] Configure an **Introductory Offer** for the free trial
- [ ] Built with **Xcode 26 / iOS 26 SDK**; deliberate decision on Liquid Glass adoption
- [ ] **`PrivacyInfo.xcprivacy`** in the app target with `NSPrivacyCollectedDataTypes` matching the Nutrition Labels and `NSPrivacyAccessedAPITypes` reason codes for UserDefaults (CA92.1), file timestamp, disk space, system boot time
- [ ] Verify the manifest survives KMP → SPM/CocoaPods packaging; generate the Privacy Report and diff it against the labels
- [ ] Audit every third-party SDK for a bundled privacy manifest **and signature**
- [ ] **Sign in with Apple** implemented (mandatory because we offer Google), with token revocation on deletion
- [ ] Specific, non-generic purpose strings; use `PHPickerViewController` so `NSPhotoLibraryUsageDescription` can be omitted; remove every unused usage-description key
- [ ] Rolling 64-notification window; server push for long-horizon reminders
- [ ] No ads in widgets, extensions or watchOS; Live Activities strictly functional

### Items flagged uncertain — verify before relying on them

| # | Claim | How to resolve |
|---|---|---|
| 1 | Medication-reminder apps do not qualify for `USE_EXACT_ALARM` | Submit a policy question via Play Console before building; worst case ship `SCHEDULE_EXACT_ALARM` anyway (safe) |
| 2 | Play requires ≤ 2-tap in-app cancellation | Build it regardless — zero cost |
| 3 | Health apps declaration deadline (Aug 2024 vs Jul 2026) | Just complete it; it blocks publishing either way |
| 4 | Whether **pet** health data counts as "Health" under Apple labels or Play Data safety | Declare as User Content / Files & docs, document the reasoning, ship the disclaimer as a hedge |
| 5 | Play AI policy's in-app reporting requirement | Read the policy directly in Console before shipping any AI |
| 6 | iOS 64-pending-local-notification limit | Well-established in developer forums; architect around it regardless |
| 7 | EAA microenterprise exemption (<10 staff, ≤ €2M) | **Get EU legal advice before EU launch** — national transpositions vary |
| 8 | Apple 4.3(b) applicability to pet-care apps | Cannot be resolved in advance; differentiate aggressively |

---

## 10. Roadmap

Sequenced so each phase is independently shippable and the two make-or-break systems — sync and reminders — are proven on real phones before anything decorative is built. Durations assume one developer at roughly 25 focused hours a week.

### Phase 0 — Foundations and spikes · Weeks 0–3

- Register the entity, apply for D-U-N-S, enrol both developer programs as an **Organization**, secure the name and domain, run the trademark search
- Buy or rent the Mac; set up Codemagic; produce **one signed iOS build of a hello-world KMP app end to end** — the single biggest practical blocker
- **Spike A:** Navigation 3 on iOS with interactive swipe-back
- **Spike B:** Room KMP + PowerSync + Supabase EU round-trip between two physical devices
- **Spike C:** exact-alarm consent flow on Android 14/15/16, and the iOS 64-slot materialiser
- Submit the Play policy question about `USE_EXACT_ALARM` eligibility
- Draft the privacy policy and terms

### Phase 1 — The daily loop (private beta) · Weeks 4–13

- Onboarding, sign-in, Today feed, Quick Log, pet profile, household invites with roles, medications and schedules, reminder health screen, Settings with deletion
- Glance widget plus WidgetKit small/medium
- Copy in the Motivoa design system, the creature rig (as avatar), and the AI engine interface with the Unsupported default
- **Closed beta: 30 households**, at least 10 with two or more members and one medicated pet
- Measure sync latency, reminder punctuality, crash-free rate. **Nothing ships until reminders are boringly correct.**

### Phase 2 — The record (launch) · Weeks 14–22

- Health record, documents vault and viewer, journal, archive and memorial, exports (boarding, new-vet, claim pack, sitter brief, backup), paywall, report/block, the 11pets importer
- Compliance pass against the checklist; App Review notes; store listings in en/de/fr/es
- ASO on "pet care tracker", "shared dog feeding tracker", "pet vaccination records"; the "Leaving 11pets?" landing page
- **Launch both stores together.** Reddit community launch in the dogcat.app style — ask what is missing, reply to everyone

### Phase 3 — Retention and reach · Months 6–9

- Sitter professional mode and multi-household switcher; travel readiness; supply counters; vet-visit prep sheet (rules-based, then on-device AI where available); comments and reactions; lost-pet poster
- Apple Watch and Wear OS quick-log; App Intents / Siri
- Begin the vet and sitter channel: a one-page "share the app with your clients" kit; explore a read-only clinic export link

### Phase 4 — Only if the numbers say so · Month 9+

- Web portal (users love iPad plus web sync); wearable-data bridge; food barcode scanning against allergies; additional locales (ar, hi)
- EU alternative billing only past ~$500K ARR

---

## 11. Risks and open decisions

### Highest risks

| Risk | Mitigation |
|---|---|
| **Reminder reliability on Android** if users deny exact alarms at scale | The fallback and the health screen; re-architect to push-primary if beta shows >30% denial |
| **iOS quality from a Windows developer** — text input, VoiceOver, swipe-back | The Mac, the week-1 spikes, and three deliberately native screens |
| **Small-maintainer libraries** (Alarmee, PdfKmp, Peekaboo, supabase-kt) | Each wrapped behind our own interface so a swap is one file |
| **Apple 4.3(b)** crowded-category removal | Cannot be resolved in advance; differentiate visibly in v1 and keep shipping |
| **Distribution ceiling** | The honest verdict in §1. Sized for 100–300K installs; anything above requires the vet/sitter channel to work |

### Decisions still open

- **Name.** "Pet Care Hub" is descriptive and may not clear trademark. Shortlist and clear before Phase 1 ends. The package `com.petcarehub` is provisional — see `docs/adr/0001-provisional-name.md`
- **Species scope at launch.** Recommended: dog and cat templates, with "Other" fully supported (free text, grams) from day one — exotics keepers are vocal and under-served
- **Lifetime price** ($79.99 proposed) and whether the free tier's document cap is 20 or 50
- **Legal review for EU launch**: GDPR processor agreements, EAA exemption per member state, consumer-law renewal notices
- **Motivoa timing.** Finishing Motivoa's billing and Play submission is weeks of work and should land before Phase 1 starts, so both apps are not half-shipped at once

---

## 12. Sources

Primary and official sources behind this plan. Store figures are point-in-time (15 September 2026). Full reports with inline citations live in [`docs/research/`](research/).

**Apple**

- [App Review Guidelines](https://developer.apple.com/app-store/review/guidelines/)
- [Auto-renewable subscriptions](https://developer.apple.com/app-store/subscriptions/)
- [App privacy details](https://developer.apple.com/app-store/app-privacy-details/)
- [Offering account deletion](https://developer.apple.com/support/offering-account-deletion-in-your-app)
- [Privacy manifest files](https://developer.apple.com/documentation/bundleresources/privacy-manifest-files)
- [Guideline updates, 8 Jun 2026 — 4.3(b), 4.5.3](https://developer.apple.com/news/?id=a233fmpw)
- [Updated age ratings](https://developer.apple.com/news/?id=ks775ehf) · [Social media questions](https://developer.apple.com/news/?id=tlur8uvi)
- [EU DSA trader status](https://developer.apple.com/news/?id=einwn76m)
- [Small Business Program](https://developer.apple.com/app-store/small-business-program/)

**Google**

- [Play Subscriptions policy](https://support.google.com/googleplay/android-developer/answer/9900533)
- [User Data policy](https://support.google.com/googleplay/android-developer/answer/10144311)
- [Account deletion requirements](https://support.google.com/googleplay/android-developer/answer/13327111)
- [User Generated Content policy](https://support.google.com/googleplay/android-developer/answer/9876937)
- [Health apps declaration](https://support.google.com/googleplay/android-developer/answer/14738291) · [Health Content and Services](https://support.google.com/googleplay/android-developer/answer/16679511)
- [AI-Generated Content policy](https://support.google.com/googleplay/android-developer/answer/14094294)
- [Photo & Video permissions](https://support.google.com/googleplay/android-developer/answer/15800983) · [Sensitive permissions & APIs](https://support.google.com/googleplay/android-developer/answer/16558241)
- [Closed testing for new personal accounts](https://support.google.com/googleplay/android-developer/answer/14151465)
- [Target API level requirements](https://support.google.com/googleplay/android-developer/answer/11926878)
- [Expanded billing choice and fees, Jun 2026](https://android-developers.googleblog.com/2026/06/play-expanded-billing.html)
- [Exact alarms denied by default](https://developer.android.com/about/versions/14/changes/schedule-exact-alarms)

**Stack**

- [Compose Multiplatform 1.11](https://blog.jetbrains.com/kotlin/2026/05/compose-multiplatform-1-11-0/) · [Navigation 3 in CMP](https://kotlinlang.org/docs/multiplatform/compose-navigation-3.html)
- [Room for KMP](https://developer.android.com/kotlin/multiplatform/room) · [supabase-kt](https://github.com/supabase-community/supabase-kt) · [PowerSync Kotlin](https://docs.powersync.com/client-sdks/reference/kotlin)
- [RevenueCat purchases-kmp](https://github.com/RevenueCat/purchases-kmp) · [Alarmee](https://github.com/Tweener/alarmee) · [FileKit](https://github.com/vinceglb/FileKit) · [PdfKmp](https://github.com/conamobiledev/PdfKmp)
- [Codemagic pricing](https://codemagic.io/pricing/) · [Kotlin/Native compile times](https://kotlinlang.org/docs/native-improving-compilation-time.html)

**Competitors**

- [11pets (Play)](https://play.google.com/store/apps/details?id=com.m11pets.elevenpets) · [11pets data export](https://www.11pets.com/en/news/export-data)
- [PetDesk](https://play.google.com/store/apps/details?id=com.locai.petpartner) · [Pet Care Tracker](https://play.google.com/store/apps/details?id=dogcat.app.android) · [DogLog](https://play.google.com/store/apps/details?id=com.mobikode.dog)
- [PetNoter](https://play.google.com/store/apps/details?id=com.petnoter) · [DogNote](https://play.google.com/store/apps/details?id=app.dognote) · [PetnotePlus](https://play.google.com/store/apps/details?id=com.lancerdog.petnote_plus) · [GreatPetCare](https://play.google.com/store/apps/details?id=com.pawprint.mobile)
- [Dogo (Sensor Tower)](https://app.sensortower.com/overview/app.dogo.com.dogo_android?country=US) · [Pawprint (Crunchbase)](https://www.crunchbase.com/organization/pawprint)

**User research and domain**

- [r/Pets — tracking medical history](https://www.reddit.com/r/Pets/comments/uo7xl3/how_do_you_track_your_pets_medical_history/) · [r/LifeProTips — vaccination spreadsheet](https://www.reddit.com/r/LifeProTips/comments/1ad9bp1/lpt_make_a_spreadsheet_to_keep_track_of_your_pets/)
- [RVC — Animal Health Certificate](https://www.rvc.ac.uk/small-animal-vet/general-practice/services/animal-health-certificate) · [Bankrate — pet insurance claim denials](https://www.bankrate.com/insurance/pet-insurance/pet-insurance-claim-denials)

---

*Plan v1 · written 2026-09-15 · Pet Care Hub · implementation not started.*
