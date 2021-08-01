package com.example.swifty_companion.adapter

import android.graphics.Color
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.R
import com.example.swifty_companion.Utils
import com.example.swifty_companion.databinding.ProjectsItemBinding
import com.example.swifty_companion.network.ProjectsUsersDTO
import com.example.swifty_companion.viewmodel.OAuth2TokenViewModel
import kotlinx.serialization.encodeToString

class ProjectsViewHolder(
    private var binding: ProjectsItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(projectsUsersDTO: ProjectsUsersDTO) {
        binding.apply {

            projectsNameTextView.text = projectsUsersDTO.project.name
            if (projectsUsersDTO.status == "finished") {
                projectsUsersDTO.finalMark?.let {
                    val mark = it.toIntOrNull()

                    if (mark != null) {
                        if (mark >= 100)
                            projectsFinalMarkTextView.setTextColor(Color.GREEN)
                        else
                            projectsFinalMarkTextView.setTextColor(Color.RED)
                    }

                }
                projectsFinalMarkTextView.text = projectsUsersDTO.finalMark
            }
            else {
                projectsFinalMarkTextView.setTextColor(R.attr.colorPrimary)
                projectsFinalMarkTextView.text = projectsUsersDTO.status
                    .replace("_", " ").replaceFirstChar { it.uppercase() }
            }
        }
    }

}