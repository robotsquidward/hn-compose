package com.example.hncompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.remember
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.ScaffoldState
import androidx.ui.tooling.preview.Preview

val stories: List<String> = listOf("Story One", "Story Two", "Story Three")

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TopNewsScreen()
            }
        }
    }
}

@Composable
fun TopNewsScreen(scaffoldState: ScaffoldState = remember { ScaffoldState() }) {
    Scaffold(
            scaffoldState = scaffoldState,
            bodyContent = { modifier ->
                TopNewsScreenBody(posts = stories)
            }
    )
}

@Composable
fun TopNewsScreenBody(
        posts: List<String>,
        modifier: Modifier = Modifier) {
    VerticalScroller {
        Column {
            posts.forEach { post ->
                Greeting(name = post)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        TopNewsScreen()
    }
}