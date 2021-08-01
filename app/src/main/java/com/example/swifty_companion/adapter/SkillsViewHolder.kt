package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.Utils
import com.example.swifty_companion.databinding.SkillsItemBinding
import com.example.swifty_companion.network.SkillsDTO
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class SkillsViewHolder(
    private val binding: SkillsItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(skillsDTO: SkillsDTO) {
        binding.apply {
            skillNameTextView.text = skillsDTO.name
            skillLevelTextView.text = Utils.getFormatLevel(skillsDTO.level)
        }
    }

}