package com.omran.coroutineandroidkotlin.model


import androidx.annotation.Keep
import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName


@Keep

data class TrendingMovieDataModel(

        @NonNull
        var page: Long,

        @field:SerializedName("results")
        val results: List<Result> = emptyList(),

        @field:SerializedName("total_pages")
        val totalPages: Long,

        @field:SerializedName("total_results")
        val totalResults: Long
)