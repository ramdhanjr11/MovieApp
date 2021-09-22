package com.muramsyah.gitsid.movieapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import com.muramsyah.gitsid.movieapp.databinding.ItemMovieHorizontalBinding

@SuppressLint("NotifyDataSetChanged")
class HomeHorizontalAdapter(val movies: List<ResultsItem>) : RecyclerView.Adapter<HomeHorizontalAdapter.ViewHolder>() {

    var onItemClicked: ((ResultsItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val binding: ItemMovieHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ResultsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                    .into(imgMovie)

                tvTitleMovie.text = movie.title

                itemView.setOnClickListener {
                    onItemClicked?.invoke(movie)
                }
            }
        }
    }
}