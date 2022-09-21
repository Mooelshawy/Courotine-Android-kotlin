package com.omran.coroutineandroidkotlin.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.omran.coroutineandroidkotlin.databinding.FragmentGalleryBinding
import com.omran.coroutineandroidkotlin.extra.App
import com.omran.coroutineandroidkotlin.paging.MoviesPagingDataAdapter
import kotlinx.coroutines.flow.collectLatest

class PopularMovies : Fragment() {

    private lateinit var popularMoviesViewModel: PopularMoviesViewModel

    private lateinit var popularBinding : FragmentGalleryBinding

    private val moviesRecyclerView= MoviesPagingDataAdapter(App.instance())

    private val factory = PopularViewModelFactory()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    )= FragmentGalleryBinding.inflate(inflater, container, false).also {
        popularBinding  = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initViewModel()

    }


    private fun initRecyclerView() {
        popularBinding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(App.instance())
            val decoration = DividerItemDecoration(App.instance(), DividerItemDecoration.VERTICAL )
            setHasFixedSize(true)
            addItemDecoration(decoration)
            popularBinding.mainProgress.visibility = View.GONE
            adapter = moviesRecyclerView

        }
    }

    private fun initViewModel() {
        popularMoviesViewModel = ViewModelProvider(this, factory).get(PopularMoviesViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            popularMoviesViewModel.getListDataPopularMovies().collectLatest {
                moviesRecyclerView.submitData(it)
            }
        }
    }

}