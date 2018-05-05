package com.hunter.christopher.githubviewer

import android.app.Application
import com.hunter.christopher.githubviewer.di.gitHubViewModules
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, gitHubViewModules)
    }
}