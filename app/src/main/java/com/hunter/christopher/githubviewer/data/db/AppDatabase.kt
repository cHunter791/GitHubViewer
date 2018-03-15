package com.hunter.christopher.githubviewer.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.hunter.christopher.githubviewer.data.model.Owner
import com.hunter.christopher.githubviewer.data.model.Repository

@Database(entities = [Repository::class, Owner::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao
}