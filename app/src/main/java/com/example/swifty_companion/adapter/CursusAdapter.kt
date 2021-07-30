package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.CursusItemBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.network.CursusUsersDTO

class CursusAdapter(
    private var adapterListener: AdapterListener
): androidx.recyclerview.widget.ListAdapter<CursusUsersDTO, CursusViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursusViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CursusItemBinding.inflate(layoutInflater, parent, false)
        return CursusViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CursusViewHolder, position: Int) {
        holder.bind(getItem(position), adapterListener)
    }

    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<CursusUsersDTO>() {

            override fun areItemsTheSame(oldItem: CursusUsersDTO, newItem: CursusUsersDTO): Boolean {
                return oldItem.cursus.id == newItem.cursus.id
            }

            override fun areContentsTheSame(oldItem: CursusUsersDTO, newItem: CursusUsersDTO): Boolean {
                return oldItem.cursus.name == newItem.cursus.name
            }
        }
    }

}