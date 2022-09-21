package com.omran.coroutineandroidkotlin.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull


@Parcelize
@Keep
data class Result(

    @field:SerializedName("data")


    @field:SerializedName("overview")
    @Expose
    val overview: String,

    @field:SerializedName("release_date")
    @Expose
    val releaseDate: String,

    @field:SerializedName("adult")
    val adult: Boolean? = null,

    @field:SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,

    @field:SerializedName("genre_ids")
    val genreIDS: List<Long>,

    @field:SerializedName("title")
    @Expose
    val title: String? = null,

    @field:SerializedName("original_language")
    @Expose
    val originalLanguage: String,

    @field:SerializedName("original_title")
    @Expose
    val originalTitle: String? = null,

    @field:SerializedName("poster_path")
    @Expose
    val posterPath: String,

    @field:SerializedName("vote_count")
    val voteCount: Long,

    @field:SerializedName("id")
    val id: Long,

    @field:SerializedName("vote_average")

    var voteAverage: Double,


    @field:SerializedName("video")
    val video: Boolean? = null,

    @field:SerializedName("popularity")
    val popularity: Double,

    @field:SerializedName("media_type")
    val mediaType: MediaType,

    @field:SerializedName("name")
    @Expose
    val name: String? = null,

    @field:SerializedName("first_air_date")
    @Expose
    val firstAirDate: String? = null,

    @field:SerializedName("origin_country")
    val originCountry: List<String>? = null,

    @field:SerializedName("original_name")
    @Expose
    val originalName: String? = null



) : Parcelable
