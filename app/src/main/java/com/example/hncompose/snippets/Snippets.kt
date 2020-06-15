package com.example.hncompose.snippets

import androidx.compose.Composable
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import com.example.hackernetwork.HNItem
import com.example.hncompose.LoadMoreCard
import com.example.hncompose.StoryCard

@Composable
fun ScrollingStoryList(stories: List<HNItem>) {
    VerticalScroller {
        Column {
            stories.forEach { story ->
                StoryCard(story = story, storyClickedListener = {})
            }
            if (stories.isNotEmpty()) {
                LoadMoreCard(loading = false) {}
            }
        }
    }
}

@Composable
fun AdapterStoryList(stories: List<HNItem>) {
    AdapterList(data = stories) { story ->
        StoryCard(story = story, storyClickedListener = {})
    }
}

//@Composable
//fun CustomVerticalStoryList(stories: List<HNItem>) {
//    VerticalList(data = stories) { story ->
//        StoryCard(story = story, storyClickedListener = {})
//    }
//}