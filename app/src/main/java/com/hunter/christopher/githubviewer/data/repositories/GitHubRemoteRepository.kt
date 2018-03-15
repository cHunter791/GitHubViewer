package com.hunter.christopher.githubviewer.data.repositories

import com.hunter.christopher.githubviewer.data.model.Repository
import com.hunter.christopher.githubviewer.data.remote.GitHubApi
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class GitHubRemoteRepository @Inject constructor(retrofit: Retrofit) {

    private val gitHubApi: GitHubApi = retrofit.create(GitHubApi::class.java)

    fun getUserRepositories(userName: String): Single<List<Repository>> =
            gitHubApi.getUserRepos(userName)
}