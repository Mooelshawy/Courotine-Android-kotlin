package com.omran.coroutineandroidkotlin.paging

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.omran.coroutineandroidkotlin.R
import com.omran.coroutineandroidkotlin.adapter.MovieVH
import com.omran.coroutineandroidkotlin.databinding.DisplayMovieBinding
import com.omran.coroutineandroidkotlin.databinding.FragmentHomeBinding
import com.omran.coroutineandroidkotlin.extra.App
import com.omran.coroutineandroidkotlin.model.Result
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class MoviesPagingDataAdapter(private val context: Context) :
    PagingDataAdapter<Result, MovieVH>(MoviesComparator) {

    //variable for know data is loaded or stel loading
    private val ITEM = 0
    private val LOADING = 20

    private lateinit var homeBinding: FragmentHomeBinding

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieVH, position: Int) {


        when (getItemViewType(position)) {
            ITEM -> {
                holder.bind(getItem(position)!!)
            }
            /*
            put data in view when data loding from api
            TODO() replace code in picasso in try/catch to put data in view when loading process
            */
            LOADING -> {
                // this is for appear loading sign  when moved on to next  page
                 homeBinding.mainRecycler.adapter =  MoviesPagingDataAdapter(context).withLoadStateHeaderAndFooter(
                        header = MoviesLoadStateAdapter { MoviesPagingDataAdapter(context).retry() },
                        footer = MoviesLoadStateAdapter { MoviesPagingDataAdapter(context).retry() }
                    )
                }
            }
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {

       homeBinding =  FragmentHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //connect adapter with layout file as you want display data
        return MovieVH(
            DisplayMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    object MoviesComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.popularity == newItem.popularity &&
                    oldItem.voteAverage == newItem.voteAverage && oldItem.posterPath == newItem.posterPath &&
                    oldItem.overview == newItem.overview
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    protected class LoadingVH(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!)
}