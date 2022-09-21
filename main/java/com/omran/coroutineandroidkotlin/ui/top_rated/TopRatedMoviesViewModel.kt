package com.omran.coroutineandroidkotlin.ui.top_rated


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omran.coroutineandroidkotlin.model.Result
import com.omran.coroutineandroidkotlin.paging.MoviesDataSource
import com.omran.coroutineandroidkotlin.remote.RetrofitBuilder
import com.omran.coroutineandroidkotlin.remote.ServiceApi
import kotlinx.coroutines.flow.Flow

class TopRatedMoviesViewModel : ViewModel() {

    val serviceInstance = RetrofitBuilder.getRetroBuilder().create(ServiceApi::class.java)



    fun getListDataTopRatedMovies(): Flow<PagingData<Result>> {
        //number of page display
        return Pager (config = PagingConfig(pageSize = 10, maxSize = 40),
            pagingSourceFactory = { MoviesDataSource(serviceInstance,1) }).flow.cachedIn(viewModelScope)
    }
}