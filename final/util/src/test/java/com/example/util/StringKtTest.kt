package com.example.util

import org.junit.Test

import org.junit.Assert.*

class StringKtTest {

    @Test
    fun getShortUrlString() {
        assertEquals("(google.com)", "https://google.com".shortUrlString)
        assertEquals("(google.com)", "https://www.google.com".shortUrlString)
        assertEquals("", "google.com".shortUrlString)
        val nullString: String? = null
        assertEquals("", nullString.shortUrlString)
    }
}