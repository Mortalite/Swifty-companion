package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.SkillsItemBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.network.SkillsDTO

class SkillsViewHolder(private val binding: SkillsItemBinding): RecyclerView.ViewHolder(binding.root) {

    private val TAG = this.javaClass.simpleName

    fun bind(skillsDTO: SkillsDTO) {
        binding.apply {
            skillNameTextView.text = skillsDTO.name
            skillLevelTextView.text = "${skillsDTO.level} lvl"
        }
    }
}