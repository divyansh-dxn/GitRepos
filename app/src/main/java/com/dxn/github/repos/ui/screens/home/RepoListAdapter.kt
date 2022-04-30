package com.dxn.github.repos.ui.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dxn.github.repos.R
import com.dxn.github.repos.common.models.Repo

class RepoListAdapter(val onItemClick: (repo: Repo) -> Unit) :
    PagingDataAdapter<Repo, RepoListAdapter.RepoItemViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id == newItem.id
        }
    }

    class RepoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun getInstance(parent: ViewGroup): RepoItemViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
                return RepoItemViewHolder(view)
            }
        }

        val repoTitleTV: TextView by lazy { itemView.findViewById(R.id.list_item_title) }
        val repoIconIV: ImageView by lazy { itemView.findViewById(R.id.list_item_iv) }
        val repoStarsTV: TextView by lazy { itemView.findViewById(R.id.list_item_subtitle) }
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.apply {
            repoTitleTV.text = item.full_name
            repoIconIV.load(item.owner.avatar_url)
            repoStarsTV.text = "Stars: ${item.stargazers_count}"
            itemView.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder =
        RepoItemViewHolder.getInstance(parent)
}