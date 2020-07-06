package com.example.hncompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.example.hackernetwork.HackerNewsRepo
import com.example.hackernetwork.HackerNewsRetrofit
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
}

@Composable
fun AppContent(listenerHandler: HackerNewsViewModel.HackerNewsListenerHandler) {
    Crossfade(current = AppScreenStatus.currentScreen) { screen ->
        when (screen) {
            is Screen.Top -> LandingScreen(
                appData = AppDataStatus,
                loadMoreTopStories = listenerHandler.handleLoadMoreTopStories
            )
            is Screen.Favorites -> FavoritesScreen()
        }
    }
}
