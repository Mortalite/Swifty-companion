package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.InformationItemBinding
import com.example.swifty_companion.network.AchievementsDTO

class AchievementsAdapter : androidx.recyclerview.widget.ListAdapter<AchievementsDTO, AchievementsViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievementsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = InformationItemBinding.inflate(layoutInflater, parent, false)
        return AchievementsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AchievementsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<AchievementsDTO>() {

            override fun areItemsTheSame(oldItem: AchievementsDTO, newItem: AchievementsDTO): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: AchievementsDTO, newItem: AchievementsDTO): Boolean {
                return  oldItem.name == newItem.name &&
                        oldItem.description == newItem.description
            }
        }
    }

}