package com.hunter.christopher.githubviewer.ui.repository.detail

import android.os.Bundle
import android.view.MenuItem
import com.android.databinding.library.baseAdapters.BR
import com.hunter.christopher.githubviewer.R
import com.hunter.christopher.githubviewer.databinding.ActivityRepositoryDetailBinding
import com.hunter.christopher.githubviewer.ui.base.BaseActivity
import com.hunter.christopher.githubviewer.ui.navigation.Navigator
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class RepositoryDetailActivity : BaseActivity<ActivityRepositoryDetailBinding>() {

    private val viewModel: RepositoryDetailViewModel by viewModel()
    override var layoutId: Int = R.layout.activity_repository_detail
    override var bindingVariable: Int = BR.viewModel
    private val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.setVariable(bindingVariable, viewModel)

        viewModel.repositoryId.value = navigator.getRepoId(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}