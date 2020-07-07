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
fun TopNewsScreen() {
    Text("Top News... coming soon.")
}

@Preview
@Composable
fun LandingScreenPreview() {
    TopNewsScreen()
}