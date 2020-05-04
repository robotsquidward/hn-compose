package com.example.hncompose

import androidx.compose.Model
import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf
import com.example.hackernetwork.HNItem

@Model
object AppScreenStatus {
    var currentScreen: Screen = Screen.Top
}

sealed class Screen {
    object Top: Screen()
    object New: Screen()
    object Jobs: Screen()
}

@Model
object AppDataStatus {
    var topStories: ModelList<HNItem> = modelListOf()
    var newStories: ModelList<HNItem> = modelListOf()
    var jobStories: ModelList<HNItem> = modelListOf()

    var topStoryIdChunks: List<List<Int>> = emptyList()
    var newStoryIdChunks: List<List<Int>> = emptyList()
    var jobStoryIdChunks: List<List<Int>> = emptyList()
}

/**
 * Navigate to different screens. Will change with Navigation support.
 */
fun navigateTo(destination: Screen) {
    AppScreenStatus.currentScreen = destination
}