package com.hunter.christopher.githubviewer.data.repositories

import android.arch.lifecycle.MutableLiveData
import com.hunter.christopher.githubviewer.data.model.Repository
import com.hunter.christopher.githubviewer.util.launchAsync
import timber.log.Timber

class GitHubRepository(private val localRepository: GitHubLocalRepository,
                       private val remoteRepository: GitHubRemoteRepository) {

    fun getUserRepositories(userName: String): MutableLiveData<Resource<List<Repository>>> {
        Timber.d("Getting repositories for user %s", userName)
        val data = MutableLiveData<Resource<List<Repository>>>()

        launchAsync {
            try {
                val repositories = remoteRepository.getUserRepositories(userName)
                data.postValue(Resource.success(repositories))

                Timber.d("Retrieved %s repositories from GitHub", repositories.size)
                localRepository.clear()
                localRepository.insertRepositories(repositories)
            } catch (e: Exception) {
                Timber.e("Error getting repositories from GitHub: %s", e.message)
                data.postValue(getLocalUserRepositories())
            }
        }

        return data
    }

    private suspend fun getLocalUserRepositories(): Resource<List<Repository>> {
        return try {
            val repositories = localRepository.getUserRepositories()
            Timber.d("Retrieved %s repositories from Room", repositories.size)
            Resource.success(repositories)
        } catch (e: Exception) {
            Timber.e("Error retrieving repositories from Room: %s", e.message)
            Resource.error(e)
        }
    }

    fun getRepository(id: Long): MutableLiveData<Resource<Repository>> {
        Timber.d("Getting repository with id %s", id)
        val data = MutableLiveData<Resource<Repository>>()

        launchAsync {
            try {
                data.postValue(Resource.success(localRepository.getRepository(id)))
            } catch (e: Exception) {
                Timber.e("Error retrieving repository %s from Room: %s", id, e.message)
                data.postValue(Resource.error(e))
            }
        }

        return data
    }
}