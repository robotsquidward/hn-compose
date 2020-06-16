package com.example.hncompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.material.MaterialTheme
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LandingScreen()
        }
    }
}

@Composable
fun LandingScreen() {
    MaterialTheme {
        BasicCard()
    }
}

@Composable
fun BasicCard() {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                asset = vectorResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp)
                    .padding(end = 4.dp)
                    .gravity(Alignment.CenterVertically)
            )
            Column {
                Text(
                    text = "Hello World!",
                    style = TextStyle(
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = "More details",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    LandingScreen()
}