package com.muramsyah.gitsid.movieapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.muramsyah.gitsid.movieapp.R
import com.muramsyah.gitsid.movieapp.data.MovieInteractor
import com.muramsyah.gitsid.movieapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import com.muramsyah.gitsid.movieapp.databinding.FragmentHomeBinding
import com.muramsyah.gitsid.movieapp.ui.adapter.HomeHorizontalAdapter
import com.muramsyah.gitsid.movieapp.ui.adapter.HomeVerticalAdapter
import com.muramsyah.gitsid.movieapp.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(), HomeView {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homePresenter: HomePresenter

    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homePresenter = HomePresenter(this, MovieInteractor(remoteDataSource))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoading(loading: Boolean) {
        if (loading) binding.progresBar.visibility = View.VISIBLE
        else binding.progresBar.visibility = View.GONE
    }

    override fun onMoviePopularSuccess(data: LiveData<List<ResultsItem>>) {
        data.observe(viewLifecycleOwner, { results ->
            val adapter = HomeHorizontalAdapter(results)
            binding.rvMovieHorizontal.adapter = adapter
            binding.rvMovieHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvMovieHorizontal.setHasFixedSize(true)

            adapter.onItemClicked = {
                val bundle = Bundle()
                bundle.putInt(DetailFragment.MOVIE_ID, it.id!!)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
        })
    }

    override fun onMovieLatestSuccess(data: LiveData<List<ResultsItem>>) {
        data.observe(viewLifecycleOwner, { results ->
            val adapter = HomeVerticalAdapter(results)
            binding.rvMovieVertical.adapter = adapter
            binding.rvMovieVertical.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvMovieVertical.setHasFixedSize(true)

            adapter.onItemClicked = {
                val bundle = Bundle()
                bundle.putInt(DetailFragment.MOVIE_ID, it.id!!)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
            }
        })
    }

    override fun onError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }


}

interface HomeView {
    fun onLoading(loading: Boolean)
    fun onMoviePopularSuccess(data: LiveData<List<ResultsItem>>)
    fun onMovieLatestSuccess(data: LiveData<List<ResultsItem>>)
    fun onError(error: String)
}