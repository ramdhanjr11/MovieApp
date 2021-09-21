package com.muramsyah.gitsid.movieapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import com.muramsyah.gitsid.movieapp.databinding.FragmentHomeBinding
import com.muramsyah.gitsid.movieapp.ui.adapter.HomeHorizontalAdapter
import com.muramsyah.gitsid.movieapp.ui.adapter.HomeVerticalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.moviePopular.observe(viewLifecycleOwner, {
            showMoviePopular(it)
        })

        viewModel.movieLatest.observe(viewLifecycleOwner, {
            showMovieLatest(it)
        })
    }

    private fun showMoviePopular(data: List<ResultsItem>) {
        val adapter = HomeVerticalAdapter(data)

        with(binding.rvMovieVertical) {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun showMovieLatest(data: List<ResultsItem>) {
        val adapter = HomeHorizontalAdapter(data)

        with(binding.rvMovieHorizontal) {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}