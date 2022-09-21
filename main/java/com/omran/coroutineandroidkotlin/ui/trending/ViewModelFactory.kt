package com.omran.coroutineandroidkotlin.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


// this class for make view model object
//and use view model provider
@Suppress("UNCHECKED_CAST")
class ViewModelFactory() : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrendingMoviesViewModel() as T
    }
}