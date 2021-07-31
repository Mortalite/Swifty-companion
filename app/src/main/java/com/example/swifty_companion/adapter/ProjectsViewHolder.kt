package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.ProjectsItemBinding
import com.example.swifty_companion.network.ProjectsUsersDTO

class ProjectsViewHolder(
    private var binding: ProjectsItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(projectsUsersDTO: ProjectsUsersDTO) {
        binding.apply {
            projectsNameTextView.text = projectsUsersDTO.project.name
            projectsFinalMarkTextView.text = projectsUsersDTO.finalMark
        }
    }

}