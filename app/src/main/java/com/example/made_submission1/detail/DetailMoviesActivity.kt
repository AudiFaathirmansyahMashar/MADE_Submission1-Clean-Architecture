package com.example.made_submission1.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.made_submission1.BuildConfig
import com.example.made_submission1.R
import com.example.made_submission1.core.domain.model.Movies
import com.example.made_submission1.databinding.ActivityDetailMoviesBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMoviesBinding
    private val detailMoviesViewModel: DetailMoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Movie"

        val detailMovie = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movies?) {
        detailMovie.let {
            binding.tvTitleDetailMovie.text = detailMovie?.title
            binding.tvVoteAverageDetailMovie.text = resources.getString(R.string.vote_average,detailMovie?.voteAverage.toString())
            binding.tvVoteCountDetailMovie.text = resources.getString(R.string.vote_count,detailMovie?.voteCount.toString())
            binding.tvLanguageDetailMovie.text = resources.getString(R.string.language,detailMovie?.originalLanguage)
            binding.tvYearDetailMovie.text = resources.getString(R.string.release_date,detailMovie?.releaseDate)
            binding.tvDescription.text = detailMovie?.overview

            Glide.with(this)
                .load("${BuildConfig.LINK_IMAGE}${detailMovie?.posterPath}")
                .into(binding.ivDetailMovie)

            if (detailMovie != null) {
                var statusFavorite = detailMovie.isFavorite
                setStatusFavorite(statusFavorite)
                binding.fabFavorite.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailMoviesViewModel.setFavoriteTourism(detailMovie, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}