package com.hunter.christopher.githubviewer.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hunter.christopher.githubviewer.data.db.AppDatabase
import com.hunter.christopher.githubviewer.data.db.RepositoryDao
import com.hunter.christopher.githubviewer.data.repositories.GitHubLocalRepository
import com.hunter.christopher.githubviewer.data.repositories.GitHubRemoteRepository
import com.hunter.christopher.githubviewer.data.repositories.GitHubRepository
import com.hunter.christopher.githubviewer.ui.navigation.Navigator
import com.hunter.christopher.githubviewer.ui.repository.detail.RepositoryDetailViewModel
import com.hunter.christopher.githubviewer.ui.repository.list.RepositoryListViewModel
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

val AppModule: Module = applicationContext {
    bean { gson() }
    bean { okHttpClient(get()) }
    bean { retrofit(get(), get(), "https://api.github.com") }

    bean { repositoryDatabase(get()) }
    bean { repositoryDao(get()) }
}

val GitHubViewerModule: Module = applicationContext {
    viewModel { RepositoryListViewModel(get()) }
    viewModel { RepositoryDetailViewModel(get()) }

    factory { Navigator() }

    bean { GitHubRemoteRepository(get()) }
    bean { GitHubLocalRepository(get()) }
    bean { GitHubRepository(get(), get()) }
}

fun repositoryDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "github-database")
            .fallbackToDestructiveMigration()
            .build()
}

fun repositoryDao(appDatabase: AppDatabase): RepositoryDao {
    return appDatabase.repositoryDao()
}

fun gson(): Gson {
    return GsonBuilder()
            .create()
}

fun retrofit(gson: Gson, okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
    return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
}

fun okHttpClient(application: Application): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
    val cache = Cache(cacheDir, 10 * 1024 * 1024)

    return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
}

val gitHubViewModules = listOf(AppModule, GitHubViewerModule)