package com.example.hncompose

import androidx.compose.Model
import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf

@Model
data class Story(
    val title: String,
    val details: String,
    var favorite: Boolean
)

@Model
object TopStoryModel {
    var storyList: ModelList<Story> = modelListOf(
        Story(
            title = "Hello World! 1",
            details = "More details about the story.",
            favorite = true
        ),
        Story(
            title = "Hello World! 2",
            details = "More details about the story.",
            favorite = true
        ),
        Story(
            title = "Hello World! 3",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 4",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 5",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 6",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 7",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 8",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 9",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 10",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 11",
            details = "More details about the story.",
            favorite = false
        ),
        Story(
            title = "Hello World! 12",
            details = "More details about the story.",
            favorite = false
        )
    )
}