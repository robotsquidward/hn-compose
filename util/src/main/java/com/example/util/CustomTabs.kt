package com.example.util

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

/**
 * Callback to notify the VM to launch a Custom Tabs Intent to selected URL.
 */
fun storyClicked(url: String?, context: Context) {
    url?.also {
        CustomTabsIntent.Builder()
            .build()
            .apply {
                launchUrl(context, Uri.parse(url))
            }
    }
}