package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.ProjectItemBinding
import com.example.swifty_companion.network.CursusUsersDTO

class ProjectsAdapter: androidx.recyclerview.widget.ListAdapter<CursusUsersDTO, ProjectsViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProjectItemBinding.inflate(layoutInflater, parent, false)
        return ProjectsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<CursusUsersDTO>() {

            override fun areItemsTheSame(oldItem: CursusUsersDTO, newItem: CursusUsersDTO): Boolean {
                return oldItem.cursus.id == newItem.cursus.id
            }

            override fun areContentsTheSame(oldItem: CursusUsersDTO, newItem: CursusUsersDTO): Boolean {
                return oldItem.cursus.name == newItem.cursus.name
            }
        }
    }

}