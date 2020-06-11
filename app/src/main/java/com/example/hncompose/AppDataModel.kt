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

interface AppDataStatusHolder {
    var topStories: ModelList<HNItem>
    var newStories: ModelList<HNItem>
    var jobStories: ModelList<HNItem>

    var topStoryIdChunks: List<List<Int>>
    var newStoryIdChunks: List<List<Int>>
    var jobStoryIdChunks: List<List<Int>>
}

@Model
object AppDataStatus: AppDataStatusHolder {
    override var topStories: ModelList<HNItem> = modelListOf()
    override var newStories: ModelList<HNItem> = modelListOf()
    override var jobStories: ModelList<HNItem> = modelListOf()

    override var topStoryIdChunks: List<List<Int>> = emptyList()
    override var newStoryIdChunks: List<List<Int>> = emptyList()
    override var jobStoryIdChunks: List<List<Int>> = emptyList()
}

@Model
object MockAppDataStatus: AppDataStatusHolder {
    override var topStories: ModelList<HNItem> = modelListOf(
        HNItem(id = 0, title = "Title 1", url = "google.com"),
        HNItem(id = 1, title = "Title 2", url = "google.com"),
        HNItem(id = 2, title = "Title 3", url = "google.com"))
    override var newStories: ModelList<HNItem> = modelListOf(
        HNItem(id = 0, title = "Title 1", url = "google.com"),
        HNItem(id = 1, title = "Title 2", url = "google.com"),
        HNItem(id = 2, title = "Title 3", url = "google.com"))
    override var jobStories: ModelList<HNItem> = modelListOf(
        HNItem(id = 0, title = "Title 1", url = "google.com"),
        HNItem(id = 1, title = "Title 2", url = "google.com"),
        HNItem(id = 2, title = "Title 3", url = "google.com"))

    override var topStoryIdChunks: List<List<Int>> = emptyList()
    override var newStoryIdChunks: List<List<Int>> = emptyList()
    override var jobStoryIdChunks: List<List<Int>> = emptyList()
}

/**
 * Navigate to different screens. Will change with Navigation support.
 */
fun navigateTo(destination: Screen) {
    AppScreenStatus.currentScreen = destination
}