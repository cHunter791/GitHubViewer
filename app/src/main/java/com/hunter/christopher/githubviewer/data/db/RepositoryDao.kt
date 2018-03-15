package com.hunter.christopher.githubviewer.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.hunter.christopher.githubviewer.data.model.Repository
import io.reactivex.Flowable

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories")
    fun getRepositoriesForUser(): Flowable<List<Repository>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: Repository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repositories: List<Repository>)

    @Query("DELETE FROM repositories")
    fun clear()
}