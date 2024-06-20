package com.example.rickandmortyapi

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyapi.models.BaseResponse
import com.example.rickandmortyapi.models.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

const val START_IDX = 1

class CartoonPagingSource : PagingSource<Int , Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val current = params.key ?: START_IDX
            val previosKey = if (current == START_IDX) null else current.minus(1)
            val response = fetchCharacters()
            LoadResult.Page(
                data = response,
                prevKey = previosKey,
                nextKey = current.plus(1)

            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    private fun fetchCharacters(): List<Character> {
        var mResponse: List<Character> = emptyList()

        RetrofitService.api.fetchCharacters().enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                mResponse = (response.body()?.characters) ?: emptyList()
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                mResponse = emptyList()
            }
        })

        return mResponse

    }
}