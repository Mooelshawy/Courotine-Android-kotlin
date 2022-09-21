package com.omran.coroutineandroidkotlin.details.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.omran.coroutineandroidkotlin.model.Result

class DetailsViewModel : ViewModel() {

    private val _movieDetail = MutableLiveData<Result>()

    val posterUrl: LiveData<String> = Transformations.map(_movieDetail) { it.posterPath}
    val title: LiveData<String> = Transformations.map(_movieDetail) { it.title }
    val rating: LiveData<Float> = Transformations.map(_movieDetail) { it.voteAverage as Float }
    val voteCount: LiveData<String> = Transformations.map(_movieDetail) { it.voteCount.toString() }
    val duration: LiveData<String> = Transformations.map(_movieDetail) { (((it.id)/60)/60).toString()}
    val releaseDate: LiveData<String> =
        Transformations.map(_movieDetail) { it.releaseDate}
    val overview: LiveData<String> = Transformations.map(_movieDetail) { it.overview }

    fun Result.display5StarsRating(): Double = this.voteAverage?.div(2) ?: 0.0

}