package com.omran.coroutineandroidkotlin.adapter


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.FutureTarget
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target
import com.omran.coroutineandroidkotlin.R
import com.omran.coroutineandroidkotlin.databinding.DisplayMovieBinding
import com.omran.coroutineandroidkotlin.extra.App
import com.omran.coroutineandroidkotlin.model.Result
import com.squareup.picasso.Picasso
import java.util.*


class MovieVH(itemView: DisplayMovieBinding) :
    RecyclerView.ViewHolder(itemView.root) {
    val mMovieTitle = itemView.movieTitle
    val mMovieDesc = itemView.movieDesc
    val mYear = itemView.movieYear // displays "year | language"
    val mPosterImg = itemView.moviePoster
    val mProgress = itemView.movieProgress
    val mRating = itemView.movieRating // displays "Rating"


    @SuppressLint("SetTextI18n")
    fun bind(result: Result) {
        //link for loading image from api
        val BASE_URL_IMG = "https://image.tmdb.org/t/p/w500/" + result.posterPath


        mMovieTitle.text = result.title

        if (result.firstAirDate != null && result.firstAirDate.length >= 4) {
            mYear.text = (result.firstAirDate // we want the year only
                .toString() + " | "
                    + result.originalLanguage.toString().toUpperCase(Locale.ROOT))
        } else {
            mYear.text = "???"
        }

        if (result.releaseDate != null
            && result.releaseDate.length >= 4
        ) {
            mYear.setText(
                result.releaseDate.substring(0, 4) // we want the year only
                    .toString() + " | "
                        + result.originalLanguage.toString()
                    .toUpperCase(Locale.ROOT)
            )

        } else {
            mYear.text = "???"
        }


        mMovieDesc.text = result.overview

        mRating.text = "R:" + result.voteAverage.toString()







        Glide
            .with(App.instance())
            .asBitmap()
            .load(BASE_URL_IMG)
            .listener(object : RequestListener<Bitmap>{
                override fun equals(other: Any?): Boolean {
                    return super.equals(other)
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("body*****999999", "onFailure: ${result.posterPath}   Loading is failure ")
                    mProgress.visibility = View.GONE
                    mPosterImg.setBackgroundColor(
                        Color.rgb(13, 37, 63)
                    )
                    Picasso.get().load(R.drawable.blue_bull)
                        .placeholder(R.drawable.blue_bull).into(mPosterImg)
                    return false                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    // image ready, hide progress now
                    mProgress.visibility = View.GONE
                    return false
                    // return false if you want Glide to handle everything else.
                }
            })
            .skipMemoryCache(true) //2
            .diskCacheStrategy(DiskCacheStrategy.NONE) //3
            .fitCenter()
            .into(BitmapImageViewTarget(mPosterImg))

    }

}


