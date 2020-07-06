package com.example.hncompose.extension

import androidx.compose.frames.ModelList
import com.example.hackernetwork.HNItem

/**
 * Extension function to make toggling favorites easier.
 *
 * @param story the [StoryItem] to toggle the 'favorite' Boolean on.
 */
fun ModelList<HNItem>.toggleFavorite(story: HNItem): ModelList<HNItem> {
    val updatedList = this
    val index = updatedList.indexOf(story)
    updatedList[index].favorite = !updatedList[index].favorite
    return updatedList
}