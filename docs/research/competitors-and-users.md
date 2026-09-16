I have comprehensive primary data. Here is the full report.

---

# Pet Care Hub — Competitive & User Research Dossier
**Research date: 15 September 2026.** All store figures below were scraped directly from live Google Play listing pages and the Apple iTunes Lookup API on that date, so they are point-in-time primary data, not secondhand summaries.

**Methodology & honesty note:** Google Play stats and verbatim reviews were extracted from the embedded `AF_initDataCallback` JSON on each listing page. Apple figures come from `itunes.apple.com/lookup`. Reddit is now hard-walled (reddit.com blocks the search agent, old.reddit.com serves a JS shell, redlib mirrors sit behind Anubis bot-walls), so Part B leans on (a) one successful harvest of real Reddit thread text via Bing's RSS endpoint and (b) several hundred verbatim app-store reviews, which are arguably better evidence anyway since they are the actual purchase-decision voice. Where I could not verify something, I say so rather than guessing.

---

## PART A — COMPETITOR DEEP DIVE

### Master scoreboard (Google Play, scraped 15 Sep 2026)

| App | Package | Stars | Reviews | Downloads | Last update | IAP range |
|---|---|---|---|---|---|---|
| **PetDesk** | com.locai.petpartner | **4.8** | 31.7K | **1M+** | Sep 11, 2026 | $5.99–$19.99 |
| **Dogo** (training) | app.dogo.com.dogo_android | 4.6 | 129K | **5M+** | Sep 7, 2026 | $0.49–$299.99 |
| **Tractive** (hardware) | com.tractive.android.gps | 4.7 | 133K | 1M+ | Sep 14, 2026 | $0.99–$191.99 |
| **11pets** | com.m11pets.elevenpets | **2.2** ⚠️ | 5.69K | 500K+ | Aug 25, 2026 | $1.99–$21.99 |
| **Pet Care Tracker** (dogcat.app) | dogcat.app.android | 4.3 | 6.39K | 100K+ | Sep 6, 2026 | $1.99–$39.99 |
| **DogLog** | com.mobikode.dog | 4.5 | 1.25K | 100K+ | Aug 20, 2026 | $3.99–$39.99 |
| **VitusVet** | com.vitusvet.android | 4.6 | 8.26K | 100K+ | Nov 7, 2025 | $0.99–$14.99 |
| **PetnotePlus** | com.lancerdog.petnote_plus | 4.5 | 1.62K | 50K+ | Aug 30, 2026 | $3.99–$39.99 |
| **PetNoter** | com.petnoter | 4.3 | 148 | 10K+ | Aug 31, 2026 | $1.99–$29.99 |
| **DogNote** | app.dognote | 4.3 | 256 | 10K+ | Aug 25, 2026 | $1.49–$44.99 |
| **GreatPetCare** (ex-Pawprint) | com.pawprint.mobile | **2.3** ⚠️ | 342 | 10K+ | Aug 24, 2026 | — |
| **Pet Parents** | com.petparents.pet | 3.8 | 86 | 10K+ | **Jun 20, 2023** 💀 | $3.99–$69.99 |
| **Notepet** | io.notepet | **2.6** ⚠️ | 43 | 1K+ | Sep 12, 2026 | $1.99–$44.99 |
| **Who Fed The Dog?** | com.whofedthedog.app | 3.7 | — | 500+ | Aug 27, 2026 | $1.99–$9.99 |
| **Vet Record** | vetrecord.app | — | — | 500+ | Jul 10, 2026 | $2.49–$29.99 |
| **I Fed the Pet** | com.drufftech.ifedthepet | 4.6 | — | 50+ | Jun 7, 2026 | $4.99–$79.99 |
| **Kibbl** | com.kibbl.test | 4.6 | — | 50+ | **Dec 5, 2025** | $12.99–$379.99 |
| Chewy | com.chewy.android | 4.8 | 672K | 10M+ | Sep 9, 2026 | — |
| PetSmart | com.petsmart.consumermobile | 3.8 | 28.5K | 5M+ | Sep 1, 2026 | — |
| Petco | com.petco.mobile | 3.8 | 22.7K | 1M+ | Sep 8, 2026 | — |
| Pawp (24/7 vet) | com.pawp.android | 4.7 | 6.12K | 100K+ | Sep 11, 2026 | — |

### iOS scoreboard (iTunes Lookup API, 15 Sep 2026)

| App | Seller | Rating | # Ratings | Version / date | First released |
|---|---|---|---|---|---|
| VitusVet | Vitus Animal Health | **4.86** | **10,776** | 2023-04-10 | — |
| DogNote | Restack OU | **4.71** | **904** | 4.4.0 / Sep 8, 2026 | Nov 6, 2020 |
| DogLog | RSLL Ventures Inc | **4.78** | 1,347 | 3.36 / Jun 2, 2026 | Apr 26, 2017 |
| Digitail | Digitail Innovation SRL | 4.90 | 3,620 | Sep 15, 2026 | — |
| **11pets** | 11PETS LTD | **3.29** | **77** | 6.003.022 / Aug 26, 2026 | Sep 28, 2017 |
| PetnotePlus | Shibapp LLC | 4.6 | 15 | 3.2.6 / Aug 30, 2026 | Feb 28, 2021 |
| PetNoter | Manu Benjamin | 4.18 | 17 | 2.2.21 / Aug 28, 2026 | Aug 16, 2021 |
| Kibbl | Colleen Krishnan | **2.11** | 19 | **1.0 / Jan 13, 2022** 💀 | Jan 13, 2022 |
| PetPilot | Bradley Shively | 5.0 | 2 | 1.8 / Jul 12, 2026 | Jan 10, 2026 |
| Pawfolio | Haashim Malik | 2.5 | 2 | 7.8 / Sep 7, 2026 | Mar 13, 2025 |
| I Fed the Pet | The Druff Technology Group | 5.0 | 1 | 1.1 / Jun 7, 2026 | Apr 22, 2026 |

---

## 1. 11pets: Pet Care — the incumbent, and a cautionary tale

