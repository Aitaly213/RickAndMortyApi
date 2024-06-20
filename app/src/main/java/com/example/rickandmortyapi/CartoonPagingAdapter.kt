package com.example.rickandmortyapi

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.rickandmortyapi.models.Character

class CartoonPagingAdapter : PagingDataAdapter<Character, CharacterViewHolder>(CharacterItemCallback()) {
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        TODO("Not yet implemented")
    }
}

class CharacterItemCallback : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        TODO("Not yet implemented")
    }

}

class CharacterViewHolder(val binding: ViewBinding) : ViewHolder(binding.root) {

}
