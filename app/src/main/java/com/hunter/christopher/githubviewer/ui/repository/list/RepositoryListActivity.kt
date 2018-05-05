package com.hunter.christopher.githubviewer.ui.repository.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hunter.christopher.githubviewer.BR
import com.hunter.christopher.githubviewer.R
import com.hunter.christopher.githubviewer.databinding.ActivityRepositoryListBinding
import com.hunter.christopher.githubviewer.ui.base.BaseActivity
import com.hunter.christopher.githubviewer.ui.navigation.Navigator
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class RepositoryListActivity : BaseActivity<ActivityRepositoryListBinding>() {

    private val viewModel: RepositoryListViewModel by viewModel()
    private lateinit var adapter: RepositoryAdapter
    override var layoutId: Int = R.layout.activity_repository_list
    override var bindingVariable: Int = BR.viewModel
    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(bindingVariable, viewModel)

        setupAdapter()

        viewModel.repositoryResult.observe(this, Observer { repositories ->
            adapter.updateRepositories(repositories?.data)
            viewModel.loading.set(false)
        })
    }

    private fun setupAdapter() {
        adapter = RepositoryAdapter(ArrayList(), object : RepositoryAdapter.OnItemClickListener {
            override fun onItemClick(repositoryId: Long) {
                navigator.navigateToRepository(this@RepositoryListActivity, repositoryId)
            }
        })
        binding.repositoryList.layoutManager = LinearLayoutManager(this)
        binding.repositoryList.adapter = adapter
    }
}
