package com.example.hncompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.remember
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.graphics.ColorFilter
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.ripple.ripple
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
        Scaffold(
            scaffoldState = remember { ScaffoldState() },
            topAppBar = {
                TopAppBar(
                    title = {
                        Text(text = "HN Compose")
                    }
                )
            },
            bodyContent = {
                StoryList(
                    stories = TopStoryModel.storyList
                ) { story ->
                    story.favorite = !story.favorite
                    TopStoryModel.storyList[TopStoryModel.storyList.indexOf(story)] = story
                }
            }
        )
    }
}

@Composable
fun StoryList(stories: ModelList<Story>, storyClicked: (Story) -> Unit) {
    VerticalScroller {
        Column {
            for (story in stories) {
                BasicCard(
                    story = story,
                    storyClicked = storyClicked
                )
            }
        }
    }
}

@Composable
fun BasicCard(story: Story, storyClicked: (Story) -> Unit) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Clickable(
            onClick = {
                storyClicked.invoke(story)
            },
            modifier = Modifier.ripple()
        ) {
            Row(modifier = Modifier.padding(8.dp)) {

                Image(
                    asset = if (story.favorite) {
                        vectorResource(id = R.drawable.ic_baseline_star_24)
                    } else {
                        vectorResource(id = R.drawable.ic_baseline_star_border_24)
                    },
                    colorFilter = ColorFilter.tint(Color(R.color.purple200)),
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .padding(end = 8.dp)
                        .gravity(Alignment.CenterVertically)
                )

                Column(
                    modifier = Modifier.fillMaxWidth()
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
    }

}

@Preview
@Composable
fun DefaultPreview() {
    LandingScreen()
}