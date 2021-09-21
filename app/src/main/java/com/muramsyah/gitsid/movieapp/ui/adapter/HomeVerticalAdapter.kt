package com.muramsyah.gitsid.movieapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import com.muramsyah.gitsid.movieapp.databinding.ItemMovieHorizontalBinding
import com.muramsyah.gitsid.movieapp.databinding.ItemMovieVerticalBinding

@SuppressLint("NotifyDataSetChanged")
class HomeVerticalAdapter(val movies: List<ResultsItem>) : RecyclerView.Adapter<HomeVerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val binding: ItemMovieVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ResultsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .into(imgMovie)

                tvTitleMovie.text = movie.title
                tvReleaseDate.text = movie.releaseDate
                ratingBar.rating = movie.voteAverage!!.toFloat()
            }
        }
    }
}