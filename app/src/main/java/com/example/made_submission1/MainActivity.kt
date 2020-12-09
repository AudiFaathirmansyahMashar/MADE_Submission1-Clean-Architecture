package com.example.made_submission1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.made_submission1.databinding.ActivityMainBinding
import com.example.made_submission1.home.HomeFragment

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpMain.adapter = ScreenSlidePagerAdapter(this)

        binding.vpMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.bnvMain.selectedItemId = R.id.bnv_item_home
                    1 -> binding.bnvMain.selectedItemId = R.id.bnv_item_about
                    else -> binding.bnvMain.selectedItemId = R.id.bnv_item_home
                }
            }
        })

        binding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bnv_item_home -> {
                    binding.vpMain.currentItem = 0
                }
                R.id.bnv_item_about -> {
                    binding.vpMain.currentItem = 1
                }
                else -> {
                    binding.vpMain.currentItem = 0
                }
            }

            true
        }
    }

    class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment =
            when (position){
                0 -> HomeFragment()
                1 -> AboutFragment()
                else -> HomeFragment()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_favorite) {
            val uri = Uri.parse("movieapp://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val NUM_PAGES = 2
    }
}