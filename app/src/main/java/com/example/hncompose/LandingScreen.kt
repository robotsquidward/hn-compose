package com.example.hncompose

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.hackernetwork.HNItem
import com.example.hncompose.snippets.shortUrlString
import com.example.hncompose.snippets.storyClicked
import com.example.hncompose.theme.JetnewsTheme
import com.example.hncompose.viewmodel.HackerNewsViewModel


@Composable
fun LandingScreen(
    appData: AppDataStatusHolder,
    loadMoreTopStories: () -> Unit
) {

    val context = ContextAmbient.current

    JetnewsTheme {
        Scaffold(
            scaffoldState = remember { ScaffoldState() },
            topAppBar = {
                TopAppBar(
                    title = {
                        Text(text = AppScreenStatus.currentScreen.title)
                    }
                )
            },
            bodyContent = {
                StoryList(
                    stories = appData.topStories,
                    storiesLoading = appData.loading,
                    storyClicked = { story -> storyClicked(url = story.url, context = context) },
                    loadMoreCardClicked = loadMoreTopStories
                )
            }
        )
    }
}

@Composable
fun StoryList(
    stories: ModelList<HNItem>,
    storiesLoading: Boolean,
    storyClicked: (HNItem) -> Unit,
    loadMoreCardClicked: () -> Unit
) {
    VerticalScroller {
        Column {
            for (story in stories) {
                BasicCard(
                    story = story,
                    storyClicked = storyClicked
                )
            }
            LoadingCard(
                stories = stories,
                loading = storiesLoading,
                loadMoreCardClicked = loadMoreCardClicked
            )
        }
    }
}

@Composable
fun BasicCard(
    story: HNItem,
    storyClicked: (HNItem) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Clickable(
            onClick = {
                storyClicked.invoke(story)
            },
            modifier = Modifier.ripple()
        ) {
            Row(modifier = Modifier.padding(8.dp)) {

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
                            .padding(start = 4.dp, end = 12.dp)
                            .gravity(Alignment.CenterVertically)
                    )
                }


                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = story.title ?: "",
                        style = MaterialTheme.typography.body1
                    )
                    Text(
                        text = story.url.shortUrlString,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingCard(
    stories: ModelList<HNItem>,
    loading: Boolean,
    loadMoreCardClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .padding(8.dp)
                    .fillMaxWidth()
                    .gravity(Alignment.CenterHorizontally)
            )
        } else if (stories.isNotEmpty()) {
            LoadMoreCard(loadMoreCardClicked = loadMoreCardClicked)
        }
    }
}

@Composable
fun LoadMoreCard(loadMoreCardClicked: () -> Unit) {
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
}

@Preview
@Composable
fun LandingScreenPreview() {
    LandingScreen(appData = MockAppDataStatus, loadMoreTopStories = {})
}