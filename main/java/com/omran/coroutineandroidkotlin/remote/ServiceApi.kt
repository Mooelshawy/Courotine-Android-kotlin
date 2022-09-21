package com.omran.coroutineandroidkotlin.remote

import com.omran.coroutineandroidkotlin.model.TrendingMovieDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("trending/all/day")
   suspend fun getTrendingMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") pageIndex: Int
    ): Response<TrendingMovieDataModel>

   @GET("movie/popular")
   suspend fun getPopularMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") pageIndex: Int
    ): Response<TrendingMovieDataModel>

   @GET("movie/top_rated")
   suspend fun getTopRatedMovies(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") pageIndex: Int
    ): Response<TrendingMovieDataModel>
}