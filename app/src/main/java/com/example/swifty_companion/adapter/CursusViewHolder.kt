package com.example.swifty_companion.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.swifty_companion.databinding.CursusItemBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.network.CursusUsersDTO

class CursusViewHolder(
    private val binding: CursusItemBinding
): RecyclerView.ViewHolder(binding.root) {

    private val TAG = this.javaClass.simpleName

    fun bind(cursusUsersDTO: CursusUsersDTO, adapterListener: AdapterListener) {
        binding.apply {
            cursusButton.text = cursusUsersDTO.cursus.name
            cursusButton.setOnClickListener {
                adapterListener.onCursusClick(cursusUsersDTO.cursus.id)
            }
        }
    }
}