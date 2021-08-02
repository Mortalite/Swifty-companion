package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.Utils.Companion.loadUrl
import com.example.swifty_companion.databinding.AchievementItemBinding
import com.example.swifty_companion.databinding.InformationItemBinding
import com.example.swifty_companion.network.AchievementsDTO

class AchievementsViewHolder(
    private val binding: AchievementItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(achievementsDTO: AchievementsDTO) {
        binding.apply {
            achievementImageView.loadUrl("https://api.intra.42.fr${achievementsDTO.image}")
            achievementNameTextView.text = achievementsDTO.name
            achievementValueTextView.text = achievementsDTO.description
        }
    }

}