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
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    fun provideApplication(application: Application): Context = application

    @Provides
    @Singleton
    fun provideRepositoryDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "github-database")
                .build()
    }

    @Provides
    @Singleton
    fun provideRepositoryDao(appDatabase: AppDatabase): RepositoryDao {
        return appDatabase.repositoryDao()
    }

    @Provides
    @Singleton
    fun provideGitHubLocalRepository(repositoryDao: RepositoryDao): GitHubLocalRepository {
        return GitHubLocalRepository(repositoryDao)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
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

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideGitHubRemoteRepository(retrofit: Retrofit): GitHubRemoteRepository {
        return GitHubRemoteRepository(retrofit)
    }

    @Provides
    @Singleton
    fun provideGitHubRepository(localRepository: GitHubLocalRepository, remoteRepository: GitHubRemoteRepository): GitHubRepository {
        return GitHubRepository(localRepository, remoteRepository)
    }
}