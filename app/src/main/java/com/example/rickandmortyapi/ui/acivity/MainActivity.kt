package com.example.rickandmortyapi.ui.acivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapi.data.CartoonPagingSource
import com.example.rickandmortyapi.databinding.ActivityMainBinding
import com.example.rickandmortyapi.models.Character
import com.example.rickandmortyapi.ui.adapter.CartoonPagingAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val characterAdapter = CartoonPagingAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        initialize()
        fetchCharacters().observe(this) {data->
            characterAdapter.submitData(this.lifecycle,data)

        }

    }

    private fun initialize() {
        binding.rvCharacter.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
        }
    }


    private fun fetchCharacters(): LiveData<PagingData<Character>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CartoonPagingSource()
            }
        ).liveData

    }


}