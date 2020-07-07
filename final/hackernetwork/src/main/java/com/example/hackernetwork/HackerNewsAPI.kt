package com.example.hackernetwork

import android.graphics.Bitmap
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HackerNewsAPI {

    @GET("item/{id}")
    suspend fun getItem(@Path("id") id: String): Response<HNItem>

    @GET("topstories")
    suspend fun getTopStories(): Response<MutableList<Int>>

    @GET("newstories")
    suspend fun getNewStories(): Response<MutableList<Int>>

    @GET("jobstories")
    suspend fun getJobStories(): Response<MutableList<Int>>

}

/**
 * An 'item' returned from the Hacker News API. Can represent almost anything on the site,
 * including stories, comments, etc.
 *
 * https://github.com/HackerNews/API
 */
data class HNItem(
    // Basic elements
    var id: Int?,
    var title: String? = null,
    var url: String? = null,

    // Advanced API attributes
    var deleted: Boolean? = false,
    var type: String? = null,
    var by: String? = null,
    var time: Int? = null,
    var text: String? = null,
    var dead: Boolean? = false,
    var parent: Int? = null,
    var poll: Int? = null,
    var kids: MutableList<Int>? = null,
    var score: Int? = null,
    var parts: MutableList<Int>? = null,
    var descendants: Int? = null,

    // App specific attribute, not from the HN API
    var favicon: Bitmap? = null,
    var favorite: Boolean = false
)