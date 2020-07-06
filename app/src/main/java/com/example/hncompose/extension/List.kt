package com.example.hncompose.extension

import androidx.compose.frames.ModelList
import com.example.hackernetwork.HNItem

/**
 * Extension function to make toggling favorites easier.
 *
 * @param story the [StoryItem] to toggle the 'favorite' Boolean on.
 */
fun ModelList<HNItem>.toggleFavorite(story: HNItem): ModelList<HNItem> {
    val index = this.indexOf(story)
    this[index].favorite = !this[index].favorite
    return this
}