package com.hunter.christopher.githubviewer.ui.navigation

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import com.hunter.christopher.githubviewer.ui.repository.detail.RepositoryDetailActivity

class Navigator {

    companion object {
        private const val EXTRA_REPO_ID = "EXTRA_REPO_ID"
    }

    fun navigateToRepository(activity: Activity, id: Long) {
        val intent = Intent(activity, RepositoryDetailActivity::class.java)
        intent.putExtra(EXTRA_REPO_ID, id)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle()
        ActivityCompat.startActivity(activity, intent, options)
    }

    fun getRepoId(activity: Activity) = activity.intent.getLongExtra(EXTRA_REPO_ID, 0)
}