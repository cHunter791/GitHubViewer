package com.hunter.christopher.githubviewer.ui.repository.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.hunter.christopher.githubviewer.data.model.Repository
import com.hunter.christopher.githubviewer.data.repositories.GitHubRepository
import com.hunter.christopher.githubviewer.data.repositories.Resource
import com.hunter.christopher.githubviewer.ui.base.BaseViewModel

class RepositoryDetailViewModel(private val gitHubRepository: GitHubRepository) : BaseViewModel() {

    var repositoryId = MutableLiveData<Long>()
    var repositoryResult: LiveData<Resource<Repository>> = Transformations.switchMap(repositoryId) {
        gitHubRepository.getRepository(it)
    }
}