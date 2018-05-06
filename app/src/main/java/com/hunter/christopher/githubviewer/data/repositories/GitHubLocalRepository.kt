package com.hunter.christopher.githubviewer.data.repositories

import com.hunter.christopher.githubviewer.data.db.RepositoryDao
import com.hunter.christopher.githubviewer.data.model.Repository
import com.hunter.christopher.githubviewer.util.async
import com.hunter.christopher.githubviewer.util.asyncAwait

class GitHubLocalRepository(private val repositoryDao: RepositoryDao) {

    suspend fun getUserRepositories(): List<Repository> =
            asyncAwait { repositoryDao.getRepositoriesForUser() }

    suspend fun getRepository(id: Long): Repository =
            asyncAwait { repositoryDao.getRepository(id) }

    suspend fun insertRepositories(repositories: List<Repository>) =
            asyncAwait { repositoryDao.insertAll(repositories) }

    suspend fun clear() =
            async { repositoryDao.clear() }
}