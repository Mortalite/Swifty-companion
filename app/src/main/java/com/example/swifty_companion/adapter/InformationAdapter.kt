package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.InformationItemBinding
import com.example.swifty_companion.network.InformationEntity

class InformationAdapter : androidx.recyclerview.widget.ListAdapter<InformationEntity, InfromationViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfromationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = InformationItemBinding.inflate(layoutInflater, parent, false)
        return InfromationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfromationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<InformationEntity>() {

            override fun areItemsTheSame(oldItem: InformationEntity, newItem: InformationEntity): Boolean {
                return oldItem.image == newItem.image
            }

            override fun areContentsTheSame(oldItem: InformationEntity, newItem: InformationEntity): Boolean {
                return  oldItem.name == newItem.name &&
                        oldItem.value == newItem.value
            }
        }
    }
}