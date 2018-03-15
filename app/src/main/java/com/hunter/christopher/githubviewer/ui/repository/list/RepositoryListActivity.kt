package com.hunter.christopher.githubviewer.ui.repository.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hunter.christopher.githubviewer.BR
import com.hunter.christopher.githubviewer.R
import com.hunter.christopher.githubviewer.databinding.ActivityRepositoryListBinding
import com.hunter.christopher.githubviewer.ui.base.BaseActivity
import javax.inject.Inject

class RepositoryListActivity : BaseActivity<ActivityRepositoryListBinding, RepositoryListViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var adapter: RepositoryAdapter
    override var layoutId: Int = R.layout.activity_repository_list
    override var bindingVariable: Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepositoryListViewModel::class.java)
        binding.setVariable(bindingVariable, viewModel)

        setupAdapter()

        viewModel.repositoryResult.observe(this, Observer { repositories ->
            adapter.updateRepositories(repositories?.data)
            viewModel.loading.set(false)
        })
    }

    private fun setupAdapter() {
        adapter = RepositoryAdapter(ArrayList())
        binding.repositoryList.layoutManager = LinearLayoutManager(this)
        binding.repositoryList.adapter = adapter
    }
}
