package com.hunter.christopher.githubviewer.ui.repository.list

import android.arch.lifecycle.ViewModel
import com.hunter.christopher.githubviewer.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class RepositoryListActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryListViewModel::class)
    abstract fun bindRepositoryListViewModel(viewModel: RepositoryListViewModel): ViewModel
}