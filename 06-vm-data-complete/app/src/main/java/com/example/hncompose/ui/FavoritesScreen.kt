package com.example.hncompose.ui

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.example.hackernetwork.HNItem
import com.example.hncompose.data.AppDataStatus
import com.example.hncompose.extension.mock
import com.example.hncompose.theme.HackerNewsTheme
import com.example.util.shortUrlString
import com.example.util.storyClicked

@Composable
fun FavoritesScreen(appData: AppDataStatus) {
    val context = ContextAmbient.current
    VerticalScroller {
        Column {
            for (story in appData.topStories.filter { it.favorite }) {
                FavoriteCard(favorite = story) {
                    storyClicked(url = story.url, context = context)
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(favorite: HNItem, favoriteClicked: () -> Unit) {
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {

        Clickable(
            onClick = favoriteClicked,
            modifier = Modifier.ripple()
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = favorite.title ?: "",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = favorite.url.shortUrlString,
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}

@Preview
@Composable
fun FavoritesPreview() {
    HackerNewsTheme {
        FavoritesScreen(appData = AppDataStatus.mock())
    }
}