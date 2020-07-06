package com.example.hncompose

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.layout.RowScope.gravity
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.hackernetwork.HNItem
import com.example.hncompose.snippets.storyClicked
import com.example.hncompose.theme.JetnewsTheme
import com.example.util.shortUrlString

@Composable
fun FavoritesScreen() {
    FavoritesList(
        stories = AppDataStatus.topStories.filter { it.favorite }
    )
}

@Composable
fun FavoritesList(stories: List<HNItem>) {
    val context = ContextAmbient.current

    VerticalScroller {
        Column {
            for (story in stories) {
                FavoritesCard(
                    story = story,
                    storyClicked = { storyClicked(url = story.url, context = context) }
                )
            }
        }
    }
}

@Composable
fun FavoritesCard(story: HNItem, storyClicked: () -> Unit) {

    Clickable(onClick = storyClicked) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
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

@Preview
@Composable
fun FavoritesPreview() {
    JetnewsTheme {
        FavoritesScreen()
    }
}