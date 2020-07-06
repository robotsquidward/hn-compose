package com.example.hncompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.remember
import androidx.ui.animation.Crossfade
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontStyle
import androidx.ui.tooling.preview.Preview
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
            AppContent()
        }

        hackerNewsViewModel.getTopStories()
    }
}

@Composable
fun AppContent() {
    Crossfade(current = AppScreenStatus.currentScreen) { screen ->
        when (screen) {
            is Screen.Top -> LandingScreen()
            is Screen.Favorites -> FavoritesScreen()
        }
    }
}
