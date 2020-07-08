package com.example.hackernetwork

import android.graphics.Bitmap
import android.util.Log
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URI

/**
 * Util function to download a favicon given a url.
 */
public suspend fun downloadImageFrom(imageUrl: String?): Bitmap? {
    return withContext(Dispatchers.IO) {
        val uri = URI(imageUrl ?: "https://news.ycombinator.com")
        val domain = uri.host
        val domainUrl = if (domain.startsWith("www.")) domain.substring(4) else domain
        val iconUrl = "https://icons.duckduckgo.com/ip2/${domainUrl}.ico"
        try {
            return@withContext Picasso.get().load(iconUrl).get()
        } catch (exception: IOException) {
            Log.d("PICASSO_EX", exception.localizedMessage ?: "Picasso get() failed.")
            return@withContext null
        }
    }
}