package com.hunter.christopher.githubviewer.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repositories")
data class Repository(
        @PrimaryKey
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: Long,
        @SerializedName("name")
        @ColumnInfo(name = "repoName")
        val name: String,
        @SerializedName("description")
        @ColumnInfo(name = "description")
        val description: String,
        @SerializedName("created_at")
        @ColumnInfo(name = "createdAt")
        val createdAt: String,
        @SerializedName("updated_at")
        @ColumnInfo(name = "updatedAt")
        val updatedAt: String,
        @SerializedName("stargazers_count")
        @ColumnInfo(name = "numStars")
        val stars: Int,
        @SerializedName("language")
        @ColumnInfo(name = "language")
        val language: String,
        @SerializedName("open_issues")
        @ColumnInfo(name = "numIssues")
        val issues: Int)