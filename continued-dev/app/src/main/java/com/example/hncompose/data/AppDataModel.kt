package com.example.hncompose.data

import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import com.example.hackernetwork.HNItem
import com.example.hncompose.model.Screen

class AppScreenStatus {
    var currentScreen by mutableStateOf(Screen.TopNews)
}

object AppDataStatus {
    var topStories: ModelList<HNItem> = modelListOf()
    var newStories: ModelList<HNItem> = modelListOf()
    var jobStories: ModelList<HNItem> = modelListOf()

    var topStoryIdChunks: List<List<Int>> = emptyList()
    var newStoryIdChunks: List<List<Int>> = emptyList()
    var jobStoryIdChunks: List<List<Int>> = emptyList()

    var loading: Boolean = true
}