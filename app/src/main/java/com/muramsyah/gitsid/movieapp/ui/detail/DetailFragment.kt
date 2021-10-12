package com.muramsyah.gitsid.movieapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.muramsyah.gitsid.movieapp.data.DetailInteractor
import com.muramsyah.gitsid.movieapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.movieapp.data.source.remote.response.DetailResponse
import com.muramsyah.gitsid.movieapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(), DetailView {

    companion object {
        const val MOVIE_ID = "movie_id"
        private val TAG = DetailFragment::class.java.simpleName
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var detailPresenter: DetailPresenter

    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(MOVIE_ID, 1)

        detailPresenter = DetailPresenter(this, DetailInteractor(remoteDataSource))
        detailPresenter.setMovieId(movieId!!)

        binding.btnBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun showDetailMovie(movie: DetailResponse) {
        with(binding) {
            Glide.with(root)
                .load("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                .apply(RequestOptions().override(255, 255))
                .into(imgMovie)

            tvTitleMovie.text = movie.title
            tvOverview.text = movie.overview
            tvOriginalTitle.text = movie.originalTitle
            tvTitleProduction.text = movie.productionCompanies!![0]!!.originCountry
            tvTitleRelease.text = movie.releaseDate

            val releaseDate = movie.releaseDate?.split("-")?.toTypedArray()!!
            release.text = releaseDate[0]

            val genres = StringBuilder().append("")
            movie.genres?.forEach {
                genres.append("${it?.name} ")
            }
            tvTitleGenre.text = genres.toString()

            tvVote.text = "${ movie.voteCount } Votes"
            overview.text = movie.voteAverage.toString()
            ratingBar.rating = movie.voteAverage!!.toFloat()/2f
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoading(loading: Boolean) {
        if (loading) binding.progresBar.visibility = View.VISIBLE
        else binding.progresBar.visibility = View.GONE
    }

    override fun onSuccess(data: LiveData<DetailResponse>) {
        data.observe(viewLifecycleOwner, { result ->
            showDetailMovie(result)
        })
    }

    override fun onError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}

interface DetailView {
    fun onLoading(loading: Boolean)
    fun onSuccess(data: LiveData<DetailResponse>)
    fun onError(error: String)
}