package com.example.hncompose.ui

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.gravity
import androidx.ui.material.*
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.hackernetwork.HNItem
import com.example.hncompose.R
import com.example.hncompose.data.AppDataStatus
import com.example.hncompose.extension.mock
import com.example.util.storyClicked
import com.example.hncompose.extension.toggleFavorite
import com.example.hncompose.theme.JetnewsTheme
import com.example.util.shortUrlString


@Composable
fun TopNewsScreen(
    appData: AppDataStatus,
    loadMoreTopStories: () -> Unit
) {
    val context = ContextAmbient.current

    StoryList(
        stories = appData.topStories,
        storiesLoading = appData.loading,
        storyFavorited = { story ->
            appData.topStories = appData.topStories.toggleFavorite(story = story)

            // HACK to make list refreshing work...
            val dummy = HNItem(id = 10, url = "https://google.com", title = "Title")
            appData.topStories.add(dummy)
            appData.topStories.remove(dummy)
        },
        storyOpened = { story ->
            storyClicked(
                url = story.url,
                context = context
            )
        },
        loadMoreCardClicked = loadMoreTopStories
    )

}

@Composable
fun StoryList(
    stories: ModelList<HNItem>,
    storiesLoading: Boolean,
    storyOpened: (HNItem) -> Unit,
    storyFavorited: (HNItem) -> Unit,
    loadMoreCardClicked: () -> Unit
) {
    VerticalScroller {
        Column {
            for (story in stories) {
                BasicCard(
                    story = story,
                    storyOpened = storyOpened,
                    storyFavorited = storyFavorited
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
    storyFavorited: (HNItem) -> Unit,
    storyOpened: (HNItem) -> Unit
) {

    Card(
        modifier = Modifier
            .padding(start = 0.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {

        Row(modifier = Modifier.padding(8.dp)) {

            FavoriteButton(favorite = story.favorite) {
                storyFavorited.invoke(story)
            }

            Box(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(2.dp)
                    .clickable(onClick = {
                        storyOpened.invoke(story)
                    }
                ), children = {
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
            )
        }
    }
}

@Composable
fun FavoriteButton(favorite: Boolean, toggleFavorite: () -> Unit) {
    IconButton(
        onClick = toggleFavorite,
        modifier = Modifier
            .padding(end = 2.dp)
            .gravity(Alignment.CenterVertically)
    ) {
        if (favorite) {
            Image(
                asset = vectorResource(id = R.drawable.ic_baseline_star_24),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
            )
        } else {
            Image(
                asset = vectorResource(id = R.drawable.ic_baseline_star_border_24),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
            )
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
            .padding(8.dp)
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun LandingScreenPreview() {
    JetnewsTheme {
        TopNewsScreen(appData = AppDataStatus.mock(), loadMoreTopStories = {})
    }
}