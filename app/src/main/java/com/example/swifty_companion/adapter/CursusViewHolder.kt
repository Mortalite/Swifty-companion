package com.example.swifty_companion.adapter

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.CursusItemBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.network.CursusUsersDTO

class CursusViewHolder(
    private val binding: CursusItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(cursusUsersDTO: CursusUsersDTO, adapterListener: AdapterListener) {
        binding.apply {
            cursusNameTextView.text = cursusUsersDTO.cursus.name
            cursusLevelTextView.text = "${cursusUsersDTO.level} lvl"
            cursusButton.setOnClickListener {
                adapterListener.onCursusClick(cursusUsersDTO.cursus.id)
            }
        }
    }

}