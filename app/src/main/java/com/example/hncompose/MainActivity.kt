package com.example.hncompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.hackernetwork.HNItem
import com.example.hackernetwork.HackerNewsRepo
import com.example.hackernetwork.HackerNewsRetrofit
import com.example.hncompose.viewmodel.HackerNewsViewModel
import com.example.util.createWithFactory
import com.example.util.shortUrlString

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
                TopNewsScreen(
                    appStatus = AppDataStatus,
                    listenerHandler = topStoriesViewModel.listenerHandler,
                    storyClickedListener = { storyUrl ->
                        topStoriesViewModel.storyClicked(storyUrl, this)
                    }
                )
            }
        }

        topStoriesViewModel.getTopStories()
    }
}

@Composable
fun TopNewsScreen(
    appStatus: AppDataStatusHolder,
    listenerHandler: HackerNewsViewModel.HackerNewsListenerHandler?,
    storyClickedListener: (String?) -> Unit,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    Scaffold(
            scaffoldState = scaffoldState,
            topAppBar = {
                TopAppBar(
                        title = { Text(text = "HN Compose") }
                )
            },
            bodyContent = {
                TopNewsScreenBody(
                    loading = appStatus.loading,
                    stories = appStatus.topStories,
                    listenerHandler = listenerHandler,
                    storyClickedListener = storyClickedListener
                )
            }
    )
}

@Composable
fun TopNewsScreenBody(
        loading: Boolean,
        stories: List<HNItem>,
        listenerHandler: HackerNewsViewModel.HackerNewsListenerHandler?,
        storyClickedListener: (String?) -> Unit) {
    VerticalScroller {
        Column {
            stories.forEach { story ->
                StoryCard(story = story, storyClickedListener = storyClickedListener)
            }
            if (stories.isNotEmpty()) {
                LoadMoreCard(
                    loadMoreCardClicked = (listenerHandler?.handleLoadMoreTopStories ?: {
                        println("Listener handler is null")
                    }),
                    loading = loading
                )
            }
        }
    }
}

@Composable
fun StoryCard(story: HNItem, storyClickedListener: (String?) -> Unit) {
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
                    println(story.url)
                    storyClickedListener.invoke(story.url)
                }
        ) {
            Row {
                story.favicon?.asImageAsset()?.also { imageAsset ->
                    Image(
                        asset = imageAsset,
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .padding(start = 4.dp, end = 4.dp)
                            .gravity(Alignment.CenterVertically)
                    )
                } ?: run {
                    Image(
                        asset = vectorResource(id = R.drawable.ic_launcher_foreground),
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .padding(start = 4.dp, end = 4.dp)
                            .gravity(Alignment.CenterVertically)
                    )
                }
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
                    if (story.url.shortUrlString.isNotEmpty()) {
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
    }
}

@Composable
fun LoadMoreCard(loading: Boolean, loadMoreCardClicked: () -> Unit) {
    Column {
        Button(
            text = {
                Text(text = "Load More")
            },
            onClick = loadMoreCardClicked,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 4.dp,
                    bottom = 8.dp
                )
                .fillMaxWidth()
        )

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(8.dp)
                    .fillMaxWidth()
                    .gravity(align = Alignment.CenterHorizontally)
            )
        } else {
            Image(
                asset = vectorResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(
                        top = 0.dp,
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                    .fillMaxWidth()
                    .gravity(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TopNewsScreen(MockAppDataStatus, listenerHandler = null, storyClickedListener = {})
    }
}