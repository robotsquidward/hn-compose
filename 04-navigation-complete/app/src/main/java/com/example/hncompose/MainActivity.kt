package com.example.hncompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.example.hackernetwork.HackerNewsRepo
import com.example.hackernetwork.HackerNewsRetrofit
import com.example.hncompose.data.AppDataStatus
import com.example.hncompose.data.AppScreenStatus
import com.example.hncompose.extension.mock
import com.example.hncompose.model.Screen
import com.example.hncompose.theme.JetnewsTheme
import com.example.hncompose.ui.FavoritesScreen
import com.example.hncompose.ui.TopNewsScreen
import com.example.hncompose.viewmodel.HackerNewsViewModel
import com.example.util.createWithFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    Scaffold(
        topAppBar = {
            TopAppBar(title = {
                Text("Hacker Compose")
            })
        }
    ) {
        TopNewsScreen(appData = AppDataStatus.mock())
    }
}

@Preview
@Composable
fun PreviewMain() {
    AppContent()
}
