package com.example.hncompose.snippets

//@Composable
//fun ScrollingStoryList(stories: List<HNItem>) {
//    VerticalScroller {
//        Column {
//            stories.forEach { story ->
//                StoryCard(story = story, storyClickedListener = {})
//            }
//            if (stories.isNotEmpty()) {
//                LoadMoreCard(loading = false) {}
//            }
//        }
//    }
//}
//
//@Composable
//fun AdapterStoryList(stories: List<HNItem>) {
//    AdapterList(data = stories) { story ->
//        StoryCard(story = story, storyClickedListener = {})
//    }
//}

//@Composable
//fun CustomVerticalStoryList(stories: List<HNItem>) {
//    VerticalList(data = stories) { story ->
//        StoryCard(story = story, storyClickedListener = {})
//    }
//}

//private val topStoriesViewModel: HackerNewsViewModel by viewModels {
//    createWithFactory {
//        HackerNewsViewModel(
//            repo = HackerNewsRepo(
//                HackerNewsRetrofit.retrofitInstance
//            )
//        )
//    }
//}
//
//@Composable
//fun TopNewsScreen(
//    appStatus: AppDataStatusHolder,
//    listenerHandler: HackerNewsViewModel.HackerNewsListenerHandler?,
//    storyClickedListener: (String?) -> Unit,
//    scaffoldState: ScaffoldState = remember { ScaffoldState() }
//) {
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topAppBar = {
//            TopAppBar(
//                title = { Text(text = "HN Compose") }
//            )
//        },
//        bodyContent = {
//            TopNewsScreenBody(
//                loading = appStatus.loading,
//                stories = appStatus.topStories,
//                listenerHandler = listenerHandler,
//                storyClickedListener = storyClickedListener
//            )
//        }
//    )
//}
//
//@Composable
//fun TopNewsScreenBody(
//    loading: Boolean,
//    stories: List<HNItem>,
//    listenerHandler: HackerNewsViewModel.HackerNewsListenerHandler?,
//    storyClickedListener: (String?) -> Unit) {
//    VerticalScroller {
//        Column {
//            stories.forEach { story ->
//                StoryCard(story = story, storyClickedListener = storyClickedListener)
//            }
//            if (stories.isNotEmpty()) {
//                LoadMoreCard(
//                    loadMoreCardClicked = (listenerHandler?.handleLoadMoreTopStories ?: {
//                        println("Listener handler is null")
//                    }),
//                    loading = loading
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun StoryCard(story: HNItem, storyClickedListener: (String?) -> Unit) {
//    Card(
//        shape = RoundedCornerShape(size = 6.dp),
//        elevation = 4.dp,
//        modifier = Modifier
//            .padding(
//                start = 8.dp,
//                end = 8.dp,
//                top = 4.dp,
//                bottom = 4.dp
//            )
//            .fillMaxWidth()
//    ) {
//        Clickable(
//            modifier = Modifier.ripple(),
//            onClick = {
//                println("Story: ${story.title}")
//                println(story.url)
//                storyClickedListener.invoke(story.url)
//            }
//        ) {
//            Row {
//                story.favicon?.asImageAsset()?.also { imageAsset ->
//                    Image(
//                        asset = imageAsset,
//                        modifier = Modifier
//                            .height(30.dp)
//                            .width(30.dp)
//                            .padding(start = 4.dp, end = 4.dp)
//                            .gravity(Alignment.CenterVertically)
//                    )
//                } ?: run {
//                    Image(
//                        asset = vectorResource(id = R.drawable.ic_launcher_foreground),
//                        modifier = Modifier
//                            .height(30.dp)
//                            .width(30.dp)
//                            .padding(start = 4.dp, end = 4.dp)
//                            .gravity(Alignment.CenterVertically)
//                    )
//                }
//                Column {
//                    Text(
//                        text = story.title ?: "",
//                        modifier = Modifier
//                            .padding(
//                                start = 8.dp,
//                                end = 8.dp,
//                                top = 8.dp,
//                                bottom = 4.dp
//                            )
//                    )
//                    if (story.url.shortUrlString.isNotEmpty()) {
//                        Text(
//                            text = story.url.shortUrlString,
//                            modifier = Modifier
//                                .padding(
//                                    top = 0.dp,
//                                    bottom = 8.dp,
//                                    start = 8.dp,
//                                    end = 8.dp
//                                ),
//                            style = TextStyle(
//                                fontSize = 10.sp,
//                                color = Color.Gray
//                            )
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun LoadMoreCard(loading: Boolean, loadMoreCardClicked: () -> Unit) {
//    Column {
//        Button(
//            text = {
//                Text(text = "Load More")
//            },
//            onClick = loadMoreCardClicked,
//            modifier = Modifier
//                .padding(
//                    start = 8.dp,
//                    end = 8.dp,
//                    top = 4.dp,
//                    bottom = 8.dp
//                )
//                .fillMaxWidth()
//        )
//
//        if (loading) {
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .height(50.dp)
//                    .width(50.dp)
//                    .padding(8.dp)
//                    .fillMaxWidth()
//                    .gravity(align = Alignment.CenterHorizontally)
//            )
//        } else {
//            Image(
//                asset = vectorResource(id = R.drawable.ic_launcher_foreground),
//                modifier = Modifier
//                    .height(50.dp)
//                    .width(50.dp)
//                    .padding(
//                        top = 0.dp,
//                        bottom = 8.dp,
//                        start = 8.dp,
//                        end = 8.dp
//                    )
//                    .fillMaxWidth()
//                    .gravity(Alignment.CenterHorizontally)
//            )
//        }
//    }
//}