package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.ProjectItemBinding
import com.example.swifty_companion.network.CursusUsersDTO

class ProjectsViewHolder(
    private val binding: ProjectItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val TAG = this.javaClass.simpleName

    fun bind(cursusUsersDTO: CursusUsersDTO) {
        binding.apply {
            projectTextView.text = cursusUsersDTO.cursus.name
        }
    }

}