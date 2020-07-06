package com.example.hncompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import com.example.hackernetwork.HackerNewsRepo
import com.example.hackernetwork.HackerNewsRetrofit
import com.example.hncompose.data.AppScreenStatus
import com.example.hncompose.data.Screen
import com.example.hncompose.theme.JetnewsTheme
import com.example.hncompose.ui.FavoritesScreen
import com.example.hncompose.ui.LandingScreen
import com.example.hncompose.viewmodel.HackerNewsViewModel
import com.example.util.createWithFactory

class MainActivity : AppCompatActivity() {

    private val hackerNewsViewModel: HackerNewsViewModel by viewModels {
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
            AppContent(hackerNewsViewModel.listenerHandler)
        }
        hackerNewsViewModel.getTopStories()
    }

    override fun onBackPressed() {
        if (AppScreenStatus.currentScreen == Screen.Favorites) {
            AppScreenStatus.currentScreen = Screen.Top
        }
    }
}

@Composable
fun AppContent(listenerHandler: HackerNewsViewModel.HackerNewsListenerHandler) {
    JetnewsTheme {
        Scaffold(
            scaffoldState = ScaffoldState(),
            topAppBar = {
                TopAppBar(
                    title = { Text(text = AppScreenStatus.currentScreen.title) },
                    actions = {
                        when (AppScreenStatus.currentScreen) {
                            Screen.Top -> {
                                IconButton(onClick = {
                                    AppScreenStatus.currentScreen = Screen.Favorites
                                }) {
                                    Icon(asset = vectorResource(id = R.drawable.ic_baseline_star_24))
                                }
                            }
                            Screen.Favorites -> {
                                IconButton(onClick = {
                                    AppScreenStatus.currentScreen = Screen.Top
                                }) {
                                    Icon(asset = vectorResource(id = R.drawable.ic_baseline_home_24))
                                }
                            }
                        }
                    }
                )
            },
            bodyContent = {
                Crossfade(current = AppScreenStatus.currentScreen) { screen ->
                    when (screen) {
                        is Screen.Top -> LandingScreen(
                            listenerHandler.handleLoadMoreTopStories
                        )
                        is Screen.Favorites -> FavoritesScreen()
                    }
                }
            }
        )
    }
}
