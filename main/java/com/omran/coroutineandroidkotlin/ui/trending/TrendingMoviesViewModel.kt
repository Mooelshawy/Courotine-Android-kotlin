package com.omran.coroutineandroidkotlin.ui.trending


import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omran.coroutineandroidkotlin.extra.App
import com.omran.coroutineandroidkotlin.model.Result
import com.omran.coroutineandroidkotlin.paging.MoviesDataSource

import com.omran.coroutineandroidkotlin.remote.RetrofitBuilder
import com.omran.coroutineandroidkotlin.remote.ServiceApi
import kotlinx.coroutines.flow.Flow


class TrendingMoviesViewModel : AndroidViewModel(App.instance()) {

    private val serviceInstance = RetrofitBuilder.getRetroBuilder().create(ServiceApi::class.java)

    fun getListData(): Flow<PagingData<Result>> {
        //number of page display
        return Pager (config = PagingConfig(pageSize = 10, maxSize = 40),
            pagingSourceFactory = {MoviesDataSource(serviceInstance,0)}).flow.cachedIn(viewModelScope)
    }
}