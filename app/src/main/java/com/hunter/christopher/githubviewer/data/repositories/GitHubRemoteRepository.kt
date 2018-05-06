package com.hunter.christopher.githubviewer.data.repositories

import com.hunter.christopher.githubviewer.data.model.Repository
import com.hunter.christopher.githubviewer.data.remote.GitHubApi
import com.hunter.christopher.githubviewer.util.asyncAwait
import retrofit2.Retrofit

class GitHubRemoteRepository(retrofit: Retrofit) {

    private val gitHubApi: GitHubApi = retrofit.create(GitHubApi::class.java)

    suspend fun getUserRepositories(userName: String): List<Repository> =
            asyncAwait { gitHubApi.getUserRepos(userName).await() }
}