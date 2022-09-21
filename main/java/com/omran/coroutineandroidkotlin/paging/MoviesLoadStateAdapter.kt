package com.omran.coroutineandroidkotlin.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omran.coroutineandroidkotlin.R
import com.omran.coroutineandroidkotlin.databinding.ItemProgressBinding

class MoviesLoadStateAdapter(  private val retry: () -> Unit)
    : LoadStateAdapter<MoviesLoadStateAdapter.PassengerLoadStateViewHolder>(){

    inner class PassengerLoadStateViewHolder(
        private val binding: ItemProgressBinding,
        private val retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.movieTitle.text="omran elshawy"
            binding.movieDesc.text="omran elshawy"
            binding.movieYear.text="omran elshawy"
            binding.movieProgress.visibility= View.VISIBLE
            binding.moviePoster.setImageResource(R.drawable.blue_bull)
        }
    }

    override fun onBindViewHolder(holder: PassengerLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    )= PassengerLoadStateViewHolder(
        ItemProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        retry
    )
}