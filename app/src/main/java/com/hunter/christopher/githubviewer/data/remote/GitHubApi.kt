package com.hunter.christopher.githubviewer.data.remote

import com.hunter.christopher.githubviewer.data.model.Repository
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{userName}/repos")
    fun getUserRepos(@Path("userName") userName: String): Deferred<List<Repository>>
}