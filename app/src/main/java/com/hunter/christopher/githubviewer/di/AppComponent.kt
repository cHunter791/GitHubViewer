package com.hunter.christopher.githubviewer.di

import android.app.Application
import com.hunter.christopher.githubviewer.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, UiModule::class])
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(instance: MainApplication)
}