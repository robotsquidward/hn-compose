package com.example.hncompose.model

/**
 * Enum class representing a full-screen [Screen] in the App.
 *
 * @property title the name of a screen for the Top App Bar
 */
enum class Screen(
    val title: String
) {
    TopNews(title = "Top News"),
    Favorites(title = "Favorites")
}
