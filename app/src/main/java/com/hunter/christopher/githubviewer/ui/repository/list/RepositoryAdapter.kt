package com.hunter.christopher.githubviewer.ui.repository.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hunter.christopher.githubviewer.data.model.Repository
import com.hunter.christopher.githubviewer.databinding.ListItemRepositoryBinding

class RepositoryAdapter(private var items: ArrayList<Repository>, private var listener: OnItemClickListener)
    : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    fun updateRepositories(repositories: List<Repository>?) {
        if (repositories != null) {
            items.clear()
            items.addAll(repositories)
            notifyDataSetChanged()
        }
    }

    interface OnItemClickListener {

        fun onItemClick(repositoryId: Long)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    class ViewHolder(private var binding: ListItemRepositoryBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository, listener: OnItemClickListener) {
            binding.repository = repository
            binding.root.setOnClickListener({ listener.onItemClick(repository.id) })
            binding.executePendingBindings()
        }
    }
}