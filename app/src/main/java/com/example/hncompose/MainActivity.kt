package com.example.hncompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp

@Model
object TopStoryModel {
    val storyList: List<Story> = listOf(
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        ),
        Story(
            title = "Hello World!",
            details = "More details about the story."
        )
    )
}

data class Story(
    val title: String = "",
    val details: String = ""
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LandingScreen()
        }
    }
}

@Composable
fun LandingScreen() {
    MaterialTheme {
        Scaffold(
            scaffoldState = remember { ScaffoldState() },
            topAppBar = {
                TopAppBar(
                    title = {
                        Text(text = "HN Compose")
                    }
                )
            },
            bodyContent = {
                StoryList(stories = TopStoryModel.storyList)
            }
        )
    }
}

@Composable
fun StoryList(stories: List<Story>) {
    VerticalScroller {
        Column {
            for (story in stories) {
                BasicCard(story = story)
            }
        }
    }
}

@Composable
fun BasicCard(story: Story) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                asset = vectorResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .padding(end = 4.dp)
                    .gravity(Alignment.CenterVertically)
            )
            Column {
                Text(
                    text = story.title,
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = story.details,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    LandingScreen()
}