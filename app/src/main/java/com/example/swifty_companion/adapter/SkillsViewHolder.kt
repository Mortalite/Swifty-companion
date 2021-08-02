package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.Utils
import com.example.swifty_companion.databinding.ProgressItemBinding
import com.example.swifty_companion.network.SkillsDTO

class SkillsViewHolder(
    private val binding: ProgressItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(skillsDTO: SkillsDTO) {
        binding.apply {
            progressNameTextView.text = skillsDTO.name
            progressLevelTextView.text = Utils.getFormatLevel(skillsDTO.level)
            progressBar.progress = Utils.getDecimalProgress(skillsDTO.level)
        }
    }

}