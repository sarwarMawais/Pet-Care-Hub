Contents
The honest verdict
What owners want
Competitors
Product laws
Pricing
Screen by screen
Architecture
What Motivoa gives us
Store compliance
Roadmap
Risks & open items
Sources
Pet Care Hub
Product plan · v1 · Kotlin Multiplatform · Google Play + App Store
One record for the whole household, on both phones.
A shared daily care log (who fed, walked, medicated — and when) fused with a portable lifetime health record, unlimited pets on one price, and exports shaped for the moment someone demands a document: a new vet, a boarding kennel, an insurer, a border.

Status Plan only — implementation not started
Research date 15 Sep 2026
Markets US · CA · UK · EU
Stack KMP + Compose Multiplatform
The honest verdict
Will this get millions of users? Not on its own. No standalone pet-record app has ever passed one million installs through consumer distribution. The only one over 1M — PetDesk — is sold to vet clinics, who invite their clients. Everything that relied on the app store alone stalled between 100K and 500K.

2.2★
11pets — the incumbent, on 500K+ installs. Its own users are publicly leaving over a €69/yr paywall and a botched migration.
1M+
PetDesk — the only record app past a million. Distributed by vets, and owners can't edit their own data.
~$1.8M
Dogo's estimated ARR at 5M downloads. The realistic ceiling for a consumer pet app — and Dogo sells training, a stronger hook.
3–5
Apps the typical owner installs in sequence looking for one that does logging + records + sharing + documents. None does.
Will people pay? Yes — and the reviews say exactly for what: unlimited pets, a medical crisis (cancer, seizures, diabetes, post-surgery), removing ads, exports and documents that actually open, and supporting a visible developer who replies. They refuse when the price exceeds their daily-app benchmark ("$5 is a small fee. $50+ is ridiculous"), when basic multi-pet is the paywall, or when a static utility demands "yet another subscription."

What this plan is actually betting on
The gap is real and validated: nobody combines shared daily logging with a real health record, and users prove it by serially installing apps to assemble one. The incumbent has 500K installs, a 2.2★ rating, a documented data-export file, and no competitor offers an import path. The best-loved apps are single developers with 100K installs, broken sync, and no widgets.

Realistic target: 100K–300K installs and 3–6K paying households in 24 months, growing through ASO on high-intent queries, an "Import from 11pets" bridge, the household-invite loop (every user recruits their partner, kids, sitter), and later a vet/sitter channel. That is a durable indie business, not a unicorn. Plan for that, and let anything more be upside.

Three things must be boringly correct or none of the rest matters, because they are the three things every competitor gets wrong: sync between household devices, medication reminders that fire on time, and documents you can open at the vet counter with no signal.

What owners actually want
Evidence base: several hundred verbatim Play and App Store reviews scraped on 15 Sep 2026, plus Reddit recommendation threads. The loudest signal in the whole dataset is people being told to build a spreadsheet — the top-voted advice in r/LifeProTips for tracking pet vaccinations. That is a product opportunity stated as a workaround.

"I'm looking for something that can be transferable and used independently of a specific vet office."
r/Pets — the PetDesk critique, stated as a requirement by someone who has never used PetDesk
Jobs to be done, ranked by evidence
#	Job	Strength	Evidence
1	Don't let me screw up the medication or vaccine schedule	Very strong	Cancer/seizure/diabetes owners; "keeps us from making medication mistakes"; "don't rely on your vet to remind you"
2	Let my household see what's done so we don't double-feed	Very strong	"Six people plus two dogs in our pack"; whiteboards and spiral notebooks being replaced
3	One portable record not hostage to my vet or my app vendor	Very strong	11pets lockouts after 7+ years; PetDesk owners can't edit their own data
4	Produce the document someone is demanding, right now	Very strong	"During an emergency I could not retrieve the records, just when I needed them most"; boarding kennels; borders
5	Store the actual PDFs and photos — and let me VIEW them	Strong	PetNoter's #1 praise; PetnotePlus lets you upload but not open; DogNote's top request
6	Handle all my animals without charging per head	Strong	Fosters at 15-pet caps; 30 reptiles; rats; horses; livestock
7	Brief my pet sitter without writing a novel	Strong	Daycare staff, family sitters, multi-household switching
8	Show me trends so I catch problems early	Strong	Weight, seizures, glucose — a diabetic-dog owner said he'd pay for graphs
9	Survive the puppy phase	Moderate	Potty prediction; "big buttons are nice when you're half asleep"
10	Keep and enjoy the memories	Moderate	Monthly albums; gotcha day; archiving a pet that has died
11	Track what this animal costs me	Moderate	Expense trackers praised as a delighted surprise
12	Get me through pet-travel paperwork	Moderate (high value, low awareness)	UK→EU AHC costs £150–250 per trip with hard 10-day and 21-day windows
What converts, and what kills
They pay for
A specific medical crisis — cancer, seizures, diabetes, surgery recovery
Genuinely unlimited pets
Removing ads
Exports and a document vault that works
Graphs on custom metrics
Supporting a responsive indie developer — two users offered to donate unprompted
A one-time lifetime option — requested in five different apps' reviews, refused by all
They uninstall over
Any pet-count paywall — 1, 3, 5 or 15 pets. Every observed instance produced an uninstall or 1★
Price above the daily-app benchmark: "$5 is a small fee, $50+ is ridiculous"
Retracting free features or breaking a "lifetime" promise
Sync that needs an app restart; reminders 10–60 minutes late
Documents that upload but won't open; save buttons hidden behind the keyboard
A dead pet consuming a paid slot
Support email that bounces
Features people love anywhere
Monthly photo albums / growth timelines — the single most emotionally cited feature: "a picture from every month since I adopted my cat."
Likes and comments on logged events — turns a chore log into a family feed (DogLog).
Gotcha day alongside birthday.
Supply inventory that warns before you run out of food or a monthly preventative.
Widgets, Watch and Siri for one-tap logging — the Android widget is the most-requested missing feature in the category's best shared-care app.
Multi-household switching for sitters — the one good idea in an otherwise zero-traction app.
Barcode food scanning checked against logged allergies — genuinely novel (Petio).
Competitors
Store figures scraped live on 15 Sep 2026. The market is split cleanly into two camps — daily coordination with no records, and records with no daily loop — plus a vet-owned CRM. Nobody bridges them.

