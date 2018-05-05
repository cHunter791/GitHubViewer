package com.hunter.christopher.githubviewer.data.repositories

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.hunter.christopher.githubviewer.data.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GitHubRepository(private val localRepository: GitHubLocalRepository,
                       private val remoteRepository: GitHubRemoteRepository) {

    @SuppressLint("CheckResult")
    fun getUserRepositories(userName: String): MutableLiveData<Resource<List<Repository>>> {
        val data = MutableLiveData<Resource<List<Repository>>>()

        remoteRepository.getUserRepositories(userName)
                .onErrorResumeNext {
                    localRepository.getUserRepositories()
                }
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSuccess({ repositories ->
                    localRepository.clear()
                    localRepository.insertRepositories(repositories)
                })
                .subscribe({ repositories ->
                    data.postValue(Resource.success(repositories))
                }, { throwable ->
                    data.postValue(Resource.error(throwable))
                })

        return data
    }

    fun getRepository(id: Long): LiveData<Repository> = localRepository.getRepository(id)
}