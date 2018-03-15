package com.hunter.christopher.githubviewer.di

import android.arch.lifecycle.ViewModelProvider
import com.hunter.christopher.githubviewer.ui.repository.list.RepositoryListActivity
import com.hunter.christopher.githubviewer.ui.repository.list.RepositoryListActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [RepositoryListActivityModule::class])
    internal abstract fun contributeLoginActivity(): RepositoryListActivity
}