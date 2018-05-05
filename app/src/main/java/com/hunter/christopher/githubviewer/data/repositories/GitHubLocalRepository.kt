package com.hunter.christopher.githubviewer.data.repositories

import android.arch.lifecycle.LiveData
import com.hunter.christopher.githubviewer.data.db.RepositoryDao
import com.hunter.christopher.githubviewer.data.model.Repository
import io.reactivex.Single

class GitHubLocalRepository(private val repositoryDao: RepositoryDao) {

    fun getUserRepositories(): Single<List<Repository>> =
            repositoryDao.getRepositoriesForUser()
                    .firstOrError()
                    .doOnSuccess { repositories ->
                        if (repositories.isEmpty()) throw Exception()
                    }

    fun getRepository(id: Long): LiveData<Repository> =
            repositoryDao.getRepository(id)

    fun insertRepositories(repositories: List<Repository>) =
            repositoryDao.insertAll(repositories)

    fun clear() = repositoryDao.clear()
}