package com.omran.coroutineandroidkotlin.ui.top_rated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.omran.coroutineandroidkotlin.databinding.FragmentSlideshowBinding
import com.omran.coroutineandroidkotlin.extra.App
import com.omran.coroutineandroidkotlin.paging.MoviesPagingDataAdapter
import kotlinx.coroutines.flow.collectLatest

class TopRatedMovies : Fragment() {

    private lateinit var topRatedMoviesViewModel: TopRatedMoviesViewModel
    private lateinit var topRatedBinding : FragmentSlideshowBinding

    private val moviesRecyclerView = MoviesPagingDataAdapter(App.instance())

    private val factory = TopRatedViewModelFactory()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    )= FragmentSlideshowBinding.inflate(inflater, container, false).also {
        topRatedBinding  = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        initViewModel()

    }

    private fun initRecyclerView() {
        topRatedBinding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(App.instance())
            val decoration = DividerItemDecoration(App.instance(), DividerItemDecoration.VERTICAL )
            topRatedBinding.mainRecycler.setHasFixedSize(true)
            addItemDecoration(decoration)
            topRatedBinding.mainProgress.visibility = View.GONE
            adapter = moviesRecyclerView

        }
    }

    private fun initViewModel() {
        topRatedMoviesViewModel = ViewModelProvider(this, factory).get(TopRatedMoviesViewModel::class.java)

        lifecycleScope.launchWhenCreated {
            topRatedMoviesViewModel.getListDataTopRatedMovies().collectLatest {
                moviesRecyclerView.submitData(it)
            }
        }
    }
}