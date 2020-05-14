package com.example.hncompose.util

/**
 * Extension on [String] that provides a [String] representing a short url. Returns empty
 * strings in error/edge cases.
 */
val String?.shortUrlString: String
    get() {
        // todo -> actually handle urls
        return "($this)"
    }