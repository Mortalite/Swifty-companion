package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.CursusItemBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.network.CursusUsersDTO
import com.example.swifty_companion.viewmodel.UserViewModel

class CursusViewHolder(
    private val binding: CursusItemBinding,
    private var adapterListener: AdapterListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(cursusUsersDTO: CursusUsersDTO) {
        binding.apply {
            cursusNameTextView.text = cursusUsersDTO.cursus.name
            cursusLevelTextView.text = "${cursusUsersDTO.level} lvl"
            cursusButton.setOnClickListener {
                adapterListener.onCursusClick(
                    cursusUsersDTO.cursus.id,
                    adapterPosition)
            }
        }
    }

}