package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.SkillsItemBinding
import com.example.swifty_companion.network.SkillsDTO

class SkillsAdapter: androidx.recyclerview.widget.ListAdapter<SkillsDTO, SkillsViewHolder>(itemComparator)
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SkillsItemBinding.inflate(layoutInflater, parent, false)
        return SkillsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<SkillsDTO>() {

            override fun areItemsTheSame(oldItem: SkillsDTO, newItem: SkillsDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SkillsDTO, newItem: SkillsDTO): Boolean {
                return  oldItem.name == newItem.name &&
                        oldItem.level == newItem.level
            }
        }
    }
}