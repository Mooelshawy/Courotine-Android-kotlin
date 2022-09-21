package com.omran.coroutineandroidkotlin.ui.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PopularViewModelFactory  : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel() as T
    }
}