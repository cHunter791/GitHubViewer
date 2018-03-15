package com.hunter.christopher.githubviewer.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "owners")
data class Owner(
        @PrimaryKey
        @SerializedName("id")
        @ColumnInfo(name = "id")
        val id: Long,
        @SerializedName("login")
        @ColumnInfo(name = "login")
        val login: String,
        @SerializedName("avatar_url")
        @ColumnInfo(name = "avatarUrl")
        val avatarUrl: String
)