App	Camp	Play	iOS	Pricing	Why they lose
11pets	Records	2.2★ · 5.7K · 500K+	3.3★ · 77	€69/yr; gates reminders and multi-pet	Price shock on a "lifetime" base, data loss on migration, lockouts, "databasey" IA, ignored support
PetDesk	Vet CRM	4.8★ · 31.7K · 1M+	—	Free to owners; clinic pays	Owners can't edit anything; reminders won't clear; dies when you change vets
Pet Care Tracker (dogcat.app)	Both, thin	4.3★ · 6.4K · 100K+	—	Ads; unlimited pets free	Solo dev, dated UI, no time-of-day recurrence, no bulk actions. Wins ASO for all three key queries
DogLog	Daily log	4.5★ · 1.3K · 100K+	4.8★ · 1.3K	$3.99/mo · $39.99/yr	Sync needs an app restart; notifications 10–60 min late; 3-dog pack cap; dogs only
PetnotePlus	Records	4.5★ · 1.6K · 50K+	4.6★ · 15	5 pets free	Documents upload but can't be viewed; blocky UI; a reported total data-loss incident
PetNoter	Records	4.3★ · 148 · 10K+	4.2★ · 17	$14.99/yr · $59.99 life; 3 pets free	Document upload and export both paywalled; support email bounced
DogNote	Daily log	4.3★ · 256 · 10K+	4.7★ · 904	~$4.99/mo; 1 pet free	Android is a second-class citizen; no document vault; nag-to-rate
GreatPetCare (ex-Pawprint)	Records	2.3★ · 342 · 10K+	—	Premium + $10 per record request	Charges for what your vet gives free; broken uploads; save buttons off-screen
Notepet	Meds	2.6★ · 43 · 1K+	—	Sub	Alerts die past one medication; requires network
Who Fed The Dog?, I Fed the Pet, Kibbl, PetPilot, Pet Feeder	Feeding only	≤1K each	—	Mixed	Single-purpose toys; PetPilot/Fed? are iCloud-only (no Android); Kibbl on v1.0 since 2022
What nobody does well
Daily log + real health record in one app. The thesis, validated by users installing 3–5 apps in sequence.
Reliable multi-device sync for shared care. The best shared-care app's users describe the failure in painful detail.
Documents you can open. PetDesk can't save them, DogNote has none, PetnotePlus can't display them, GreatPetCare's save button is off-screen.
Purpose-shaped exports. Everyone ships "a PDF." Nobody ships a boarding vaccination certificate, an insurance claim pack, a new-vet handover or a sitter brief as distinct artifacts.
Pet-travel paperwork despite AHCs costing £150–250 per trip with computable date windows.
Bereavement. Archiving a deceased pet either isn't possible or consumes a paid slot.
Correct reminders. Late, dead past one med, won't clear, monthly firing daily, recurring tasks deleting their own future — across six apps.
Quiet hours. "Every 3 hours" wakes people at 3am.
Bulk operations. No "mark done for all pets," no shared medication across pets.
Android/iOS parity, and an 11pets import bridge — unclaimed.
What the giants can't do — and can
Chewy owns purchase and prescription fulfilment and will never be a neutral, exportable record. Rover's care log exists only inside a paid booking. Tractive has passive GPS/heart-rate data no phone app can fake — and can't hold a PDF or coordinate who gave the 8pm pill. Petco/PetSmart only work inside their own stores. The giants own transactions; none owns the portable, owner-controlled, multi-carer lifetime record, because that asset has no captive revenue stream. That is precisely the gap.

Product laws
Each of these is a direct answer to a documented failure in the category. They are not preferences; break one and the reviews already tell you what happens.

Unlimited pets in the free tier. Forever.
Six apps were punished for gating this. It is the sharpest wedge against the entire field and the one promise we never retract.
Archiving a pet that has died is free, dignified, and never consumes a slot.
No paywall, no upsell, no promotional notification for a memorialised pet. A paywall shown to a grieving owner ends up on social media.
Sync is near-real-time and attributed.
Every log line shows who and when. If your partner logs a feed, it appears on your phone before you can ask. Eventually-consistent-on-restart is broken at the premise.
Reminders are data, not alarms.
The schedule lives in the shared database; the OS holds only a rolling window of the next 7–14 days. Exact clock times, quiet hours, repeat-until-done, snooze/skip/gave-early, and an honest fallback when Android denies exact alarms.
Documents open offline, in-app, at the vet counter.
Stored locally after first sync, previewed inline, never "sent to your phone to view."
Exports are shaped for the job.
Boarding certificate, new-vet handover, insurance claim pack, sitter brief, travel readiness — distinct, named artifacts, not one generic PDF.
Every dropdown has a free-text fallback.
Rescue mixes exist; so do arthropods and horses. Species-scoped tasks never leak across animals.
Offline-first for reads and writes.
An emergency vet visit at 2am on a rural road is the moment the record must open. Notepet lost users to a network blip.
Never retract a free feature. Never break a lifetime promise.
The 11pets extinction event, in one line.
Reply to every review and support email within 24 hours.
Measurably a growth channel here — a fix within hours converted 1★ to 5★. It is also an Apple 1.2 obligation.
Never appeal to children in art or copy.
Cute is fine; cartoon mascot with big eyes in a bubbly font drags us into Play's Families policy and COPPA. Adult audience, 16+ terms.
Say "record," never "diagnose."
No dosage calculators, no "detects," no "vet-approved." Every AI or trend output carries "consult your vet." Health copy is a compliance surface.
Pricing & packaging
Anchored on what users named: $5 is small, $50+ is ridiculous; PetNoter's $14.99/yr is called "very reasonably priced"; DogLog's $39.99/yr draws "I can't spend that much." The category's most under-served demand is a one-time purchase. We offer it — it converts the subscription-fatigued, and Apple treats a non-consumable lifetime unlock as ordinary IAP.

Tier	Price	Includes	Why this line
Free	$0	Unlimited pets · daily log · household of up to 3 people · full health record · reminders · 20 documents · 1 export per month · widgets · archive	Everything the incumbent gates. Generous enough to earn 4.5★ and reviews; the free tier is the marketing budget.
Household (annual)	$29.99 / yr
€29.99 · £24.99	Unlimited household members & time-boxed sitter access · unlimited documents · all shaped exports · claim packs · travel readiness · monthly albums · custom typed fields & graphs · CSV	Under the $39.99 pain line, above PetNoter. Priced per household, never per pet or per person. 7-day trial.
Household (monthly)	$3.99 / mo	Same	Exists for the crisis buyer (post-surgery, new puppy). Annual shown first and largest.
Lifetime	$79.99 one-time	Same, forever, for this household	The demand nobody serves. ~2.7 years of annual; priced so annual remains the default choice.
Unit economics at the realistic target
4,000 paying households × ~$27 blended ≈ $108K/yr gross; at the 15% small-business tier on both stores ≈ $92K net, against ~$1.5K/yr of Supabase + PowerSync + CI. Photos are the only cost that scales; compress client-side and cap free-tier storage. Break-even on infrastructure at roughly 60 subscribers.

Paywall rules (both stores, non-negotiable): billed amount is the most prominent price (annual total larger than the "/month" equivalent); explicit auto-renew sentence; trial end shown as an exact date; Restore Purchases; Terms and Privacy links on the sheet; visible close control; CTA reads "Subscribe — $29.99/year"; cancellation reachable from Settings in two taps with a deep link to the store's subscription centre; no guilt screens. Enable Apple Family Sharing (irreversible, and a fit for a household product). Do not bother with EU alternative billing at launch — the fee stack nets to near zero at this scale.

Screen by screen
Every screen below is specified across the same seven facets so nothing is forgotten on either platform. Both means one Compose Multiplatform implementation; Android / iOS mark where a platform diverges. v1 ships at launch; v2 follows once sync and reminders are proven in the field.

First run & onboarding
Both
v1
Get a pet on screen in under 60 seconds and make the household invite feel like the point, not a setting. No account required to start.

