package com.example.hncompose.extension

import androidx.compose.frames.modelListOf
import com.example.hackernetwork.HNItem
import com.example.hncompose.data.AppDataStatus

/**
 * An extension function for [AppDataStatus] to mock out data for Previews.
 */
fun AppDataStatus.mock(): AppDataStatus {
    return this.apply {
        topStories = modelListOf(
            HNItem(id = 0, title = "Title 1", url = "https://www.google.com", favorite = true),
            HNItem(id = 1, title = "Title 2", url = "https://www.google.com"),
            HNItem(id = 2, title = "Title 3", url = "https://www.google.com")
        )
        newStories = modelListOf(
            HNItem(id = 0, title = "Title 1", url = "https://www.google.com", favorite = true),
            HNItem(id = 1, title = "Title 2", url = "https://www.google.com"),
            HNItem(id = 2, title = "Title 3", url = "https://www.google.com")
        )
        jobStories = modelListOf(
            HNItem(id = 0, title = "Title 1", url = "https://www.google.com", favorite = true),
            HNItem(id = 1, title = "Title 2", url = "https://www.google.com"),
            HNItem(id = 2, title = "Title 3", url = "https://www.google.com")
        )

        topStoryIdChunks = emptyList()
        newStoryIdChunks = emptyList()
        jobStoryIdChunks = emptyList()

        loading = false
    }
}