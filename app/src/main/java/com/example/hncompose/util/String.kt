package com.example.hncompose.util

import java.net.URI

/**
 * Extension on [String] that provides a [String] representing a short url. Returns empty
 * strings in error/edge cases.
 */
val String?.shortUrlString: String
    get() {
        val urlString = this ?: return ""
        val uri = URI(urlString)
        val domain = uri.host
        val domainUrl = if (domain.startsWith("www.")) domain.substring(4) else domain
        return "($domainUrl)"
    }