package com.petcarehub.coremodel

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

/**
 * These pin product law 7 (free-text fallback, species-scoped tasks never leak).
 * If one of these fails, the fix is the code, not the test.
 */
class SpeciesTest {

    @Test
    fun other_keeps_the_owners_own_words() {
        val species = Species.Other("Bearded dragon")
        assertEquals("Bearded dragon", species.label)
        assertEquals(Species.OTHER_KEY, species.key)
    }

    @Test
    fun other_rejects_a_blank_label() {
        assertFailsWith<IllegalArgumentException> { Species.Other("  ") }
    }

    @Test
    fun known_species_match_themselves_and_nothing_else() {
        assertTrue(Species.Known.DOG.matches(Species.Known.DOG))
        assertFalse(Species.Known.DOG.matches(Species.Known.CAT))
    }

    @Test
    fun tasks_never_leak_between_two_different_free_text_species() {
        assertTrue(Species.Other("Horse").matches(Species.Other("Horse")))
        assertFalse(Species.Other("Horse").matches(Species.Other("Tarantula")))
    }

    @Test
    fun a_free_text_species_never_collapses_into_a_known_one() {
        // "Dog" typed by hand is still Other. Silently promoting it would relabel the pet.
        assertFalse(Species.Other("Dog").matches(Species.Known.DOG))
        assertFalse(Species.Known.DOG.matches(Species.Other("Dog")))
    }

    @Test
    fun unknown_keys_do_not_resolve() {
        assertEquals(Species.Known.CAT, Species.Known.fromKey("cat"))
        assertNull(Species.Known.fromKey("horse"))
    }
}
