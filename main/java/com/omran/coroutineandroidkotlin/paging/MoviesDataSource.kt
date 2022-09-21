package com.omran.coroutineandroidkotlin.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omran.coroutineandroidkotlin.model.Result
import com.omran.coroutineandroidkotlin.model.TrendingMovieDataModel
import com.omran.coroutineandroidkotlin.remote.ServiceApi
import retrofit2.Response

class MoviesDataSource (
    private val apiInterface: ServiceApi ,
    private val endpoint :Int
) : PagingSource<Int, Result>() {


    lateinit var  response : Response<TrendingMovieDataModel>

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {


        return try {
            val nextPage = params.key ?: pageIndex

            when(endpoint){
                0 -> {
                    response = apiInterface.getTrendingMovies(apiKey = apiKey ,
                        language = language , pageIndex = nextPage)
                }
                1-> {
                    response = apiInterface.getTopRatedMovies(apiKey = apiKey ,
                        language = language , pageIndex = nextPage)
                }
                2 -> {
                    response = apiInterface.getPopularMovies(apiKey = apiKey ,
                        language = language , pageIndex = nextPage)
                }
            }


            var nextPageNumber: Int? = null
            if (response.body()!!.page < response.body()!!.totalPages){
                nextPageNumber = (response.body()!!.page +1 ).toInt()
            }

            var prevPageNumber: Int? = null
            if(response.body()!!.page < response.body()!!.totalPages) {
                prevPageNumber = (response.body()!!.page  - 1 ).toInt()
            }


            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = prevPageNumber ,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            Log.i("//////", "load: load function")
            LoadResult.Error(e)
        }
    }

    companion object{
        val  apiKey = "3303e2d183249b1cb838f78f2862138a"
        val language = "en"
        var pageIndex = 1
    }
    }
