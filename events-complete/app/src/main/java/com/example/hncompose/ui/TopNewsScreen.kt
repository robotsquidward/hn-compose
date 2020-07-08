package com.example.hncompose.ui

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
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
import com.example.util.storyClicked
import com.example.hncompose.extension.toggleFavorite
import com.example.hncompose.theme.JetnewsTheme
import com.example.util.shortUrlString


@Composable
fun TopNewsScreen(appData: AppDataStatus) {
    VerticalScroller {
        Column {
            for (story in appData.topStories) {
                StoryCard(
                    story = story,
                    storySelected =  {
                        // todo -> display article contents
                    },
                    storyFavorited = {
                        appData.topStories.toggleFavorite(story = story)
                    }
                )
            }
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
                IconButton(onClick = storyFavorited) {
                    if (story.favorite) {
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

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = story.title ?: "")
                    Text(text = story.url ?: "")
                }
            }
        }
    }

}

@Preview
@Composable
fun LandingScreenPreview() {
    TopNewsScreen(AppDataStatus.mock())
}