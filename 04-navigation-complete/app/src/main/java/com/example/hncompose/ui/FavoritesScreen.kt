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
import com.example.hncompose.extension.toggleFavorite
import com.example.util.storyClicked
import com.example.hncompose.theme.JetnewsTheme
import com.example.util.shortUrlString

@Composable
fun FavoritesScreen(appData: AppDataStatus) {
    VerticalScroller {
        Column {
            for (story in appData.topStories.filter { it.favorite }) {
                FavoriteCard(favorite = story) {
                    // todo -> display article contents
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
                Text(text = favorite.title ?: "")
                Text(text = favorite.url ?: "")
            }

        }
    }
}


@Preview
@Composable
fun FavoritesPreview() {
    FavoritesScreen(appData = AppDataStatus.mock())
}