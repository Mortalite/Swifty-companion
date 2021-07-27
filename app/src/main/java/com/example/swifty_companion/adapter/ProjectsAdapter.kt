package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.ProjectItemBinding
import com.example.swifty_companion.network.cursusUsersDTO

class ProjectsAdapter constructor(
    private var cursusUsers: List<cursusUsersDTO>
): androidx.recyclerview.widget.ListAdapter<cursusUsersDTO, ProjectsViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProjectItemBinding.inflate(layoutInflater, parent, false)
        return ProjectsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return cursusUsers.size
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<cursusUsersDTO>() {

            override fun areItemsTheSame(oldItem: cursusUsersDTO, newItem: cursusUsersDTO): Boolean {
                return oldItem.cursus.id == newItem.cursus.id
            }

            override fun areContentsTheSame(oldItem: cursusUsersDTO, newItem: cursusUsersDTO): Boolean {
                return oldItem.cursus.name == newItem.cursus.name
            }
        }
    }

}