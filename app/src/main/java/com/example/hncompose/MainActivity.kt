package com.example.hncompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.hncompose.TopNewsStatus.stories

@Model
object TopNewsStatus {
    val stories: List<String> = listOf(
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story One",
            "Story Two",
            "Story Three"
    )
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TopNewsScreen()
            }
        }
    }
}

@Composable
fun TopNewsScreen(scaffoldState: ScaffoldState = remember { ScaffoldState() }) {
    Scaffold(
            scaffoldState = scaffoldState,
            topAppBar = {
                TopAppBar(
                        title = { Text(text = "HN Compose") }
                )
            },
            bodyContent = { TopNewsScreenBody(posts = stories) }
    )
}

@Composable
fun TopNewsScreenBody(
        posts: List<String>) {
    VerticalScroller {
        Column {
            posts.forEach { post ->
                StoryCard(story = post)
            }
        }
    }
}

@Composable
fun StoryCard(story: String) {
    Card(
            shape = RoundedCornerShape(size = 6.dp),
            elevation = 4.dp,
            modifier = Modifier
                    .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 4.dp,
                            bottom = 4.dp
                    )
                    .fillMaxWidth()
    ) {
        Clickable(
                modifier = Modifier.ripple(),
                onClick = {/* todo */}
        ) {
            Column {
                Text(
                    text = "Hello $story!",
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp,
                            bottom = 4.dp
                        )
                )
                Text(
                    text = "($story)",
                    modifier = Modifier
                        .padding(
                            top = 0.dp,
                            bottom = 8.dp,
                            start = 8.dp,
                            end = 8.dp
                        ),
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TopNewsScreen()
    }
}