package com.example.hncompose.snippets

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.example.hncompose.R

/**
 * Callback to notify the VM to launch a Custom Tabs Intent to selected URL.
 */
fun storyClicked(url: String?, context: Context, toolbarColor: Int = context.getColor(R.color.purple500)) {
    url?.also {
        CustomTabsIntent.Builder()
            .setToolbarColor(toolbarColor)
            .build()
            .apply {
                launchUrl(context, Uri.parse(url))
            }
    }
}