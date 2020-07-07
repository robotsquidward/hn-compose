package com.example.hncompose.model

/**
 * Sealed class representing a full-screen [Screen] in the App.
 *
 * @property title the name of a screen for the Top App Bar
 */
sealed class Screen(
    val title: String
) {
    object TopNews: Screen(title = "Top News")
    object Favorites: Screen(title = "Favorites")
}