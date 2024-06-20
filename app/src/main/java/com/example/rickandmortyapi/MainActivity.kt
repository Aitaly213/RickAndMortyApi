package com.example.rickandmortyapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickandmortyapi.databinding.ActivityMainBinding
import com.example.rickandmortyapi.models.Character

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val adapter by lazy {
        CartoonPagingAdapter()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchCharacters().observe(this) {data->
            adapter.submitData(this.lifecycle,data)
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