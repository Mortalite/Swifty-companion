package com.example.swifty_companion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.swifty_companion.databinding.ProgressItemBinding
import com.example.swifty_companion.listener.AdapterListener
import com.example.swifty_companion.network.CursusUsersDTO
import com.example.swifty_companion.viewmodel.UserViewModel

class CursusAdapter(
    private var adapterListener: AdapterListener,
    private val userViewModel: UserViewModel?
): androidx.recyclerview.widget.ListAdapter<CursusUsersDTO, CursusViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursusViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProgressItemBinding.inflate(layoutInflater, parent, false)
        return CursusViewHolder(binding, adapterListener)
    }

    override fun onBindViewHolder(holder: CursusViewHolder, position: Int) {
        holder.bind(getItem(position))

        userViewModel?.buttonSettings?.let {
            if (position == it.position)
                holder.itemView.setBackgroundColor(it.colorSelected)
            else
                holder.itemView.setBackgroundColor(it.colorInit)
        }
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