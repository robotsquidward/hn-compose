package com.example.hncompose

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.remember
import androidx.ui.core.Alignment
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

@Composable
fun FavoritesScreen() {
    MaterialTheme {
        Scaffold(
            scaffoldState = remember { ScaffoldState() },
            topAppBar = {
                TopAppBar(
                    title = {
                        Text(text = "HN Compose")
                    },
                    actions = {
                        IconButton(onClick = {
                            AppScreenStatus.currentScreen = Screen.Top
                        }) {
                            Icon(asset = vectorResource(id = R.drawable.ic_baseline_home_24))
                        }
                    }
                )
            },
            bodyContent = {
                FavoritesList(
                    stories = TopStoryModel.favorites
                )
            }
        )
    }
}

@Composable
fun FavoritesList(stories: List<Story>) {
    VerticalScroller {
        Column {
            for (story in stories) {
                FavoritesCard(story = story)
            }
        }
    }
}

@Composable
fun FavoritesCard(story: Story) {

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
                text = story.title,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
            Text(
                text = story.details,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }

}

@Preview
@Composable
fun FavoritesPreview() {
    FavoritesScreen()
}