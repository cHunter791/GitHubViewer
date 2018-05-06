package com.hunter.christopher.githubviewer.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.hunter.christopher.githubviewer.data.model.Repository

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repositories")
    fun getRepositoriesForUser(): List<Repository>

    @Query("SELECT * FROM repositories WHERE id LIKE :id")
    fun getRepository(id: Long): Repository

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: Repository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repositories: List<Repository>)

    @Query("DELETE FROM repositories")
    fun clear()
}