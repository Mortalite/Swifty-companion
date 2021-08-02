package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.Utils
import com.example.swifty_companion.databinding.ProgressItemBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.network.CursusUsersDTO

class CursusViewHolder(
    private val binding: ProgressItemBinding,
    private var adapterListener: AdapterListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(cursusUsersDTO: CursusUsersDTO) {
        binding.apply {
            progressNameTextView.text = cursusUsersDTO.cursus.name
            progressLevelTextView.text = Utils.getFormatLevel(cursusUsersDTO.level)
            progressBar.progress = Utils.getDecimalProgress(cursusUsersDTO.level)

            progressButton.setOnClickListener {
                adapterListener.onCursusClick(
                    cursusUsersDTO.cursus.id,
                    adapterPosition)
            }
        }
    }

}