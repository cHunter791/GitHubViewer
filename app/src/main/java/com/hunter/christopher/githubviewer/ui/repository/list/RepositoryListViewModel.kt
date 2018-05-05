package com.hunter.christopher.githubviewer.ui.repository.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableBoolean
import com.hunter.christopher.githubviewer.data.model.Repository
import com.hunter.christopher.githubviewer.data.repositories.GitHubRepository
import com.hunter.christopher.githubviewer.data.repositories.Resource
import com.hunter.christopher.githubviewer.ui.base.BaseViewModel

class RepositoryListViewModel(private val gitHubRepository: GitHubRepository) : BaseViewModel() {

    val searchName = MutableLiveData<String>()
    private val searchTerm = MutableLiveData<String>()
    val loading = ObservableBoolean(false)

    var repositoryResult: LiveData<Resource<List<Repository>>> = Transformations.switchMap(searchTerm) {
        if (it != null && it.isNotEmpty()) {
            gitHubRepository.getUserRepositories(it)
        } else {
            MutableLiveData()
        }
    }

    fun searchRepositories() {
        loading.set(true)
        searchTerm.value = searchName.value
    }
}