package com.example.hncompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.*
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.example.hackernetwork.HackerNewsRepo
import com.example.hackernetwork.HackerNewsRetrofit
import com.example.hncompose.data.AppDataStatus
import com.example.hncompose.data.AppScreenStatus
import com.example.hncompose.model.Screen
import com.example.hncompose.theme.HackerNewsTheme
import com.example.hncompose.ui.FavoritesScreen
import com.example.hncompose.ui.TopNewsScreen
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
            HackerNewsTheme {
                AppContent(hackerNewsViewModel.listenerHandler.handleLoadMoreTopStories)
            }
        }

        hackerNewsViewModel.getTopStories()
    }

}

@Composable
fun AppContent(loadMoreStoriesClicked: () -> Unit) {
    val screenState = remember { AppScreenStatus() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text("Hacker Compose") 
                },
                actions = {
                    Crossfade(current = screenState.currentScreen) { state ->
                        when(state) {
                            Screen.TopNews -> {
                                IconButton(onClick = {
                                    screenState.currentScreen = Screen.Favorites
                                }) {
                                    Image(asset = vectorResource(id = R.drawable.ic_baseline_star_24))
                                }
                            }
                            Screen.Favorites -> {
                                IconButton(onClick = {
                                    screenState.currentScreen = Screen.TopNews
                                }) {
                                    Image(asset = vectorResource(id = R.drawable.ic_baseline_home_24))
                                }
                            }
                        }
                    }
                }
            )
        }
    ) {
        Crossfade(current = screenState.currentScreen) { state ->
            when (state) {
                Screen.TopNews -> TopNewsScreen(appData = AppDataStatus, loadMoreStoriesClicked = loadMoreStoriesClicked)
                Screen.Favorites -> FavoritesScreen(appData = AppDataStatus)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMain() {
    HackerNewsTheme {
        AppContent {}
    }
}