**Developer:** 11PETS LTD (Nicosia, Cyprus; founded 2015 by Demos Pavlou & Kyriakos Stavrou; **unfunded**, 1–10 employees per [Tracxn](https://tracxn.com/d/companies/11pets/__vqgzhWntDrOqt1rtkKD2swYiE94y54tDmDLi9SK6byI)). Platforms: Android + iOS + web portal.

**Stores:** [Google Play](https://play.google.com/store/apps/details?id=com.m11pets.elevenpets) — **500K+ downloads but only 2.2★ from 5.69K reviews**. [App Store](https://apps.apple.com/us/app/11pets-pet-care/id1232470530) — 3.29★ from just 77 US ratings, IAPs listed at **$1.99 and $17.99**, Play IAP range **$1.99–$21.99**.

That 2.2★ on half a million installs is the single most important fact in this entire report. **The category incumbent has a broken relationship with its own user base**, and the reviews explain exactly why.

**Pricing:** Two tiers, Free and Premium. The [official pricing page](https://www.11pets.com/en/price) conspicuously **shows no prices at all** — only a checkmark grid. Premium gates: reminder notifications, **support for multiple pets**, unlimited data storage, adding family members, behaviour monitoring, loyalty scheme. Free keeps: pet profiles, medical records, sharing with caregivers. Gating *reminders* and *multi-pet* — the two things the product is for — is the core strategic error.

**The 2025 pricing backlash, in users' own words** (verbatim Play reviews):

> "I've used this app for years... Now that the free version is going away, I can't justify paying **€69 a year** to use two of its features. I'm not opposed to paying a yearly subscription, but that fee is more than I pay for subscriptions on apps I use on a daily basis."

> "I have **3 years worth of data** logged for my dog, and now they're forcing me to pay **$70 CAD ANNUALLY**... I'd rather use an **excel spreadsheet FOR FREE** than pay for this app."

> "Used to love this app for YEARS. But advertising free forever and then not allowing people who've had it from beginning of it for free and forcing payment isn't right. I'm all for paying an AFFORDABLE fee. But **locking me out of literally years worth of info** isn't cool... **$5 is a small fee. $50+ is ridiculous.**"

> "Up until this pay to use thing became a thing, I would have given this app more than 5 stars but suddenly they're **holding my saved documents and information hostage**. They won't let me receive a password reset, won't let me log into my account where my three dogs information has been stored for **7+ years**!"

**The migration disaster** — separate from pricing, and arguably worse:

> "Since the 'upgrade,' the app has been awful... I paid a **'lifetime subscription'** but only for a newer version that **didn't convert all my info over properly** and didn't allow as many functions as I had before."

> "It used to be a perfect app until I updated it... I have 11 pets right now, but I have had more than 30 in the past. Now there is **no way to filter my current pets**... and I **can't find a way to see all the tasks for all the pets, I have to check one by one**... They import my pets, but it's not possible to edit them. I've had the premium app before, **money wasted**."

> "Everything was fine until the latest update. Now, when I enter my username and password, the app states an invalid username or password. I tried and **reset the password... but I never got an email**... this newest change **limits the user to ONE AND ONLY ONE PET** unless the user wants to pay."

> "'There was an error! Try again later.' If you like seeing these words over and over, this is the app for you. It used to be good, but now it just gives errors and locks up and is nearly unusable. **I wish I had not subscribed.**"

> "**DO NOT PUT WEIGHT INTO APP!** Any weight entered is **converted into the wrong weight** on 'shareable' petsitter report... I emailed customer service multiple times with **no response**."

**The company's own defence**, posted publicly as a developer reply: *"we needed to update the app as soon as possible to help animal shelters find homes with some new features. We apologize for making the experience worse for your own pet."* They pivoted the consumer app to serve a shelter product and openly admit degrading the owner experience. A later reply concedes: *"We're actively working to restore the usability and features that made 11pets valuable to you."*

**TOP 5 PRAISES** (from the surviving positive reviews):
1. **Breadth + customisability across exotic species.** *"I have over 30 reptiles/amphibians and this app allows me to keep track of feeding, weight, birthdays and reproduction... Everyone is shocked when I show them the app."*
2. **Genuinely deep record model.** *"There is a place for labs, contacts, conditions, and even a food inventory that tells you when you are about to run out!"*
3. **Cross-device + web portal sync.** *"I LOVE that it syncs with my iPad and that I can use their web portal too."*
4. **No intrusive ads.** *"I haven't noticed any intrusive advertising... this is one of the best ones I've ever used."*
5. **Vet-visit utility.** Long-time App Store reviewer calls it *"one of best pet apps available,"* specifically for veterinary visits.

**TOP 5 COMPLAINTS:**
1. **Price shock on a previously-free/lifetime base** — €69/yr, $70 CAD/yr against a "lifetime" promise. Repeated verbatim above.
2. **Data loss / scrambled migration** and weight-unit corruption on shared reports.
3. **Lockout** — login and password-reset both broken, users cut off from 7+ years of records.
4. **Multi-pet gating and lost multi-pet views** — the killer for the exact power users who loved it.
5. **"Databasey" IA and buried buttons.** *"Information is entered in a very 'databasey' and challenging manner. The same information can be entered in multiple places, some of the locations can't be extracted into any of the 'sharing' reports."* Plus: *"I could NOT get the app to do ANYTHING, but try and get me to join more things."*

**Most-requested missing features:** editable/inactive medications without deletion (*"it would be nice to be able to edit or change the animal's medicine or vaccinations without having to delete or snooze"*); per-species task scoping — one user got *"notifications to trim the nails on my aquarium"* because tasks leaked across species; grams as a weight unit for reptiles; real nutrition/calorie tracking (*"look elsewhere if you want anything more than a timestamp saying fed and a note"*); bulk photo upload and full gallery download; vaccine **group** names (Lepto4 showing as 4 separate entries); monthly preventatives in the Supplies tab.

**Abandonment signals:** None — it ships frequently (Play Aug 25, 2026; iOS Aug 26, 2026). This is *not* an abandoned app. It is an **actively-maintained app that actively destroyed its own goodwill**, which is a far more exploitable situation: 500K installs of people who have proven they will keep records daily for 7 years, and who are currently, loudly, looking for an exit.

---

## 2. PetNoter

**Developer:** listed as Manu Benjamin (iOS) / [petnoter.com](https://petnoter.com/). Both platforms. [Play](https://play.google.com/store/apps/details?id=com.petnoter) 4.3★ / 148 reviews / 10K+ installs; [App Store](https://apps.apple.com/us/app/petnoter-all-pet-care-tracker/id1580581463) 4.18★ / only 17 US ratings. Claims "20,000+ pet parents" in its listing copy.

**Pricing:** Monthly $2.99, Yearly $14.99, **Lifetime $59.99** (iOS IAP data). Play range $1.99–$29.99. Free tier caps at **3 pets**; **document upload and export are both paywalled**.

**Features:** Medical-record vault (vaccination history, microchip, insurance docs, lab results), vaccine/med reminders that "repeat daily until the task is done", weight/growth charts, expense tracking, **one-tap vet-ready PDF export** for a new vet/sitter/boarding facility, multi-species (rabbits, birds, hamsters, reptiles).

**Praises:** (1) *"FINALLY an app where I can store all of my pet documents easily. I was looking around, tried **mPet, VitusVet and PetDesk**. I liked the user interface of PetDesk, but **it didn't save documents**. This was the fourth app I tried and the first that had a nice user interface and an actual document storage option."* — a direct, explicit competitive-gap statement. (2) The nagging-reminder model: *"The medication logger/tracker format is excellent... Love that you can choose the frequency."* (3) *"Affordable. Easy to set up. And I can upload vet records and receipts."* (4) Aesthetics — *"the graphics are aesthetic and neat."* (5) *"after trying, and failing with 3 other similar apps LOVE this one."*

**Complaints:** (1) **Paywall placement** — *"Can only store 3 pets. Document upload & Export are pay-walled. **Uninstalled.**"* and *"I'm also disappointed I have to pay to use properly for 2 cats."* (2) Off-by-one date bug after an update: *"Any day you enter will enter a day behind."* (3) Photo-upload from gallery was dead for a long period; support email bounced — *"Emailed for support. Email undeliverable."* (4) Recurring to-dos originally deleted all future occurrences when completed. (5) Unsorted upcoming vaccinations across multiple pets. Also, from the App Store: breaks down past ~30 pets, no alphabetical sorting.

**Most requested:** bi-monthly / quarterly / alternate-day medication frequencies; **apply one expense or one medication to multiple pets at once**; multiple profile photos per pet; explicit dark-mode toggle; age in days/weeks/months; photo-to-document conversion.

**Cadence:** Healthy — Play Aug 31, 2026, iOS Aug 28, 2026, and the developer visibly ships requested fixes ("We've listened to your feedback and this is now implemented").

---

## 3a. DogLog — the shared-care benchmark

**Developer:** RSLL Ventures Inc (iOS) / "Lynn Marks" (Play). [doglogapp.com](https://www.doglogapp.com/). [Play](https://play.google.com/store/apps/details?id=com.mobikode.dog) 4.5★ / 1.25K reviews / 100K+ installs, **contains ads**; [App Store](https://apps.apple.com/us/app/doglog-track-your-dogs-life/id1229529595) **4.78★ / 1,347 ratings**, live since April 2017.

**Pricing:** Monthly $3.99, Annual $39.99. Free basic logging; **premium gates export/email of data and the potty-prediction feature**.

**Features:** The "**Pack**" sharing model — invite co-carers who all log into one shared feed. Loggable: food, water, treat, walk, pee, poop, sleep, teeth brushing, grooming, training, medicine, custom. Photos attached to events with **likes and comments** (a social layer nobody else has). Reminders, statistics dashboards, vaccination/blood-glucose/symptom tracking, multi-pet, search, export.

**UI/UX:** Big-button logging explicitly designed for the sleep-deprived: *"The large buttons are nice when you're half asleep."* Swipe-left on a log row to edit/delete — discoverable enough that a reviewer filed a bug about it not existing, and the developer had to explain it, which is itself a discoverability finding.

**Engagement mechanics:** The photo feed with likes/comments is the standout — *"I love the option to add a photo and 'like' or comment on everything."* This turns a chore log into a shared family artifact.

**TOP 5 PRAISES:**
1. **Household coordination is the whole value.** *"No more questioning of if we fed her, or what time we let her outside."* And: *"very useful for us where there are **six people plus two dogs** in our pack."*
2. **Replaces analogue systems.** *"We used to keep all this information in a **spiral** that we would write in throughout the day."* / *"we use a **whiteboard** to track feedings, bathroom breaks and play time."*
3. **Puppy potty-training killer app.** *"this app was a god send, specially if you have a young pup... keeping track of naps and potty times gets hard on low sleep."*
4. **Daily-habit stickiness.** *"Have been using doglog for a couple years now, it's one of the few apps that I use **every single day**. I'd be lost without it."*
5. **Vet-shareable history.** *"It's a lifesaver for multi-carer packs & dogs with health issues, being able to easily create a log of everything to share with the vet is **priceless**."*

**TOP 5 COMPLAINTS:**
1. **Sync is unreliable — and this is fatal for a shared-care app.** *"Doesn't sync properly across devices... the only way to get it to sync across all devices is to close the app and reopen it. I get a text from my wife asking if the last time the dog went potty was 6 hours ago and when I look at my log she says nevermind it just popped up."*
2. **Notifications late or dead.** *"the timers were too inaccurate. I would get notifications anywhere from **10 minutes to an hour late**. That makes it useless for potty training."* And: *"On newer phones, notifications of pet activity don't come through, posing an issue for pets with multiple caretakers."*
3. **Pack size cap.** *"I wish that you could have more than 3 dogs in your pack... I've been using 2 packs to cover all the pups."*
4. **Ads appeared abruptly** on a beloved free app: *"Huge banner ad appeared at the top of the app today. It's very obnoxious."* (Notably the user still gave 5★ and donated — evidence people will fund a tool they love.)
5. **Broken stats + rigid dropdowns.** *"the stats are broken which makes the app a bit useless."* / rescue-mix owners can't enter their breed or food brand because both are dropdown-only, no free text.

**Most requested:** **Android home-screen widget for one-tap logging** (requested repeatedly — *"NEED a Droid widget to quick add potty events!"*); invite-by-link onboarding (*"you can't invite people to download the app to join a 'pack'. They have to already have the app"*); cat/other-species support (*"this app would be PERFECT if it included other animal types"*); custom icons + renameable custom categories; PDF diet export for the vet; graphable custom metrics (a diabetic-dog owner: *"If this app grows to that level **I would likely pay**"*); one-time purchase instead of $40/yr.

## 3b. DogNote

**Developer:** Restack OU. [dognote.app](https://dognote.app/). [Play](https://play.google.com/store/apps/details?id=app.dognote) 4.3★ / 256 reviews / 10K+; [App Store](https://apps.apple.com/us/app/dognote-pet-journal-walks/id1527756855) **4.71★ / 904 ratings** — note the **large iOS/Android quality gap**, confirmed by reviews.

**Pricing:** ~$4.99/mo premium; Play IAP $1.49–$44.99. **More than one pet is paywalled** — *"I can't add more than 1 pet, because adding more than one is hidden behind a paywall. Surprise surprise. No thanks, awful app."*

**Features:** Family hub with invites, shared pet activity feed, one-time/recurring reminders, photo moments, custom events with emoji icons and reorderable activities, weight graphs, filter/search, **PDF export**, and critically: **Apple Watch app, Siri Shortcuts, and Widgets for quick logging** — the most complete quick-capture surface of any app reviewed.

**Praises:** professional use (*"I used it along with my coworkers at my **dog daycare job**"*); pet-sitting for family; custom categories/emoji; *"easy and intuitive and basic. not an overly complicated app"*; training notes on the fly.

**Complaints:** (1) **Android is a second-class citizen** — *"it might be an android only problem, meaning I can't use the app as intended anymore on my Samsung"*; *"if you're on android you can only see one photo when posting many at once and **iOS users can see them all**."* (2) **Login failures destroying data entry** — *"I spent hours putting all of my dog info into this app. Today I wanted to add more and I can't log in."* (3) **A full service outage** — developer reply: *"This was our first service interruption in years."* (4) **No real comment threads** — *"There is no true comment section. You have to write on the initial note which makes it hard to know who wrote what comment."* (5) Rating-prompt nagging with no opt-out (earned a literal 1★). (6) Subscription fatigue — *"It would be better if it had a **one-off** remove ads charge... rather than yet another subscription."*

**Most requested:** **document/image upload for vet paperwork** (*"my requested feature would be either a text scanner or the ability to upload files or images to have vet paperwork available on the go"* — DogNote has no document vault); video sending; vet/groomer contact sections; height/temperature fields; dark mode; in-app camera capture.

---

## 4. Pet Feeder (petfeeder.app)

**Developer:** WebMovement LLC. [petfeeder.app](https://petfeeder.app/) · [App Store](https://apps.apple.com/us/app/pet-feeder-app/id6758022385) — v1.0.2, released Feb 28 2026, **no ratings yet**. Essentially pre-traction.

**Pricing:** **100% free, ad-supported**, no subscription — a deliberate positioning shot at the category's subscription fatigue. **Free tier caps at 3 pets and 3 family members.**

**Features:** feeding/treat/medication logging, recurring reminders, push notifications to all members to prevent double-feeding, one-tap logging, real-time shared log. Privacy-forward copy ("encrypted, we don't sell your information"). No health-record layer, no export, no documents. Worth watching as a *pricing* threat, not a *feature* threat.

## 5. I Fed the Pet (drufftech)

**Developer:** The Druff Technology Group (Pty) Ltd. [App Store](https://apps.apple.com/us/app/i-fed-the-pet-feed-tracker/id6762333960) 5.0★ from **1 rating**; [Play](https://play.google.com/store/apps/details?id=com.drufftech.ifedthepet) 4.6★, **50+ installs**. Released April 2026. Effectively zero traction.

**Pricing:** 7-day free trial then subscription; Play IAP range $4.99–$79.99 — aggressive pricing for a single-function app with 50 installs.

**Features:** one-button "fed" that syncs to the whole household instantly. Its genuinely differentiated angle is **pet-sitter/professional mode: manage multiple households from one account, switch between client homes instantly, owners get real-time feeding updates.** That multi-household switcher is a real idea worth stealing for a sitter-facing feature.

**Reviews:** *"This app is so easy to use. One touch tells my family that I have fed my dogs."* Sample size far too small to draw complaint themes.

## 6. Kibbl — abandoned

**Developer:** Colleen Krishnan / Kibbl.ai. [App Store](https://apps.apple.com/us/app/kibbl/id1603040066) **2.11★ / 19 ratings, still on v1.0 from 13 Jan 2022**. [Play](https://play.google.com/store/apps/details?id=com.kibbl.test) 4.6★ but **50+ installs** and the package is literally `com.kibbl.test`. Last Play update Dec 5, 2025.

**Pricing:** originally ~$4 lifetime for multi-pet + family members; Play now lists IAP up to **$379.99**, suggesting an abandoned AI-platform pivot.

**Verdict:** Dead, and instructive: the 2.11★ iOS score against a 4.6★ Play score on a v1.0 binary shows what happens when you ship a "have you fed the dog" MVP and never iterate. Reviews cite *"a little slow/glitchy and taking a while for profiles to load."*

## 7. PetPilot (iOS)

**Developer:** Bradley Shively. [App Store](https://apps.apple.com/us/app/petpilot/id6749286937) — 5.0★ from **2 ratings**, v1.8 (Jul 12, 2026), first released Jan 10, 2026. iOS-only.

**Features:** quick activity logging (feeding, water, walks, medications), **iCloud family sharing**, multi-pet, **home-screen widget for one-tap logging**. Modern, native, small. Its iCloud-sharing approach means zero account friction but also no Android and no web — a structural ceiling.

## 8. Pawprint → GreatPetCare — the most valuable failure to study

**Developer:** Pawprint Acquisition, LLC (Metamorphosis Partners). Founded 2014 by Emily Dong & Jennifer Yip, explicitly from *"the challenge of managing paper-based veterinary records."* At acquisition in 2020 it had **65,000 users across 13,000 vet clinics** ([Crunchbase](https://www.crunchbase.com/organization/pawprint), [Dealroom](https://app.dealroom.co/companies/pawprint)). Rebranded to GreatPetCare.

**Stores:** [Play](https://play.google.com/store/apps/details?id=com.pawprint.mobile) **2.3★ / 342 reviews / 10K+ installs**. A category pioneer that collapsed.

**The killer mechanic:** Pawprint's magic was **fetching your records from your vet for you** — you don't type anything, they call the clinic. GreatPetCare kept it but **charges per record request**:

> "We have 8 companion animals, and one of them has a serious condition. Being able to track in one place is fantastic... For animals with chronic, ongoing conditions, **$10 every time we want to update the records is a bit much**. I reached out to the Dev... and was offered the solution that I could get the files from our vets and scan them in to the app. **If I got the files from all the vets directly, why would I do that?**"

> "GPC now **charges for services you can get for free by calling your vet's office**, such as record requests and exam details. It's not a very useful app anymore."

> "Even if you pay for the premium services, **you still have to pay more to request medical records**."

**TOP complaints:** (1) per-request charging on top of premium; (2) **document upload is broken** — *"I can't upload documents... the save button just disappears off the screen once I've attached a document"*, reported across phone and tablet; (3) **save buttons hidden behind system UI** — *"the save button in almost every screen gets hidden either behind the app's menu or behind the phone's menu buttons... Please review your media queries and flexbox configs"* (a reviewer diagnosing your CSS is a special kind of indictment); (4) migration damage — *"vaccines from other app were transferred and look incorrect or duplicated"*; (5) **no manual-entry path** — *"I want to add records and info on my own. I don't want to have to request records from my vet... It doesn't let you do one or the other."*

**Praises:** travel/boarding/emergency access — *"We travel with our old dogs often and it's extremely handy to have their medical records available on my phone in case of emergency, or boarding. It's also very handy when we have someone dog sitting."*

**Lesson for Pet Care Hub:** vet-record retrieval is the single highest-value feature in the category *and* the hardest to monetise per-use without enraging people. Bundle it or don't ship it.

## 9. PetDesk — the only standalone pet-record app past 1M

**Developer:** PetDesk. [Play](https://play.google.com/store/apps/details?id=com.locai.petpartner) **4.8★ / 31.7K reviews / 1M+ downloads**, updated Sep 11 2026. IAP $5.99–$19.99.

**Business model:** **B2B2C** — the clinic buys PetDesk; the app is free to owners and distributed *by the vet*. Owners arrive because their vet invited them. This is the growth answer for the whole category, and it's discussed in Part C.

**Features:** appointment request/reschedule/cancel, vaccine & service reminders synced from the clinic's PIMS, prescription refill requests, two-way messaging with the clinic, multi-provider (vet + groomer + boarding + daycare) in one place, loyalty points, calendar sync, lab results.

**TOP 5 PRAISES:** (1) Emergency value — *"I had to take my cat to the e-vet. Having her most recent **lab results at my fingertips** helped them establish a baseline which was really important because **we almost lost her**."* (2) Refills without phone calls, 24/7. (3) Multi-pet, multi-provider consolidation — *"hard keeping up with 6 animals with different health issues. This has it all in one place!"* (4) *"No more sorting through paperwork!"* (5) Reduces clinic phone friction generally.

**TOP 5 COMPLAINTS:** (1) **Reminders that won't clear after the appointment** — the single most repeated complaint, and users can't fix it themselves: *"reminders that didn't clear after the appointments... kept reminding me over and over again. The app support suggestions didn't fix it. **I had to wait for the vet's office to do it.**"* (2) **Owners can't edit their own data** — *"You can not update any information after your initial setup... it does not update their age, and will not allow me to enter new weights."* (3) **Duplicate pet records** created by the clinic with no merge tool: *"there's no way for me to replace their blank pet entry with the one that I created."* (4) **Total dependence on clinic participation** — *"If the providers don't respond, this is essentially a place to keep a list of your pets."* (5) Notes fields that won't scroll, 6-line limits, and **notifications from former providers you cannot turn off** — *"I'm getting notifications from a provider I had years ago."*

**The structural gap Pet Care Hub should attack:** PetDesk is your vet's CRM wearing a consumer skin. Users say it plainly: *"Wish it would allow your choice vet to send and allow you to **view** health records"* and *"there's no way for owners to add any medical info... **There are other apps that offer much better record keeping for pets.**"* **It is not portable, not owner-controlled, and dies when you change vets.**

## 10. The long tail with real traction

**Pet Care Tracker — Dog Cat App (`dogcat.app.android`)** — [Play](https://play.google.com/store/apps/details?id=dogcat.app.android) **4.3★ / 6.39K reviews / 100K+ installs**, updated Sep 6, 2026. **This is the most underrated competitor in the set** and it ranked #1 for all three of my Play search probes ("pet care tracker", "dog feeding tracker shared", "pet vaccination records") — i.e. it is winning ASO. It's a **solo indie developer**, who says so in public replies: *"I'm just one person doing design, app development, server development and customer support. All in my free time."*

Its reviews are the best-tempered in the category: *"I have yet to find a feature I want that it doesn't have"*; *"my vet wanted me to document the food and the poops, and with this awesome app, it makes it easy"*; *"This app keeps us from making medication mistakes!"*; *"I love that you can add photos, events of any kind (medication, walk in the park, bath) and health metrics like weight and vomit/diarrhea."* Free tier is ads + **unlimited pets** + 1 gallery photo/month; premium adds no-ads, unlimited photos, custom fields.

Its documented weak spots — **all directly attackable**: no specific time-of-day recurrence (*"setting the schedule to repeat every 3hrs also means it does so at night when they're asleep"*); **no bulk "mark done for all pets"** (*"I'd also like if events for multiple pets could be marked completed for all pets in one step"*); custom items must be recreated per pet; dated visual design (*"I would like the interface to look more seamless and modern"*); no feeding-quantity/nutrition calculator.

**PetnotePlus (`com.lancerdog.petnote_plus`)** — 4.5★ / 1.62K / 50K+. [Play](https://play.google.com/store/apps/details?id=com.lancerdog.petnote_plus) · [App Store](https://apps.apple.com/us/app/petnoteplus-pet-care-tracker/id1553584485). The **power-user favourite**: *"The best parts are 1. **CSV export**, 2. **Bulk log**, 3. Different tracking methods (Text, Integer, Rating, Check Mark, etc), Graphs."* Custom typed fields, reptile/arthropod templates, **widgets** (*"I can quickly add seizure logs for my dog"*), family sharing up to 15 members via share code, 5 pets free. Weaknesses: **documents can be uploaded but not viewed** — *"Why would the app allow me to upload documents connected to a visit but then not allow me to view them... If I'm at the vet, and want to show them a record. I don't want to have to send it to their phone or mine just to look at it"*; blocky/unintuitive UI; a reported **total data-loss incident**; no way to archive a deceased pet without burning a slot.

**Notepet (`io.notepet`)** — **2.6★**, 1K+ installs, but shipping actively (Sep 12, 2026). Medication-specialist. Loved by the right niche: *"this app has been invaluable for my **dog with cancer**. He's on so many meds different schedules, they need to be paused sometimes... It's too much to remember."* Killed by reliability: *"**Alerts stop working or are late once you have more than one**... so I missed pet meds because of this terrible app. just use pet desk it works waaaaay better"*; *"the screen says 'please wait' forever"*; requires network — developer admits *"Notepet requires network connection to work reliably and we plan to improve offline support in the future."* **Offline-first is a competitive weapon here.**

**Pet Parents (`com.petparents.pet`)** — 3.8★, 10K+, **last updated 20 June 2023 — abandoned**. Its reviews are a gift, because they describe an app people *wanted* to love: *"I LOVE this app! I have searched for an app which allows me to put in my own medical records with digital copies of vet's physical records. This is the ONLY app I have found that will allow this. **Perfect for Service Dog records!**"* Then: *"This app was great last year, but now the **share link has an error**... I now can't share my dog's records with caregivers."* And the emergency failure: *"During emergency when I had to take my pet in the middle of the night to the docs, **I was not able to retrieve the medical records, just when I needed it the most**."* Caps at 15 pets, no weight tracking (*"No way to track weight? ...deal breaker. Uninstalled"*).

**Vet Record (`vetrecord.app`)** — 500+ installs, new, tiny. iOS version sits at 2.0★ from 1 rating. Positive early signal (*"No more looking through papers, everything is at one place"*) but a broken Google sign-in on Samsung S-series. Not a threat yet.

**Who Fed The Dog? (`com.whofedthedog.app`)** — 3.7★, 500+ installs. Single-purpose: log feeding/medication/walks/water/toilet, see who recorded each activity and when, share with family/sitters/walkers.

**Others found in-category during ASO probes:** Pawfolio (2.5★ iOS, calorie tracking + Lock Screen widgets + colour-coded history grid), Fed? (Smart Kiitos Oy — iCloud-only, no accounts, Home Screen widget), SharedPets, PawPact, Pawlo, DogSync, PetDiary, Pet Care Tracker For Dog & Cat (3.5★), VitusVet (**4.86★ / 10,776 iOS ratings, 100K+ Play** — but **last Play update Nov 7, 2025**, a genuine abandonment signal on an otherwise beloved app).

## 11. Well-funded newcomers

**Dogo** — the category's commercial success story, though it's *training*, not records. [Play](https://play.google.com/store/apps/details?id=app.dogo.com.dogo_android): **4.6★ / 129K reviews / 5M+ downloads**, IAP up to **$299.99**. Sensor Tower estimates via search: **~40K downloads + $50K revenue/mo on Android, ~50K + $100K/mo on iOS — roughly $150K/month combined**. Pricing $9.99/wk, $29.99/mo, $49.99/quarter, with aggressive 50%-off first-period discounting ([Dogster review](https://www.dogster.com/lifestyle/dogo-app-review/)). Note the shape: **5M downloads → ~$1.8M/yr**. That is the realistic ceiling economics of a consumer pet app, and it is a *training* app with far better emotional hooks than record-keeping.

**Woofz** (Wikipedia-notable, same playbook) bundles "profiles for each dog... monitor their health", a doggy calendar, behaviour programmes and **completion certificates** — gamification that record apps entirely lack.

**Petio** — the clearest "AI-first" newcomer. Per its own [comparison content](https://www.petiogo.com/blog/best-ai-vet-chat-apps): built on Gemini, uses breed/age/health history, **barcode food scanning checked against the pet's logged allergies**, health metrics. **Free tier: 1 pet, AI chat, food scanning, basic tracking, 5 documents. Plus $5.99/mo or $47.99/yr: unlimited pets, higher AI/scan limits, more document storage, family sharing.** That is the most directly competitive price/packaging structure I found for Pet Care Hub — and note they gate at **1 pet free**, exactly the move users punish.

**Tably** (Sylvester.ai) — AI feline pain detection from a photo using the University of Montreal **Feline Grimace Scale**, claimed 97% accurate, aimed largely at clinics ([CBC](https://www.cbc.ca/news/canada/calgary/tably-calgary-alberta-artificial-intelligence-cats-1.7025398), [Forbes](https://www.forbes.com/sites/marksparrow/2026/04/14/can-the-power-of-ai-help-you-to-chat-with-your-cat/)). Narrow, but a genuine "delight" primitive.

**Adjacent vet-access subscriptions** setting price anchors: **Pawp ~$99/yr unlimited vet chat** (4.7★, 100K+ installs), **Vetster ~$35/appointment**, **Airvet** monthly. Pupford/Puppr are not on Play under guessable package IDs and appear to be small iOS-led training plays.

## 12. Adjacent giants — what they can do that you can't, and vice versa

**Chewy** ([Play](https://play.google.com/store/apps/details?id=com.chewy.android): 4.8★, 672K reviews, **10M+ installs**) — Chewy owns the *purchase* relationship: autoship, prescription fulfilment routed through your vet, and Chewy's own telehealth. Because they ship the food and the meds, they know your pet's diet and prescription cadence without you logging anything, and they can fund the app from retail margin so it's free forever. **What they can't do:** Chewy will never be a neutral, portable, exportable health record — it's a storefront, it has no interest in your vaccination history for a boarding kennel, and it can't log that your partner already walked the dog.

**Rover** — owns the *sitter/walker marketplace*: booking, GPS walk logs, daily photo updates, and per [market coverage](https://www.cognitivemarketresearch.com/pet-care-app-market-report) launched "Rover Care" in April 2025 with live pet activity updates, health tracking and telemedicine. **What they can't do:** Rover's care log exists only inside a booked Rover engagement. It doesn't help the unpaid sitter (your mum, your neighbour), it doesn't persist as your pet's lifetime record, and owners can't use it day-to-day between bookings. Pet Care Hub's sitter-handoff is *always on*; Rover's is transactional.

**Tractive / Whistle** ([Tractive Play](https://play.google.com/store/apps/details?id=com.tractive.android.gps): 4.7★, 133K reviews, 1M+) — hardware gives them passive, continuous, objective data no phone app can fake: GPS location, virtual fences, escape alerts, activity, sleep, **heart rate and respiratory rate**, barking anomalies. Tractive has **acquired Whistle**, which is shutting down and migrating users across ([Engadget](https://www.engadget.com/wearables/whistle-pet-trackers-are-shutting-down-next-month-212828325.html)). Basic plan ~$108/yr, family/cellular tier ~$120/yr ([SafeWise](https://www.safewise.com/kids-safety/gps-trackers/tractive/)). **What they can't do:** a collar cannot tell you a vaccination expiry, hold a PDF from the vet, produce an insurance claim pack, or coordinate who gave the 8pm pill. Also note the *opportunity*: a real user asked 11pets for exactly this bridge — *"If there was a food database for meals and a way to **attach my cat's fitness tracker** to this app it would be perfect."*

**Petco (1M+, 3.8★) / PetSmart (5M+, 3.8★)** — retail + grooming/boarding/vet-clinic booking inside their own estates, plus loyalty. They can pull a vaccination record for a grooming appointment *at their own stores*. **What they can't do:** anything outside their four walls. Both sit at 3.8★, well below the category's independent trackers, and neither is trying to be your pet's record of truth.

**The synthesis:** the giants own *transactions* (buying, booking, locating). None of them owns the **portable, owner-controlled, multi-carer, lifetime record** — because that asset has no captive revenue stream attached to it. That is precisely the gap.

---

## PART B — WHAT PET OWNERS ACTUALLY WANT

**Evidence base:** verbatim Reddit thread text harvested via Bing's RSS endpoint (reddit.com itself is agent-blocked), plus several hundred verbatim Play/App Store reviews quoted throughout Part A.

### The recommendation threads: what people ask for

The striking thing is that **people ask for this app repeatedly and are told to use a spreadsheet.**

- **r/LifeProTips — ["LPT: Make a spreadsheet to keep track of your pets' vaccination..."](https://www.reddit.com/r/LifeProTips/comments/1ad9bp1/lpt_make_a_spreadsheet_to_keep_track_of_your_pets/)**: *"**Don't rely on your vet to remind you** - keep track of it all yourself. Make it easy with a clear column that says when they're next due for a vaccine, and put that on your calendar. This has really helped me keep track of our **7 dogs and 2 cats of varying ages and schedules** without any big gaps in immunizations."* — The top-voted advice in the category is *build a spreadsheet*. That is a product opportunity stated as a workaround.

- **r/Pets — ["How do you track your pet's medical history?"](https://www.reddit.com/r/Pets/comments/uo7xl3/how_do_you_track_your_pets_medical_history/)**: *"Does anyone have any apps or products they use to **digitize** their pet's medical data? I know certain vet offices have their own apps they use, but I'm looking for something that can be **transferable and used independently of a specific vet office**."* — This is the PetDesk critique, stated as a requirement, by a user who has never used PetDesk. **Portability and vet-independence are the headline job.**

- **r/Pets — ["Does anyone know of an app for tracking pet vet/training etc"](https://www.reddit.com/r/Pets/comments/i6r25e/does_anyone_know_of_an_app_for_tracking_pet/)**: *"I have a small farm and own **30 animals** including dogs, cats, and livestock. I am looking for an app that will allow me to organize my animals in a way where **everyone has a page**. I would want to track vet/vaccines, training progress, and any health stuff."*

- **r/puppy101 — ["Pup parents, how do you keep track of vaccination dates"](https://www.reddit.com/r/puppy101/comments/yuq1p9/pup_parents_how_do_you_keep_track_of_vaccination_dates/)**: *"Vaccinations and meds/dewormer go into **my phone calendar**. I use **Puppy Potty Log** for tracking food/water/potty... it has an option to track accidents and it will **attempt to predict next potties**, and it's **shareable with multiple people**."* — Note the app-juggling: calendar for health, separate app for daily logs. Nobody has unified them.

- **r/androidapps — ["What app to log your pet condition regularly, i.e. weight..."](https://www.reddit.com/r/androidapps/comments/nxer91/what_app_to_log_your_pet_condition_regularly_ie/)**: *"I know an app called Pet Diary existed, but the review is mixed. What pet log/pet diary app do you use? Preferably something that can log **multiple pets**, like one diary for Cat A, another for Dog B."*

- **r/Pets (older)**: *"I imagine if you had any sort of newish phone you can **program dates in the calendar**... I just make sure to go to a yearly appointment to get the info I need, and I administer flea/tick meds on the 1st of every month so I always remember."* — the manual coping strategy, in the wild.

- **r/googlesheets — ["Need a sheet to track expired vaccinations"](https://www.reddit.com/r/googlesheets/comments/x0rajo/need_a_sheet_to_track_expired_vaccinations/)**: *"It is for a **dog daycare/boarding facility**. Layout: Client Name - Pet Name - **Rabies - Distemper - Bordetella**. When vaccination expired I want the font to turn red so we know to notify clients."* — This is the boarding-kennel side of the same pain, and it names the exact three vaccines that gate boarding in the US.

- Notably, the developer of `dogcat.app` **launched on Reddit** ([r/Bloodhound](https://www.reddit.com/r/Bloodhound/comments/ov6ce5/i_created_a_free_pet_health_tracking_app_would/)): *"I'm a software engineer and I recently created a Pet Health Tracking app... if it doesn't sound useful, I'd love to know why too or what is missing!"* — and that app now has 100K+ installs and 6.39K reviews. Community-led launch works in this category.

### Complaints about juggling apps, spreadsheets and paper

Direct from store reviews (all quoted in Part A with sources):
- *"We used to keep all this information in a **spiral** that we would write in throughout the day."* (DogLog)
- *"we use a **whiteboard** to track feedings, bathroom breaks and play time. Am looking for a digital way."* (DogLog)
- *"With **6 aging/ailing furkids**, I've been looking for something to replace my **notepad** to track meds, symptoms, weights."* (dogcat.app)
- *"I tried a few different techniques - **pen and paper, diary apps, and pet specific health trackers**. I was ready to **make myself a Google Form** and then I found this app."* (PetnotePlus)
- *"instead of **searching through a stack of papers**."* (PetNoter, App Store)
- *"This is the **3rd pet app** I've signed up for and I'm going to keep this one."* / *"after trying, and failing with **3 other similar apps**"* / *"This was the **fourth app I tried**"* / *"I'm trying **multiple pet care logging apps**" / "I've tried **many apps** for pets"* / *"I tried so many other different apps and found a lot of them to be **needlessly complicated while still not having all the functions I needed**."*

**The serial-trialling behaviour is the loudest signal in the entire dataset.** Users churn through 3–5 apps looking for one that does records *and* daily logging *and* sharing *and* documents. Nobody has convinced them.

### Multi-person households and double-feeding

This is a solved-in-principle, unsolved-in-practice problem — an entire micro-genre exists (Who Fed The Dog?, Pawfolio, Kibbl, PawPact, DogSync, Pet Feeder, I Fed the Pet, Fed?), and **almost all of them are sub-1K-install toys**. Meanwhile the apps with real users have broken sync (DogLog: *"I get a text from my wife asking if the last time the dog went potty was 6 hours ago"*) or paywall the second person.

Evidence the need is real and emotional:
- *"six people plus two dogs in our pack"* (DogLog)
- *"when multiple people are responsible for the pet (my spouse and I) we can see the entries from each other so we **don't double feed or doubt whether or not he's had care**"* (PetnotePlus)
- *"it has helped so much with us making sure we **feed our shared cat on time and not to overfeed or underfeed**"* (PetnotePlus)
- *"keeping track of the dogs needs with **so many people coming and going**"* (DogNote)
- *"This app keeps us from making **medication mistakes**! We use for reminders for several different medications for our dog. **2 users**, free version."* (dogcat.app)
- **Notifications failing breaks the whole premise:** *"On newer phones, notifications of pet activity don't come through, posing an issue for pets with **multiple caretakers**."* (DogLog)

### Pet sitters needing instructions

- *"I adore this app for **taking care of my sister's dog while they're out of town**."* (DogNote)
- *"I used it along with my coworkers at my **dog daycare job**."* (DogNote)
- *"It's also very handy when we have someone **dog sitting**."* (GreatPetCare)
- GreatPetCare's own positioning names it: *"Connect your pet's caretaker network around a central information source... managing medical records, **feeding instructions**, reminders."*
- I Fed the Pet's differentiator is explicitly professional: *"manage multiple households from one account, switch between client homes instantly."*
- And the failure mode, from 11pets: the shareable **petsitter report converted weights to the wrong units** and support never replied.

### Moving vets / losing records

- *"looking for something that can be **transferable and used independently of a specific vet office**"* (r/Pets, above)
- PetDesk users: *"Wish it would allow your choice vet to send and allow you to **view** health records"*; *"there's no way for owners to add any medical info"*; and the notification-from-a-former-provider complaint — *"I'm getting notifications from a provider I had years ago."*
- GreatPetCare: *"vaccines from other app were transferred and **look incorrect or duplicated** and trying to track now is not as easy as the last app."*
- 11pets: users locked out of **7+ years** of records.

### Boarding kennels demanding vaccination proof

- The r/googlesheets thread above is a boarding facility building a **Rabies / Distemper / Bordetella** expiry tracker by hand.
- UK regulation requires boarding establishments to hold up-to-date vaccination records on site for the whole stay, and the **course must have been completed at least four weeks before the first day of boarding** ([Rother DC animal licensing](https://www.rother.gov.uk/licences-and-permits/animal-licensing/vaccinations/)).
- Users cite it as a top use case: *"We travel with our old dogs often and it's extremely handy to have their medical records available on my phone in case of emergency, **or boarding**."* (GreatPetCare); PetNoter markets *"perfect for a new veterinarian, pet sitter, **or boarding facility**."*

### EU/UK pet travel paperwork (AHC, rabies)

Post-Brexit this is a recurring, expensive, deadline-driven document problem — ideal for a records app:
- **AHC costs £150–£250** per trip; London vets £200–250, rural £100–150 ([VetCost](https://vetcost.co.uk/blog/how-much-does-a-pet-passport-cost-uk)).
- **A new AHC is required for every single trip** — there is no reusable document, unlike the old pet passport ([RVC](https://www.rvc.ac.uk/small-animal-vet/general-practice/services/animal-health-certificate)).
- It must be **issued within 10 days of travel**, and you must wait **21 days after a primary rabies vaccination** before it can be signed.
- The **rabies vaccine must be administered after the microchip was implanted**, and the microchip number is recorded on the certificate — so chip date vs vaccine date ordering genuinely matters and is exactly the kind of thing an app should validate.
- Dogs returning to the UK need **tapeworm treatment administered by a vet 24–120 hours before arrival** ([Roundwood Vets 2026 update](https://www.roundwoodvets.co.uk/single-post/eu-pet-travel-update)).

A "Travel Readiness" checklist that computes these date windows from stored rabies/microchip/tapeworm records is a feature **no competitor in this review offers**.

### Pet insurance claims and denials

- **Missing or incorrect documentation is a leading cause of denial**; insurers require the claim form plus **vet records and receipts**, and appeals require gathering additional documentation ([Bankrate: pet insurance claim denials](https://www.bankrate.com/insurance/pet-insurance/pet-insurance-claim-denials), [Bankrate: how to file](https://www.bankrate.com/insurance/pet-insurance/how-to-file-a-pet-insurance-claim)).
- Advice is explicitly to *"keep your receipt and details of the visit, including any **test results, medication prescriptions and treatment points**, and ensure you keep **proof of payment**."*
- Pre-existing-condition disputes turn on **historic** records — which is exactly what nobody has, because their old vet has them.

**Nobody in Part A offers an "insurance claim pack" export.** PetNoter and DogNote export a generic PDF; PetnotePlus exports CSV. A claim-shaped export (invoice + clinical notes + date range + condition history) is an open lane.

### Senior-pet medication schedules

The most emotionally intense, highest-willingness-to-pay segment in the whole dataset:
- *"this app has been invaluable for my **dog with cancer**. He's on so many meds different schedules, they need to be **paused** sometimes... It's too much to remember."* (Notepet)
- *"My dog just had **surgery with an intense 8 week cycle** to heal and 3 months of therapy. I really needed a diary/chart for records."* (PetnotePlus)
- *"With **6 aging/ailing furkids**... to track meds, symptoms, weights."* (dogcat.app)
- *"I have a **senior Bichon** with food and poop issues, and my vet wanted me to document the food and the poops."* (dogcat.app)
- *"We have a **senior cat** that we've been keeping a close eye on."* (PetDesk)
- *"I mainly use it to keep track of my pet's weights, especially those with **medical conditions** that require their weight to be monitored, and note incidents (**track my dog's occasional seizure**)."* (11pets)
- *"I can quickly add **seizure logs** for my dog."* (PetnotePlus)
- *"I need to monitor **blood sugar checks, urine checks**... I'm using a diabetic pet specific app but it is not stable. **If this app grows to that level I would likely pay.**"* (DogLog)

Requirements this segment implies: pausable/cyclable medication courses, taper and every-8-to-12-hours dosing, "gave it early" tolerance, symptom logging with graphs, and **offline reliability** — because Notepet losing a dose notification to a network blip is how you lose this user forever.

### Multi-pet households, fosters and exotics

- Fosters: *"Really helps keeping record for all my **fosters**"*; *"as a foster, **15 will not do** when I have 2 or 3 litters of kittens or puppies"*; *"I really wanted to **share my fosters' records with the charity** but can't."* (Pet Parents)
- Service dogs: *"Perfect for **Service Dog records**!!"* (Pet Parents)
- Exotics are wildly underserved and vocal: 30+ reptiles/amphibians (11pets); **3 pet rats** (PetnotePlus); **arthropods** (PetnotePlus); **snakes needing hot/cool tank temps** (PetnotePlus); rabbits (PetnotePlus, PetNoter); **horses** (dogcat.app); a **leopard gecko** (Notepet); *"pet bugs... track feedings and molting"* (PetnotePlus); a farm with **30 animals including livestock** (r/Pets). Requests include **grams as a weight unit**, length/shedding fields, and per-species task scoping.

### What makes people PAY — and refuse

**They pay for:** (i) a specific medical crisis — cancer, seizures, diabetes, post-surgery; (ii) genuinely unlimited pets; (iii) removing ads; (iv) export/documents; (v) supporting a visible, responsive indie developer. Two users volunteered money unprompted: *"Can I **buy you a coffee**?"* (dogcat.app) and *"I **donated through the link**"* (DogLog).

**They refuse when:** (i) the price exceeds their daily-app benchmark — *"that fee is more than I pay for subscriptions on apps I use on a **daily** basis"*; (ii) it's a subscription for what feels like a static utility — *"It would be better if it had a **one-off** ... rather than **yet another subscription** to add to all the other apps that want a subscription"*; (iii) previously-free functionality is retracted; (iv) **basic multi-pet is the paywall**.

**Explicit price points users named:**
| Signal | Source |
|---|---|
| **"$5 is a small fee. $50+ is ridiculous."** | 11pets |
| **"I'd be willing to pay a one-time fee ($5-$10) for an ad free version"** | dogcat.app |
| "€69 a year... more than I pay for subscriptions on apps I use daily" | 11pets |
| "$70 CAD ANNUALLY... I'd rather use an excel spreadsheet FOR FREE" | 11pets |
| "$40 a year... I can't spend that much a year. I wish they would let you **buy individual features** without a monthly cost" | DogLog |
| "the **premium price is very inexpensive**, I don't know what that other commenter is talking about. Other apps cost way more" | PetnotePlus |
| "if you want the premium it's **very reasonably priced**" ($2.99/mo tier) | PetNoter |
| "the **monthly subscription price** to unlock premium... feels a bit too high. It would be amazing if you could introduce a more affordable **'Basic' plan**" | dogcat.app |

**Reaction to per-pet / pet-count pricing is uniformly hostile.** Every single instance I found produced a downgrade or an uninstall:
- *"this newest change limits the user to **ONE AND ONLY ONE PET** unless the user wants to pay"* → 11pets 1★
- *"I can't add more than 1 pet, because adding more than one is hidden behind a paywall. Surprise surprise. **No thanks, awful app.**"* → DogNote 1★
- *"Can only store 3 pets... **Uninstalled.**"* → PetNoter
- *"I'm also disappointed I have to pay to use properly for **2 cats**"* → PetNoter
- *"The only downside... only being able to add **5 pets** without paying"* → PetnotePlus
- *"**Only allow us to add max 15 pets. What a shame.**"* → Pet Parents

And the counter-example proving the point — a dogcat.app user assumed multi-pet was paywalled and complained about price, and the developer replied: *"**You can add unlimited pets for free, that is not part of the subscription :)**"* That app has 4.3★ across 6.39K reviews. **"Unlimited pets" in Pet Care Hub's positioning is not a nice-to-have; it is the single sharpest wedge against the entire field.**

### Features with emotional pull

Photo journals dominate, and they're what converts a chore-logger into something people won't delete:
- *"The **monthly photo albums** are a fantastic feature—I enjoy looking back and seeing **how my pets have changed over time**. It's not only great for **cherishing memories** but also for analysing any differences I might have missed."* (dogcat.app)
- *"I really like the photo album. It's so nice to have **a picture from every month since I adopted my cat**, especially since **he's grown a lot**!"* (dogcat.app)
- *"I especially really love the **gallery** feature... saving my poor phone from hundreds of low-quality chihuahua pictures."* (11pets)
- *"I love the option to add a photo and **'like' or comment** on everything."* (DogLog)
- *"Really love the **notes section** to note his behavior changes, **likes and dislikes as he grows up**, and the **moments** to add special photos."* (PetNoter)
- **"Gotcha day":** *"I love that they consider **the date when we adopted them** and not just their birthdate."* (PetnotePlus)
- **New-chapter/memorial framing:** *"new start for my adored fur baby from the 01/01/25."* (DogNote)
- Anxiety relief as the emotional core: *"removes a lot of **anxiety** about me possibly forgetting or losing something important."* (11pets)
- And the memorial gap, unmet: *"I want to be able to... **archive pets that have passed**"* (Pet Parents); *"I don't think I can **disable a rat's profile after they might pass**. Making me unable to add more rats later on"* (PetnotePlus). **Deceased pets consuming a paid pet slot is a uniquely cruel bug. Archiving must be free and dignified.**

### Ranked "Jobs To Be Done"

| # | Job | Evidence strength | Key evidence |
|---|---|---|---|
| 1 | **"Don't let me screw up my pet's medication/vaccine schedule"** | Very strong | Notepet cancer/seizure users; *"keeps us from making medication mistakes"*; LPT thread's *"don't rely on your vet to remind you"* |
| 2 | **"Let my household see what's already been done so we don't double-feed"** | Very strong | Whole micro-genre exists; *"six people plus two dogs"*; whiteboard/spiral replacements |
| 3 | **"Give me one portable record that isn't hostage to my vet or my app vendor"** | Very strong | r/Pets *"transferable and used independently of a specific vet office"*; 11pets lockouts; PetDesk's owner-can't-edit ceiling |
| 4 | **"Produce the document someone is demanding, right now"** (new vet, boarder, sitter, insurer, emergency, border) | Very strong | *"During emergency... I was not able to retrieve the medical records, just when I needed it the most"*; e-vet lab-results save; AHC/boarding rules |
| 5 | **"Store the actual PDFs/photos of my vet paperwork and let me VIEW them"** | Strong | PetNoter's #1 praise; PetnotePlus's upload-but-can't-view bug; DogNote's top feature request |
| 6 | **"Handle ALL my animals — including the weird ones — without charging per head"** | Strong | fosters at 15-pet caps, 30 reptiles, rats, arthropods, horses, livestock |
| 7 | **"Brief my pet sitter without writing a novel"** | Strong | daycare/sitter usage; multi-household switching; GreatPetCare's positioning |
| 8 | **"Show me trends so I can catch problems early"** (weight, symptoms, seizures, glucose) | Strong | *"helps at vet appointments"*; diabetic-dog user says he'd pay; *"analysing any differences I might have missed"* |
| 9 | **"Help me survive the puppy phase"** | Moderate-strong | potty prediction, big sleepy-thumb buttons, r/puppy101 |
| 10 | **"Let me keep and enjoy the memories"** | Moderate-strong | monthly albums, gotcha day, archiving deceased pets |
| 11 | **"Track what this animal costs me"** | Moderate | expense trackers praised in PetNoter/PetnotePlus; *"COST TRACKER?"* as a delighted surprise |
| 12 | **"Get me through pet travel paperwork"** | Moderate (high value, low current awareness) | AHC £150–250/trip, per-trip, 10-day and 21-day windows |

### Things people explicitly said they'd pay for

1. **Ad removal** — *"I'd be willing to pay a **one-time fee ($5-$10)** for an ad free version."*
2. **Graphable custom medical metrics** — *"I really need a graph like the temp feature... **If this app grows to that level I would likely pay.**"*
3. **Unlimited/high pet counts for fosters, breeders, exotics keepers.**
4. **Export/PDF for the vet** — *"Would also love to see an export feature so I can **print a PDF with his diet and provide it to his vet**."*
5. **Document storage that actually works** — the decisive factor in PetNoter's best review.
6. **Deep customisation** — *"Premium is definitely worth it"* (PetnotePlus), driven by custom typed fields.
7. **Supporting a responsive indie dev** — unsolicited offers to donate/buy coffee.
8. **A cheaper middle "Basic" tier** — requested by name.
9. **One-time lifetime purchase** — requested repeatedly across DogNote, PetnotePlus (×3), dogcat.app, DogLog. This is the most under-served pricing demand in the category.

---

## PART C — GROWTH REALITY CHECK

### How big does a pet-tracking app actually get? Honest answer: not very.

**Verified download tiers (Google Play, 15 Sep 2026):**

| Tier | Apps |
|---|---|
| 10M+ | Chewy (retail) |
| 5M+ | PetSmart (retail), **Dogo (training)** |
| 1M+ | **PetDesk (vet-distributed)**, Tractive (hardware), Petco (retail) |
| 500K+ | **11pets** |
| 100K+ | DogLog, dogcat.app, VitusVet, Tractive-adjacent, Pawp, PetsApp |
| 50K+ | PetnotePlus |
| 10K+ | PetNoter, DogNote, GreatPetCare, Pet Parents |
| ≤1K | Notepet, Who Fed The Dog?, Vet Record, I Fed the Pet, Kibbl |

### Has any standalone pet-record app exceeded 1M downloads?

**Yes — exactly one, and it isn't really standalone: PetDesk (1M+, 4.8★, 31.7K reviews).** And it got there by **not being a consumer acquisition play at all**. It is sold to veterinary clinics, and clinics invite their clients. Reviewers say so directly: *"I initially received an **invitation from our veterinarian** to use the app"* and *"The clinic where I work has started using PetDesk and we have a lot of positive experiences from the clients."* The dependency is total — *"As long as your pet's providers use this, ALL of your pets' needs can be taken care of"* versus *"If the providers don't respond, this is essentially a place to keep a list of your pets."*

**Everything that tried pure consumer distribution stalled at 100K–500K**, and 11pets's 500K came with a 2.2★ rating that is now actively repelling new installs.

**The other 1M+/5M+ pet apps are all funded by something other than the record:** Chewy/Petco/PetSmart by retail margin, Tractive by hardware + a ~$108/yr subscription, Dogo by a genuinely high-willingness-to-pay outcome (a trained dog) at up to $299.99 IAP.

**Dogo is the honest ceiling benchmark:** 5M+ downloads, 129K reviews, and Sensor Tower estimates of roughly **90K downloads and $150K revenue per month combined across iOS and Android** — call it **~$1.8M ARR at 5M downloads**. A records app, with weaker emotional pull than "train your dog", should model *below* that conversion rate.

**Treat top-down market numbers with suspicion.** Published "pet care apps market" figures range from **$1.2B to $2.8B for 2025** depending on vendor — [OMR Global says $1.2B](https://www.omrglobal.com/industry-reports/pet-care-apps-market), [Research and Markets says $2.74B](https://www.researchandmarkets.com/reports/6183668/pet-care-apps-market-outlook-market-share), [others say $2.8B](https://www.datainsightsmarket.com/reports/pet-care-apps-1408832) or [$1.5B](https://www.businessresearchinsights.com/market-reports/pet-care-apps-market-123827). A 2.3× spread between firms means none of them should drive a plan. Note also that these totals are dominated by **Chewy, Petco, Rover and Trupanion** — i.e. commerce and insurance, not record-keeping.

### What actually drove growth for the ones that grew

1. **Vet-channel distribution (PetDesk, VitusVet).** The only proven route past 1M. VitusVet's **4.86★ across 10,776 iOS ratings** is the highest-quality score in the entire dataset — but it **hasn't shipped on Play since 7 Nov 2025**, a real abandonment risk on an otherwise superb asset.
2. **ASO on high-intent long-tail queries (dogcat.app).** A single unpaid developer reached 100K+ installs and 6.39K reviews, and now ranks #1 for "pet care tracker", "dog feeding tracker shared" *and* "pet vaccination records". This is the most replicable playbook for a new entrant.
3. **Community launch (dogcat.app again).** Publicly launched on Reddit, cleared with mods, asking *"what is missing?"*.
4. **Ferocious developer responsiveness as a growth loop.** dogcat.app fixed a weight-saving bug **within hours** of a review — *"within just a couple hours of my comment, the bug was fixed"* — and the user rewrote the review to 5★. Multiple reviewers upgraded ratings after replies. In a category where 11pets ignores support emails and PetNoter's support address bounced, **replying is a differentiator.**
5. **Acquisition/consolidation, not organic scale (Pawprint, Whistle).** Pawprint was acquired at **65,000 users / 13,000 clinics** — a modest number that nonetheless represented category leadership in 2020. Whistle is being shut down and migrated into Tractive.
6. **Hardware attach (Tractive).** Not available to a software-only product.

### Evidence of "import from 11pets" migration flows

**I found no app offering an 11pets importer.** I searched for it specifically and the result was negative — the only migration guidance returned was generic B2B pet-business-software advice from [MoeGo](https://www.moego.pet/blog/switch-pet-business-software-without-downtime).

What does exist is the raw material for one: **11pets publishes an official data-export flow** — menu (☰) → profile name → **"Export your data"** at bottom-left, documented at [11pets.com/en/news/export-data](https://www.11pets.com/en/news/export-data), which *"will generate a file containing all your stored information."*

**This is an unclaimed, high-intent acquisition channel.** There are 500K+ 11pets installs, a 2.2★ rating, thousands of users publicly saying they're leaving, and a documented export file nobody has written a parser for. An "Import from 11pets" landing page plus file-upload flow would capture demand that currently has nowhere to go — several reviewers say they searched for an alternative and came back because *"I'm still locked out"* and *"I haven't found anything that compared."*

Secondary migration targets with the same dynamic: **GreatPetCare (2.3★)**, **Pet Parents (abandoned since June 2023, broken share links)**, **Notepet (2.6★)**, and **Kibbl (v1.0 since 2022)**.

---

## SYNTHESIS

### 1. Competitive-gap summary — what NOBODY does well

1. **Nobody combines daily shared logging with a real health record.** DogLog/DogNote/dogcat.app do the daily log; PetNoter/Pet Parents/GreatPetCare do the record; PetDesk does the clinic relationship. Users prove the gap by installing 3–5 apps in sequence. **This is Pet Care Hub's entire thesis, and it is validated.**
2. **Nobody does reliable multi-device sync for shared care.** The one app with real shared-care traction (DogLog, 100K+) has users describing the failure in painful detail. A shared-care app whose sync is eventually-consistent-on-app-restart is broken at the premise level.
3. **Nobody does documents properly.** PetDesk can't save them. DogNote has none. PetnotePlus lets you upload but **not view** them. GreatPetCare's upload save-button is off-screen. 11pets can't get all data into its shareable reports. PetNoter is the only one that does it — and paywalls it, which is *why* it wins that one review.
4. **Nobody offers a purpose-shaped export.** Everyone ships "a PDF". Nobody ships **a boarding-kennel vaccination certificate**, **an insurance claim pack**, **a new-vet handover**, or **a sitter brief** as distinct, named artifacts. The jobs are distinct; the outputs shouldn't be identical.
5. **Nobody handles pet-travel paperwork** despite AHCs costing £150–250 **per trip** with hard 10-day and 21-day date windows that an app could compute from data it already holds.
6. **Nobody treats bereavement decently.** Archiving a deceased pet either isn't possible or consumes a paid slot.
7. **Nobody offers a credible one-time purchase.** Requested across at least five different apps' reviews; universally refused.
8. **Reminders are unreliable across the entire category.** Late notifications (DogLog), alerts that die past one medication (Notepet), reminders that won't clear (PetDesk), recurring tasks that delete their own future occurrences (PetNoter), monthly reminders firing daily (Notepet). **Being boringly correct about notifications would be a differentiator.**
9. **No time-of-day-aware scheduling.** dogcat.app's "every 3 hours" wakes people at 3am. Nobody supports quiet hours or specific clock times well.
10. **No bulk operations.** No "mark done for all pets", no shared medication across pets, no expense split across pets — despite multi-pet households being the core user.
11. **Android/iOS parity is routinely broken.** DogNote is 4.71★ on iOS vs 4.3★ on Android with Android-only crashes and a photo-viewing disparity users noticed themselves. 11pets is 3.29★/77 ratings on iOS vs 2.2★/5.69K on Android. Apple Watch/Siri/widget support is almost always iOS-first.
12. **Nobody has built the 11pets migration bridge.**

### 2. Table stakes — what every serious app must have

- **Unlimited pets in the free tier.** Non-negotiable, based on six distinct apps punished for gating this.
- **Free archiving of deceased pets that does not consume a slot.**
- **Rock-solid, near-real-time multi-device sync** with clear "who logged this, and when" attribution.
- **Reliable, offline-capable notifications**: exact times of day, quiet hours, repeat-until-done, snooze/skip/give-early tolerance, per-pet and grouped.
- **Full medical record**: vaccinations (with expiry + group names like Lepto4), medications with pause/cycle/taper, weight + growth charts, vet visits, conditions, labs, microchip, insurance policy, allergies.
- **Document vault that stores, previews AND displays** PDFs/photos in-app, offline, at the vet counter.
- **Daily logging**: food, water, treats, walk, pee, poop, sleep, meds, grooming, training, custom — with one-tap entry, large targets, retroactive time entry, and edit/delete.
- **Household sharing via invite link**, no pre-existing account required, no per-seat charge.
- **Export**: PDF and CSV, per-pet, date-ranged.
- **Widgets on both platforms** + Apple Watch/Siri; Android widget is the most-requested missing feature in the category's best shared-care app.
- **Custom fields/events with types** (text, number, rating, checkbox, photo) and **species-scoped tasks** that don't leak across animals.
- **Free-text fallback on every dropdown** (breed, food brand) — rescue mixes exist.
- **Multi-species including exotics**, with grams and length units.
- **Dark mode with an explicit toggle.**
- **Responsive human support**, and an obvious in-app channel. It is a rating driver.
- **Offline-first read AND write.**

### 3. Delight list — features users rave about anywhere

- **Monthly photo albums / growth timelines** — *"a picture from every month since I adopted my cat"*. The single most emotionally cited feature.
- **Likes and comments on logged events** (DogLog) — turns a chore into a family feed.
- **Gotcha day** alongside birthday.
- **Potty prediction** from logged patterns (Puppy Potty Log, DogLog premium).
- **Supply inventory that warns you before you run out** — *"a food inventory that tells you when you are about to run out!"*
- **Graphs on any custom metric** — the feature a diabetic-dog owner said he'd pay for.
- **Bulk logging + CSV export** — PetnotePlus's power-user trio.
- **Multi-household switching for sitters** (I Fed the Pet).
- **Barcode food scanning checked against logged allergies** (Petio) — genuinely novel.
- **Web portal + tablet sync** — *"I LOVE that it syncs with my iPad and that I can use their web portal too."*
- **In-app chat with a developer who actually replies** — repeatedly converted 3★ into 5★.
- **Big sleepy-thumb buttons** for 3am puppy logging.
- **Wearable integration** — *"a way to attach my cat's fitness tracker to this app it would be perfect."*

### 4. Top 10 review complaints across the category — design against these

1. **Sync failures and stale data in shared households** — *"I get a text from my wife asking if the last time the dog went potty was 6 hours ago."*
2. **Notifications late, missing, undismissable, or wrong-frequency** — the most widespread defect class in the dataset, present in DogLog, Notepet, PetDesk, PetNoter, DogNote and Pet Parents.
3. **Pet-count paywalls** (1, 3, 5, 15) — produced an uninstall or a 1★ in every observed instance.
4. **Retracting previously-free features / breaking "lifetime" promises** — the 11pets extinction event.
5. **Data loss, lockout and scrambled migrations** — *"locking me out of literally years worth of info"*; *"all data vanished post-exit."*
6. **Documents you can upload but can't view, or can't upload at all** — the most consistently botched high-value feature.
7. **Price anchored above the daily-app benchmark, subscription-only** — *"$5 is a small fee. $50+ is ridiculous"*; *"yet another subscription."*
8. **Records unavailable at the exact moment of need** — *"During emergency... I was not able to retrieve the medical records, just when I needed it the most."*
9. **Clunky, "databasey", non-obvious IA** — hidden save buttons behind the keyboard or system bar, unscrollable notes, duplicate entry points, no bulk actions, buried multi-pet views.
10. **iOS/Android parity gaps and platform-specific breakage** — Android-only crashes, iOS-only photo galleries, iOS-first widgets/Watch.

**Honourable mention (11th):** ignored support email. 11pets left users unanswered; PetNoter's support address bounced; both cost them stars. dogcat.app answered within hours and got review upgrades. Support responsiveness is, measurably, a growth channel in this category.

---

### The strategic read

The incumbent is sitting on **500K installs and a 2.2★ rating** with thousands of users publicly announcing they're leaving, a documented data-export file, and **no competitor offering them an import path**. The one app that broke 1M did it through vet clinics, not consumers, and its users complain they can't edit their own records. The best-loved apps are single developers with 100K installs, no widgets, and broken sync.

Pet Care Hub's stated shape — shared household logging + full health record + exports, **unlimited pets**, both platforms — is precisely the combination the evidence says people repeatedly search for, install 3–5 apps trying to assemble, and fail to find. The differentiators that matter most, in order: **unlimited pets free**, **sync and notifications that are boringly correct**, **documents you can actually open at the vet counter**, **purpose-shaped exports (boarding / insurance / new vet / sitter)**, and **an 11pets import button.**

**Sources:** [11pets Play](https://play.google.com/store/apps/details?id=com.m11pets.elevenpets) · [11pets App Store](https://apps.apple.com/us/app/11pets-pet-care/id1232470530) · [11pets pricing](https://www.11pets.com/en/price) · [11pets data export](https://www.11pets.com/en/news/export-data) · [11pets on Tracxn](https://tracxn.com/d/companies/11pets/__vqgzhWntDrOqt1rtkKD2swYiE94y54tDmDLi9SK6byI) · [DogLog Play](https://play.google.com/store/apps/details?id=com.mobikode.dog) · [DogLog App Store](https://apps.apple.com/us/app/doglog-track-your-dogs-life/id1229529595) · [doglogapp.com](https://www.doglogapp.com/) · [PetNoter Play](https://play.google.com/store/apps/details?id=com.petnoter) · [PetNoter App Store](https://apps.apple.com/us/app/petnoter-all-pet-care-tracker/id1580581463) · [petnoter.com](https://petnoter.com/) · [Pet Care Tracker (dogcat.app)](https://play.google.com/store/apps/details?id=dogcat.app.android) · [DogNote Play](https://play.google.com/store/apps/details?id=app.dognote) · [DogNote App Store](https://apps.apple.com/us/app/dognote-pet-journal-walks/id1527756855) · [dognote.app](https://dognote.app/) · [PetnotePlus Play](https://play.google.com/store/apps/details?id=com.lancerdog.petnote_plus) · [PetnotePlus App Store](https://apps.apple.com/us/app/petnoteplus-pet-care-tracker/id1553584485) · [Notepet Play](https://play.google.com/store/apps/details?id=io.notepet) · [Pet Parents Play](https://play.google.com/store/apps/details?id=com.petparents.pet) · [PetDesk Play](https://play.google.com/store/apps/details?id=com.locai.petpartner) · [GreatPetCare Play](https://play.google.com/store/apps/details?id=com.pawprint.mobile) · [Pawprint Crunchbase](https://www.crunchbase.com/organization/pawprint) · [Pawprint Dealroom](https://app.dealroom.co/companies/pawprint) · [Vet Record Play](https://play.google.com/store/apps/details?id=vetrecord.app) · [I Fed the Pet App Store](https://apps.apple.com/us/app/i-fed-the-pet-feed-tracker/id6762333960) · [I Fed the Pet Play](https://play.google.com/store/apps/details?id=com.drufftech.ifedthepet) · [Kibbl App Store](https://apps.apple.com/us/app/kibbl/id1603040066) · [Kibbl Play](https://play.google.com/store/apps/details?id=com.kibbl.test) · [PetPilot App Store](https://apps.apple.com/us/app/petpilot/id6749286937) · [Pet Feeder App Store](https://apps.apple.com/us/app/pet-feeder-app/id6758022385) · [petfeeder.app](https://petfeeder.app/) · [Fed? App Store](https://apps.apple.com/us/app/fed-pet-feeding-tracker-log/id6760776848) · [Pawfolio App Store](https://apps.apple.com/us/app/pawfolio-pet-feeding-tracker/id6743056578) · [Who Fed The Dog? Play](https://play.google.com/store/apps/details?id=com.whofedthedog.app) · [Dogo Play](https://play.google.com/store/apps/details?id=app.dogo.com.dogo_android) · [Dogo Sensor Tower](https://app.sensortower.com/overview/app.dogo.com.dogo_android?country=US) · [Dogster Dogo review](https://www.dogster.com/lifestyle/dogo-app-review/) · [Woofz Wikipedia](https://en.wikipedia.org/wiki/Woofz) · [Petio AI vet chat comparison](https://www.petiogo.com/blog/best-ai-vet-chat-apps) · [Tably CBC](https://www.cbc.ca/news/canada/calgary/tably-calgary-alberta-artificial-intelligence-cats-1.7025398) · [Tably Forbes](https://www.forbes.com/sites/marksparrow/2026/04/14/can-the-power-of-ai-help-you-to-chat-with-your-cat/) · [Chewy Play](https://play.google.com/store/apps/details?id=com.chewy.android) · [Petco Play](https://play.google.com/store/apps/details?id=com.petco.mobile) · [PetSmart Play](https://play.google.com/store/apps/details?id=com.petsmart.consumermobile) · [Tractive Play](https://play.google.com/store/apps/details?id=com.tractive.android.gps) · [Tractive SafeWise review](https://www.safewise.com/kids-safety/gps-trackers/tractive/) · [Whistle shutdown (Engadget)](https://www.engadget.com/wearables/whistle-pet-trackers-are-shutting-down-next-month-212828325.html) · [Rover Care / pet app market](https://www.cognitivemarketresearch.com/pet-care-app-market-report) · [r/LifeProTips vaccination spreadsheet](https://www.reddit.com/r/LifeProTips/comments/1ad9bp1/lpt_make_a_spreadsheet_to_keep_track_of_your_pets/) · [r/Pets medical history](https://www.reddit.com/r/Pets/comments/uo7xl3/how_do_you_track_your_pets_medical_history/) · [r/Pets farm tracking](https://www.reddit.com/r/Pets/comments/i6r25e/does_anyone_know_of_an_app_for_tracking_pet/) · [r/puppy101 vaccination dates](https://www.reddit.com/r/puppy101/comments/yuq1p9/pup_parents_how_do_you_keep_track_of_vaccination_dates/) · [r/androidapps pet log](https://www.reddit.com/r/androidapps/comments/nxer91/what_app_to_log_your_pet_condition_regularly_ie/) · [r/googlesheets boarding vaccinations](https://www.reddit.com/r/googlesheets/comments/x0rajo/need_a_sheet_to_track_expired_vaccinations/) · [r/Bloodhound dogcat.app launch](https://www.reddit.com/r/Bloodhound/comments/ov6ce5/i_created_a_free_pet_health_tracking_app_would/) · [Bankrate claim denials](https://www.bankrate.com/insurance/pet-insurance/pet-insurance-claim-denials) · [Bankrate filing a claim](https://www.bankrate.com/insurance/pet-insurance/how-to-file-a-pet-insurance-claim) · [RVC Animal Health Certificate](https://www.rvc.ac.uk/small-animal-vet/general-practice/services/animal-health-certificate) · [VetCost pet passport costs](https://vetcost.co.uk/blog/how-much-does-a-pet-passport-cost-uk) · [Roundwood Vets 2026 EU rules](https://www.roundwoodvets.co.uk/single-post/eu-pet-travel-update) · [Rother DC boarding vaccination rules](https://www.rother.gov.uk/licences-and-permits/animal-licensing/vaccinations/) · [OMR pet care apps market](https://www.omrglobal.com/industry-reports/pet-care-apps-market) · [Research and Markets pet care apps](https://www.researchandmarkets.com/reports/6183668/pet-care-apps-market-outlook-market-share) · [Business Research Insights](https://www.businessresearchinsights.com/market-reports/pet-care-apps-market-123827) · [MoeGo switching guide](https://www.moego.pet/blog/switch-pet-business-software-without-downtime)