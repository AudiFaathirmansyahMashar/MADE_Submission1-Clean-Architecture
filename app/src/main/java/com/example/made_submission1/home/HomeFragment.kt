package com.example.made_submission1.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.made_submission1.detail.DetailMoviesActivity
import com.example.made_submission1.core.source.Resource
import com.example.made_submission1.databinding.FragmentHomeBinding
import com.example.made_submission1.core.ui.MovieAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()

            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailMoviesActivity::class.java)
                intent.putExtra(DetailMoviesActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.movie.observe(viewLifecycleOwner) { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading<*> -> binding.pbHome.visibility = View.VISIBLE
                        is Resource.Success<*> -> {
                            binding.pbHome.visibility = View.GONE
                            movieAdapter.setData(movie.data, requireActivity())
                        }
                        is Resource.Error<*> -> {
                            binding.pbHome.visibility = View.GONE
                            Toast.makeText(
                                context,
                                movie.message ?: "Kesalahan terjadi",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            with(binding.rvHome) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as Activity).title = "Home"
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}