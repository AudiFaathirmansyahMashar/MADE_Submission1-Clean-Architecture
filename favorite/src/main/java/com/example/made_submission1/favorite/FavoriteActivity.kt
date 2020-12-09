package com.example.made_submission1.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.made_submission1.core.ui.MovieAdapter
import com.example.made_submission1.favorite.databinding.ActivityFavoriteBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoritesViewModel: FavoritesViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"

        getFavoriteData()
    }

    private fun getFavoriteData() {
        val favoriteAdapter = MovieAdapter()

        favoriteAdapter.onItemClick = { data ->
            Snackbar.make(binding.tvError.rootView, data.title, Snackbar.LENGTH_SHORT).show()
        }

        favoritesViewModel.favorite.observe(this, { favorite ->
            if (favorite != null) {
                favoriteAdapter.setData(favorite, this)
                binding.tvError.visibility = if (favorite.isNotEmpty()) View.INVISIBLE else View.VISIBLE
            }
        })

        with(binding.rvFavorite) {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            setHasFixedSize(true)
            adapter = favoriteAdapter
        }
    }
}