package com.example.hncompose.snippets

import com.example.hncompose.Story

/**
 * Extension function to make toggling favorites easier.
 *
 * @param story the [Story] to toggle the 'favorite' Boolean on.
 */
fun List<Story>.toggleFavorite(story: Story) {
    this[this.indexOf(story)].favorite = !story.favorite
}