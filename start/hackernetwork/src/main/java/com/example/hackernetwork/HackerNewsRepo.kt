package com.example.hackernetwork

import retrofit2.Response
import retrofit2.Retrofit

/**
 * Makes calls to [HackerNewsAPI] using suspend functions.
 */
class HackerNewsRepo(private val hackerNewsRetrofit: Retrofit) {

    /**
     * Given an [id], return the details of a [HNItem]
     *
     * @param id string ID representing a [HNItem]
     * @return a [Response] wrapping an expected [HNItem]
     */
    suspend fun getItem(id: String): Response<HNItem> {
        return hackerNewsRetrofit.create(HackerNewsAPI::class.java).getItem(id)
    }

    /**
     * Get the list of the current Top story ID's
     *
     * @return [Response] wrapping a list of top story id's
     */
    suspend fun getTopStories(): Response<MutableList<Int>> {
        return hackerNewsRetrofit.create(HackerNewsAPI::class.java).getTopStories()
    }

    /**
     * Get the list of the current New story ID's
     *
     * @return [Response] wrapping a list of New story id's
     */
    suspend fun getNewStories(): Response<MutableList<Int>> {
        return hackerNewsRetrofit.create(HackerNewsAPI::class.java).getNewStories()
    }

    /**
     * Get the list of the current Job story ID's
     *
     * @return [Response] wrapping a list of Job story id's
     */
    suspend fun getJobStories(): Response<MutableList<Int>> {
        return hackerNewsRetrofit.create(HackerNewsAPI::class.java).getJobStories()
    }

}