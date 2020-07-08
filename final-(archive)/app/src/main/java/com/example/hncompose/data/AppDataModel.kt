package com.example.hncompose.data

import androidx.compose.Model
import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf
import com.example.hackernetwork.HNItem
import com.example.hncompose.model.Screen

@Model
object AppScreenStatus {
    var currentScreen: Screen = Screen.TopNews
}

@Model
object AppDataStatus {
    var topStories: ModelList<HNItem> = modelListOf()
    var newStories: ModelList<HNItem> = modelListOf()
    var jobStories: ModelList<HNItem> = modelListOf()

    var topStoryIdChunks: List<List<Int>> = emptyList()
    var newStoryIdChunks: List<List<Int>> = emptyList()
    var jobStoryIdChunks: List<List<Int>> = emptyList()

    var loading: Boolean = true
}