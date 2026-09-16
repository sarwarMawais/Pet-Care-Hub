# ADR 0005 — Unlimited pets in the free tier, priced per household

**Status:** Accepted
**Date:** 2026-09-15

## Context

Every competitor surveyed gates on pet count, and **every single observed instance produced an uninstall or a 1★ review**:

| App | Cap | User response |
|---|---|---|
| 11pets | 1 pet free | *"this newest change limits the user to ONE AND ONLY ONE PET unless the user wants to pay"* → 1★ |
| DogNote | 1 pet free | *"I can't add more than 1 pet… Surprise surprise. No thanks, awful app."* → 1★ |
| PetNoter | 3 pets free | *"Can only store 3 pets. Document upload & Export are pay-walled. **Uninstalled.**"* |
| PetnotePlus | 5 pets free | *"The only downside… only being able to add 5 pets without paying"* |
| Pet Parents | 15 pets | *"Only allow us to add max 15 pets. What a shame."* |

And the counter-example that proves it: a dogcat.app user assumed multi-pet was paywalled and complained about price. The developer replied *"You can add unlimited pets for free, that is not part of the subscription :)"* That app sits at **4.3★ across 6,390 reviews** and wins ASO for all three key queries.

The users who care most — fosters with three litters of kittens, keepers with 30 reptiles, a smallholder with livestock, someone with six ageing pets — are precisely the users who log daily, write reviews, and recommend the app. Gating them is gating your advocates.

Separately, users refuse subscriptions when the price exceeds their daily-app benchmark (*"€69 a year… more than I pay for subscriptions on apps I use on a daily basis"*), and the most under-served pricing demand in the category is a **one-time purchase**, requested across at least five different apps' reviews and offered by almost none.

## Decision

**Unlimited pets in the free tier, forever. Price the subscription per household, never per pet and never per person.**

| Tier | Price | Gates |
|---|---|---|
| Free | $0 | Unlimited pets, daily log, full health record, reminders, widgets, archive, household of 3, 20 documents, 1 export/month |
| Household | $29.99/yr · $3.99/mo | Unlimited members + time-boxed sitters, unlimited documents, all shaped exports, claim packs, travel readiness, albums, custom fields and graphs, CSV |
| Lifetime | $79.99 once | The same, forever |

Corollaries that follow from the same reasoning and are equally binding:

- **Archiving a deceased pet is free and never consumes a slot** (see `CLAUDE.md` law 2). A dead pet occupying a paid slot is a uniquely cruel bug, and two competitors ship it.
- **Sitters are never charged and never see the paywall.** They are distribution, not customers.
- **Never retract a free feature.** The 11pets collapse is what that looks like.

## Consequences

**Good**

- The sharpest single wedge against the entire field, and free marketing in every review comparing us to the incumbent.
- The free tier *is* the marketing budget — generous enough to earn 4.5★ and word of mouth without paid acquisition.
- Per-household pricing means adding your partner, your kids and your sitter costs nothing, which is exactly the loop that grows the user base.

**Bad**

- We give away the feature most competitors monetise, so revenue must come from **documents, exports, household size and albums** instead. Those must be genuinely good, not artificially crippled.
- Storage cost scales with pets and photos while free-tier revenue does not. Mitigate with client-side compression and the 20-document free cap; monitor cost per free user from the first week of beta.
- We can never walk this back. It is a permanent constraint on the business model, taken deliberately.

## Related

- `PLAN.md` §5 — pricing and unit economics
- `CLAUDE.md` — laws 1, 2, 9
- `research/competitors-and-users.md` — the pricing evidence
