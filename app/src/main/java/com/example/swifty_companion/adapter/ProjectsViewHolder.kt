package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.ProjectItemBinding
import com.example.swifty_companion.network.cursusUsersDTO

class ProjectsViewHolder(
    private val binding: ProjectItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val TAG = this.javaClass.simpleName

    fun bind(dataDTO: cursusUsersDTO) {

    }

}