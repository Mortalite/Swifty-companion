package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.InformationItemBinding
import com.example.swifty_companion.network.InformationEntity

class InfromationViewHolder(private val binding: InformationItemBinding): RecyclerView.ViewHolder(binding.root) {


    fun bind(informationEntity: InformationEntity) {
        binding.apply {
            informationImageView.setImageResource(informationEntity.image)
            informationNameTextView.text = informationEntity.name
            informationValueTextView.text = informationEntity.value
        }
    }

}