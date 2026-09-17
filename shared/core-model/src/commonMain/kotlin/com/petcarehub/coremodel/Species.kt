package com.petcarehub.coremodel

/**
 * What kind of animal a pet is.
 *
 * Product law 7 — "every dropdown has a free-text fallback" — is structural here, not a UI
 * concern: [Other] carries the user's own words, so a rescue mix, an arthropod or a horse is
 * representable without us shipping a template for it. A UI that offers only [Known] values
 * is a bug, not a simplification.
 *
 * Species is also what scopes care tasks. A task defined for a dog must never be offered for
 * a fish, so comparisons go through [matches] rather than through display text.
 */
sealed interface Species {

    /** Stable key used for storage and task scoping. Never shown to the user directly. */
    val key: String

    /** A species we ship templates and task lists for. */
    enum class Known(override val key: String) : Species {
        DOG("dog"),
        CAT("cat"),
        ;

        companion object {
            fun fromKey(key: String): Known? = entries.firstOrNull { it.key == key }
        }
    }

    /**
     * Anything else, in the owner's own words.
     *
     * [label] is stored verbatim and shown verbatim. It is never normalised into a [Known]
     * value: someone who typed "Bearded dragon" should not find their pet relabelled.
     */
    data class Other(val label: String) : Species {
        override val key: String = OTHER_KEY

        init {
            require(label.isNotBlank()) { "Species.Other requires a non-blank label" }
        }
    }

    companion object {
        const val OTHER_KEY: String = "other"
    }
}

/**
 * True when two species are the same for task-scoping purposes.
 *
 * Two [Species.Other] values match only when their labels match exactly, so a "Horse" task
 * never leaks onto a "Tarantula".
 */
fun Species.matches(other: Species): Boolean = when {
    this is Species.Other && other is Species.Other -> label == other.label
    this is Species.Other || other is Species.Other -> false
    else -> key == other.key
}
