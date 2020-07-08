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
            HNItem(id = 0, title = "New MacBook Released", url = "https://www.apple.com", favorite = true),
            HNItem(id = 1, title = "New MacBook Review!", url = "https://www.theverge.com"),
            HNItem(id = 2, title = "New MacBook by the Numbers", url = "https://www.bloomberg.com"),
            HNItem(id = 3, title = "New MacBook Repair Guide", url = "https://www.ifixit.com")
        )
        newStories = modelListOf(
            HNItem(id = 0, title = "New MacBook Released", url = "https://www.apple.com", favorite = true),
            HNItem(id = 1, title = "New MacBook Review!", url = "https://www.theverge.com"),
            HNItem(id = 2, title = "New MacBook by the Numbers", url = "https://www.bloomberg.com"),
            HNItem(id = 3, title = "New MacBook Repair Guide", url = "https://www.ifixit.com")
        )
        jobStories = modelListOf(
            HNItem(id = 0, title = "New MacBook Released", url = "https://www.apple.com", favorite = true),
            HNItem(id = 1, title = "New MacBook Review!", url = "https://www.theverge.com"),
            HNItem(id = 2, title = "New MacBook by the Numbers", url = "https://www.bloomberg.com"),
            HNItem(id = 3, title = "New MacBook Repair Guide", url = "https://www.ifixit.com")
        )

        topStoryIdChunks = emptyList()
        newStoryIdChunks = emptyList()
        jobStoryIdChunks = emptyList()

        loading = false
    }
}