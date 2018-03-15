package com.hunter.christopher.githubviewer.data.remote

import com.hunter.christopher.githubviewer.data.model.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {

    @GET("users/{userName}/repos")
    fun getUserRepos(@Path("userName") userName: String): Single<List<Repository>>
}