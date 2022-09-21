package com.omran.coroutineandroidkotlin.ui.top_rated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TopRatedViewModelFactory  : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopRatedMoviesViewModel() as T
    }
}