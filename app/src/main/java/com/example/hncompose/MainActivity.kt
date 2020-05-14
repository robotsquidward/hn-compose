package com.example.hncompose

import android.os.Bundle
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.animation.TweenBuilder
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Observe
import androidx.compose.remember
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.foundation.gestures.DragDirection
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.hackernetwork.HNItem
import com.example.hackernetwork.HackerNewsRepo
import com.example.hackernetwork.HackerNewsRetrofit
import com.example.hncompose.util.*
import com.example.hncompose.viewmodel.HackerNewsViewModel

class MainActivity : AppCompatActivity() {

    private val topStoriesViewModel: HackerNewsViewModel by viewModels {
        createWithFactory {
            HackerNewsViewModel(
                repo = HackerNewsRepo(
                    HackerNewsRetrofit.retrofitInstance
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TopNewsScreen(AppDataStatus)
            }
        }

        topStoriesViewModel.getTopStories()
    }
}

@Composable
fun TopNewsScreen(appStatus: AppDataStatusHolder, scaffoldState: ScaffoldState = remember { ScaffoldState() }) {
    Scaffold(
            scaffoldState = scaffoldState,
            topAppBar = {
                TopAppBar(
                        title = { Text(text = "HN Compose") }
                )
            },
            bodyContent = { TopNewsScreenBody(stories = appStatus.topStories) }
    )
}

@Composable
fun TopNewsScreenBody(
        stories: List<HNItem>) {
    VerticalScroller {
        Column {
            stories.forEach { story ->
                StoryCard(story = story)
            }
            if (stories.isNotEmpty()) {
                LoadMoreCard()
            }
        }
    }
}

@Composable
fun StoryCard(story: HNItem) {
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
                onClick = {
                    println("Story: ${story.title}")
                }
        ) {
            Column {
                Text(
                    text = story.title ?: "",
                    modifier = Modifier
                        .padding(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp,
                            bottom = 4.dp
                        )
                )
                Text(
                    text = story.url.shortUrlString,
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

@Composable
fun LoadMoreCard() {
    Card(
        shape = RoundedCornerShape(size = 0.dp),
        elevation = 0.dp,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth()
    ) {
        Button(
            text = {
                Text(text = "Load More")
            },
            onClick = {
                println("Load more clicked")
            }
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TopNewsScreen(MockAppDataStatus)
    }
}