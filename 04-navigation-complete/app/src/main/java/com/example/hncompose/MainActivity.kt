package com.example.hncompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.state
import androidx.ui.animation.Crossfade
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.material.IconButton
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.example.hncompose.data.AppDataStatus
import com.example.hncompose.data.AppScreenStatus
import com.example.hncompose.extension.mock
import com.example.hncompose.model.Screen
import com.example.hncompose.ui.FavoritesScreen
import com.example.hncompose.ui.TopNewsScreen

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
    val screenStatus by state { AppScreenStatus }
    val appDataStatus by state { AppDataStatus.mock() }

    Scaffold(
        topAppBar = {
            TopAppBar(
                title = { 
                    Text("Hacker Compose") 
                },
                actions = {
                    Crossfade(current = screenStatus.currentScreen) { state ->
                        when(state) {
                            Screen.TopNews -> {
                                IconButton(onClick = {
                                    screenStatus.currentScreen = Screen.Favorites
                                }) {
                                    Image(asset = vectorResource(id = R.drawable.ic_baseline_star_24))
                                }
                            }
                            Screen.Favorites -> {
                                IconButton(onClick = {
                                    screenStatus.currentScreen = Screen.TopNews
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
        Crossfade(current = screenStatus.currentScreen) { state ->
            when (state) {
                Screen.TopNews -> TopNewsScreen(appData = appDataStatus)
                Screen.Favorites -> FavoritesScreen(appData = appDataStatus)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMain() {
    AppContent()
}