UI
Three steps, one screen each: Add your pet (name, species, photo — everything else optional), Who else looks after them? (share-sheet invite link, skippable), Done — here's Today. Large photo well at top, one primary button, progress as three dots. Species picker shows dog, cat, then "Other" with free-text.
UX
Local-only until the user chooses to sync or invite. Never ask for notifications, camera or account here. "Import from 11pets / a spreadsheet" is a quiet link on step one. Disclaimer ("record-keeping tool, not a medical device — consult your vet") appears as one calm line on the final step, acknowledged by continuing.
Data
Creates Pet + a local Household with the device user as owner. Persists to Room immediately; sync begins only after sign-in.
Android
Photo via PickVisualMedia (Photo Picker — no permission) or ACTION_IMAGE_CAPTURE. Predictive back enabled between steps.
iOS
PHPickerViewController (no permission, no purpose string). Interactive swipe-back wired between steps — prototype in week 1.
Play
Prominent-disclosure screen is required before the first cloud upload, not here. Adult target audience; no child-directed art.
Apple
5.1.1(v): app must be usable without login. 2.1: reviewer must be able to reach everything — pre-seeded demo household in review notes.
Sign in & account
Both
v1
Sync and household sharing need an identity. Offered when the user first invites someone or turns on sync — never as a wall.

UI
Sheet with three equal buttons: Continue with Apple, Continue with Google, Continue with email (magic link with a 6-digit code fallback). One line: "Your local data stays; signing in just adds sync."
UX
Local pets merge into the new account, never overwritten. Email path avoids passwords entirely. If a user opens an invite link before signing in, we hold the invite and complete it after.
Data
Supabase Auth. User row + membership row. Sitters invited by link become full data subjects — serve them the privacy notice at join.
Android
Credential Manager for Google; Apple sign-in via web OAuth (works, slightly clunkier).
iOS
Native ASAuthorizationController (SwiftUI screen, not Compose). Store the Apple refresh token for later revocation.
Play
Account creation triggers the deletion requirement: in-app delete + public web URL declared in Data safety.
Apple
4.8: because we offer Google, Sign in with Apple is mandatory. 5.1.1(v): deletion in-app, with token revocation via Apple's REST API.
Today — the household feed
Both
v1
The daily-loop screen. Answers "what's done, what's due, who did it" in one glance for every pet, and offers one-tap logging.

UI
Top: pet avatar row (scrollable, tap to filter, long-press to reorder; drawn character avatars from the Motivoa rig). Then Due now cards (meds, feeds, treatments) with a big "Done" and a smaller "Skip / Later". Then the timeline: attributed entries — "Sara · fed Luna · 8:02". Persistent quick-log FAB. Sleepy-thumb targets ≥ 48dp everywhere.
UX
Overdue items rise and turn amber (with an icon — never colour alone). "Mark done for all pets" appears when a task is shared across animals. Pull to refresh is unnecessary: the feed is live. Empty-state on day one shows a pre-filled sample entry labelled as an example.
Data
Reads CareEvent (immutable, UUID, author, occurredAt) + materialised DueItems from schedule rules. Realtime subscription scoped by household_id.
Android
Glance widget mirrors the "due now" list. Notification channel "Household activity" for partner-logged events (default: silent).
iOS
WidgetKit small/medium widgets; Live Activity for an active dog walk is v2 and strictly functional (Apple 4.5.3).
Play
Content on this feed is UGC in a closed group: every entry needs a "Report" in its overflow menu.
Apple
1.2 applies; report + block reachable from here. 2.5.16: widget content must match this screen.
Quick Log sheet
Both
v1
Two taps from anywhere to "fed", "walked", "gave meds", "peed", "pooped", "weighed" — with optional detail, never required detail.

UI
Bottom sheet: pet selector (pre-selected if filtered), grid of large icon tiles, time chip defaulting to now (tap to backdate), optional note/photo/amount row collapsed. Custom event types appear after the defaults; user can pin and rename them.
UX
Tap a tile → logged → sheet closes → toast "Fed Luna · 8:02 · Undo". Retroactive entry, edit and delete via swipe on the feed. Amount fields remember the last value per pet. Species-scoped: a nail-trim tile never appears on the aquarium.
Data
Appends a CareEvent; conflict-free by construction. Edits write a new version row; deletes are tombstones. Photos compressed to ≤ 1600px before upload.
Android
Also launchable from the widget and from a notification action ("Done") without opening the app.
iOS
App Intents so Siri and interactive widgets can "Log that I fed Luna". Haptic on confirm.
Play
Camera via intent needs no permission; if an in-app camera is built later, CAMERA becomes a runtime permission with rationale.
Apple
Specific NSCameraUsageDescription string; generic strings are an automatic rejection.
Pet profile
Both
v1
The pet's home page: identity, essentials someone else might need in a hurry, and the doors into record, documents, journal and exports.

