package com.example.hncompose.ui

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.layout.RowScope.gravity
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.hackernetwork.HNItem
import com.example.hncompose.R
import com.example.hncompose.data.AppDataStatus
import com.example.hncompose.extension.mock
import com.example.hncompose.extension.toggleFavorite
import com.example.hncompose.theme.HackerNewsTheme
import com.example.util.shortUrlString
import com.example.util.storyClicked


@Composable
fun TopNewsScreen(appData: AppDataStatus, loadMoreStoriesClicked: () -> Unit) {
    val context = ContextAmbient.current

    VerticalScroller {
        Column(modifier = Modifier.fillMaxWidth()) {
            for (story in appData.topStories) {
                StoryCard(
                    story = story,
                    storySelected =  {
                        storyClicked(url = story.url, context = context)
                    },
                    storyFavorited = {
                        appData.topStories.toggleFavorite(story = story)
                    }
                )
            }

            LoadingCard(
                storiesLoaded = appData.topStories.isNotEmpty(),
                storiesLoading = appData.loading,
                loadMoreStoriesClicked = loadMoreStoriesClicked)
        }
    }
}

@Composable
fun StoryCard(
    story: HNItem,
    storySelected: () -> Unit,
    storyFavorited: () -> Unit
) {

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {

        Clickable(
            onClick = storySelected,
            modifier = Modifier.ripple()
        ) {
            Row {

                FavoriteButton(
                    favorite = story.favorite,
                    storyFavorited = storyFavorited
                )

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
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
fun FavoriteButton(favorite: Boolean, storyFavorited: () -> Unit) {
    IconButton(onClick = storyFavorited) {
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
    storiesLoaded: Boolean,
    storiesLoading: Boolean,
    loadMoreStoriesClicked: () -> Unit) {

    if (storiesLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(8.dp)
                .fillMaxWidth()
                .gravity(Alignment.CenterHorizontally)
        )
    } else if (storiesLoaded) {
        Button(
            text = {
                Text(text = "Load More")
            },
            onClick = loadMoreStoriesClicked,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun LandingScreenPreview() {
    HackerNewsTheme {
        TopNewsScreen(AppDataStatus.mock(), {})
    }
}