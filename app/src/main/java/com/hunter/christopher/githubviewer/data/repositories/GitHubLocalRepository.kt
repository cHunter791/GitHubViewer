package com.hunter.christopher.githubviewer.data.repositories

import com.hunter.christopher.githubviewer.data.db.RepositoryDao
import com.hunter.christopher.githubviewer.data.model.Repository
import io.reactivex.Single
import javax.inject.Inject

class GitHubLocalRepository @Inject constructor(private val repositoryDao: RepositoryDao) {

    fun getUserRepositories(): Single<List<Repository>> =
            repositoryDao.getRepositoriesForUser()
                    .firstOrError()
                    .doOnSuccess { repositories ->
                        if (repositories.isEmpty()) throw Exception()
                    }

    fun insertRepositories(repositories: List<Repository>) =
            repositoryDao.insertAll(repositories)

    fun clear() = repositoryDao.clear()
}