UI
Hero photo or drawn character (user's choice), name, species/breed (free text allowed), age computed from birthday and "gotcha day". Essentials card: microchip, weight (latest + trend arrow), allergies, insurer + policy number, vet contact. Tabs: Record · Meds · Documents · Journal. Overflow: Export, Share with sitter, Archive.
UX
Every field optional; every field editable in place. Multiple photos per pet. Weight unit per pet (kg / lb / g). "Emergency card" action shows a full-screen high-contrast summary for handing to a stranger or an ER vet.
Data
Pet with typed optional fields; Weight series; Contact rows for vets/insurers.
Android
Shortcut ("Open Luna") via ShortcutManager for multi-pet homes.
iOS
Spotlight indexing of pets; Dynamic Type must scale the essentials card to 200% without clipping.
Play
Breed reference art must be licensed or original — no scraped images; keep licence PDFs on file.
Apple
5.2.1 same; 2.3.8 screenshots of this screen must be 4+-appropriate (no wound photos).
Health record
Both
v1
The portable lifetime record: vaccinations with expiry, conditions, vet visits, labs, weight chart — owner-controlled, never hostage to a clinic.

UI
Sections in a single scroll: Vaccinations (name, given, due, batch; grouped names like "Lepto4" stay grouped; overdue badged), Visits (date, clinic, reason, notes, attached invoice), Conditions (active/resolved), Labs (value + unit + reference), Weight chart with tappable points. Add via a single "+" that asks what kind.
UX
Vaccination templates by species pre-fill names and typical intervals but never assert a schedule — copy says "typical interval; your vet decides." Edit without delete-and-recreate. Each visit can spawn a claim-pack export directly.
Data
Vaccination, Visit, Condition, LabResult, Weight — all with pet_id, author, source (manual / import). Vaccination protocol text is written by us, not copied from kennel clubs or manufacturers.
Android
Weight chart in Compose Canvas; tabular-nums for values.
iOS
Same Canvas chart; verify VoiceOver reads chart points via explicit semantics.
Play
Health apps declaration: answer "no health features" (form is human-oriented) and ship the not-a-medical-device disclaimer as a hedge. Do not touch Health Connect.
Apple
Nutrition Labels: declare as User Content, not Health (documented reasoning). Age-rating questionnaire: answer the medical/wellness question honestly → expect 13+.
Medications & schedules
Both
v1
Job #1. Courses that pause, taper and cycle; exact clock times; quiet hours; "gave it early" tolerance; visible to the whole household.

UI
Medication card: name, dose as text (never calculated), times-of-day chips, days pattern (daily / every N days / specific weekdays / monthly on date), start–end or ongoing, "Pause" toggle. Parasite preventatives are the same object with a monthly pattern and a supply counter ("3 doses left"). Adherence strip shows the last 14 days as dots.
UX
A due dose shows on Today for everyone; when one person marks it done, the others' reminder cancels within seconds. "Given early/late" keeps the true time. Snooze offers 15/30/60 min. Quiet hours default 22:00–07:00 per household, with an explicit "this one is critical — ring anyway" flag per medication.
Data
ScheduleRule rows (pet, item, times, pattern, window). Occurrences are derived, never stored as alarms. A DoseEvent is a CareEvent subtype.
Android
SCHEDULE_EXACT_ALARM only (never USE_EXACT_ALARM — upload blocked). Check canScheduleExactAlarms() before every schedule; WorkManager re-materialises the 7–14 day window; inexact fallback with an honest in-app banner if denied. Never request battery-optimisation exemption.
iOS
Hard cap of 64 pending local notifications: use UNCalendarNotificationTrigger for regular series, rolling window for the rest, re-materialise on foreground and BGAppRefresh. Long-horizon items (annual boosters) via server push as a backstop.
Play
Reminder-reliability disclaimer on this screen. No dosage calculator of any kind. Separate notification channel "Medication".
Apple
1.4.2 forbids dose calculators for anyone but manufacturers/hospitals — dose is a free-text field. 5.1.2(i): never require notifications to be enabled to use the app.
Reliable reminders — permissions & health check
Both
v1
The one screen that explains, asks and proves. Shown the first time a schedule is created; reachable from Settings forever.

UI
A short explanation in the pet's name ("So we can remind you about Luna's 8pm tablet"), a single primary button per permission, and a live status card: notifications on/off, exact alarms granted/denied, battery restrictions detected, "next 3 scheduled reminders" listed with times. A "Send test reminder in 10 seconds" button.
UX
Contextual ask, never on cold launch. Pre-prompt before the system dialog. If denied twice, the screen shows a deep link to system settings and what will degrade ("reminders may arrive up to 15 minutes late"). The self-check runs on every foreground and surfaces a dismissible banner if the OS has silently revoked something.
Data
Stores permission state snapshots so support can see what a user's phone actually allowed. No personal data.
Android
POST_NOTIFICATIONS runtime ask; ACTION_REQUEST_SCHEDULE_EXACT_ALARM; listen for ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED. Port Motivoa's ReminderHealthProbe pattern.
iOS
requestAuthorization once, after the pre-prompt; consider .provisional for low-stakes household-activity nudges; show the 64-slot budget only in a debug menu.
Play
Restricted-permission declarations must match the manifest; USE_EXACT_ALARM is limited to alarm-clock/calendar apps and we do not qualify — submit a pre-launch policy question to confirm.
Apple
No UIBackgroundModes we don't genuinely use. 5.1.2(i): notifications are optional to use the app.
Documents vault & viewer
Both
v1
Vet invoices, prescriptions, lab PDFs, insurance policy, microchip certificate — stored, previewed, openable offline, and linkable to visits. The most consistently botched high-value feature in the category.

UI
Grid of document cards with thumbnail, title, date, tags (Invoice · Prescription · Lab · Insurance · Vaccination · Other), linked visit. Full-screen viewer with pinch-zoom, page indicator, share and "Add to export". Capture flow: camera with edge-crop or pick a PDF/image.
UX
Save button is always above the keyboard and never behind system bars (a GreatPetCare reviewer diagnosed their CSS — we don't get that review). Documents download to the device on first sync and stay cached. Title is auto-suggested from date + tag. Multi-select to attach several to one claim.
Data
Document row (pet, tag, visit_id, storage path, size, sha256) + object in Supabase Storage under household_id/, private bucket with signed URLs. Free tier 20 docs; paid unlimited. Client-side compression for images; PDFs stored as-is (≤ 20 MB).
Android
ACTION_OPEN_DOCUMENT (SAF) for PDFs — no storage permission. In-app PDF rendering via PdfRenderer. Strip READ_MEDIA_* from the merged manifest.
iOS
Native QLPreviewController hosted inside Compose for the viewer — better than re-implementing. PHPickerViewController for images, UIDocumentPickerViewController for PDFs.
Play
Prominent disclosure + affirmative consent before the first document leaves the device. Data safety: "Files and docs", encrypted in transit.
Apple
Privacy manifest reason codes for file-timestamp and disk-space APIs. Nutrition Label: User Content → Photos/Videos and Other User Content.
Household & members
Both
v1
Who can see and log for these pets. Invite by link, roles, removal — and the Apple 4.3(b) answer: genuinely uncommon multi-user real-time sharing.

UI
Member list with avatar, role chip (Owner · Member · Sitter), last active. "Invite" opens the system share sheet with a link and a short message. Pending invites listed with expiry and a revoke button. Per-member overflow: change role, remove, report.
UX
Invitee taps the link → app opens (or store, then app) → sees "Sara invited you to look after Luna and Milo" → accepts terms → joins. No contacts access, ever. Owner leaving must transfer ownership or dissolve the household — the flow is explicit, since account deletion depends on it.
Data
Membership (user, household, role, expires_at). Invite tokens: single-use, 7-day expiry, revocable, validated server-side. Postgres RLS: every table filtered by household_id membership.
Android
Verified App Links via assetlinks.json. Never READ_CONTACTS (Contact Picker policy from Jan 2027 anyway).
iOS
Universal Links via apple-app-site-association; no custom URL schemes (hijackable).
Play
UGC in a closed group: in-app report for content and users is explicitly required. Terms accepted before first upload; consent logged with version + timestamp.
Apple
1.2 has no private-group exemption: report, block, filtering and published contact info are all required. Invite model is identified and consented — not "anonymous chat".
Sitter mode & the sitter brief
Both
v1
Job #7. A time-boxed guest who sees exactly what to do today, can log it, and loses access automatically when you're home. Also the feature that turns every holiday into a referral.

UI
For the owner: "Add a sitter" → dates → what they may see (care schedule always; health record and documents optional) → link. For the sitter: a simplified Today with the routine as a checklist, feeding amounts, medication instructions, vet and emergency contacts, "House notes", and a big "Something's wrong — call owner" button. Professional sitters get a household switcher.
UX
The sitter never sees the paywall, never gets marketing, never needs to create an account beyond a name and email. Owner sees a live feed of what the sitter logged. Access ends at the chosen time; the sitter is told when.
Data
Membership.role = SITTER with expires_at enforced in RLS, not just UI. Sitter brief is also exportable as a PDF for non-app sitters.
Android
Sitter-role widget shows only today's checklist.
iOS
Same; Live Activity for "walk in progress" is v2.
Play
Time-limited access is data minimisation — say so in the privacy policy. Sitter is a data subject: privacy notice at join.
Apple
Named in review notes as the differentiator (4.3(b) defence). Demo: provide a second reviewer account already joined as a sitter.
Exports — shaped for the job
Both
v1
Claim pack v1 · Travel v2
Job #4. Not "a PDF" — five named artifacts, each laid out for the person who will read it.

UI
Export picker as cards: Boarding certificate (vaccinations with expiry, microchip, vet, emergency contact), New-vet handover (full record, chronological), Insurance claim pack (selected visit + itemised invoice + relevant history + condition timeline + proof-of-payment slot), Sitter brief, Full backup (CSV + JSON + documents zip). Date range and pet selection on each. Preview before share.
UX
One tap from a visit → claim pack pre-filled with that visit. Generated on-device; shared via the system sheet; nothing is emailed by us. Footer on every PDF: "Generated by the pet owner from self-reported records. Not a medical document. Consult your veterinarian." Free tier: one export per month; paid: unlimited.
Data
Pure ReportBuilder in common code (port Motivoa's builder/generator split) → PdfKmp renderer, or expect fun render() with platform actuals as fallback. Tagged/accessible PDFs are an explicit engineering task (EAA).
Android
android.graphics.pdf.PdfDocument actual (already proven in Motivoa). Share via FileProvider.
iOS
UIGraphicsPDFRenderer actual; share via UIActivityViewController.
Play
Export is a digital feature — gating it behind the paid tier must go through Play Billing. No insurer logos without permission.
Apple
3.1.1 same. Never take referral fees from insurers without regulatory advice (FCA/IDD) — neutral export only.
Travel readiness
Both
v2
Job #12 and a feature no competitor offers: compute the UK↔EU (and later US/CA) date windows from records we already hold.

UI
Pick destination and travel date → a checklist with computed status: microchip before rabies vaccine ✓/✗, rabies ≥ 21 days before travel, AHC must be issued within 10 days of travel (shows the window), tapeworm treatment 24–120 h before UK return, valid-vaccination expiry. Each item links to the record that satisfies it or a "Add record" button.
UX
Framed as "paperwork readiness", never as legal advice; each rule shows its source and "rules change — confirm with your vet and gov.uk". Reminder offered for the AHC appointment window.
Data
Rules as versioned data (country pair → constraints), updatable remotely without an app release. Pure functions, fully unit-tested in commonTest.
Android
—
iOS
—
Play
Content is informational; keep the disclaimer. No government logos.
Apple
2.3.1: don't ship this dormant — either it's in the build and screenshots, or it isn't.
Journal & monthly album
Both
v1
Albums paid
Job #10 and the most emotionally cited feature in the category. This is what converts a chore log into something nobody deletes.

UI
Chronological photo feed per pet with captions; auto-generated monthly album pages ("Luna · March 2027", weight that month, notable events); "On this day" card on Today; birthday and gotcha-day markers; household members can react and comment on entries.
UX
Photos taken in Quick Log flow here automatically. Comments are threaded and attributed (DogNote's "can't tell who wrote what" complaint). Albums exportable as a PDF booklet (paid).
Data
JournalEntry = CareEvent with media; Reaction, Comment rows. Photos private to the household.
Android
Coil 3 with disk cache; Photo Picker for imports.
iOS
Same; shared photo-picker code path.
Play
UGC: report on every photo and comment; block member.
Apple
1.2 same. Age-rating "social media" questions: private, invite-only feed → answer no, and be ready to justify it.
Paywall
Both
v1
One shared Compose paywall driven by RevenueCat offerings, identical on both platforms, built to pass Apple 3.1.2 and Play's subscription policy on the first submission.

UI
Header names the household ("Everything for the Ahmed household"). Three plan cards — Annual pre-selected and visually dominant with the full billed price largest ("$29.99 / year"), monthly, lifetime. Feature list as short verbs. Beneath the CTA, plain text: trial length and exact end date, renewal sentence, cancel instructions. Footer: Restore Purchases · Terms · Privacy. Close control top-right, always visible.
UX
Appears only when the user hits a paid boundary ("Add a 4th household member", "21st document", "second export this month") with that boundary named. Never on launch, never after a memorialised pet, never to sitters. Purchase success is a quiet confirmation, not a celebration screen. Cancellation is two taps from Settings.
Data
RevenueCat entitlement "household" cached locally; entitlement is per household — one purchase unlocks every member's device.
Android
Play Billing 8 via purchases-kmp. Deep link for cancellation: play.google.com/store/account/subscriptions?sku=…&package=….
iOS
StoreKit 2 via purchases-kmp; Introductory Offer configured in App Store Connect for the trial; Family Sharing enabled (irreversible); Restore wired.
Play
Disclose terms, price, cycle, auto-renew "without additional action"; no SKU named "Free Trial"; localised prices everywhere; never reduce benefits after the first period; no dismiss-less prompts.
Apple
3.1.2: billed amount most prominent; trial duration + post-trial price; Restore; ToU + Privacy links in-app and metadata; Small Business Program enrolment (15%).
Settings, privacy & account deletion
Both
v1
The compliance screen that also earns trust: theme, units, quiet hours, notification channels, data export, delete account, disclaimers, support.

UI
Grouped list: Household (quiet hours, default units, first day of week), Notifications (per-channel toggles + reminder health check), Appearance (explicit light/dark/system toggle), Data (Export everything, Import, Storage used), Subscription (manage/cancel — two taps), Privacy (policy, terms, community guidelines, "Delete account"), Help (support email, in-app message, about, disclaimer).
UX
"Delete account" asks what to do with the household (transfer to another member or dissolve), then confirms once, then deletes — no survey, no guilt. "Export everything" produces the full backup zip first and offers it. Support messages get a promised 24-hour reply.
Data
Deletion removes user, memberships, and — if dissolving — all household data and storage objects; processors instructed to delete; retention exceptions named in the policy. Consent log kept 3 years (California ARL).
Android
Public deletion web page URL declared in Data safety; same page linked here.
iOS
Revoke Sign in with Apple token on deletion; optional Privacy Choices URL points to the same page.
Play
Both in-app deletion and a public, login-free, direct web link are required. Privacy policy must be HTTPS, non-PDF, public, and name the company and app.
Apple
5.1.1(v): a real in-app control, not a mailto. 1.2: published contact info. Standard EULA link at the end of the store description.
Archive & memorial
Both
v1
Law #2. A pet that has died leaves Today, keeps every record and photo, never counts against anything, and is never used to sell.

UI
From the profile overflow: "Luna has passed away" → date → a calm confirmation. The profile becomes a memorial: hero photo, years together, the album, the full record still readable and exportable. Muted palette; no badges, no streaks. An "Archived" section at the bottom of the pet row, collapsed by default. Also a plain "Archive (rehomed / no longer in my care)" variant.
UX
All reminders for that pet cancel instantly across the household. No promotional notifications ever again for that pet. Restoring from archive is one tap. Export + delete offered without pressure.
Data
Pet.status = MEMORIAL | ARCHIVED, passed_on. Excluded from due-item materialisation.
Android
Widget drops the pet from its rotation.
iOS
Same; Spotlight entry updated.
Play
Not a policy surface — a reputation one. Never gate.
Apple
Screenshots of this screen must stay tasteful (2.3.8). If a memorial page is ever shareable publicly, it becomes public UGC and needs moderation.
Lost-pet poster
Both
v2
Data we already hold — photo, microchip, description, contacts — assembled into a printable poster and a shareable page in the worst hour of an owner's year.

UI
"Luna is missing" → confirm last-seen area (free text, no GPS) → choose photo → poster preview (A4/Letter PDF + square social image) → share sheet + optional public link with a "Found" form.
UX
Zero paywall. Contact shown is a choice (phone, email, or the app's relay). Link expires in 90 days and is revocable; "Luna is home" closes it and thanks the sharers.
Data
Poster page rendered server-side from a snapshot; free text length-limited and URL-stripped; image passes safe-search moderation before going live; rate-limited per account; noindex.
Android
Save poster to Photos via MediaStore (no permission on API 29+).
iOS
NSPhotoLibraryAddUsageDescription with a specific string, only if we save to the library.
Play
Public UGC: in-app report on the poster, moderation, revocability. No location permission — we never use GPS.
Apple
1.2 public-UGC obligations; 5.1.1(viii) — never compile a "found pets" directory from public data.
Widgets, Watch & Wear
Android
iOS
Widgets v1
Watch/Wear v2
The most-requested missing feature in the category's best shared-care app, and a concrete Apple 4.3(b) differentiator. The one place the view layer is written twice.

UI
Small: one pet — "Fed 3h ago · Meds 8pm" with a one-tap Log button. Medium: due-now list for the household. Large: today's timeline. Sensible redacted placeholder for the widget gallery.
UX
Interactive: tapping "Done" logs without opening the app and updates every household member's widget within seconds. Works for the free tier — a widget that is only an upsell risks rejection.
Data
Both widgets read the same Room database the app writes (Android: direct; iOS: App Group container). Formatting logic shared in Kotlin; only the view is native.
Android
androidx.glance 1.2 — port Motivoa's StreakWidget pattern and WidgetTheme contrast rule. Wear OS tile in v2 (must be 64-bit, mentioned in listing).
iOS
WidgetKit extension in SwiftUI calling the KMP framework via App Group; App Intents for interactivity; watchOS complication "last fed" in v2 (SwiftUI; entitlement synced from phone).
Play
Wear quality guidelines apply if shipped; tiles/complications must be mentioned in the listing.
Apple
2.5.16 widgets relate to app content; 3.1.7 no ads in extensions; 3.1.2(a) subscription must work on Watch (sync entitlement, no Watch paywall).
Import — from 11pets, spreadsheets and rivals
Both
11pets v1
An unclaimed acquisition channel: 500K+ installs at 2.2★, thousands publicly leaving, an official export file, and no parser anywhere.

UI
"Switch from another app" → pick source (11pets export, PetnotePlus CSV, generic CSV, Pet Parents) → file picker → mapping preview (pets found, records found, anything we couldn't read) → import → summary. Also a marketing landing page "Leaving 11pets? Bring 7 years of records with you."
UX
Never silently drop a field — unmapped data lands in notes with its original label (GreatPetCare's "duplicated and incorrect" migration is the anti-pattern). Weight units are verified, not assumed (11pets' own bug). Import is reversible for 7 days.
Data
Parsers in commonMain, fuzz-tested against real export samples gathered in beta. Source recorded on every imported row.
Android
SAF ACTION_OPEN_DOCUMENT — no storage permission.
iOS
UIDocumentPickerViewController; also accept "Open in…" from Files/Mail.
Play
Never put "11pets" in the title, keywords or description — competitor-brand stuffing is a metadata violation and a takedown trigger. The landing page lives on our website, not the listing.
Apple
4.1 / 5.2.1 same; no competitor names in metadata. Fine to support their file format.
Report, block & community guidelines
Both
v1
Not a feature anyone asked for; a feature both stores require the moment two people share a feed. Build it once, wire it everywhere.

UI
"Report" in the overflow of every photo, note, comment and log entry; "Report member" and "Remove & block" on every member row; a short reason picker; confirmation with the 24-hour commitment. Community guidelines page reachable from Settings and the website.
UX
Reporting never leaves the app. Blocked identities cannot be re-invited. The household owner can purge a removed member's contributions or keep them attributed.
Data
Report rows → an abuse inbox with SLA tracking. Lightweight server-side image scan on upload; full moderation only on public surfaces (lost-pet pages).
Android
—
iOS
—
Play
UGC policy for closed groups: in-app reporting for content and users; terms accepted before creating content; objectionable content defined.
Apple
1.2: filter, report, block, published contact info, timely response — no private-group exemption.
Beyond the field — features nobody has yet
Supply counter that reorders itself into a reminder
"3 flea doses left" → "Order more" reminder 10 days before you run out. Praised in 11pets' one surviving positive review; nobody else has it.
Vet-visit prep sheet
Before an appointment: one page of what changed since last visit — weight delta, new symptoms logged, adherence gaps, questions you noted. Rules-based in v1; on-device AI summary in v1.1 where the device supports it, with "consult your vet" inline.
Professional mode for sitters and walkers
One account, many households, a today-list across all of them. Turns every professional into a distribution channel to every client they have.
The drawn companion
Motivoa's Canvas creature rig — nine species, coats, moods, zero assets — becomes an optional avatar that reflects the pet's day: content after a walk, expectant near meal time. Adult art direction, never a mascot. A delight layer no record app has.
Trends that speak plainly
"Milo has lost 6% since March" as an observation, never a diagnosis. Graphs on any custom metric — the feature a diabetic-dog owner said he'd pay for.
Wearable bridge (v3)
Import Tractive activity/sleep as read-only context on the timeline — a real user asked 11pets for exactly this.
Architecture & stack
Kotlin Multiplatform with Compose Multiplatform for every screen except three that are better native on iOS: Sign in with Apple, the document viewer (QLPreviewController), and long-form text entry until CMP's native text input leaves experimental. Widgets are the one deliberate double-write. Everything platform-specific hides behind an interface in a core-* module; feature modules contain no expect/actual at all.

Concern	Choice	Version	Risk	Why
UI	Compose Multiplatform	1.12	Low	iOS stable since 1.8; concurrent rendering default. Accessibility is manual — every custom-drawn thing needs semantics{}.
Navigation	Navigation 3 (multiplatform)	1.1.1	Medium	Google's direction; hand-register NavKey serializers off-JVM. Fallback: Decompose if iOS swipe-back fights us in the week-1 spike.
Local DB	Room KMP	2.8.x	Low	Same migration discipline as Motivoa's 12 versions; iOS via bundled SQLite driver. Stay off androidx.room3 until after first ship.
Prefs	DataStore Preferences	1.2.x	Low	One expect for the file path.
Backend	Supabase — EU region	supabase-kt 3.x	Medium	Auth (Apple + Google native), Postgres with RLS by household_id, Storage, Realtime. EU residency is the decisive GDPR win over Firebase.
Sync	PowerSync	1.13.x	Medium	Offline-first queue, retry, partial sync between Room/SQLite and Postgres. The insurance policy against hand-rolling an outbox.
Billing	RevenueCat purchases-kmp	≥3.0	Low	Bundles the iOS SDK (no CocoaPods). One shared Compose paywall driven by Offerings; skip the iOS-only paywall UI.
Reminders	Alarmee + own scheduler	—	Medium	Repeating API fits medication; still write the exact-alarm consent dance ourselves.
Push	FCM + APNs (thin actuals)	—	Low	Only for "your partner logged a dose" and long-horizon backstops — never the primary medication reminder.
Files & images	FileKit · Coil 3 · Peekaboo	—	Low	Native pickers, no storage permissions; client-side compression.
PDF	PdfKmp, with platform-actual fallback	1.x	Medium	Young library; Motivoa already proves the Android PdfDocument actual.
DI	Koin + annotations	4.1	Low	Shortest bridge from Hilt for a solo dev.
Dates	kotlinx-datetime	1.0	Low	Locale-aware formatting needs a 30-line expect (Motivoa's DateFormats documents why).
i18n	Compose Resources	—	Low	Same strings.xml shape as Motivoa; re-point the audit scripts. Launch locales: en, de, fr, es; add ar, hi later.
Crash / analytics	Sentry KMP · Aptabase	—	Low	No IDFA, so no ATT prompt; no Firebase Analytics.
On-device AI	Interface first; ML Kit GenAI + Apple Foundation Models actuals	v1.1	Medium	Rules-based default for 100% of users; AI is progressive enhancement. On-device = not "collected", no third-party-AI disclosure.
CI / iOS builds	Codemagic (+ a used Mac mini)	—	High	You cannot build, sign or upload iOS from Windows. CI ships; only a Mac diagnoses (crashes, VoiceOver, screenshots).
Module graph
shared/
  core-model         pure data classes — everything depends on this, it depends on nothing
  core-common        Result, dispatchers (expect), Clock, UUID (expect)
  core-datetime      kotlinx-datetime + expect locale formatting
  core-designsystem  palette (accentFill rule), type, motion tokens, atoms   ← Motivoa
  core-database      Room 2.8 entities/DAOs + expect builder (App Group path on iOS)
  core-network       Ktor + Supabase client
  core-sync          PowerSync schema, event model, conflict policy
  core-notifications expect Scheduler — rolling-window materialiser
  core-billing       RevenueCat wrapper, Entitlement
  core-ai            interface AiEngine + UnsupportedAiEngine (default)      ← Motivoa
  core-files         FileKit, compression, expect share sheet
  feature-pets · feature-log · feature-reminders · feature-household
  feature-record · feature-documents · feature-export · feature-paywall
  app-ui             Nav3 graph, scaffold, theme host
androidApp/          Application, Glance widgets, FCM service
iosApp/              SwiftUI shell, ComposeUIViewController, WidgetKit, Sign in with Apple, APNs
The two designs that decide whether this works
Sync: an append-only event log
A care log is append-mostly. Every log line is an immutable CareEvent with a client UUID, occurred_at, author_id, household_id.
Inserts merge by union — no conflicts possible. Edits are new version rows; deletes are tombstones; last-writer-wins only on the rare same-row edit.
Mutable things (pet profile, schedule rules) are few and low-contention; LWW with updated_at is acceptable.
PowerSync syncs WHERE household_id IN (my memberships); RLS enforces the same server-side so a revoked sitter's device stops receiving.
Target: a partner's log appears on your phone in < 3 s on Wi-Fi; offline writes queue and reconcile without user action.
Reminders: schedule as data, OS as cache
ScheduleRule rows are the truth. Occurrences are computed, never stored as alarms.
A materialiser keeps a rolling window (7 days Android, ≤ 40 slots iOS) of OS notifications; re-runs on foreground, background refresh, reboot, timezone change, and after any household member marks a dose done.
Android: SCHEDULE_EXACT_ALARM when granted, inexact + visible banner when not; WorkManager drives re-materialisation.
iOS: calendar triggers for regular series; server push as a backstop for long-horizon boosters.
A self-check (Motivoa's ReminderHealthProbe) compares what should be queued with what the OS says is queued, and tells the user when they differ.
What Motivoa gives us
The existing Android app is 311 files, single-module, Hilt + Room 2.8 + Compose. Its most valuable parts are already KMP-shaped. The audit found five things worth carrying over, in order of value per line.

The Canvas creature rig
CompanionChibi.kt and friends: 1,130 lines of pure DrawScope on a 256-unit artboard, zero assets, zero Android imports, nine species with coats, a face rig, outfits, and ~20 unit tests. Drops into Compose Multiplatform Canvas unchanged. For a pet app it is almost literally the product's delight layer.
The CompanionAiEngine interface
Already models Ready / NeedsDownload / Unsupported with a streaming reply and a first-class "no AI" object. Rename it, add an iOS actual over Foundation Models, and the on-device AI architecture is done.
The accentFill palette contrast system
Any of 13 colours works in either theme because a measured WCAG rule (never fill with accent; use accentDeep in light) is pinned by PaletteContrastTest. Pure Compose; the widget theme derives the same rule.
Room migration discipline
Twelve versions with idempotent hasColumn() guards, exported schemas under version control, migration tests, and the seedMutex fix for a real double-seed race. For health records where data loss is unforgivable, the discipline matters more than the code.
Reminder policy, Strings, DateFormats
Distinct notification IDs, distinct PendingIntent request codes, a health probe that asks the OS what's queued; and the i18n rule that engines look up ids and never concatenate sentences. Policy, not code — it survives the port intact.
Sized blockers if code is ported rather than re-written: 108 files reference R.* resources, 45 use java.time, 39 take a Context, 28 carry Hilt annotations. Recommendation: start the pet app as a fresh multi-module KMP project and copy in the five assets above, rather than porting Motivoa wholesale. The Motivoa modularisation happens later, on its own timeline, and can borrow the module graph from this project.

Store compliance
Condensed from the full policy audit (15 Sep 2026, against Apple's App Review Guidelines and Google Play's Developer Policy Center). The eight things most likely to bite, then the checklist. Anything marked verify comes from secondary sources or ambiguous policy text.

Risk	Store	Severity	Answer
Medication reminders vs Android exact alarms — we don't qualify for USE_EXACT_ALARM	Play	Critical	SCHEDULE_EXACT_ALARM + consent flow + honest inexact fallback. Spike this before any feature work.
Paywall disclosure (3.1.2) — the most common subscription rejection	Apple	Critical	Price most prominent, renewal sentence, trial end date, Restore, ToU + Privacy on the sheet.
Health apps declaration is mandatory for every app; pet health isn't addressed	Play	High	Declare "no health features", ship the disclaimer anyway, keep written reasoning. Verify.
Household sharing is UGC — no private-group exemption	Both	High	Report on every item, report/block member, terms before first upload, 24-h abuse inbox.
12 testers / 14 days closed test for new personal Play accounts	Play	High	Register as an Organization (D-U-N-S, ~2 weeks) — skips it entirely.
EU DSA trader status must be declared or the app is removed in the EU	Both	High	Business address + phone (publicly shown). Never a home address.
European Accessibility Act — law, not store policy	EU	High	VoiceOver/TalkBack labels, 200% type, 4.5:1 contrast, 48dp targets, accessibility statement. Microenterprise exemption likely — verify per country.
Apple 4.3(b) (June 2026): "indistinguishable" apps in crowded categories rejected or removed	Apple	High	Ship household sharing, sitter access, interactive widgets and claim-pack exports in v1; say so in review notes; keep shipping.
Both stores
Legal entity + D-U-N-S; enrol as Organization on both ($99/yr Apple, $25 Play)
DSA trader declaration with business contact details
Privacy policy (HTTPS, non-PDF, names company + app, retention + deletion), Terms (16+ minimum, auto-renew terms, no-vet-advice), Community Guidelines, accessibility statement, support email + URL
Public login-free account-deletion page; in-app Delete Account; household-owner deletion semantics defined
Paywall: all 3.1.2 / Play subscription elements; two-tap cancel from Settings; CTA states the obligation to pay
Report content / report user / block; consent log with ToS version
Disclaimers: first run, Settings, every export footer, every trend or AI output; reminder-reliability note on medication
No dose calculators; banned words: diagnose, treat, prescribe, clinically proven, vet-approved, detects
No competitor names in metadata; no price/promo words, emojis, "#1" or CTAs in title/icon/screenshots; licences on file for all art
Trademark clearance on the final name in US / EU / UK / CA
Google Play
Target API 36; 16 KB page-size audit of every native
.so
(SQLite, PDF, image codecs); Billing Library 8+; AAB + Play App Signing
Data safety form consistent with privacy policy and observed behaviour, incl. SDKs
Health apps declaration; Target audience adults only,
not
Designed for Families; IARC rating; Ads = none; App access credentials
Manifest:
SCHEDULE_EXACT_ALARM
only; no
READ_MEDIA_*
, no
READ_CONTACTS
, no location, no battery-optimisation exemption
Prominent disclosure before first cloud upload; contextual
POST_NOTIFICATIONS
with channels
AI-generated content declaration for any AI-made listing asset
Apple
Xcode 26 / iOS 26 SDK; deliberate Liquid Glass decision;
PrivacyInfo.xcprivacy
with reason codes (UserDefaults CA92.1, file timestamp, disk space); Privacy Report diffed against Nutrition Labels
Nutrition Labels: pet records as User Content, not Health (reasoning documented)
Age rating incl. medical/wellness and social-media questions → expect 13+; not Kids Category
Sign in with Apple (mandatory given Google) + token revocation on deletion
Specific purpose strings;
PHPicker
so no photo-library string; no unused keys
Review notes: two demo accounts already in one household, seeded pets, live invite link, numbered walkthrough, "no location/contacts", IAP sandbox note
Small Business Program; Introductory Offer for the trial; Family Sharing enabled (irreversible)
Rolling 64-notification window; no ads in widgets; Live Activities strictly functional
Roadmap
Sequenced so that each phase is independently shippable and the two make-or-break systems — sync and reminders — are proven on real phones before anything decorative is built. Durations assume one developer at roughly 25 focused hours a week.

Weeks 0–3
Phase 0 — Foundations & spikes
Register the entity, apply for D-U-N-S, enrol both developer programs as an Organization, secure the name and domain, trademark search.
Buy or rent the Mac; set up Codemagic; produce one signed iOS build of a hello-world KMP app end to end — the single biggest practical blocker.
Spike A: Nav3 on iOS with swipe-back. Spike B: Room KMP + PowerSync + Supabase EU round-trip between two devices. Spike C: exact-alarm consent flow on Android 14/15/16 and the iOS 64-slot materialiser.
Submit the Play policy question about USE_EXACT_ALARM eligibility; draft privacy policy and terms.
Weeks 4–13
Phase 1 — The daily loop (private beta)
Onboarding, sign-in, Today feed, Quick Log, pet profile, household invites with roles, medications & schedules, reminder health screen, Settings with deletion.
Glance widget + WidgetKit small/medium. Copy in the Motivoa design system, creature rig (as avatar), AI engine interface with the Unsupported default.
Closed beta: 30 households, at least 10 with two or more members and one medicated pet. Measure sync latency, reminder punctuality, crash-free rate. Nothing ships until reminders are boringly correct.
Weeks 14–22
Phase 2 — The record (launch)
Health record, documents vault + viewer, journal, archive & memorial, exports (boarding, new-vet, claim pack, sitter brief, backup), paywall, report/block, 11pets importer.
Compliance pass against the checklist; App Review notes; store listings in en/de/fr/es; ASO on "pet care tracker", "shared dog feeding tracker", "pet vaccination records"; the "Leaving 11pets?" landing page.
Launch both stores together. Reddit community launch in the dogcat.app style — ask what's missing, reply to everyone.
Months 6–9
Phase 3 — Retention & reach
Sitter professional mode and multi-household switcher; travel readiness; supply counters; vet-visit prep sheet (rules-based, then on-device AI where available); comments and reactions; lost-pet poster.
Apple Watch and Wear OS quick-log; App Intents / Siri.
Begin the vet and sitter channel: a one-page "share the app with your clients" kit; explore a read-only clinic export link.
Month 9+
Phase 4 — Only if the numbers say so
Web portal (users love iPad + web sync); wearable-data bridge; food barcode scanning against allergies; additional locales (ar, hi); EU alternative billing only past ~$500K ARR.
Risks & open items
Highest risks
Reminder reliability on Android if users deny exact alarms at scale — mitigated by the fallback and the health screen; re-architect to push-primary if beta shows >30% denial.
iOS quality from a Windows developer — text input, VoiceOver, swipe-back. Mitigated by the Mac, the week-1 spikes and three native screens.
Small-maintainer libraries (Alarmee, PdfKmp, Peekaboo, supabase-kt) — each wrapped behind our own interface so a swap is one file.
Apple 4.3(b) — cannot be resolved in advance; differentiate visibly in v1 and keep shipping.
Distribution ceiling — the honest verdict above. The plan is sized for 100–300K installs; anything above requires the vet/sitter channel to work.
Decisions still open
Name. "Pet Care Hub" is descriptive and may not clear trademark. Shortlist and clear before Phase 1 ends.
Species scope at launch. Recommended: dog + cat templates, with "Other" fully supported (free text, grams) from day one — exotics keepers are vocal and under-served.
Lifetime price ($79.99 proposed) and whether the free tier's document cap is 20 or 50.
Legal review for EU launch: GDPR processor agreements, EAA exemption per member state, consumer-law renewal notices.
Motivoa timing. Finishing Motivoa's billing and Play submission is weeks of work and should land before Phase 1 starts, so both apps aren't half-shipped at once.
Sources
Primary and official sources used in the research behind this plan. Store figures are point-in-time (15 Sep 2026).

Apple App Review Guidelines
Apple — Auto-renewable subscriptions
Apple — App privacy details
Apple — Offering account deletion
Apple — Privacy manifest files
Apple — Guideline updates, 8 Jun 2026 (4.3(b), 4.5.3)
Apple — Updated age ratings
Apple — EU DSA trader status
Apple — Small Business Program
Play — Subscriptions policy
Play — User Data policy
Play — Account deletion requirements
Play — User Generated Content policy
Play — Health apps declaration
Play — Health Content and Services
Play — AI-Generated Content policy
Play — Photo & Video permissions
Play — Sensitive permissions & APIs
Play — Closed testing for new personal accounts
Play — Target API level requirements
Play — Expanded billing choice & fees (Jun 2026)
Android — Exact alarms denied by default
Compose Multiplatform 1.11 · Navigation 3 in CMP
Room for KMP · supabase-kt · PowerSync Kotlin
RevenueCat purchases-kmp · Alarmee · FileKit · PdfKmp
Codemagic pricing · Kotlin/Native compile times
11pets (Play) · 11pets data export
PetDesk · Pet Care Tracker · DogLog
PetNoter · DogNote · PetnotePlus · GreatPetCare
Dogo (Sensor Tower) · Pawprint (Crunchbase)
r/Pets — tracking medical history · r/LifeProTips — vaccination spreadsheet
RVC — Animal Health Certificate · Bankrate — claim denials