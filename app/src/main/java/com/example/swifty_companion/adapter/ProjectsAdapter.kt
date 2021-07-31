package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.ProjectsItemBinding
import com.example.swifty_companion.network.ProjectsUsersDTO

class ProjectsAdapter: androidx.recyclerview.widget.ListAdapter<ProjectsUsersDTO, ProjectsViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProjectsItemBinding.inflate(layoutInflater, parent, false)
        return ProjectsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<ProjectsUsersDTO>() {

            override fun areItemsTheSame(oldItem: ProjectsUsersDTO, newItem: ProjectsUsersDTO): Boolean {
                return oldItem.project.name == newItem.project.name
//                return  oldItem.status == newItem.status
            }

            override fun areContentsTheSame(oldItem: ProjectsUsersDTO, newItem: ProjectsUsersDTO): Boolean {
                return  oldItem.status == newItem.status &&
                        oldItem.project.name == newItem.project.name
//                return  oldItem.status == newItem.status

            }
        }
    }

}