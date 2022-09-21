package com.omran.coroutineandroidkotlin.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.omran.coroutineandroidkotlin.R
import com.omran.coroutineandroidkotlin.adapter.MyClickListener
import com.omran.coroutineandroidkotlin.databinding.FragmentHomeBinding
import com.omran.coroutineandroidkotlin.extra.App
import com.omran.coroutineandroidkotlin.paging.MoviesPagingDataAdapter
import kotlinx.coroutines.flow.collectLatest

class TrendingMovies : Fragment() {


    private lateinit var homeViewModel: TrendingMoviesViewModel

    private lateinit var homeBinding: FragmentHomeBinding

    private var moviesRecyclerView = MoviesPagingDataAdapter(App.instance())

    private val factory = ViewModelFactory()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(inflater, container, false).also {
        homeBinding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        initViewModel()

        ItemClickLoader()

    }


    private fun initRecyclerView() {
        homeBinding.mainRecycler.apply {
            layoutManager = LinearLayoutManager(App.instance())
            val decoration = DividerItemDecoration(App.instance(), DividerItemDecoration.VERTICAL )
            homeBinding.mainRecycler.setHasFixedSize(true)
            addItemDecoration(decoration)
            homeBinding.mainProgress.visibility = View.GONE
            adapter = moviesRecyclerView

        }
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(this, factory).get(TrendingMoviesViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            homeViewModel.getListData().collectLatest {
                moviesRecyclerView.submitData(it)
            }
        }
    }


    private fun ItemClickLoader(){
        homeBinding.mainRecycler.addOnItemTouchListener(MyClickListener(App.instance(),
            homeBinding.mainRecycler, object : MyClickListener.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    view!!.findNavController().navigate(R.id.action_nav_trending_to_detailsTrending3)
                }

                override fun onLongItemClick(view: View?, position: Int) {
                    TODO("Not yet implemented")
                }
            }))
    }

}